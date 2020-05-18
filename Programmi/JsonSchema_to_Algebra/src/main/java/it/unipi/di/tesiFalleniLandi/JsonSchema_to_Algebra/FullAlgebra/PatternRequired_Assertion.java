package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.MyPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.PosixPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAnd;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessOr;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessPattReq;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class PatternRequired_Assertion implements Assertion{
	private HashMap<PosixPattern, Assertion> pattReq;

	public PatternRequired_Assertion() {
		pattReq = new HashMap<>();
	}
	
	public PatternRequired_Assertion(HashMap<PosixPattern, Assertion> pattReq) {
		this.pattReq = pattReq;
	}
	
	public PatternRequired_Assertion(PosixPattern name, Assertion assertion) {
		pattReq = new HashMap<>();
		pattReq.put(name, assertion);
	}

	public void add(PosixPattern key, Assertion value) {
		if(pattReq.containsKey(key)) throw new ParseCancellationException("Detected 2 patternRequired with the same name");
		pattReq.put(key, value);
	}

	@Override
	public String toString() {
		return "PatternRequired_Assertion [" + pattReq + "]";
	}

	@Override
	public Object toJSONSchema() {
		Type_Assertion t = new Type_Assertion();
		t.add(GrammarStringDefinitions.TYPE_OBJECT);
		AllOf_Assertion and = new AllOf_Assertion();

		for(Entry<PosixPattern, Assertion> entry : pattReq.entrySet()) {
			Properties_Assertion pro = new Properties_Assertion();
			pro.addPatternProperties(entry.getKey(), entry.getValue().not());
			and.add(new Not_Assertion(pro));
		}

		return new IfThenElse_Assertion(t, and, null).toJSONSchema();
	}
	
	@Override
	public Assertion not() {
		AllOf_Assertion and = new AllOf_Assertion();
		AnyOf_Assertion or = new AnyOf_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add("obj");
		
		Set<Entry<PosixPattern, Assertion>> entrySet = pattReq.entrySet();
		
		for(Entry<PosixPattern, Assertion> entry : entrySet) {
			Properties_Assertion properties = new Properties_Assertion();
			Assertion not = entry.getValue().not();
			if(not != null)
				properties.addPatternProperties(entry.getKey(), not);
			or.add(properties);
		}
		
		and.add(type);
		and.add(or);
		
		return and;
	}

	@Override
	public Assertion notElimination() {
		PatternRequired_Assertion pattReqAss = new PatternRequired_Assertion();
		Set<Entry<PosixPattern, Assertion>> entrySet = pattReq.entrySet();
		
		for(Entry<PosixPattern, Assertion> entry : entrySet) {
			Assertion not = entry.getValue().notElimination();
			if(not != null)
				pattReqAss.add(entry.getKey(), not);
		}
		
		return pattReqAss;
	}
	
	@Override
	public String toGrammarString() {
		String str = "";
		
		if(pattReq != null) {
			Set<Entry<PosixPattern, Assertion>> entrySet = pattReq.entrySet();
			for(Entry<PosixPattern, Assertion> entry : entrySet) {
				String returnedValue = entry.getValue().toGrammarString();
				if(!returnedValue.isEmpty())
					str += GrammarStringDefinitions.COMMA + String.format(GrammarStringDefinitions.SINGLEPROPERTIES, entry.getKey(), returnedValue);
			}
		}

		if(str.isEmpty()) return "";
		return String.format(GrammarStringDefinitions.PATTERNREQUIRED, str.substring(GrammarStringDefinitions.COMMA.length()), "");
	}

	@Override
	public WitnessAssertion toWitnessAlgebra() {
		WitnessOr or = new WitnessOr();
		WitnessAnd and = new WitnessAnd();
		Type_Assertion tmp = new Type_Assertion();
		tmp.add(GrammarStringDefinitions.TYPE_OBJECT);
		WitnessAssertion type = tmp.not().toWitnessAlgebra();
		or.add(type);
		or.add(and);

		Set<Entry<PosixPattern, Assertion>> entrySet = pattReq.entrySet();

		for(Entry<PosixPattern, Assertion> entry : entrySet) {
			PosixPattern p = new MyPattern(entry.getKey().getPattern());
			WitnessPattReq pattReq = new WitnessPattReq(p, entry.getValue().toWitnessAlgebra());
			and.add(pattReq);
		}

		return or;
	}

}
