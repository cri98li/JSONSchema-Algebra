package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class Length implements JSONSchemaElement{
	private Long minLength;
	private Long maxLength;
	
	private boolean initialized;
	
	public Length () { }
	
	public void setMinLength(Object obj) {
		Long value = (Long) obj;
		
		initialized = true;
		minLength = value;
	}
	
	public void setMaxLength(Object obj) {
		Long value = (Long) obj;
		
		initialized = true;
		maxLength = value;
	}
	
	public boolean isInitialized() {
		return initialized;
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
		String min = "", max = "";
		
		if(minLength != null) min = minLength+"";
		if(maxLength != null) max = maxLength+"";
		
		return String.format(GrammarStringDefinitions.LENGTH, min, max);
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
	public Defs searchDef(Iterator<String> URIIterator) {
		return null;
	}

}
