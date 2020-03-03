package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;

public class Type implements JSONSchemaElement {
	private String type;
	private List<String> type_array;
	public Type(Object obj){
		try{
			type = (String) obj;
			return;
		}catch(ClassCastException e) {}
		
		JSONArray array = (JSONArray) obj;
		
		type_array = new LinkedList<>();
		
		Iterator<?> it = array.iterator();
		
		while(it.hasNext())
			type_array.add((String) it.next());
	}
	
	@Override
	public String toString() {
		return "Type [type=" + type + ", type_array=" + type_array + "]";
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
