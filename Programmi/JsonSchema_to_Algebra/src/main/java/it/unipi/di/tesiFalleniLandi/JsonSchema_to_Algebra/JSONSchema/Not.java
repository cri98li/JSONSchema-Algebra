package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Not_Assertion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class Not implements JSONSchemaElement {
	private JSONSchema value;

	private static Logger logger = LogManager.getLogger(Not.class);
	
	public Not(JsonElement obj) {
		value = new JSONSchema(obj);
		logger.trace("Creating a new Not: {}", this);
	}
	
	public Not() {
		logger.trace("Creating an empty Not");
	}

	@Override
	public String toString() {
		return "Not [value=" + value + "]";
	}

	@Override
	public JsonElement toJSON() {
		JsonObject obj = new JsonObject();
		obj.add("not", value.toJSON());

		return obj;
	}

	@Override
	public Assertion toGrammar() {
		return new Not_Assertion(value.toGrammar());
	}

	@Override
	public Not assertionSeparation() {
		Not not = new Not();
		not.value = value.assertionSeparation();

		return not;
	}

	@Override
	public List<URI_JS> getRef() {
		return value.getRef();
	}

	@Override
	public JSONSchema searchDef(Iterator<String> URIIterator) {
		if(URIIterator.hasNext() && URIIterator.next().equals("not")){
			logger.debug("searchDef: searching for {} in {}", URIIterator.next(), this);
			URIIterator.remove();
			return value.searchDef(URIIterator);
		}
		
		return null;
	}

	@Override
	public List<Entry<String,Defs>> collectDef() {
		return Utils_JSONSchema.addPathElement("not", value.collectDef());
	}

	@Override
	public int numberOfTranslatableAssertions() {
		return value.numberOfTranslatableAssertions();
	}
	
	@Override
	public Not clone(){
		Not clone = new Not();
		clone.value = value.clone();
		
		return clone;
	}

}