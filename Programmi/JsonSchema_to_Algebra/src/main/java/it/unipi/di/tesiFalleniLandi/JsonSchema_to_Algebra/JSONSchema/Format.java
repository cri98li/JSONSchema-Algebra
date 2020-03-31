package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class Format implements JSONSchemaElement{
	private String format;
	
	public Format(Object obj) {
		format = (String)obj;
	}
	
	
	public Format() {
	}


	@Override
	public Object toJSON() {
		return format;
	}

	@Override
	public JSONSchemaElement assertionSeparation() {
		return this.clone();
	}

	@Override
	public String toGrammarString() {
		return "";
	}

	@Override
	public int numberOfAssertions() {
		return 0;
	}

	@Override
	public List<Entry<String, Defs>> collectDef() {
		return new LinkedList<>();
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
	public JSONSchemaElement clone() {
		Format clone = new Format();
		clone.format = format;
		return clone;
	}

}
