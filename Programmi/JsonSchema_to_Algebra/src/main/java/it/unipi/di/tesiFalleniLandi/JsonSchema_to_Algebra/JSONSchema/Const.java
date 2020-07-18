package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

import java.util.LinkedList;

public class Const extends Enum {

	private Const() {
		super();
	}
	
	public Const(JsonElement obj) {
		super();
		parseArray(obj);
	}
	
	public String toString() {
		return "[enumArray_str=" + enumArray_str + ", enumArray_num=" + enumArray_num + ", enumArray_bool="
			+ enumArray_bool + ", enumArray_obj=" + enumArray_obj + ", enumArray_array=" + enumArray_array
			+ ", thereIsNull=" + thereIsNull + "]";
		}

	@Override
	public JsonObject toJSON() {
		JsonObject obj = new JsonObject();

		if(super.thereIsNull) obj.add("const", JsonNull.INSTANCE);
		else if(!enumArray_array.isEmpty()) obj.add("const", enumArray_array.get(0));
		else if(!enumArray_obj.isEmpty()) obj.add("const", enumArray_obj.get(0));
		else if(!enumArray_bool.isEmpty()) obj.addProperty("const", enumArray_bool.get(0));
		else if(!enumArray_num.isEmpty()) obj.addProperty("const", enumArray_num.get(0));
		else if(!enumArray_str.isEmpty()) obj.addProperty("const", enumArray_str.get(0));

		return obj;
	}

	@Override
	public Const assertionSeparation() {
		return clone();
	}

	@Override
	public String toGrammarString() {

		if(!enumArray_str.isEmpty()) {
			String decodedKey = new JsonPrimitive(enumArray_str.get(0)).toString();
			return String.format(GrammarStringDefinitions.CONST, decodedKey);
		}
		
		if(!enumArray_num.isEmpty())
			return String.format(GrammarStringDefinitions.CONST, enumArray_num.get(0).toString());
		
		if(!enumArray_bool.isEmpty())
			return String.format(GrammarStringDefinitions.CONST, enumArray_bool.get(0).toString());
		
		if(!enumArray_array.isEmpty())
			return String.format(GrammarStringDefinitions.CONST, enumArray_array.get(0).toString());

		if(!enumArray_obj.isEmpty())
			return String.format(GrammarStringDefinitions.CONST, enumArray_obj.get(0).toString());

		if(thereIsNull) return String.format(GrammarStringDefinitions.CONST, "null");

		return "";
	}

	@Override
	public Const clone() {
		Const clone = new Const();

		clone.thereIsNull = thereIsNull;

		if(enumArray_str != null) clone.enumArray_str = new LinkedList<>(enumArray_str);

		if(enumArray_num != null) clone.enumArray_num = new LinkedList<>(enumArray_num);

		if(enumArray_bool != null) clone.enumArray_bool = new LinkedList<>(enumArray_bool);

		if(enumArray_array != null) clone.enumArray_array = new LinkedList<>(enumArray_array);

		if(enumArray_obj != null) clone.enumArray_obj = new LinkedList<>(enumArray_obj);

		return clone;
	}
}
