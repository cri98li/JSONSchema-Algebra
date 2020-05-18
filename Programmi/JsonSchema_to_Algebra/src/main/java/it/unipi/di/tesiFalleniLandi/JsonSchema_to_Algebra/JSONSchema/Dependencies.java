package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;
import java.util.Map.Entry;

public class Dependencies implements JSONSchemaElement{
	
	private HashMap<String, List<String>> dependentRequired;
	private HashMap<String, JSONSchema> dependentSchemas;
	
	public Dependencies() {	}
	
	public void setDependencies(Object obj) {
		JSONObject object = (JSONObject) obj;
		
		@SuppressWarnings("unchecked")
		Set<Entry<?, ?>> entrySet = object.entrySet();
		
		for(Entry<?,?> entry : entrySet) {
			try {
				if(dependentSchemas == null)  dependentSchemas = new HashMap<>();
				JSONSchema js = new JSONSchema(entry.getValue());
				dependentSchemas.put((String)entry.getKey(), js);
			}catch(ClassCastException ex) {
				if(dependentRequired == null) dependentRequired = new HashMap<>();
				LinkedList<String> list = new LinkedList<>();
				JSONArray array = (JSONArray) entry.getValue();
				
				Iterator<?> it_array = array.iterator();
				
				while(it_array.hasNext()) {
					list.add(((String) it_array.next()));
				}
				
				dependentRequired.put((String) entry.getKey(), list);
			}
		}
	}

	public void setDependentRequired(Object obj) {
		JSONObject object = (JSONObject) obj;
		
		dependentRequired = new HashMap<>();
		
		Iterator<?> it = object.keySet().iterator();
		
		while(it.hasNext()) {
			LinkedList<String> list = new LinkedList<>();
			String key = (String) it.next();
			JSONArray array = (JSONArray) object.get(key);
			
			Iterator<?> it_array = array.iterator();
			
			while(it_array.hasNext()) {
				list.add((String) it_array.next());
			}
			
			dependentRequired.put(key, list);
		}
	}
	
	public void setDependentSchemas(Object obj){
		JSONObject object = (JSONObject) obj;
		dependentSchemas = new HashMap<>();
		
		@SuppressWarnings("unchecked")
		Set<Entry<?, ?>> entrySet = object.entrySet();
		
		for(Entry<?,?> entry : entrySet)
			dependentSchemas.put((String)entry.getKey(), new JSONSchema(entry.getValue()));
	}

	@SuppressWarnings("unchecked")
	//TODO: json schema version
	@Override
	public Object toJSON() {
		JSONObject obj = new JSONObject();
		
		if(dependentSchemas != null && !dependentSchemas.isEmpty()){
			JSONObject tmp = new JSONObject();
			Set<String> keys = dependentSchemas.keySet();
				
			for(String key : keys)
				tmp.put(key, dependentSchemas.get(key).toJSON());
				
			obj.put("dependentSchemas", tmp);
		}
		
		if(dependentRequired != null && !dependentRequired.isEmpty()){
			JSONObject tmp = new JSONObject();
			Set<String> keys = dependentRequired.keySet();
				
			for(String key : keys) {
				JSONArray array = new JSONArray();
				List<String> list = dependentRequired.get(key);
				for(String str : list) {
					array.add(str);
				}
				tmp.put(key, array);
			}
				
			obj.put("dependentRequired", tmp);
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
			
			for(String key : keys) {
				String reqList = "";
				String req = "";
				List<String> list = dependentRequired.get(key);
				for(String tmp : list) {
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
		
		return ds + GrammarStringDefinitions.COMMA + dr;
	}

	@Override
	public int numberOfAssertions() {
		return 1;
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
