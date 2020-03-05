package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

interface JSONSchemaElement {	
	public Object toJSON(); //ritorna una rappresentazione JSON dell'oggetto
	
	public JSONSchemaElement assertionSeparation();//ritorna una rappresentazione JSON dell'oggetto con asserzioni separate
	
	public String toGrammarString();
}
