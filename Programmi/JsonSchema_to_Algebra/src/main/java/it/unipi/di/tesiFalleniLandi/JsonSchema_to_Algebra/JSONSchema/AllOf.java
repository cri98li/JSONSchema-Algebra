package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.AllOf_Assertion;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class AllOf implements JSONSchemaElement{
	private List<JSONSchema> allOf;
	
	public AllOf() {
		allOf = new LinkedList<>();
	}
	
	public AllOf(JsonElement obj) {
		JsonArray array = obj.getAsJsonArray();
		allOf = new LinkedList<>();
		
		Iterator<JsonElement> it = array.iterator();
		
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

	@SuppressWarnings("unchecked")
	@Override
	public JsonElement toJSON() {
		JsonObject obj = new JsonObject();
		JsonArray array = new JsonArray();
		
		for(JSONSchema js : allOf)
			array.add(js.toJSON());

		obj.add("allOf", array);
		return obj;
	}

	@Override
	public AllOf_Assertion toGrammar() {
		AllOf_Assertion newAllOf = new AllOf_Assertion();

		for(JSONSchemaElement el : allOf)
			newAllOf.add(el.toGrammar());

		return newAllOf;
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
	public int numberOfTranslatableAssertions() {
		int returnValue = 0;
		if(allOf != null)
			for(JSONSchemaElement jse : allOf)
				returnValue += jse.numberOfTranslatableAssertions();

		return returnValue;
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
		try {
			int i = Integer.parseInt(URIIterator.next());
			if(i < allOf.size()){
				URIIterator.remove();
				return allOf.get(i).searchDef(URIIterator);
			}
		}catch (ClassCastException e){
		}

		return null;
	}

	@Override
	public List<Entry<String,Defs>> collectDef() {
		LinkedList<Entry<String, Defs>> returnList = new LinkedList<>();

		for(JSONSchema el : allOf)
			returnList.addAll(el.collectDef());

		return returnList;
	}
	
	
	@Override
	public AllOf clone(){
		AllOf clone = new AllOf();
		
		for(JSONSchema el : allOf)
			clone.addElement(el.clone());
		
		return clone;
	}
}
