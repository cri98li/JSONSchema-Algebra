package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class UnknowElement implements JSONSchemaElement {
	private Object obj;
	
	public UnknowElement(Object obj) {
		 this.obj = obj;
	}
	
	@Override
	public Object toJSON() {
		return obj;
	}

	@Override
	public String toGrammarString() {
		System.out.println("UNKNOWN ELEMENT: "+obj.toString());
		return "";
	}

	@Override
	public JSONSchemaElement assertionSeparation() {
		return new UnknowElement(obj);
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
	
}
