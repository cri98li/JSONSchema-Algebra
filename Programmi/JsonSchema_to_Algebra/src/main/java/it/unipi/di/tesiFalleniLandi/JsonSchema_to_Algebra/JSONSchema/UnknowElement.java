package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class UnknowElement implements JSONSchemaElement {
	private HashMap<String, Object> obj;
	
	public UnknowElement() {
		 obj = new HashMap<>();
	}
	
	public void add(String key, Object value) {
		obj.put(key, value);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JsonElement toJSON() {
		JsonObject obj = new JsonObject();
		
		//obj.putAll(obj);
		
		return obj;
	}

	@Override
	public String toGrammarString() {
		/*String str = "";
		
		Set<Entry<String, Object>> entrySet = obj.entrySet();
		
		for(Entry<String, Object> entry : entrySet)
			str += GrammarStringDefinitions.COMMA + String.format(GrammarStringDefinitions.SINGLEUNKNOW, entry.getKey(), entry.getValue().toString());
		
		return String.format(GrammarStringDefinitions.UNKNOW, str.substring(GrammarStringDefinitions.COMMA.length()));*/
		return "";
	}
	
	@Override
	public int numberOfAssertions() {
		//return obj.size();
		return 0;
	}

	@Override
	public JSONSchemaElement assertionSeparation() {
		return this.clone();
	}

	@Override
	public List<URI_JS> getRef() {
		return new LinkedList<>();
	}

	@Override
	public JSONSchema searchDef(Iterator<String> URIIterator) {
		return null;
	}

	@Override
	public List<Entry<String,Defs>> collectDef() {
		return new LinkedList<>();
	}
	
	@Override
	public UnknowElement clone() {
		UnknowElement clone =  new UnknowElement();
		
		clone.obj.putAll(obj);
		
		return clone;
	}
	
}
