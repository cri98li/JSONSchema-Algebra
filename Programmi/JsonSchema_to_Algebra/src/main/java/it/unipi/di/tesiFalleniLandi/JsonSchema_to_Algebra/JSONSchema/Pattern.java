package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Map.Entry;

public class Pattern implements JSONSchemaElement{
	private String pattern;

	public Pattern(JsonElement str) {
		try{
			str.getAsString();
		}catch (Exception e) {
			throw new ParseCancellationException("Error: patter must be String!");
		}

		pattern = str.getAsString();
	}

	private Pattern(String pattern) {
		this.pattern = pattern;
	}
	
	public Pattern() { }

	@Override
	public String toString() {
		return "Pattern [pattern=" + pattern + "]";
	}


	@Override
	public JsonObject toJSON() {
		JsonObject obj = new JsonObject();
		obj.add("pattern", new JsonPrimitive(pattern));

		return obj;
	}

	@Override
	public String toGrammarString() {
		String pattern = new JsonPrimitive(this.pattern).toString();
		return String.format(GrammarStringDefinitions.PATTERN, pattern);
	}
	
	@Override
	public int numberOfAssertions() {
		return 1;
	}

	@Override
	public Pattern assertionSeparation() {
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
	public Pattern clone(){
		return new Pattern(pattern);
	}

}
