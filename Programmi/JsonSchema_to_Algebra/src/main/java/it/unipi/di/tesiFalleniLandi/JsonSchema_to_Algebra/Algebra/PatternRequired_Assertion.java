package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.IfThenElse;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class PatternRequired_Assertion implements Assertion{
	private HashMap<String, Assertion> pattReq;

	public PatternRequired_Assertion() {
		pattReq = new HashMap<>();
	}
	
	public PatternRequired_Assertion(HashMap<String, Assertion> pattReq) {
		this.pattReq = pattReq;
	}
	
	public PatternRequired_Assertion(String name, Assertion assertion) {
		pattReq = new HashMap<>();
		pattReq.put(name, assertion);
	}

	public void add(String key, Assertion value) {
		pattReq.put(key, value);
	}

	@Override
	public String toString() {
		return "PatternRequired_Assertion [" + pattReq + "]";
	}

	//TODO: è giusta??
	@Override
	public Object toJSONSchema() {
		Type_Assertion t = new Type_Assertion();
		t.add(GrammarStringDefinitions.TYPE_OBJECT);

		And_Assertion and = new And_Assertion();
		for(Entry<String, Assertion> entry : pattReq.entrySet()) {
			Properties_Assertion pro = new Properties_Assertion();
			pro.addPatternProperties(entry.getKey(), entry.getValue().not());
			and.add(pro.not());
		}

		return new IfThenElse_Assertion(t, and, null).toJSONSchema();

		//throw new UnsupportedOperationException();
	}
	
	@Override
	public Assertion not() {
		And_Assertion and = new And_Assertion();
		Or_Assertion or = new Or_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add("obj");
		
		Set<Entry<String, Assertion>> entrySet = pattReq.entrySet();
		
		for(Entry<String, Assertion> entry : entrySet) {
			Properties_Assertion properties = new Properties_Assertion();
			Assertion not = entry.getValue().not();
			if(not != null)
				properties.addProperties(entry.getKey(), not);
			or.add(properties);
		}
		
		and.add(type);
		and.add(or);
		
		return and;
	}

	@Override
	public Assertion notElimination() {
		PatternRequired_Assertion pattReqAss = new PatternRequired_Assertion();
		Set<Entry<String, Assertion>> entrySet = pattReq.entrySet();
		
		for(Entry<String, Assertion> entry : entrySet) {
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
			Set<Entry<String, Assertion>> entrySet = pattReq.entrySet();
			for(Entry<String, Assertion> entry : entrySet) {
				String returnedValue = entry.getValue().toGrammarString();
				if(!returnedValue.isEmpty())
					str += GrammarStringDefinitions.COMMA + String.format(GrammarStringDefinitions.SINGLEPROPERTIES, entry.getKey(), returnedValue);
			}
		}

		if(str.isEmpty()) return "";
		return String.format(GrammarStringDefinitions.PATTERNREQUIRED, str.substring(GrammarStringDefinitions.COMMA.length()), "");
	}

}
