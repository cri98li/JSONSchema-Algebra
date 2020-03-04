package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import org.json.simple.JSONObject;

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
		// TODO Auto-generated method stub
		return null;
	}

}
