package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

public class MultipleOf implements JSONSchemaElement{
	private Long value;
	
	public MultipleOf(Object obj) {
		Long value = (Long) obj;
		
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "MultipleOf [value=" + value + "]";
	}

	@Override
	public Long toJSON() {
		return value;
	}

	@Override
	public String toGrammarString() {
		// TODO Auto-generated method stub
		return null;
	}
}
