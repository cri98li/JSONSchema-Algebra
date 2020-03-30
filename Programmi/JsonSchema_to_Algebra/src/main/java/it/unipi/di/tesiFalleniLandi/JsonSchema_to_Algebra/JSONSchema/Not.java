package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class Not implements JSONSchemaElement {
	private JSONSchema value;
	
	public Not(Object obj) {
		value = new JSONSchema(obj);
	}
	
	public Not() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Not [value=" + value + "]";
	}

	@Override
	public Object toJSON() {
		return value.toJSON();
	}

	@Override
	public String toGrammarString() {
		return String.format(GrammarStringDefinitions.NOT, value.toGrammarString());
	}

	@Override
	public Not assertionSeparation() {
		Not obj = new Not();
		
		obj.value = value.assertionSeparation();
		
		return obj;
	}

	@Override
	public List<URI_JS> getRef() {
		return value.getRef();
	}

	@Override
	public JSONSchema searchDef(Iterator<String> URIIterator) {
		if(URIIterator.hasNext() && URIIterator.next().equals("not")){
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
	public int numberOfAssertions() {
		return value.numberOfAssertions();
	}
	
	@Override
	public Not clone(){
		Not clone = new Not();
		
		clone.value = value.clone();
		
		return clone;
	}

}