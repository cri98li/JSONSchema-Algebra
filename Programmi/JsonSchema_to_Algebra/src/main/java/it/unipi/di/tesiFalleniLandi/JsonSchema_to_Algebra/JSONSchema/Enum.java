package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import com.google.gson.*;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Enum_Assertion;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class Enum implements JSONSchemaElement{
	JsonArray _enum;

	public Enum() { }

	public Enum(JsonElement array) {
		try {
			_enum = (JsonArray) array;
		}catch(ClassCastException e) {
			throw new ParseCancellationException("Expected array as value of \"enum\"");
		}
	}

	@Override
	public String toString() {
		return "Enum{" +
				"_enum=" + _enum +
				'}';
	}

	@SuppressWarnings("unchecked")
	@Override
	public JsonElement toJSON() {
		JsonObject obj = new JsonObject();
		JsonArray array = new JsonArray();

		for(JsonElement element : _enum) {
			if(element.isJsonNull())
				array.add(JsonNull.INSTANCE);

			else if(element.isJsonObject())
				array.add(element.getAsJsonObject());

			else if(element.isJsonArray())
				array.add(element.getAsJsonArray());

			else if(element.getAsJsonPrimitive().isString())
				array.add(element.getAsString());

			else if(element.getAsJsonPrimitive().isNumber())
				array.add(element.getAsNumber());

			else
				array.add(element.getAsBoolean());
		}

		obj.add("enum", array);
		return obj;
	}

	@Override
	public Assertion toGrammar() {
		return new Enum_Assertion(_enum.deepCopy());
		/*
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

		 */
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
	public int numberOfTranslatableAssertions() {
		return _enum.size(); //TODO: giusto ritornare enum.size?
	}
	
	public Enum clone() {
		return new Enum(_enum.deepCopy());
	}
}
