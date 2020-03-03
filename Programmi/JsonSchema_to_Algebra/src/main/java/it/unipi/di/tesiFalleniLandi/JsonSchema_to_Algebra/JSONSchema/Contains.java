package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;


public class Contains implements JSONSchemaElement{
	private JSONSchema contains;
	private Long minContains = 0L;
	private Long maxContains = null;
	
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
