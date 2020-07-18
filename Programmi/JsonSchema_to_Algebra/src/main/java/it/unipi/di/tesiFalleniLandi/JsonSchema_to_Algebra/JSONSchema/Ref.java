package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class Ref implements JSONSchemaElement{
	private URI_JS uri;
	
	protected Ref() {}
	
	public Ref(JsonElement uri) {
		try {
			this.uri = new URI_JS(uri.getAsString());
		}catch(ClassCastException ex) {
			throw new ParseCancellationException("Error: $ref must be string!");
		}
	}

	@Override
	public JSONSchemaElement assertionSeparation() {
		return this.clone();
	}

	@Override
	public String toGrammarString() {
		String uri = new JsonPrimitive(this.uri.getNormalizedName()).toString();
		return String.format(GrammarStringDefinitions.REF, uri.substring(1, uri.length()-1));
	}
	
	@Override
	public int numberOfAssertions() {
		return 1;
	}

	@Override
	public JsonElement toJSON() {
		JsonObject obj = new JsonObject();
		obj.addProperty("ref", uri.toString());

		return obj;
	}

	@Override
	public List<URI_JS> getRef() {
		List<URI_JS> returnList = new LinkedList<>();
		returnList.add(uri);
		
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
	public Ref clone() {
		Ref clone = new Ref();
		clone.uri = uri.clone();
		
		return clone;
	}
	
}
