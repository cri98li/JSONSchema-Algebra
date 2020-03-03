package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import org.json.simple.JSONObject;

public class Contains implements JSONSchemaElement{
	private JSONSchema contains;
	private Long minContains = 0L;
	private Long maxContains = null;
	
	private boolean initialized;
	
	public Contains() { }
	
	public void setContains(JSONObject obj) {
		initialized = true;
		contains = new JSONSchema(obj);
	}
	
	public void setMinContains(Long value) {
		initialized = true;
		minContains = value;
	}
	
	public void setMaxContains(Long value) {
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

	@Override
	public String toJSONString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toGrammarString() {
		// TODO Auto-generated method stub
		return null;
	}

}
