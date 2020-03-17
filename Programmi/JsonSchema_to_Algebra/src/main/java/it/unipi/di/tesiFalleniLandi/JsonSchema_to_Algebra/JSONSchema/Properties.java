package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.JSONObject;


public class Properties implements JSONSchemaElement{

	private HashMap<String, JSONSchema> properties;
	private HashMap<String, JSONSchema> patternProperties;
	private HashMap<String, JSONSchema> additionalProperties;
	
	private Boolean booleanAsAdditionalProperties;
	
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
	
	public void setPatternProperties(Object obj) {
		
		JSONObject object = (JSONObject) obj;
		
		patternProperties = new HashMap<String, JSONSchema>();
		
		Iterator<?> it = object.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String) it.next();
			JSONSchema value = new JSONSchema(object.get(key));
			
			patternProperties.put(key, value);
		}
	}
	
	public void setAdditionalProperties(Object obj) {
		try {
			booleanAsAdditionalProperties = (Boolean) obj;
			return;
		}catch(ClassCastException e) {	}
		
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
		
		if(properties != null && !properties.isEmpty()){
			JSONObject tmp = new JSONObject();
			Set<String> keys = properties.keySet();
				
			for(String key : keys)
				tmp.put(key, properties.get(key).toJSON());
				
			obj.put("properties", tmp);
		}
		
		if(patternProperties != null && !patternProperties.isEmpty()){
			JSONObject tmp = new JSONObject();
			Set<String> keys = patternProperties.keySet();
				
			for(String key : keys)
				tmp.put(key, patternProperties.get(key).toJSON());
				
			obj.put("patternProperties", tmp);
		}
		
		if(booleanAsAdditionalProperties != null) {
			if(additionalProperties != null && !additionalProperties.isEmpty()){
				JSONObject tmp = new JSONObject();
				Set<String> keys = additionalProperties.keySet();
					
				for(String key : keys)
					tmp.put(key, additionalProperties.get(key).toJSON());
					
				obj.put("additionalProperties", tmp);
			}
		} else {
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

	@Override
	public Properties assertionSeparation() {
		Properties obj = new Properties();
		
		if(properties != null) {
			obj.properties = new HashMap<>();
			Iterator<Entry<String, JSONSchema>> it = properties.entrySet().iterator();
			while(it.hasNext()) {
				Entry<String, JSONSchema> tmp = it.next();
				obj.properties.put(tmp.getKey(), tmp.getValue().assertionSeparation());
			}
		}
		
		if(patternProperties != null) {
			obj.patternProperties = new HashMap<>();
			Iterator<Entry<String, JSONSchema>> it = patternProperties.entrySet().iterator();
			while(it.hasNext()) {
				Entry<String, JSONSchema> tmp = it.next();
				obj.patternProperties.put(tmp.getKey(), tmp.getValue().assertionSeparation());
			}
		}
		
		if(additionalProperties != null) {
			obj.additionalProperties = new HashMap<>();
			Iterator<Entry<String, JSONSchema>> it = additionalProperties.entrySet().iterator();
			while(it.hasNext()) {
				Entry<String, JSONSchema> tmp = it.next();
				obj.additionalProperties.put(tmp.getKey(), tmp.getValue().assertionSeparation());
			}
		}
		
		if(booleanAsAdditionalProperties != null) obj.booleanAsAdditionalProperties = booleanAsAdditionalProperties;
		
		return obj;
	}

	@Override
	public List<URI_JS> getRef() {
		List<URI_JS> returnList = new LinkedList<>();
		
		if(booleanAsAdditionalProperties != null) return returnList;
		
		
		if(properties != null) {
			Set<Entry<String, JSONSchema>> entrySet = properties.entrySet();
			for(Entry<String, JSONSchema> entry : entrySet) {
				returnList.addAll(entry.getValue().getRef());
			}
		}
		if(patternProperties != null) {
			Set<Entry<String, JSONSchema>> entrySet = patternProperties.entrySet();
			for(Entry<String, JSONSchema> entry : entrySet) {
				returnList.addAll(entry.getValue().getRef());
			}
		}
		if(additionalProperties != null) {
			Set<Entry<String, JSONSchema>> entrySet = additionalProperties.entrySet();
			for(Entry<String, JSONSchema> entry : entrySet) {
				returnList.addAll(entry.getValue().getRef());
			}
		}
		
		return returnList;
	}


	@Override
	public List<Entry<String,Defs>> collectDef() {
		List<Entry<String,Defs>> returnList = new LinkedList<>();
		
		if(booleanAsAdditionalProperties != null) return returnList;
		
		if(properties != null) {
			Set<Entry<String, JSONSchema>> entrySet = properties.entrySet();
			for(Entry<String, JSONSchema> entry : entrySet)
				returnList.addAll(Utils.addPathElement(entry.getKey(), entry.getValue().collectDef()));
		}
		
		if(patternProperties != null) {
			Set<Entry<String, JSONSchema>> entrySet = patternProperties.entrySet();
			for(Entry<String, JSONSchema> entry : entrySet)
				returnList.addAll(Utils.addPathElement(entry.getKey(), entry.getValue().collectDef()));
		}
		
		if(additionalProperties != null) {
			Set<Entry<String, JSONSchema>> entrySet = additionalProperties.entrySet();
			for(Entry<String, JSONSchema> entry : entrySet)
				returnList.addAll(Utils.addPathElement(entry.getKey(), entry.getValue().collectDef()));
		}
		
		return returnList;
	}

	@Override
	public JSONSchema searchDef(Iterator<String> URIIterator) {
		if(!URIIterator.hasNext())
			return null;
		
		
		switch(URIIterator.next()) {
		case "properties":
			URIIterator.remove();
			String next = URIIterator.next();
			if(properties.containsKey(next)) {
				URIIterator.remove();
				return properties.get(next).searchDef(URIIterator);
			}
			
		case "patternProperties":
			URIIterator.remove();
			next = URIIterator.next();
			if(properties.containsKey(next)) {
				URIIterator.remove();
				return properties.get(next).searchDef(URIIterator);
			}
			
		case "additionalProperties":
			URIIterator.remove();
			next = URIIterator.next();
			if(properties.containsKey(next)) {
				URIIterator.remove();
				return properties.get(next).searchDef(URIIterator);
			}
		}
		
		return null;
	}
}