package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.AntlrBoolean;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;

public class Or_Assertion implements Assertion{
	private List<Assertion> orList;
	
	public Or_Assertion() {
		this.orList = new LinkedList<>();
	}
	
	public void add(Assertion assertion) {
		orList.add(assertion);
	}
	
	public void add(Or_Assertion assertion) {
		addAll(assertion.orList);
	}
	
	public void addAll(List<Assertion> list) {
		orList.addAll(list);
	}

	@Override
	public String toString() {
		return "Or_Assertion [" + orList + "]";
	}
	
	@Override
	public String getJSONSchemaKeyword() {
		return "anyOf";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONArray toJSONSchema() {
		JSONArray array = new JSONArray();
		
		for(Assertion assertion : orList) {
			JSONObject obj = new JSONObject();
			
			if(assertion.getClass() == Boolean_Assertion.class)
				array.add(assertion.toJSONSchema());
			else
				Utils.putContent(obj, assertion.getJSONSchemaKeyword(), assertion.toJSONSchema());
	
			array.add(obj);
		}
		
		
		return array;
	}

	@Override
	public Assertion not() {
		And_Assertion and = new And_Assertion();
		
		for(Assertion assertion : orList)
			and.add(assertion.not());
		
		return and;
	}
	
	public Assertion notElimination() {
		Or_Assertion or = new Or_Assertion();
		
		for(Assertion assertion : orList)
			or.add(assertion.notElimination());
		
		return or;
	}
	
	
	@Override
	public String toGrammarString() {
		String str = "";
		
		Iterator<Assertion> it = orList.iterator();
			
		while(it.hasNext()) {
			String returnedValue = it.next().toGrammarString();
			if(returnedValue.isEmpty())
				continue;
			str += GrammarStringDefinitions.COMMA + returnedValue;
		}
		
		if(str.isEmpty()) return "";
		if(orList.size() == 1) return str.substring(GrammarStringDefinitions.COMMA.length());
		return String.format(GrammarStringDefinitions.ANYOF, str.substring(GrammarStringDefinitions.COMMA.length()));
	}
}
