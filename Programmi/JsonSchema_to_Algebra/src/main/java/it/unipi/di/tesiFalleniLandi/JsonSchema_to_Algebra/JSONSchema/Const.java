package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.json.simple.JSONObject;

import java.util.LinkedList;

public class Const extends Enum {

	private Const() {
		super();
	}
	
	public Const(Object obj) {
		super();
		parseArray(obj);
	}
	
	public String toString() {
		return "[enumArray_str=" + enumArray_str + ", enumArray_num=" + enumArray_num + ", enumArray_bool="
			+ enumArray_bool + ", enumArray_obj=" + enumArray_obj + ", enumArray_array=" + enumArray_array
			+ ", thereIsNull=" + thereIsNull + "]";
		}

	@Override
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		Object value = null;

		if(super.thereIsNull) value = null;
		else if(!enumArray_array.isEmpty()) value = enumArray_array.get(0);
		else if(!enumArray_obj.isEmpty()) value = enumArray_obj.get(0);
		else if(!enumArray_bool.isEmpty()) value = enumArray_bool.get(0);
		else if(!enumArray_num.isEmpty()) value = enumArray_num.get(0);
		else if(!enumArray_str.isEmpty()) value = enumArray_str.get(0);

		obj.put("const", value);

		return obj;
	}

	@Override
	public Const assertionSeparation() {
		return clone();
	}

	@Override
	public String toGrammarString() {
		if(!enumArray_str.isEmpty())
			return String.format(GrammarStringDefinitions.CONST, "\""+ enumArray_str.get(0) +"\"");
		
		if(!enumArray_num.isEmpty())
			return String.format(GrammarStringDefinitions.CONST, enumArray_num.get(0).toString());
		
		if(!enumArray_bool.isEmpty())
			return String.format(GrammarStringDefinitions.CONST, enumArray_bool.get(0).toString());
		
		if(!enumArray_array.isEmpty())
			return String.format(GrammarStringDefinitions.CONST, enumArray_array.get(0));

		if(!enumArray_obj.isEmpty())
			return String.format(GrammarStringDefinitions.CONST, enumArray_obj.get(0).toJSONString());

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
