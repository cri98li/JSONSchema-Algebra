package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.json.simple.JSONArray;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class Type implements JSONSchemaElement {
	protected String type;
	protected List<String> type_array;
	
	public Type(Object obj){
		try{
			type = (String) obj;
			return;
		}catch(ClassCastException e) {}
		
		JSONArray array = (JSONArray) obj;
		
		type_array = new LinkedList<>();
		
		Iterator<?> it = array.iterator();
		
		while(it.hasNext())
			type_array.add((String) it.next());
	}
	
	public Type() {
	}
	
	@Override
	public String toString() {
		return "Type [type=" + type + ", type_array=" + type_array + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object toJSON() {
		if(type != null) return type;
		
		JSONArray array = new JSONArray();
		for(String s : type_array)
			array.add(s);
		return array;
	}

	@Override
	public String toGrammarString() {
		
		if(type != null) return String.format(GrammarStringDefinitions.TYPE, jsonTypeToGrammar(type));
		
		/*Iterator <String> it = type_array.iterator();
		if(it.hasNext())
			str +=String.format(GrammarStringDefinitions.TYPE, jsonTypeToGrammar(it.next()));
		
		while(it.hasNext()) {
			str += GrammarStringDefinitions.OR + String.format(GrammarStringDefinitions.TYPE, jsonTypeToGrammar(it.next()));
		}
		
		return str;*/
		return ""; //non ci dovrei mai arrivare
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
		if(type != null)
			obj.type = type;
		
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
	
}
