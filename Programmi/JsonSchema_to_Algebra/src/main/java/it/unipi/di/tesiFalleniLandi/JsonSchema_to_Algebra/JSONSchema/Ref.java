package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class Ref implements JSONSchemaElement{
	private URI_JS uri;
	
	protected Ref() {}
	
	public Ref(Object uri) {
		try {
			this.uri = new URI_JS((String)uri);
		}catch(ClassCastException ex) {
			System.out.println("Error: $ref must be string!");
		}
	}

	@Override
	public JSONSchemaElement assertionSeparation() {
		return this.clone();
	}

	@Override
	public String toGrammarString() {
		return String.format(GrammarStringDefinitions.REF, uri.toString());
	}
	
	@Override
	public int numberOfAssertions() {
		return 1;
	}

	@Override
	public Object toJSON() {
		return uri.toString();
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
