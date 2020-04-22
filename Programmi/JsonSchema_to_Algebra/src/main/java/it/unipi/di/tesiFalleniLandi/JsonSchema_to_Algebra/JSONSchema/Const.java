package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.LinkedList;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class Const extends Enum {

	private Const() {
		super();
	}
	
	public Const(Object obj) {
		super();
		parseArray(obj);
	}
	
	public String toString() {
		if(!super.arrayOnly)
			return "Const [enumArray_str=" + enumArray_str + ", enumArray_num=" + enumArray_num + ", enumArray_bool="
				+ enumArray_bool + ", enumArray_obj=" + enumArray_obj + ", enumArray_array=" + enumArray_array+ ", thereIsNull=" + thereIsNull + "]";
			
		return "[enumArray_str=" + enumArray_str + ", enumArray_num=" + enumArray_num + ", enumArray_bool="
			+ enumArray_bool + ", enumArray_obj=" + enumArray_obj + ", enumArray_array=" + enumArray_array
			+ ", thereIsNull=" + thereIsNull + "]";
		}

	@Override
	public Object toJSON() {
		if(super.thereIsNull) return null;
		if(!enumArray_array.isEmpty()) return enumArray_array.get(0);
		if(!enumArray_obj.isEmpty()) return enumArray_obj.get(0);
		if(!enumArray_obj.isEmpty()) return enumArray_obj.get(0);
		if(!enumArray_bool.isEmpty()) return enumArray_bool.get(0);
		if(!enumArray_num.isEmpty()) return enumArray_num.get(0);
		if(!enumArray_str.isEmpty()) return enumArray_str.get(0);
		
		return "ERROR";
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

		return ""; //non ci dovrei mai cadere
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
