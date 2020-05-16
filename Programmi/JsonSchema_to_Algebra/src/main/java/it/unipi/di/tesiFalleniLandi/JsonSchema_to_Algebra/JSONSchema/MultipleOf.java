package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.json.simple.JSONObject;

public class MultipleOf implements JSONSchemaElement{
	private Object value;
	
	public MultipleOf(Object obj) {
		Object value = (Object) obj;
		
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "MultipleOf [value=" + value + "]";
	}

	@Override
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		obj.put("multipleOf", value);

		return obj;
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

	@Override
	public int numberOfAssertions() {
		return 1;
	}
	
	@Override
	public MultipleOf clone(){
		return new MultipleOf(value);
	}
}
