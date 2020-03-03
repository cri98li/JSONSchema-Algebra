package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import org.json.simple.JSONObject;

public class Type implements JSONSchemaElement {

	private String type;
	
	Type(String value){
		type = value;
	}
	
	
	@Override
	public String toString() {
		return type;
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
