package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;

public class AnyOf implements JSONSchemaElement{
	private List<JSONSchema> anyOf;
	
	public AnyOf(Object obj) {
		JSONArray array = (JSONArray) obj;
		anyOf = new LinkedList<>();
		
		Iterator<?> it = array.iterator();
		
		while(it.hasNext()) {
			anyOf.add(new JSONSchema(it.next()));
		}
	}
	
	
	@Override
	public String toString() {
		return "AnyOf [anyOf=" + anyOf + "]";
	}



	@SuppressWarnings("unchecked")
	@Override
	public JSONArray toJSON() {
		JSONArray array = new JSONArray();
		
		for(JSONSchema js : anyOf)
			array.add(js.toJSON());
		
		return array;
	}

	@Override
	public String toGrammarString() {
		// TODO Auto-generated method stub
		return null;
	}
}
