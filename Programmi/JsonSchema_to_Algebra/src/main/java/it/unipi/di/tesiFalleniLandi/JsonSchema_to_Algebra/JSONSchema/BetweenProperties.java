package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Pro_Assertion;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class BetweenProperties implements JSONSchemaElement{
	private Long minProperties;
	private Long maxProperties;
	
	public BetweenProperties() { }
	
	public void setMinProperties(JsonElement obj) {
		if(!obj.isJsonPrimitive() || !obj.getAsJsonPrimitive().isNumber())
			throw new ParseCancellationException("expected integer as value of minProperties");

		minProperties = obj.getAsLong();
	}
	
	public void setMaxProperties(JsonElement obj) {
		if(!obj.isJsonPrimitive() || !obj.getAsJsonPrimitive().isNumber())
			throw new ParseCancellationException("expected integer as value of maxProperties");

		maxProperties = obj.getAsLong();
	}

	@Override
	public String toString() {
		return "BetweenProperties [minProperties=" + minProperties + ", maxProperties=" + maxProperties + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JsonElement toJSON() {
		JsonObject obj = new JsonObject();
		
		if(minProperties != null) obj.addProperty("minProperties", minProperties);
		
		if(maxProperties != null) obj.addProperty("maxProperties", maxProperties);

		return obj;
	}

	@Override
	public Pro_Assertion toGrammar() {
		return new Pro_Assertion(minProperties, maxProperties);
	}

	@Override
	public BetweenProperties assertionSeparation() {
		BetweenProperties obj = new BetweenProperties();
		
		if(minProperties != null) obj.minProperties = minProperties;
		if(maxProperties != null) obj.maxProperties = maxProperties;
		
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
	public int numberOfTranslatableAssertions() {
		return 1;
	}
	
	public BetweenProperties clone() {
		BetweenProperties clone = new BetweenProperties();
		
		clone.minProperties = minProperties;
		clone.maxProperties = maxProperties;
		
		return clone;
	}
}
