package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

public class Length implements JSONSchemaElement{
	private Long minLength = 0L;
	private Long maxLength = null;
	
	private boolean initialized;
	
	public Length () { }
	
	public void setMinLength(Long value) {
		initialized = true;
		minLength = value;
	}
	
	public void setMaxLength(Long value) {
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
