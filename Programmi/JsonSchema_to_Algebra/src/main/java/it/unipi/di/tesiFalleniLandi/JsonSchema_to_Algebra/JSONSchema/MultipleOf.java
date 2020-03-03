package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

public class MultipleOf implements JSONSchemaElement{
	private Long value = 1L;
	
	public MultipleOf(Object obj) {
		Long value = (Long) obj;
		
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "MultipleOf [value=" + value + "]";
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
