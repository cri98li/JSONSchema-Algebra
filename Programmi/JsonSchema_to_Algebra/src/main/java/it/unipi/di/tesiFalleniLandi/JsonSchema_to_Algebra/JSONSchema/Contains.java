package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import org.json.simple.JSONObject;

public class Contains implements JSONSchemaElement{
	private JSONSchema contains;
	private Long minContains;
	private Long maxContains;
	
	private boolean initialized;
	
	public Contains() { }
	
	public void setContains(Object obj) {
		initialized = true;
		contains = new JSONSchema(obj);
	}
	
	public void setMinContains(Object obj) {
		Long value = (Long) obj;
		
		initialized = true;
		minContains = value;
	}
	
	public void setMaxContains(Object obj) {
		Long value = (Long) obj;
		
		initialized = true;
		maxContains = value;
	}
	
	public boolean isInitialized() {
		return initialized;
	}
	
	@Override
	public String toString() {
		return "Contains [contains=" + contains + ", minContains=" + minContains + ", maxContains=" + maxContains + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		
		if(contains != null) obj.put("contains", contains.toJSON());
		if(minContains != null) obj.put("minContains", minContains);
		if(maxContains != null) obj.put("maxContains", maxContains);
		
		return obj;
	}

	@Override
	public String toGrammarString() {
		// TODO Auto-generated method stub
		return null;
	}

}
