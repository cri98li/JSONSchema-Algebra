package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.AntlrBoolean;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;

public class And_Assertion implements Assertion{
	private List<Assertion> andList;
	private boolean duplicates;
	
	public And_Assertion() {
		this.andList = new LinkedList<>();
		duplicates = false;
	}

	public And_Assertion(boolean b) {
		// TODO Auto-generated constructor stub
		throw new UnsupportedOperationException();
	}

	public void addAll(List<Assertion> list) {
		for(Assertion assertion : list) 
			duplicates |= andList.contains(assertion);
		andList.addAll(list);
	}
	
	public void add(Assertion assertion) {
		duplicates |= andList.contains(assertion);
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
		if(!duplicates)
			return "AllOf_Schema";
		return "allOf";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object toJSONSchema() {
		if(duplicates) {
			JSONArray array = new JSONArray();
			
			for(Assertion assertion : andList) {
				JSONObject obj = new JSONObject();
				try {
					array.add((AntlrBoolean)assertion.toJSONSchema());
					continue;
				}catch(ClassCastException e) {}
				Utils.putContent(obj, assertion.getJSONSchemaKeyword(), assertion.toJSONSchema());
				array.add(obj);
			}
			
			
			return array;
		}
		
		
		JSONObject obj = new JSONObject();
		
		for(Assertion assertion : andList)
			Utils.putContent(obj, assertion.getJSONSchemaKeyword(), assertion.toJSONSchema());
		
		return obj;
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
