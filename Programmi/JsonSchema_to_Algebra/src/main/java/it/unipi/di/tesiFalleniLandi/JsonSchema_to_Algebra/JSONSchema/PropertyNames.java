package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Names_Assertion;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class PropertyNames implements JSONSchemaElement{
	private JSONSchema propertyNames;
	
	public PropertyNames() { }
	
	public PropertyNames(JsonElement obj) {
		this.propertyNames = new JSONSchema(obj);
	}

	@Override
	public JsonElement toJSON() {
		JsonObject obj = new JsonObject();
		obj.add("propertyNames", propertyNames.toJSON());

		return obj;
	}

	@Override
	public JSONSchemaElement assertionSeparation() {
		PropertyNames p = new PropertyNames();
		p.propertyNames = this.propertyNames.assertionSeparation();
		
		return p;
	}

	@Override
	public Names_Assertion toGrammar() {
		return new Names_Assertion(propertyNames.toGrammar());
	}

	@Override
	public int numberOfTranslatableAssertions() {
		return propertyNames.numberOfTranslatableAssertions();
	}

	@Override
	public List<Entry<String, Defs>> collectDef() {
		return propertyNames.collectDef();
	}

	@Override
	public List<URI_JS> getRef() {
		return propertyNames.getRef();
	}

	@Override
	public JSONSchema searchDef(Iterator<String> URIIterator) {
		return propertyNames.searchDef(URIIterator);
	}

	@Override
	public JSONSchemaElement clone() {
		PropertyNames clone = new PropertyNames();
		clone.propertyNames = propertyNames.clone();
		
		return clone;
	}

}
