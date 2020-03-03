package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AllOf implements JSONSchemaElement{
	private List<JSONSchema> allOf;
	
	public AllOf(JSONArray array) {
		allOf = new LinkedList<>();
		
		Iterator<?> it = array.iterator();
		
		while(it.hasNext()) {
			JSONObject element = (JSONObject) it.next();
			allOf.add(new JSONSchema(element));
		}
	}
	
	
	@Override
	public String toString() {
		return "AllOf [allOf=" + allOf + "]";
	}



	@Override
	public String toJSONString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toGrammarString() {
		// TODO Auto-generated method stub
		return null;
	}
}
