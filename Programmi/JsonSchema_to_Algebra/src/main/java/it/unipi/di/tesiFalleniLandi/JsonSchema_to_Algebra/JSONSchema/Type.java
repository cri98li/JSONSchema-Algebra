package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import javax.json.Json;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class Type implements JSONSchemaElement {
	protected List<String> type_array;
	
	public Type(JsonElement obj){
		if(obj.isJsonNull())
			throw new ParseCancellationException("Error: Expected JsonArray or Sting in type!\r\n");

		if(obj.isJsonArray()){
			JsonArray array = obj.getAsJsonArray();
			type_array = new LinkedList<>();

			Iterator<JsonElement> it = array.iterator();

			while(it.hasNext()){
				JsonElement str = it.next();
				////Verify the type string value
				if(!str.isJsonPrimitive() || !str.getAsJsonPrimitive().isString())
					throw new ParseCancellationException("Error: Expected JsonArray or Sting in type but was "+ obj);
				jsonTypeToGrammar(str.getAsJsonPrimitive().getAsString());
				type_array.add(str.getAsString());
			}
		}else {
			if(obj.isJsonPrimitive() && obj.getAsJsonPrimitive().isString()) {
				String type_str = obj.getAsString();
				//Verify the type string value
				jsonTypeToGrammar(type_str);
				type_array = new LinkedList<>();
				type_array.add(type_str);
				return;
			} else
				throw new ParseCancellationException("Error: Expected JsonArray or Sting in type but was "+ obj);
		}
	}
	
	public Type() { }
	
	@Override
	public String toString() {
		return "Type [type_array=" + type_array + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JsonElement toJSON() {
		JsonObject obj = new JsonObject();

		if(type_array.size() == 1) {
			obj.addProperty("type", type_array.get(0));
			return obj;
		}
		
		JsonArray array = new JsonArray();
		for(String s : type_array)
			array.add(s);

		obj.add("type", array);

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
			default:
				throw new ParseCancellationException("Error: type '"+type+"' is not allowed!\r\n");
		}
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
			return new Type(new JsonPrimitive(type_array.get(0)));
		}else {
			Type newType = new Type();
			newType.type_array = new LinkedList<>();
			newType.type_array.addAll(type_array);
			return newType;
		}
	}
	
}
