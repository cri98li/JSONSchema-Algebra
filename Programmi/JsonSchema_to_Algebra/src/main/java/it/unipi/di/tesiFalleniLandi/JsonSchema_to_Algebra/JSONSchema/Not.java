package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import org.json.simple.JSONObject;

public class Not implements JSONSchemaElement {
	private JSONSchema value;
	
	public Not(JSONObject obj) {
		value = new JSONSchema(obj);
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
