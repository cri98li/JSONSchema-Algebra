package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class Properties implements JSONSchemaElement{

	private HashMap<String, JSONSchema> properties;
	private HashMap<String, JSONSchema> patternProperties;
	private JSONSchema additionalProperties;
	
	public Properties() { }
	
	public void setProperties(Object obj) {
		JSONObject object = (JSONObject) obj;
		properties = new HashMap<>();
		
		Iterator<?> it = object.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String) it.next();
			JSONSchema value = new JSONSchema(object.get(key));
			
			properties.put(key, value);
		}
	}
	
	public void setPatternProperties(Object obj) {
		JSONObject object = (JSONObject) obj;
		patternProperties = new HashMap<>();
		
		Iterator<?> it = object.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String) it.next();
			JSONSchema value = new JSONSchema(object.get(key));
			
			patternProperties.put(key, value);
		}
	}
	
	public void setAdditionalProperties(Object obj) {
		additionalProperties = new JSONSchema(obj);
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
		
		if(additionalProperties != null)
			obj.put("additionalProperties", additionalProperties.toJSON()); 


		return obj;
	}

	@Override
	public String toGrammarString() {
		String str = "";
		
		if(properties != null) {
			Set<Entry<String, JSONSchema>> entrySet = properties.entrySet();
			for(Entry<String, JSONSchema> entry : entrySet) {
				String returnedValue = entry.getValue().toGrammarString();
				if(!returnedValue.isEmpty())
					str += GrammarStringDefinitions.COMMA + String.format(GrammarStringDefinitions.SINGLEPROPERTIES, entry.getKey(), returnedValue);
			}
		}
		
		if(patternProperties != null) {
			Set<Entry<String, JSONSchema>> entrySet = patternProperties.entrySet();
			for(Entry<String, JSONSchema> entry : entrySet) {
				String returnedValue = entry.getValue().toGrammarString();
				if(!returnedValue.isEmpty())
					str += GrammarStringDefinitions.COMMA + String.format(GrammarStringDefinitions.SINGLEPROPERTIES, entry.getKey(), returnedValue);
			}
				
		}
		
		if(additionalProperties != null) 
			if(str.isEmpty())
				return String.format(GrammarStringDefinitions.PROPERTIES, "", additionalProperties.toGrammarString());
			else
				return String.format(GrammarStringDefinitions.PROPERTIES, str.substring(GrammarStringDefinitions.COMMA.length()), additionalProperties.toGrammarString());
		
		if(str.isEmpty() && additionalProperties == null) return "";
		return String.format(GrammarStringDefinitions.PROPERTIES, str.substring(GrammarStringDefinitions.COMMA.length()), "");
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
		
		if(additionalProperties != null)
			obj.additionalProperties = additionalProperties.assertionSeparation();
		
		return obj;
	}

	@Override
	public List<URI_JS> getRef() {
		List<URI_JS> returnList = new LinkedList<>();
		
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
		if(additionalProperties != null) 
			returnList.addAll(additionalProperties.getRef());

		return returnList;
	}


	@Override
	public List<Entry<String,Defs>> collectDef() {
		List<Entry<String,Defs>> returnList = new LinkedList<>();
		
		if(properties != null) {
			Set<Entry<String, JSONSchema>> entrySet = properties.entrySet();
			for(Entry<String, JSONSchema> entry : entrySet)
				returnList.addAll(Utils_JSONSchema.addPathElement(entry.getKey(), entry.getValue().collectDef()));
		}
		
		if(patternProperties != null) {
			Set<Entry<String, JSONSchema>> entrySet = patternProperties.entrySet();
			for(Entry<String, JSONSchema> entry : entrySet)
				returnList.addAll(Utils_JSONSchema.addPathElement(entry.getKey(), entry.getValue().collectDef()));
		}
		
		if(additionalProperties != null)
			returnList.addAll(additionalProperties.collectDef());
		
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
			if(patternProperties.containsKey(next)) {
				URIIterator.remove();
				return patternProperties.get(next).searchDef(URIIterator);
			}
			
		case "additionalProperties":
			URIIterator.remove();
			next = URIIterator.next();
			return additionalProperties.searchDef(URIIterator);
		}
		
		return null;
	}

	@Override
	public int numberOfAssertions() {
		return 1;
	}
	
	public void addPatternProperties(String key, JSONSchema value) {
		patternProperties.put(key, value);
	}

	@Override
	public Properties clone() {
		Properties newProperties = new Properties();
		
		if(properties != null) {
			newProperties.properties = new HashMap<>();
			Set<Entry<String, JSONSchema>> entrySet = properties.entrySet();
			for(Entry<String, JSONSchema> entry : entrySet) {
				newProperties.properties.put(entry.getKey(), entry.getValue().clone());
			}
		}
		
		if(patternProperties != null) {
			newProperties.patternProperties = new HashMap<>();
			Set<Entry<String, JSONSchema>> entrySet = patternProperties.entrySet();
			for(Entry<String, JSONSchema> entry : entrySet) {
				newProperties.patternProperties.put(entry.getKey(), entry.getValue().clone());
			}
		}
		
		if(additionalProperties != null)
			newProperties.additionalProperties = additionalProperties.clone();

		return newProperties;
	}
}
