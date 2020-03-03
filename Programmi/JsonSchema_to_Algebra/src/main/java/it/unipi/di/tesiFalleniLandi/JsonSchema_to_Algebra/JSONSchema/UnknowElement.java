package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

public class UnknowElement implements JSONSchemaElement {
	private String unknow;
	
	public UnknowElement() {
		
	}
	
	@Override
	public String toString() {
		return unknow;
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
