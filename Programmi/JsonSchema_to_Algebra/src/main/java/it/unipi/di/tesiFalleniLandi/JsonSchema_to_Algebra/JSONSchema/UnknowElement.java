package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

public class UnknowElement implements JSONSchemaElement {
	private Object obj;
	
	public UnknowElement(Object obj) {
		 this.obj = obj;
	}
	
	@Override
	public Object toJSON() {
		return obj;
	}

	@Override
	public String toGrammarString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONSchemaElement assertionSeparation() {
		return new UnknowElement(obj);
	}
	
}
