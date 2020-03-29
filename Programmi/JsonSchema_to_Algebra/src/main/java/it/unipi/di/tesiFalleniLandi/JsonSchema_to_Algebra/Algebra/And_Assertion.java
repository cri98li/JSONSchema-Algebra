package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.Utils;

public class And_Assertion implements Assertion{
	private List<Assertion> andList;
	
	public And_Assertion() {
		this.andList = new LinkedList<>();
	}

	public void add(Assertion assertion) {
		andList.add(assertion);
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
	public Object toJSONSchema() {
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
	
	
}
