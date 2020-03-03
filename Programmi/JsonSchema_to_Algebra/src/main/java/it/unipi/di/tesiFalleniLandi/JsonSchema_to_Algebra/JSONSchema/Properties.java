package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONObject;

//TODO: ADDITIONAL_PROPERTIES

public class Properties implements JSONSchemaElement{

	private HashMap<String, JSONSchema> properties;

	public Properties(JSONObject object) {
		properties = new HashMap<String, JSONSchema>();
		
		Iterator<?> it = object.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String) it.next();
			JSONSchema value = new JSONSchema((JSONObject)object.get(key));
			
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
		return properties.toString();
	}

}
