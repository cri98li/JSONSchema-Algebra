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
	public Object toJSON() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toGrammarString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONSchemaElement assertionSeparation() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
