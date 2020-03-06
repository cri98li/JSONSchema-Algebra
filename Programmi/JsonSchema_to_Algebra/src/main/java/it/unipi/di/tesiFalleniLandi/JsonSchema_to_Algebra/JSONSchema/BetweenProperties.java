package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class BetweenProperties implements JSONSchemaElement{
	private Long minProperties;
	private Long maxProperties;
	
	private boolean initialized;
	
	public BetweenProperties() { }
	
	public void setMinProperties(Object obj) {
		Long value = (Long) obj;
		minProperties = value;
	}
	
	public void setMaxProperties(Object obj) {
		Long value = (Long) obj;
		maxProperties = value;
	}
	
	public boolean isInitialized() {
		return initialized;
	}

	@Override
	public String toString() {
		return "BetweenProperties [minProperties=" + minProperties + ", maxProperties=" + maxProperties + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		
		if(minProperties != null) obj.put("minProperties", minProperties);
		
		if(maxProperties != null) obj.put("maxProperties", maxProperties);
		
		return obj;
	}

	@Override
	public String toGrammarString() {
		String min = "", max = "";
		
		if(minProperties != null) min = minProperties+"";
		if(maxProperties != null) max = maxProperties+"";
		
		return String.format(GrammarStringDefinitions.BETWEENPROPERTIES, min, max);
	}

	@Override
	public BetweenProperties assertionSeparation() {
		BetweenProperties obj = new BetweenProperties();
		
		if(minProperties != null) obj.minProperties = minProperties;
		if(maxProperties != null) obj.maxProperties = maxProperties;
		
		return obj;
	};
	
}
