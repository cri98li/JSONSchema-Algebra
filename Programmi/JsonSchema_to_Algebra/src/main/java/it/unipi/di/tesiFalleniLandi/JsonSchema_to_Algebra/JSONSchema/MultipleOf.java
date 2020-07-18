package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class MultipleOf implements JSONSchemaElement{
	private Number value;

	private MultipleOf(Number obj) {
		this.value = obj;
	}

	public MultipleOf(JsonElement obj) {
		if(!obj.isJsonPrimitive() || !obj.getAsJsonPrimitive().isNumber())
			throw new ParseCancellationException("expected number as value of multipleOf got " + obj);

		this.value = obj.getAsNumber();
	}
	
	@Override
	public String toString() {
		return "MultipleOf [value=" + value + "]";
	}

	@Override
	public JsonElement toJSON() {
		JsonObject obj = new JsonObject();
		obj.addProperty("multipleOf", value);

		return obj;
	}

	@Override
	public String toGrammarString() {
		return String.format(GrammarStringDefinitions.MULTIPLEOF, value);
	}

	@Override
	public MultipleOf assertionSeparation() {
		return new MultipleOf(value);
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
	public int numberOfAssertions() {
		return 1;
	}
	
	@Override
	public MultipleOf clone(){
		return new MultipleOf(value);
	}
}
