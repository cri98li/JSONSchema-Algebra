package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.*;
import java.util.Map.Entry;

public class Properties implements JSONSchemaElement{

	private HashMap<String, JSONSchema> properties;
	private HashMap<String, JSONSchema> patternProperties;
	private JSONSchema additionalProperties;
	
	public Properties() { }
	
	public void setProperties(JsonElement obj) {
		JsonObject object = null;

		try{
			object = obj.getAsJsonObject();
		}catch(ClassCastException ex){
			if(obj.isJsonArray())
				throw new ParseCancellationException("Error: properties value must be JsonObject, not JsonArray!\r\n");
		}

		properties = new HashMap<>();
		
		Iterator<?> it = object.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String) it.next();
			JSONSchema value = null;

			if((object.get(key).isJsonPrimitive() && object.get(key).getAsJsonPrimitive().isBoolean()) || object.get(key).isJsonObject())
				value = new JSONSchema(object.get(key));
			else{
				throw new ParseCancellationException("Error: the value in properties must be String: JsonObject or Boolean!\r\n");
			}
			
			properties.put(key, value);
		}
	}
	
	public void setPatternProperties(JsonElement obj) {
		JsonObject object = null;

		try{
			object = obj.getAsJsonObject();
		}catch(ClassCastException ex){
			if(obj.getClass() == JsonArray.class)
				throw new ParseCancellationException("Error: patterProperties value must be JsonObject, not JsonArray!\r\n");
		}

		patternProperties = new HashMap<>();
		
		Iterator<String> it = object.keySet().iterator();
		
		while(it.hasNext()) {
			String key = it.next();
			JSONSchema value = null;

			try {
				value = new JSONSchema(object.get(key));
			}catch (ClassCastException ex){
				throw new ParseCancellationException("Error: the value in patterProperties must be String: JsonObject!\r\n");
			}
			
			patternProperties.put(key, value);
		}
	}
	
	public void setAdditionalProperties(JsonElement obj) {
		if(!obj.isJsonObject() && !obj.isJsonPrimitive()) // TODO: trovare modo per controllare i tipi primitivi
			throw new ParseCancellationException("Error: Expected JsonObject in additionalProperties!\r\n");

		additionalProperties = new JSONSchema(obj);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JsonElement toJSON() {
		JsonObject obj = new JsonObject();
		
		if(properties != null && !properties.isEmpty()){
			JsonObject tmp = new JsonObject();
			Set<String> keys = properties.keySet();
				
			for(String key : keys)
				tmp.add(key, properties.get(key).toJSON());
				
			obj.add("properties", tmp);
		}
		
		if(patternProperties != null && !patternProperties.isEmpty()){
			JsonObject tmp = new JsonObject();
			Set<String> keys = patternProperties.keySet();
				
			for(String key : keys)
				tmp.add(key, patternProperties.get(key).toJSON());
				
			obj.add("patternProperties", tmp);
		}
		
		if(additionalProperties != null)
			obj.add("additionalProperties", additionalProperties.toJSON());


		return obj;
	}

	@Override
	public String toGrammarString() {
		String str = "";
		
		if(properties != null) {
			Set<Entry<String, JSONSchema>> entrySet = properties.entrySet();
			for(Entry<String, JSONSchema> entry : entrySet) {
				String returnedValue = entry.getValue().toGrammarString();
				if(!returnedValue.isEmpty()) {
					String decodedKey = new JsonPrimitive(entry.getKey()).toString();
					str += GrammarStringDefinitions.COMMA + String.format(GrammarStringDefinitions.SINGLEPROPERTIES, decodedKey.substring(1, decodedKey.length() - 1), returnedValue);
				}
			}
		}
		
		if(patternProperties != null) {
			Set<Entry<String, JSONSchema>> entrySet = patternProperties.entrySet();
			for(Entry<String, JSONSchema> entry : entrySet) {
				String returnedValue = entry.getValue().toGrammarString();
				if(!returnedValue.isEmpty()) {
					String decodedKey = new JsonPrimitive(entry.getKey()).toString();
					str += GrammarStringDefinitions.COMMA + String.format(GrammarStringDefinitions.SINGLEPROPERTIES, decodedKey.substring(1, decodedKey.length() - 1), returnedValue);
				}
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
			break;
			
		case "patternProperties":
			URIIterator.remove();
			next = URIIterator.next();
			if(patternProperties.containsKey(next)) {
				URIIterator.remove();
				return patternProperties.get(next).searchDef(URIIterator);
			}
			break;
			
		case "additionalProperties":
			URIIterator.remove();
			next = URIIterator.next();
			return additionalProperties.searchDef(URIIterator);
		}
		
		return null;
	}

	@Override
	public int numberOfAssertions() {
		int count = 0;

		if(properties != null)
			for(Entry<String, JSONSchema> entry : properties.entrySet())
				count += entry.getValue().numberOfAssertions();

		if(patternProperties != null)
			for(Entry<String, JSONSchema> entry : patternProperties.entrySet())
				count += entry.getValue().numberOfAssertions();

		if(additionalProperties != null)
			count += additionalProperties.numberOfAssertions();

		return count;
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
