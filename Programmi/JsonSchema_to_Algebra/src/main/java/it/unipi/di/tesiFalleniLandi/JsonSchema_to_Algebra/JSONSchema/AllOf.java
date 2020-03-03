package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;

public class AllOf implements JSONSchemaElement{
	private List<JSONSchema> allOf;
	
	public AllOf(Object obj) {
		JSONArray array = (JSONArray) obj;
		allOf = new LinkedList<>();
		
		Iterator<?> it = array.iterator();
		
		while(it.hasNext()) {
			allOf.add(new JSONSchema(it.next()));
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
