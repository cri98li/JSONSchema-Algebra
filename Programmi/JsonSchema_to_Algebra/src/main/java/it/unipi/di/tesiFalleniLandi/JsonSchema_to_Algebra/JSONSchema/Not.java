package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

public class Not implements JSONSchemaElement {
	private JSONSchema value;
	
	public Not(Object obj) {
		value = new JSONSchema(obj);
	}
	
	
	

	@Override
	public String toString() {
		return "Not [value=" + value + "]";
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