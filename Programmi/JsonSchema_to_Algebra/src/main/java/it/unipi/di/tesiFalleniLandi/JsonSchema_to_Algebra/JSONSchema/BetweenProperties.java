package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

public class BetweenProperties implements JSONSchemaElement{
	private Long minProperties = 0L;
	private Long maxProperties = null;
	
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

	@Override
	public String toJSONString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toGrammarString() {
		// TODO Auto-generated method stub
		return null;
	};
	
}
