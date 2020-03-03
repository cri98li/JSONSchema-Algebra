package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

public class BetweenItems implements JSONSchemaElement{
	private Long minItems = 0L;
	private Long maxItems = null;
	
	private boolean initialized;
	
	public BetweenItems() { }
	
	public void setMinItems(Long value) {
		initialized = true;
		minItems = value;
	}
	
	public void setMaxItems(Long value) {
		initialized = true;
		maxItems = value;
	}
	
	public boolean isInitialized() {
		return initialized;
	}
	
	@Override
	public String toString() {
		return "BetweenItems [minItems=" + minItems + ", maxItems=" + maxItems + ", initialized=" + initialized + "]";
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
