package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;

public class OneOf implements JSONSchemaElement{
	private List<JSONSchema> oneOf;
	
	public OneOf(Object obj) {
		JSONArray array = (JSONArray) obj;
		oneOf = new LinkedList<>();
		
		Iterator<?> it = array.iterator();
		
		while(it.hasNext()) {
			oneOf.add(new JSONSchema(it.next()));
		}
	}
	
	
	
	@Override
	public String toString() {
		return "OneOf [oneOf=" + oneOf + "]";
	}



	@SuppressWarnings("unchecked")
	@Override
	public JSONArray toJSON() {
		JSONArray array = new JSONArray();
		
		for(JSONSchema js : oneOf)
			array.add(js.toJSON());
		
		return array;
	}

	@Override
	public String toGrammarString() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
