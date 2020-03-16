package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class Pattern implements JSONSchemaElement{

	private String pattern;
	
	public Pattern(Object str) {
		pattern = (String)str;
	}
	
	
	
	public Pattern() {
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "Pattern [pattern=" + pattern + "]";
	}



	@Override
	public String toJSON() {
		return pattern;
	}

	@Override
	public String toGrammarString() {
		return String.format(GrammarStringDefinitions.PATTERN, pattern);
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

}
