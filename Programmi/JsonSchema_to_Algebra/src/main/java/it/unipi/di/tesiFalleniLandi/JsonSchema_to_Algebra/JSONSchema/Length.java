package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class Length implements JSONSchemaElement{
	private Number minLength;
	private Number maxLength;
	
	public Length () { }
	
	public void setMinLength(JsonElement obj) {
		minLength = obj.getAsNumber();
	}
	
	public void setMaxLength(JsonElement obj) {
		maxLength = obj.getAsNumber();
	}
	
	@Override
	public String toString() {
		return "Length [minLength=" + minLength + ", maxLength=" + maxLength + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JsonElement toJSON() {
		JsonObject obj = new JsonObject();
		
		if(minLength != null) obj.addProperty("minLength", minLength);
		if(maxLength != null) obj.addProperty("maxLength", maxLength);
		
		return obj;
	}

	@Override
	public String toGrammarString() {
		String min = "0", max = GrammarStringDefinitions.POS_INF;
		
		if(minLength != null) min = minLength+"";
		if(maxLength != null) max = maxLength+"";
		
		return String.format(GrammarStringDefinitions.LENGTH, min, max);
	}
	
	@Override
	public int numberOfAssertions() {
		return 1;
	}

	@Override
	public Length assertionSeparation() {
		Length obj = new Length();
		
		if(minLength != null) obj.minLength = minLength;
		if(maxLength != null) obj.maxLength = maxLength;
		
		return obj;
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
	public Length clone(){
		Length newLen = new Length();

		newLen.minLength = minLength;
		newLen.maxLength = maxLength;
		
		return newLen;
	}

}
