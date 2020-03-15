package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
		Ref ref = new Ref(uri);
		return ref;
	}

	@Override
	public String toGrammarString() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object toJSON() {
		return uri;
	}

	@Override
	public List<URI_JS> getRef() {
		List<URI_JS> returnList = new LinkedList<>();
		
		returnList.add(uri);
		
		return returnList;
	}

	@Override
	public Defs searchDef(Iterator<String> URIIterator) {
		return null;
	}
	
	
}
