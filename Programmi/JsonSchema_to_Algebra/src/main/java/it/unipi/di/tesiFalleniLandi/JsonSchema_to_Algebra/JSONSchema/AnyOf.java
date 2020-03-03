package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AnyOf implements JSONSchemaElement{
	private List<JSONSchema> anyOf;
	
	public AnyOf(JSONArray array) {
		anyOf = new LinkedList<>();
		
		Iterator<?> it = array.iterator();
		
		while(it.hasNext()) {
			JSONObject element = (JSONObject) it.next();
			anyOf.add(new JSONSchema(element));
		}
	}
	
	
	@Override
	public String toString() {
		return "AnyOf [anyOf=" + anyOf + "]";
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
