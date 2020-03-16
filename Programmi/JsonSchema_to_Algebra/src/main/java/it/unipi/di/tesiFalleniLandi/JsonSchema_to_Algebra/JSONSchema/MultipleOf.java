package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class MultipleOf implements JSONSchemaElement{
	private Long value;
	
	public MultipleOf(Object obj) {
		Long value = (Long) obj;
		
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "MultipleOf [value=" + value + "]";
	}

	@Override
	public Long toJSON() {
		return value;
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
}
