package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Pattern_Assertion;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import patterns.REException;

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
	public Assertion toGrammar() {
		try {
			String tmp = new JsonPrimitive(pattern).toString();
			return new Pattern_Assertion(ComplexPattern.createFromRegexp(tmp.substring(1, tmp.length()-1)));
		} catch (REException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public int numberOfTranslatableAssertions() {
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
