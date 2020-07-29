package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.*;

import java.util.*;
import java.util.Map.Entry;

public class Dependencies implements JSONSchemaElement{
	
	private HashMap<String, List<String>> dependentRequired;
	private HashMap<String, JSONSchema> dependentSchemas;
	
	public Dependencies() {
		dependentSchemas = new HashMap<>();
		dependentRequired = new HashMap<>();
	}
	
	public void setDependencies(JsonElement obj) {
		JsonObject object = obj.getAsJsonObject();

		
		for(Entry<String,JsonElement> entry : object.entrySet()) {
			try {
				JSONSchema js = new JSONSchema(entry.getValue());
				dependentSchemas.put((String)entry.getKey(), js);
			}catch(ClassCastException ex) {
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

		for(String key : object.keySet()) {
			LinkedList<String> list = new LinkedList<>();
			JsonArray array = object.get(key).getAsJsonArray();

			for(JsonElement el : array) {
				list.add(el.getAsString());
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
		
		if(!dependentSchemas.isEmpty()){
			JsonObject tmp = new JsonObject();
			Set<String> keys = dependentSchemas.keySet();
				
			for(String key : keys)
				tmp.add(key, dependentSchemas.get(key).toJSON());
				
			obj.add("dependentSchemas", tmp);
		}
		
		if(!dependentRequired.isEmpty()){
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
	public Assertion toGrammar() {
		Type_Assertion typeObj = new Type_Assertion(FullAlgebraString.TYPE_OBJECT);
		IfThenElse_Assertion ds = null;
		IfThenElse_Assertion dr = null;
		
		if(!dependentSchemas.isEmpty()) {
			AllOf_Assertion allOf = new AllOf_Assertion();
			Required_Assertion required;
			IfThenElse_Assertion ifThenElse;

			Set<Entry<String, JSONSchema>> entrySet = dependentSchemas.entrySet();
			for(Entry<String, JSONSchema> entry : entrySet) {
				required = new Required_Assertion();
				required.add(entry.getKey());
				ifThenElse = new IfThenElse_Assertion(required, entry.getValue().toGrammar(), null);
				allOf.add(ifThenElse);
			}
			ds = new IfThenElse_Assertion(typeObj, allOf, null);
		}
		
		if(!dependentRequired.isEmpty()) {
			AllOf_Assertion allOf = new AllOf_Assertion();

			Set<String> keys = dependentRequired.keySet();

			for (String key : keys) {
				Required_Assertion reqList = new Required_Assertion();
				Required_Assertion req = new Required_Assertion();

				List<String> list = dependentRequired.get(key);
				for (String tmp : list) {
					reqList.add(tmp);
				}

				req.add(key);
				IfThenElse_Assertion ifThen = new IfThenElse_Assertion(req, reqList, null);
				allOf.add(ifThen);

			}

			dr = new IfThenElse_Assertion( typeObj, allOf, null);
		}

		if(ds != null && dr != null) {
			AllOf_Assertion allOf = new AllOf_Assertion();
			allOf.add(ds);
			allOf.add(dr);
			return allOf;
		}else if(ds != null)
			return ds;
		else
			return dr;
	}

	@Override
	public int numberOfTranslatableAssertions() {
		int count = 0;

		if(dependentSchemas != null)
			for(Entry<String, JSONSchema> entry : dependentSchemas.entrySet())
				count += entry.getValue().numberOfTranslatableAssertions();

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
		if(dependentSchemas.containsKey(URIIterator.next())) {
			JSONSchema tmp = dependentSchemas.get(URIIterator.next());
			URIIterator.remove();
			return tmp.searchDef(URIIterator);
		}
		
		return null;
	}

	@Override
	public Dependencies clone() {
		Dependencies newDependencies = new Dependencies();

		for(Entry<String, List<String>> entry : dependentRequired.entrySet()) {
			List<String> list = new LinkedList<>();
			list.addAll(entry.getValue());
			newDependencies.dependentRequired.put(entry.getKey(), list);
		}

			
		for(Entry<String, JSONSchema> entry : dependentSchemas.entrySet())
			newDependencies.dependentSchemas.put(entry.getKey(), entry.getValue().clone());

		return newDependencies;
	}

}
