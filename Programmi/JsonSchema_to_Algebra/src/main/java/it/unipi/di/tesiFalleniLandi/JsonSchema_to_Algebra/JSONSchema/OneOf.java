package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.OneOf_Assertion;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class OneOf implements JSONSchemaElement{
	private List<JSONSchema> oneOf;
	
	public OneOf(JsonElement obj) {
		JsonArray array = obj.getAsJsonArray();
		oneOf = new LinkedList<>();
		
		Iterator<JsonElement> it = array.iterator();

		while(it.hasNext()) {
			oneOf.add(new JSONSchema(it.next()));
		}
	}
	
	public OneOf() {
		oneOf = new LinkedList<>();
	}
	
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
	public JsonObject toJSON() {
		JsonObject obj = new JsonObject();
		JsonArray array = new JsonArray();
		
		for(JSONSchema js : oneOf)
			array.add(js.toJSON());

		obj.add("oneOf", array);

		return obj;
	}

	@Override
	public Assertion toGrammar() {
		OneOf_Assertion oneOf = new OneOf_Assertion();

		for(JSONSchema element : this.oneOf)
			oneOf.add(element.toGrammar());

		return oneOf;
	}

	
	@Override
	public int numberOfTranslatableAssertions() {
		int returnValue = 0;
		if(oneOf != null)
			for(JSONSchemaElement jse : oneOf)
				returnValue += jse.numberOfTranslatableAssertions();

		return returnValue;
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
