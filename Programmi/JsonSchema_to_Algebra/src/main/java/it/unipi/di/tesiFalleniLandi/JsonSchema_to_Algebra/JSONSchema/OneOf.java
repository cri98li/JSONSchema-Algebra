package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

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
	
	
	
	public OneOf() {
		// TODO Auto-generated constructor stub
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
		String str = "";
		
		Iterator<JSONSchema> it = oneOf.iterator();
		if(it.hasNext()) 
			str += it.next().toGrammarString();
			
		while(it.hasNext())
			str += GrammarStringDefinitions.AND + it.next().toGrammarString();
			
		return String.format(GrammarStringDefinitions.ONEOF, str);
	}



	@Override
	public OneOf assertionSeparation() {
		OneOf obj = new OneOf();
		
		obj.oneOf = new LinkedList<>();
		for(JSONSchema s : oneOf)
			obj.oneOf.add(s.assertionSeparation());
			
		
		return obj;
	}
	
}
