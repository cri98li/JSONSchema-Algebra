package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

interface JSONSchemaElement {
	
	//public String toString();
	
	public Object toJSON();
	
	public String toGrammarString();
}
