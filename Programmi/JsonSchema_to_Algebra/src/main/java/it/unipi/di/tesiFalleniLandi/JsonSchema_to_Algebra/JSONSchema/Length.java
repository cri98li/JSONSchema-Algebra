package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class Length implements JSONSchemaElement{
	private Object minLength;
	private Object maxLength;
	
	public Length () { }
	
	public void setMinLength(Object obj) {
		minLength = obj;
	}
	
	public void setMaxLength(Object obj) {
		maxLength = obj;
	}
	
	@Override
	public String toString() {
		return "Length [minLength=" + minLength + ", maxLength=" + maxLength + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		
		if(minLength != null) obj.put("minLength", minLength);
		
		if(maxLength != null) obj.put("maxLength", maxLength);		
		
		return obj;
	}

	@Override
	public String toGrammarString() {
		String min = GrammarStringDefinitions.NULLVALUE, max = GrammarStringDefinitions.NULLVALUE;
		
		if(minLength != null) min = minLength+"";
		if(maxLength != null) max = maxLength+"";
		
		return String.format(GrammarStringDefinitions.LENGTH, min, max);
	}
	
	@Override
	public int numberOfGeneratedAssertions() {
		return 1;
	}

	@Override
	public Length assertionSeparation() {
		Length obj = new Length();
		
		if(minLength != null) obj.minLength = minLength;
		if(maxLength != null) obj.maxLength = maxLength;
		
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
