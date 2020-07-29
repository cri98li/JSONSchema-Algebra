package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.UniqueItems_Assertion;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class UniqueItems implements JSONSchemaElement{
	private boolean uniqueItems;

	public UniqueItems(JsonElement obj){
		uniqueItems = obj.getAsBoolean();
	}

	private UniqueItems(boolean obj){
		uniqueItems = obj;
	}
	
	public UniqueItems() {
		uniqueItems = false;
	}

	@Override
	public String toString() {
		return "UniqueItems [uniqueItems=" + uniqueItems + "]";
	}

	@Override
	public JsonObject toJSON() {
		JsonObject obj = new JsonObject();
		obj.addProperty("uniqueItems", uniqueItems);

		return obj;
	}

	@Override
	public UniqueItems_Assertion toGrammar() {
		return uniqueItems? new UniqueItems_Assertion() : null;
	}
	
	@Override
	public int numberOfTranslatableAssertions() {
		return 1;
	}

	@Override
	public UniqueItems assertionSeparation() {
		return this.clone();
	}


	@Override
	public List<URI_JS> getRef() {
		return new LinkedList<>();
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
	public UniqueItems clone() {
		return new UniqueItems(uniqueItems);
	}
}