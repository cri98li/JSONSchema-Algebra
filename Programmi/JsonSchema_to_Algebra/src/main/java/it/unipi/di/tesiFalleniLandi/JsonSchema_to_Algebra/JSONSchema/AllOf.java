package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class AllOf implements JSONSchemaElement{
	private List<JSONSchema> allOf;
	
	public AllOf() {
		allOf = new LinkedList<>();
	}
	
	public AllOf(Object obj) {
		JsonArray array = (JsonArray) obj;
		allOf = new LinkedList<>();
		
		Iterator<?> it = array.iterator();
		
		while(it.hasNext()) {
			allOf.add(new JSONSchema(it.next()));
		}
	}
	
	public void addElement(JSONSchema schema) {
		if(allOf == null) allOf = new LinkedList<>();
		allOf.add(schema);
	}
	
	@Override
	public String toString() {
		return "AllOf [allOf=" + allOf + "]";
	}

	@Override
	public JsonArray toJSON() {
		JsonArray array = new JsonArray();
		
		for(JSONSchema js : allOf) {
			Object obj = js.toJSON();
			try {
				array.add((JsonObject) obj);
			}catch(ClassCastException e) {
				array.add((Boolean) obj);
			}
		}
		
		return array;
	}

	@Override
	public String toGrammarString() {
		String str = "";
		
		Iterator<JSONSchema> it = allOf.iterator();
			
		while(it.hasNext()) {
			String returnedValue = it.next().toGrammarString();
			if(returnedValue.isEmpty())
				continue;
			str += GrammarStringDefinitions.COMMA + returnedValue;
		}
		
		if(str.isEmpty()) return "";
		if(allOf.size() == 1) return str.substring(GrammarStringDefinitions.COMMA.length());
		return String.format(GrammarStringDefinitions.ALLOF, str.substring(GrammarStringDefinitions.COMMA.length()));
	}

	@Override
	public AllOf assertionSeparation() {
		AllOf obj = new AllOf();
		
		obj.allOf = new LinkedList<>();
		for(JSONSchema s : allOf)
			obj.allOf.add(s.assertionSeparation());
			
		
		return obj;
	}
	
	@Override
	public int numberOfAssertions() {
		return 1;
	}

	@Override
	public List<URI_JS> getRef() {
		List<URI_JS> returnList = new LinkedList<>();
		
		Iterator<JSONSchema> it = allOf.iterator();
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
	public AllOf clone(){
		AllOf clone = new AllOf();
		
		for(JSONSchema el : allOf)
			clone.addElement(el.clone());
		
		return clone;
	}
}
