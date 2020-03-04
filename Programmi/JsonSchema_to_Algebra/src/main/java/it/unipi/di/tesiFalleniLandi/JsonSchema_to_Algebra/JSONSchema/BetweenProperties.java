package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import org.json.simple.JSONObject;

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
		// TODO Auto-generated method stub
		return null;
	};
	
}
