package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class Not implements JSONSchemaElement {
	private JSONSchema value;
	
	public Not(Object obj) {
		value = new JSONSchema(obj);
	}
	
	public Not() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Not [value=" + value + "]";
	}

	@Override
	public Object toJSON() {
		return value.toJSON();
	}

	@Override
	public String toGrammarString() {
		return String.format(GrammarStringDefinitions.NOT, value.toGrammarString());
	}

	@Override
	public Not assertionSeparation() {
		Not obj = new Not();
		
		obj.value = value.assertionSeparation();
		
		return obj;
	}

}