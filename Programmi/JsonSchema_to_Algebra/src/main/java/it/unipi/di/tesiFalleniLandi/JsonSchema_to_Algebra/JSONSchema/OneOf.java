package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

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
			
		while(it.hasNext()) {
			String returnedValue = it.next().toGrammarString();
			if(returnedValue.isEmpty())
				continue;
			str += GrammarStringDefinitions.AND + returnedValue;
		}
		
		if(str.isEmpty()) return "";
		
		return str;
	}



	@Override
	public OneOf assertionSeparation() {
		OneOf obj = new OneOf();
		
		obj.oneOf = new LinkedList<>();
		for(JSONSchema s : oneOf)
			obj.oneOf.add(s.assertionSeparation());
			
		
		return obj;
	}
	

	@Override
	public List<URI_JS> getRef() {
		List<URI_JS> returnList = new LinkedList<>();
		
		for(JSONSchema schema : oneOf)
			returnList.addAll(schema.getRef());
		
		return returnList;
	}

	@Override
	public JSONSchema searchDef(Iterator<String> URIIterator) {
		return null;
	}



	@Override
	public List<Entry<String,Defs>> collectDef() {
		return new LinkedList<>();
	}
}
