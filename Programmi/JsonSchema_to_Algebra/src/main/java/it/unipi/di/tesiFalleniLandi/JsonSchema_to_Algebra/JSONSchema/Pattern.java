package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class Pattern implements JSONSchemaElement{
	private String pattern;
	
	public Pattern(Object str) {
		pattern = (String)str;
	}
	
	public Pattern() { }

	@Override
	public String toString() {
		return "Pattern [pattern=" + pattern + "]";
	}


	@Override
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		obj.put("pattern", pattern);

		return obj;
	}

	@Override
	public String toGrammarString() {
		return String.format(GrammarStringDefinitions.PATTERN, pattern);
	}
	
	@Override
	public int numberOfAssertions() {
		return 1;
	}

	@Override
	public Pattern assertionSeparation() {
		Pattern obj = new Pattern();
		obj.pattern = pattern;
		
		return obj;
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
	public Pattern clone(){
		return new Pattern(pattern);
	}

}
