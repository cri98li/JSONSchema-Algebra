package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

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
		return "RequiredPattern_Assertion [" + pattReq + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "requiredPattern";
	}

	@Override
	public Assertion not() {
		return null;
	}

	@Override
	public Object toJSONSchema() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Assertion notElimination() {
		PatternRequired_Assertion pattReqAss = new PatternRequired_Assertion();
		Set<Entry<String, Assertion>> entrySet = pattReq.entrySet();
		
		for(Entry<String, Assertion> entry : entrySet) {
			pattReqAss.add(entry.getKey(), entry.getValue().notElimination());
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
		
		return String.format(GrammarStringDefinitions.PATTERNREQUIRED, str.substring(GrammarStringDefinitions.COMMA.length()), "");
	}

}
