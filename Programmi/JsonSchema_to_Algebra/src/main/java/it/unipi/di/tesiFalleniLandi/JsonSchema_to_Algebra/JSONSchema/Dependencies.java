package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

import java.util.*;
import java.util.Map.Entry;

public class Dependencies implements JSONSchemaElement{
	
	private HashMap<String, List<String>> dependentRequired;
	private HashMap<String, JSONSchema> dependentSchemas;
	
	public Dependencies() {	}
	
	public void setDependencies(JsonElement obj) {
		JsonObject object = obj.getAsJsonObject();

		
		for(Entry<String,JsonElement> entry : object.entrySet()) {
			try {
				if(dependentSchemas == null)  dependentSchemas = new HashMap<>();
				JSONSchema js = new JSONSchema(entry.getValue());
				dependentSchemas.put((String)entry.getKey(), js);
			}catch(ClassCastException ex) {
				if(dependentRequired == null) dependentRequired = new HashMap<>();
				LinkedList<String> list = new LinkedList<>();
				JsonArray array = (JsonArray) entry.getValue();
				
				Iterator<?> it_array = array.iterator();
				
				while(it_array.hasNext()) {
					list.add(((String) it_array.next()));
				}
				
				dependentRequired.put((String) entry.getKey(), list);
			}
		}
	}

	public void setDependentRequired(JsonElement obj) {
		JsonObject object = obj.getAsJsonObject();
		
		dependentRequired = new HashMap<>();
		
		Iterator<?> it = object.keySet().iterator();
		
		while(it.hasNext()) {
			LinkedList<String> list = new LinkedList<>();
			String key = (String) it.next();
			JsonArray array = object.get(key).getAsJsonArray();
			
			Iterator<?> it_array = array.iterator();
			
			while(it_array.hasNext()) {
				list.add((String) it_array.next());
			}
			
			dependentRequired.put(key, list);
		}
	}
	
	public void setDependentSchemas(JsonElement obj){
		JsonObject object = obj.getAsJsonObject();
		dependentSchemas = new HashMap<>();

		
		for(Entry<String,JsonElement> entry : object.entrySet())
			dependentSchemas.put(entry.getKey(), new JSONSchema(entry.getValue()));
	}

	@SuppressWarnings("unchecked")
	//TODO: json schema version
	@Override
	public JsonElement toJSON() {
		JsonObject obj = new JsonObject();
		
		if(dependentSchemas != null && !dependentSchemas.isEmpty()){
			JsonObject tmp = new JsonObject();
			Set<String> keys = dependentSchemas.keySet();
				
			for(String key : keys)
				tmp.add(key, dependentSchemas.get(key).toJSON());
				
			obj.add("dependentSchemas", tmp);
		}
		
		if(dependentRequired != null && !dependentRequired.isEmpty()){
			JsonObject tmp = new JsonObject();
			Set<String> keys = dependentRequired.keySet();
				
			for(String key : keys) {
				JsonArray array = new JsonArray();
				List<String> list = dependentRequired.get(key);
				for(String str : list) {
					array.add(str);
				}
				tmp.add(key, array);
			}
				
			obj.add("dependentRequired", tmp);
		}
		
		return obj;
	}

	@Override
	public JSONSchemaElement assertionSeparation() {
		return this.clone();
	}

	@Override
	public String toGrammarString() {
		String typeObj = String.format(GrammarStringDefinitions.TYPE, GrammarStringDefinitions.TYPE_OBJECT);
		String ds = "";
		String dr = "";
		
		if(dependentSchemas != null && !dependentSchemas.isEmpty()) {
			String str = "";
			Set<Entry<String, JSONSchema>> entrySet = dependentSchemas.entrySet();
			for(Entry<String, JSONSchema> entry : entrySet) {
				String req = String.format(GrammarStringDefinitions.REQUIRED, "\"" + entry.getKey() + "\"");
				str += String.format(GrammarStringDefinitions.IF_THEN, req, entry.getValue().toGrammarString());
				str += GrammarStringDefinitions.COMMA;
			}
			str = str.substring(0, str.length() - GrammarStringDefinitions.COMMA.length());
			String allOf = String.format(GrammarStringDefinitions.ALLOF, str);
			ds = String.format(GrammarStringDefinitions.IF_THEN, typeObj, allOf);
		}
		
		if(dependentRequired != null && !dependentRequired.isEmpty()) {
			String str = "";

			Set<String> keys = dependentRequired.keySet();

			for (String key : keys) {
				String reqList = "";
				String req = "";
				List<String> list = dependentRequired.get(key);
				for (String tmp : list) {
					reqList += "\"" + tmp + "\"" + ",";
				}

				reqList = reqList.substring(0, reqList.length() - 1);
				reqList = String.format(GrammarStringDefinitions.REQUIRED, reqList);
				req = String.format(GrammarStringDefinitions.REQUIRED, "\"" + key + "\"");
				str += String.format(GrammarStringDefinitions.IF_THEN, req, reqList);
				str += GrammarStringDefinitions.COMMA;
			}

			str = str.substring(0, str.length() - GrammarStringDefinitions.COMMA.length());
			String allOf = String.format(GrammarStringDefinitions.ALLOF, str);
			dr = String.format(GrammarStringDefinitions.IF_THEN, typeObj, allOf);

		}
		if(!ds.isEmpty() && !dr.isEmpty()) {
			return ds + GrammarStringDefinitions.COMMA + dr;
		}else if(!ds.isEmpty())
			return ds;
		else
			return dr;
	}

	@Override
	public int numberOfAssertions() {
		int count = 0;

		if(dependentSchemas != null)
			for(Entry<String, JSONSchema> entry : dependentSchemas.entrySet())
				count += entry.getValue().numberOfAssertions();

		//TODO: check
		if(dependentRequired != null)
			count += dependentRequired.size();

		return count;
	}

	@Override
	public List<Entry<String, Defs>> collectDef() {
		List<Entry<String, Defs>> list = new LinkedList<>();
		
		if(dependentSchemas == null) return list;
		
		Set<Entry<String, JSONSchema>> entrySet = dependentSchemas.entrySet();
		
		for(Entry<String, JSONSchema> entry : entrySet)
			list.addAll(Utils_JSONSchema.addPathElement(entry.getKey(), entry.getValue().collectDef()));
		
		return list;
	}

	@Override
	public List<URI_JS> getRef() {
		List<URI_JS> list = new LinkedList<>();
		
		if(dependentSchemas == null) return list;
		
		Set<Entry<String, JSONSchema>> entrySet = dependentSchemas.entrySet();
		
		for(Entry<String, JSONSchema> entry : entrySet)
			list.addAll(entry.getValue().getRef());
		
		return list;
	}

	@Override
	public JSONSchema searchDef(Iterator<String> URIIterator) {
		if(!URIIterator.hasNext() && (URIIterator.next().equals("dependencies") || URIIterator.next().equals("dependentSchemas")))
			return null;
		
		URIIterator.remove();
		if(dependentSchemas != null && dependentSchemas.containsKey(URIIterator.next())) {
			JSONSchema tmp = dependentSchemas.get(URIIterator.next());
			URIIterator.remove();
			return tmp.searchDef(URIIterator);
		}
		
		return null;
	}

	@Override
	public Dependencies clone() {
		Dependencies newDependencies = new Dependencies();
		
		if(dependentRequired != null) {
			newDependencies.dependentRequired = new HashMap<>();
			Set<Entry<String, List<String>>> entrySet = dependentRequired.entrySet();
			for(Entry<String, List<String>> entry : entrySet) {
				List<String> list = new LinkedList<>();
				list.addAll(entry.getValue());
				newDependencies.dependentRequired.put(entry.getKey(), list);
			}
		}
		
		if(dependentSchemas != null) {
			newDependencies.dependentSchemas = new HashMap<>();
			
			Set<Entry<String, JSONSchema>> entrySet = dependentSchemas.entrySet();
			
			for(Entry<String, JSONSchema> entry : entrySet)
				newDependencies.dependentSchemas.put(entry.getKey(), entry.getValue().clone());
		}
		
		return newDependencies;
	}

}
