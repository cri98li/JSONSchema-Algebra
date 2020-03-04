package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONObject;


public class Properties implements JSONSchemaElement{

	private HashMap<String, JSONSchema> properties;
	private HashMap<java.util.regex.Pattern, JSONSchema> patternProperties;
	private HashMap<String, JSONSchema> additionalProperties;
	
	private Boolean booleanAsProperties;
	private Boolean booleanAsPatternProperties;
	private Boolean booleanAsAdditionalProperties;
	
	public Properties() { }
	
	public void setProperties(Object obj) {
		try {
			booleanAsProperties = (Boolean) obj;
			return;
		} catch(ClassCastException e) { }
		
		JSONObject object = (JSONObject) obj;
		
		properties = new HashMap<String, JSONSchema>();
		
		Iterator<?> it = object.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String) it.next();
			JSONSchema value = new JSONSchema(object.get(key));
			
			properties.put(key, value);
		}
	}
	
	public void setPatternProperties(Object obj) {
		try {
			booleanAsPatternProperties = (Boolean) obj;
			return;
		} catch(ClassCastException e) { }
		
		JSONObject object = (JSONObject) obj;
		patternProperties = new HashMap<java.util.regex.Pattern, JSONSchema>();
		
		Iterator<?> it = object.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String) it.next();
			JSONSchema value = new JSONSchema(object.get(key));
			
			properties.put(key, value);
		}
	}
	
	public void setAdditionalProperties(Object obj) {
		try {
			booleanAsAdditionalProperties = (Boolean) obj;
			return;
		} catch(ClassCastException e) { }
		
		JSONObject object = (JSONObject) obj;
		
		additionalProperties = new HashMap<String, JSONSchema>();
		
		Iterator<?> it = object.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String) it.next();
			JSONSchema value = new JSONSchema(object.get(key));
			
			additionalProperties.put(key, value);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		
		if(booleanAsProperties == null) {
			if(properties != null && !properties.isEmpty()){
				JSONObject tmp = new JSONObject();
				Set<String> keys = properties.keySet();
				
				for(String key : keys)
					tmp.put(key, properties.get(key).toJSON());
				
				obj.put("properties", tmp);
			}
		}else {
			obj.put("properties", booleanAsProperties);
		}
		
		if(booleanAsPatternProperties == null) {
			if(patternProperties != null && !patternProperties.isEmpty()){
				JSONObject tmp = new JSONObject();
				Set<java.util.regex.Pattern> keys = patternProperties.keySet();
				
				for(java.util.regex.Pattern key : keys)
					tmp.put(key, patternProperties.get(key).toJSON());
				
				obj.put("patternProperties", tmp);
			}
		}
		else {
			obj.put("patternProperties", booleanAsPatternProperties);
		}
		
		if(booleanAsAdditionalProperties == null) {
			if(additionalProperties != null && !additionalProperties.isEmpty()){
				JSONObject tmp = new JSONObject();
				Set<String> keys = additionalProperties.keySet();
				
				for(String key : keys)
					tmp.put(key, additionalProperties.get(key).toJSON());
				
				obj.put("additionalProperties", tmp);
			}
		}
		else {
			obj.put("additionalProperties", booleanAsAdditionalProperties);
		}
		
		return obj;
	}

	@Override
	public String toGrammarString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "Properties [properties=" + properties + ", patternProperties=" + patternProperties
				+ ", additionalProperties=" + additionalProperties + "]";
	}
}
