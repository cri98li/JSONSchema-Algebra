package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.MyPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.IfThenElse;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class PatternRequired_Assertion implements Assertion{
	private HashMap<MyPattern, Assertion> pattReq;

	public PatternRequired_Assertion() {
		pattReq = new HashMap<>();
	}
	
	public PatternRequired_Assertion(HashMap<MyPattern, Assertion> pattReq) {
		this.pattReq = pattReq;
	}
	
	public PatternRequired_Assertion(MyPattern name, Assertion assertion) {
		pattReq = new HashMap<>();
		pattReq.put(name, assertion);
	}

	public void add(MyPattern key, Assertion value) {
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
		And_Assertion and = new And_Assertion();

		for(Entry<MyPattern, Assertion> entry : pattReq.entrySet()) {
			Properties_Assertion pro = new Properties_Assertion();
			pro.addPatternProperties(entry.getKey(), entry.getValue().not());
			and.add(new Not_Assertion(pro));
		}

		return new IfThenElse_Assertion(t, and, null).toJSONSchema();
	}
	
	@Override
	public Assertion not() {
		And_Assertion and = new And_Assertion();
		Or_Assertion or = new Or_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add("obj");
		
		Set<Entry<MyPattern, Assertion>> entrySet = pattReq.entrySet();
		
		for(Entry<MyPattern, Assertion> entry : entrySet) {
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
		Set<Entry<MyPattern, Assertion>> entrySet = pattReq.entrySet();
		
		for(Entry<MyPattern, Assertion> entry : entrySet) {
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
			Set<Entry<MyPattern, Assertion>> entrySet = pattReq.entrySet();
			for(Entry<MyPattern, Assertion> entry : entrySet) {
				String returnedValue = entry.getValue().toGrammarString();
				if(!returnedValue.isEmpty())
					str += GrammarStringDefinitions.COMMA + String.format(GrammarStringDefinitions.SINGLEPROPERTIES, entry.getKey(), returnedValue);
			}
		}

		if(str.isEmpty()) return "";
		return String.format(GrammarStringDefinitions.PATTERNREQUIRED, str.substring(GrammarStringDefinitions.COMMA.length()), "");
	}

}
