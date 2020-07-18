package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import com.google.gson.*;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class Enum implements JSONSchemaElement{
	protected List<String> enumArray_str;
	protected List<Number> enumArray_num;
	protected List<Boolean> enumArray_bool;
	protected List<JsonObject> enumArray_obj;
	protected List<JsonArray> enumArray_array;
	protected boolean thereIsNull;
	
	protected Enum() {
		enumArray_str = new LinkedList<>();
		enumArray_num = new LinkedList<>();
		enumArray_bool = new LinkedList<>();
		enumArray_obj = new LinkedList<>();
		enumArray_array = new LinkedList<>();
		thereIsNull = false;
	}
	
	public Enum(JsonElement array) {
		enumArray_str = new LinkedList<>();
		enumArray_num = new LinkedList<>();
		enumArray_bool = new LinkedList<>();
		enumArray_obj = new LinkedList<>();
		enumArray_array = new LinkedList<>();
		thereIsNull = false;
		
		try {
			Iterator<JsonElement> it = array.getAsJsonArray().iterator();
			
			while(it.hasNext()){
				JsonElement currentObject = it.next();
				parseArray(currentObject);
			}
		}catch(ClassCastException e) {
			throw new ParseCancellationException("Expected array as value of \"enum\"");
		}
	}
	
	protected void parseArray(JsonElement currentObject) {
		if(currentObject.isJsonPrimitive()){
			JsonPrimitive primitive = currentObject.getAsJsonPrimitive();
			if(primitive.isNumber())
				enumArray_num.add(primitive.getAsNumber());
			if(primitive.isBoolean())
				enumArray_bool.add(primitive.getAsBoolean());
			if(primitive.isString())
				enumArray_str.add(primitive.getAsString());
		}else if(currentObject.isJsonObject())
			enumArray_obj.add(currentObject.getAsJsonObject());
		else if(currentObject.isJsonArray())
			enumArray_array.add(currentObject.getAsJsonArray());
		else if(currentObject.isJsonNull())
			thereIsNull = true;

	}

	@Override
	public String toString() {
		return "Enum [enumArray_str=" + enumArray_str + ", enumArray_num=" + enumArray_num + ", enumArray_bool="
				+ enumArray_bool + ", enumArray_obj=" + enumArray_obj + ", enumArray_array=" + enumArray_array
				+ ", thereIsNull=" + thereIsNull + "]";

	}

	@SuppressWarnings("unchecked")
	@Override
	public JsonElement toJSON() {
		JsonObject obj = new JsonObject();
		JsonArray array = new JsonArray();
		
		Iterator<?> it = enumArray_str.iterator();
		while(it.hasNext()) array.add(new JsonPrimitive((String) it.next()));
		
		it = enumArray_num.iterator();
		while(it.hasNext()) array.add((Number) it.next());
		
		it = enumArray_bool.iterator();
		while(it.hasNext()) array.add((Boolean) it.next());

		Iterator<? extends JsonObject> it_JSO = enumArray_obj.iterator();
		while(it_JSO.hasNext()) array.add(it_JSO.next());
		
		Iterator<? extends JsonArray>it_JSE = enumArray_array.iterator();
		while(it_JSE.hasNext()) array.add(it_JSE.next());
		
		if(thereIsNull) array.add(JsonNull.INSTANCE);

		obj.add("enum", array);

		return obj;
	}

	@Override
	public String toGrammarString() {
		String str = ""; String separator = ", ";
		
		Iterator <?> it = enumArray_str.iterator();
		while(it.hasNext()){
			String decodedKey = new JsonPrimitive((String) it.next()).toString();
			str += (separator + decodedKey);
		}
		
		it = enumArray_bool.iterator();
		while(it.hasNext()) str += (separator + it.next());
		
		it = enumArray_num.iterator();
		while(it.hasNext()) str += (separator + it.next());
		
		Iterator<? extends JsonObject> it_JSO = enumArray_obj.iterator();
		while(it_JSO.hasNext()) str += (separator + it_JSO.next().toString());
		
		Iterator<? extends JsonArray> it_JSE = enumArray_array.iterator();
		while(it_JSE.hasNext()) str += (separator + it_JSE.next().toString());
		
		if(thereIsNull) str += (separator + "null");

		return String.format(GrammarStringDefinitions.ENUM, str.subSequence(separator.length(), str.length()));
	}


	@Override
	public Enum assertionSeparation() {
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
	public int numberOfAssertions() {
		return enumArray_str.size() + enumArray_array.size() + enumArray_bool.size() + enumArray_obj.size() + enumArray_num.size();
	}
	
	public Enum clone() {
		Enum clone = new Enum();
		
		Iterator <?> it = enumArray_str.iterator();
		while(it.hasNext()) clone.enumArray_str.add((String) it.next());
		
		it = enumArray_bool.iterator();
		while(it.hasNext()) clone.enumArray_bool.add((Boolean) it.next());
		
		it = enumArray_num.iterator();
		while(it.hasNext()) clone.enumArray_num.add((Number) it.next());
		
		Iterator<JsonObject> it_JS = enumArray_obj.iterator();
		while(it_JS.hasNext()) clone.enumArray_obj.add(it_JS.next().deepCopy());
		
		Iterator<JsonArray> it_JSA = enumArray_array.iterator();
		while(it_JSA.hasNext()) clone.enumArray_array.add(it_JSA.next().deepCopy());

		clone.thereIsNull = thereIsNull;
		
		return clone;
	}
}
