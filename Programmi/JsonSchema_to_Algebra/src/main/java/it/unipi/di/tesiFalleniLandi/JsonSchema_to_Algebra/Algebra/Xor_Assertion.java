package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.AntlrBoolean;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;

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

	@Override
	public String getJSONSchemaKeyword() {
		return "oneOf";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONArray toJSONSchema() {
		JSONArray array = new JSONArray();
		
		for(Assertion assertion : xorList) {
			JSONObject obj = new JSONObject();
			if(assertion.getClass() == Boolean_Assertion.class)
				array.add(assertion.toJSONSchema());
			else
				Utils.putContent(obj, assertion.getJSONSchemaKeyword(), assertion.toJSONSchema());
			array.add(obj);
		}
		
		return array;
	}

	// A xor B = (A or B) and not(A and B)
	// not(A xor B) = (not(A) and not(b)) or (A and B)
	@Override
	public Assertion not() {
		And_Assertion andList = new And_Assertion();
		And_Assertion notAndList = new And_Assertion();
		Or_Assertion orList = new Or_Assertion();
		
		for(Assertion assertion : xorList) {
			andList.add(assertion);
			notAndList.add(assertion.not());
		}
		
		orList.add(notAndList);
		orList.add(andList);
		
		return orList;
	}

	public Assertion notElimination() {
		Xor_Assertion xor = new Xor_Assertion();
		
		for(Assertion assertion : xorList)
			xor.add(assertion.notElimination());
		
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
