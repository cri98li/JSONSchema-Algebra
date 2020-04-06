package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.AntlrBoolean;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;

public class And_Assertion implements Assertion{
	private List<Assertion> andList;
	
	public And_Assertion() {
		this.andList = new LinkedList<>();
	}

	public And_Assertion(boolean b) {
		// TODO Auto-generated constructor stub
		throw new UnsupportedOperationException();
	}

	public void addAll(List<Assertion> list) {
		andList.addAll(list);
	}
	
	public void add(Assertion assertion) {
		andList.add(assertion);
	}
	
	public void add(And_Assertion assertion) {
		addAll(assertion.andList);
	}
	
	@Override
	public String toString() {
		return "And_Assertion [" + andList + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "allOf";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONArray toJSONSchema() {
		JSONArray array = new JSONArray();
		
		for(Assertion assertion : andList) {
			JSONObject obj = new JSONObject();
			if(assertion.getClass() == AntlrBoolean.class) { //caso boolean as a schema
				array.add(assertion.toJSONSchema());
				continue;
			}
			Utils.putContent(obj, assertion.getJSONSchemaKeyword(), assertion.toJSONSchema());
			array.add(obj);
		}
		
		
		return array;
	}

	@Override
	public Assertion not() {
		Or_Assertion or = new Or_Assertion();
		
		for(Assertion assertion : andList) {
			Assertion notAssertion = assertion.not();
			if(notAssertion != null)
				or.add(notAssertion);
		}
		
		return or;
	}
	
	
}
