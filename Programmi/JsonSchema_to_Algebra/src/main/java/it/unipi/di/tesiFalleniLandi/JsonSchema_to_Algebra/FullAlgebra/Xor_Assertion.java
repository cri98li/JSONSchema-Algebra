package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Xor_Assertion implements Assertion{
	private List<Assertion> xorList;
	
	public Xor_Assertion() {
		this.xorList = new LinkedList<>();
	}
	
	public void add(Assertion assertion) {
		xorList.add(assertion);
	}
	
	public void add(Xor_Assertion assertion) {
		addAll(assertion.xorList);
	}
	
	public void addAll(List<Assertion> list) {
		xorList.addAll(list);
	}

	@Override
	public String toString() {
		return "Xor_Assertion [" + xorList + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONSchema() {
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		
		for(Assertion assertion : xorList) {
			array.add(assertion.toJSONSchema());
		}

		obj.put("oneOf", array);

		return obj;
	}

	// A xor B = (A or B) and not(A and B)
	// not(A xor B) = (not(A) and not(b)) or (A and B)
	// A XOR B XOR C
	// NEGATO: (A AND NOT(B) AND NOT(X)) OR (NOT(A) AND B AND NOT(X)) OR (NOT(A) AND NOT(B) AND X)
	@Override
	public Assertion not() {
		List<Assertion> notXorList = new LinkedList<>();
		for(Assertion a : xorList) {
			Assertion not = a.not();
			if(not != null)
				notXorList.add(not);
		}

		And_Assertion andList = new And_Assertion();

		for(int i = 0; i < xorList.size(); i++) {
			Or_Assertion orList = new Or_Assertion();
			for (int j = 0; j < xorList.size(); j++) {
				if (i == j) orList.add(notXorList.get(j));
				else orList.add(xorList.get(j));
			}
			andList.add(orList);
		}

		return andList;
	}

	public Assertion notElimination() {
		Xor_Assertion xor = new Xor_Assertion();
		
		for(Assertion assertion : xorList) {
			Assertion not = assertion.notElimination();
			if(not != null)
				xor.add(not);
		}
		
		return xor;
	}
	
	@Override
	public String toGrammarString() {
		String str = "";
		
		Iterator<Assertion> it = xorList.iterator();
			
		while(it.hasNext()) {
			String returnedValue = it.next().toGrammarString();
			if(returnedValue.isEmpty())
				continue;
			str += GrammarStringDefinitions.COMMA + returnedValue;
		}
		
		if(str.isEmpty()) return "";
		if(xorList.size() == 1) return str.substring(GrammarStringDefinitions.COMMA.length());
		return String.format(GrammarStringDefinitions.ONEOF, str.substring(GrammarStringDefinitions.COMMA.length()));
	}
}
