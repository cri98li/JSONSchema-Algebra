package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

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
	
	public OneOf() {}
	
	public void addElement(JSONSchema schema) {
		if(oneOf == null) oneOf = new LinkedList<>();
		oneOf.add(schema);
	}
	
	@Override
	public String toString() {
		return "OneOf [oneOf=" + oneOf + "]";
	}


	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		
		for(JSONSchema js : oneOf)
			array.add(js.toJSON());

		obj.put("oneOf", array);

		return obj;
	}

	@Override
	public String toGrammarString() {
		String str = "";
		
		Iterator<JSONSchema> it = oneOf.iterator();
			
		while(it.hasNext()) {
			String returnedValue = it.next().toGrammarString();
			if(returnedValue.isEmpty())
				continue;
			str += GrammarStringDefinitions.COMMA + returnedValue;
		}
		
		if(str.isEmpty()) return "";
		if(oneOf.size() == 1) return str.substring(GrammarStringDefinitions.COMMA.length());
		return String.format(GrammarStringDefinitions.ONEOF, str.substring(GrammarStringDefinitions.COMMA.length()));
		//return str;
	}

	
	@Override
	public int numberOfAssertions() {
		return 1;
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
	
	
	@Override
	public OneOf clone(){
		OneOf clone = new OneOf();
		
		for(JSONSchema el : oneOf)
			clone.addElement(el.clone());
		
		return clone;
	}
}
