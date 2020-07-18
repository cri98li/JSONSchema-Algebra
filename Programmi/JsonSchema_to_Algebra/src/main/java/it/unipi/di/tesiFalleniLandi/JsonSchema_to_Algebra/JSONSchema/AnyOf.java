package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class AnyOf implements JSONSchemaElement{
	private List<JSONSchema> anyOf;
	
	public AnyOf(JsonElement obj) {
		JsonArray array = obj.getAsJsonArray();
		anyOf = new LinkedList<>();
		
		Iterator<JsonElement> it = array.iterator();
		
		while(it.hasNext()) {
			anyOf.add(new JSONSchema(it.next()));
		}
	}

	public AnyOf() {
		anyOf = new LinkedList<>();
	}
	
	public void addElement(JSONSchema schema) {
		if(anyOf == null) anyOf = new LinkedList<>();
		anyOf.add(schema);
	}

	@Override
	public String toString() {
		return "AnyOf [anyOf=" + anyOf + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JsonElement toJSON() {
		JsonObject obj = new JsonObject();
		JsonArray array = new JsonArray();
		
		for(JSONSchema js : anyOf)
			array.add(js.toJSON());

		obj.add("allOf", array);
		return obj;
	}

	public String toGrammarString() {
		String str = "";
		
		Iterator<JSONSchema> it = anyOf.iterator();
			
		while(it.hasNext()) {
			String returnedValue = it.next().toGrammarString();
			if(returnedValue.isEmpty())
				continue;
			str += GrammarStringDefinitions.COMMA + returnedValue;
		}
		
		if(str.isEmpty()) return "";
		if(anyOf.size() == 1) return str.substring(GrammarStringDefinitions.COMMA.length());
		return String.format(GrammarStringDefinitions.ANYOF, str.substring(GrammarStringDefinitions.COMMA.length()));
	}
	
	@Override
	public int numberOfAssertions() {
		int returnValue = 0;
		if(anyOf != null)
			for(JSONSchemaElement jse : anyOf)
				returnValue += jse.numberOfAssertions();

		return returnValue;
	}

	@Override
	public AnyOf assertionSeparation() {
		AnyOf obj = new AnyOf();
		
		obj.anyOf = new LinkedList<>();
		for(JSONSchema s : anyOf)
			obj.anyOf.add(s.assertionSeparation());
		
		return obj;
	}

	@Override
	public List<URI_JS> getRef() {
		List<URI_JS> returnList = new LinkedList<>();
		
		Iterator<JSONSchema> it = anyOf.iterator();
		while(it.hasNext())
			returnList.addAll(it.next().getRef());
		
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
	public AnyOf clone(){
		AnyOf clone = new AnyOf();
		
		for(JSONSchema el : anyOf)
			clone.addElement(el.clone());
		
		return clone;
	}
}
