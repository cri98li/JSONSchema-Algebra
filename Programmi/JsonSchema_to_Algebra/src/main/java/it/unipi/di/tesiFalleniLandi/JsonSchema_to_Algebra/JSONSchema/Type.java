package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class Type implements JSONSchemaElement {
	protected List<String> type_array;
	
	public Type(Object obj){
		try{
			String type_str = (String) obj;
			type_array = new LinkedList<>();
			type_array.add(type_str);
			return;
		}catch(ClassCastException e) {}
		
		JSONArray array = (JSONArray) obj;
		type_array = new LinkedList<>();
		
		Iterator<?> it = array.iterator();
		
		while(it.hasNext())
			type_array.add((String) it.next());
	}
	
	public Type() { }
	
	@Override
	public String toString() {
		return "Type [type_array=" + type_array + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();

		if(type_array.size() == 1) {
			obj.put("type", type_array.get(0));
			return obj;
		}
		
		JSONArray array = new JSONArray();
		for(String s : type_array)
			array.add(s);

		obj.put("type", array);

		return obj;
	}

	@Override
	public String toGrammarString() {
		
		if(type_array.size() == 1) return String.format(GrammarStringDefinitions.TYPE, jsonTypeToGrammar(type_array.get(0)));
		
		String str = "";
		Iterator <String> it = type_array.iterator();
		
		while(it.hasNext()) {
			str += GrammarStringDefinitions.COMMA + jsonTypeToGrammar(it.next());
		}
		
		return String.format(GrammarStringDefinitions.TYPE, str.substring(GrammarStringDefinitions.COMMA.length()));
	}
	
	@Override
	public int numberOfAssertions() {
		return 1;
	}
	
	private String jsonTypeToGrammar(String type) {
		switch(type) {
		case "array": return GrammarStringDefinitions.TYPE_ARRAY;
		case "integer": return GrammarStringDefinitions.TYPE_INTEGER;
		case "number": return GrammarStringDefinitions.TYPE_NUMBER;
		case "string": return GrammarStringDefinitions.TYPE_STRING;
		case "object": return GrammarStringDefinitions.TYPE_OBJECT;
		case "boolean": return GrammarStringDefinitions.TYPE_BOOLEAN;
		case "null": return GrammarStringDefinitions.TYPE_NULL;
		}
		
		return null;
	}

	@Override
	public Type assertionSeparation() {
		Type obj = new Type();

		if(type_array != null)
			obj.type_array = new LinkedList<>(type_array);

		return obj;
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
	public Type clone() {
		if(type_array.size() == 1) {
			return new Type(type_array.get(0));
		}else {
			Type newType = new Type();
			newType.type_array = new LinkedList<>();
			newType.type_array.addAll(type_array);
			return newType;
		}
	}
	
}
