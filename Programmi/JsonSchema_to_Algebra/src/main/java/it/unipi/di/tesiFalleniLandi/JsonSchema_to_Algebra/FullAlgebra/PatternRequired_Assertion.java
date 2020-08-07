package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.*;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessFalseAssertionException;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessTrueAssertionException;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import patterns.REException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PatternRequired_Assertion implements Assertion{
	private HashMap<ComplexPattern, Assertion> pattReq;

	public PatternRequired_Assertion() {
		pattReq = new HashMap<>();
	}
	
	public PatternRequired_Assertion(HashMap<ComplexPattern, Assertion> pattReq) {
		this.pattReq = pattReq;
	}
	
	public PatternRequired_Assertion(ComplexPattern name, Assertion assertion) {
		pattReq = new HashMap<>();
		pattReq.put(name, assertion);
	}

	public void add(ComplexPattern key, Assertion value) {
		if(pattReq.containsKey(key)) throw new ParseCancellationException("Detected 2 patternRequired with the same name");
		pattReq.put(key, value);
	}

	@Override
	public String toString() {
		return "PatternRequired_Assertion [" + pattReq + "]";
	}

	@Override
	public JsonElement toJSONSchema() {
		Type_Assertion t = new Type_Assertion();
		t.add(FullAlgebraString.TYPE_OBJECT);
		AllOf_Assertion and = new AllOf_Assertion();

		for(Map.Entry<ComplexPattern, Assertion> entry : pattReq.entrySet()) {
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
		
		Set<Map.Entry<ComplexPattern, Assertion>> entrySet = pattReq.entrySet();
		
		for(Map.Entry<ComplexPattern, Assertion> entry : entrySet) {
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
		Set<Map.Entry<ComplexPattern, Assertion>> entrySet = pattReq.entrySet();
		
		for(Map.Entry<ComplexPattern, Assertion> entry : entrySet) {
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
			Set<Map.Entry<ComplexPattern, Assertion>> entrySet = pattReq.entrySet();
			for(Map.Entry<ComplexPattern, Assertion> entry : entrySet) {
				String returnedValue = entry.getValue().toGrammarString();
				if(!returnedValue.isEmpty())
					str += FullAlgebraString.COMMA + entry.getKey().getAlgebraString()+":"+ returnedValue;
			}
		}

		if(str.isEmpty()) return "";
		return String.format(FullAlgebraString.PATTERNREQUIRED, str.substring(FullAlgebraString.COMMA.length()), "");
	}

	@Override
	public WitnessAssertion toWitnessAlgebra() throws REException {
		WitnessOr or = new WitnessOr();
		WitnessAnd and = new WitnessAnd();
		Type_Assertion tmp = new Type_Assertion();
		tmp.add(FullAlgebraString.TYPE_OBJECT);
		WitnessAssertion type = tmp.not().toWitnessAlgebra();

		Set<Map.Entry<ComplexPattern, Assertion>> entrySet = pattReq.entrySet();

		for(Map.Entry<ComplexPattern, Assertion> entry : entrySet) {
			ComplexPattern p = entry.getKey().clone();
			WitnessPattReq pattReq = WitnessPattReq.build(p, entry.getValue().toWitnessAlgebra());
			try {
				and.add(pattReq);
			}catch (WitnessFalseAssertionException ex){ return or;}
		}

		try {
			or.add(type);
			or.add(and);
		}catch (WitnessTrueAssertionException e){
			throw new RuntimeException(e); //impossible
		}

		return or;
	}

}
