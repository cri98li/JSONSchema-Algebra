package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONObject;

//TODO: ADDITIONAL_PROPERTIES

public class Properties implements JSONSchemaElement{

	private HashMap<String, JSONSchema> properties;
	private HashMap<java.util.regex.Pattern, JSONSchema> patternProperties;
	private HashMap<String, JSONSchema> additionalProperties;

	public Properties() { }
	
	public void setProperties(Object obj) {
		JSONObject object = (JSONObject) obj;
		
		properties = new HashMap<String, JSONSchema>();
		
		Iterator<?> it = object.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String) it.next();
			JSONSchema value = new JSONSchema(object.get(key));
			
			properties.put(key, value);
		}
	}
	
	public void setPatternProperties(JSONObject obj) {
		JSONObject object = (JSONObject) obj;
		patternProperties = new HashMap<java.util.regex.Pattern, JSONSchema>();
		
		Iterator<?> it = object.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String) it.next();
			JSONSchema value = new JSONSchema(object.get(key));
			
			properties.put(key, value);
		}
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

	@Override
	public String toString() {
		return "Properties [properties=" + properties + "\\r\\n  patternProperties=" + patternProperties
				+ "\\r\\n  additionalProperties=" + additionalProperties + "]";
	}

	

}
