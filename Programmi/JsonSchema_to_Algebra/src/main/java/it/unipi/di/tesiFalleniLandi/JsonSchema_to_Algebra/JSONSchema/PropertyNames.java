package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class PropertyNames implements JSONSchemaElement{
	private JSONSchema propertyNames;
	
	public PropertyNames() {
	}
	
	public PropertyNames(Object obj) {
		this.propertyNames = new JSONSchema(obj);
	}

	@Override
	public Object toJSON() {
		return propertyNames.toJSON();
	}

	@Override
	public JSONSchemaElement assertionSeparation() {
		PropertyNames p = new PropertyNames();
		
		p.propertyNames = this.propertyNames.assertionSeparation();
		
		return p;
	}

	@Override
	public String toGrammarString() {
		return String.format(GrammarStringDefinitions.PROPERTYNAMES, propertyNames.toGrammarString());
	}

	@Override
	public int numberOfAssertions() {
		return propertyNames.numberOfAssertions();
	}

	@Override
	public List<Entry<String, Defs>> collectDef() {
		return propertyNames.collectDef();
	}

	@Override
	public List<URI_JS> getRef() {
		// TODO Auto-generated method stub
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
