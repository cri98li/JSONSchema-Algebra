package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

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
		if(!enumArray_array.isEmpty()) return enumArray_array.get(0).toJSON();
		if(!enumArray_obj.isEmpty()) return enumArray_obj.get(0).toJSON();
		if(!enumArray_bool.isEmpty()) return enumArray_bool.get(0);
		if(!enumArray_num.isEmpty()) return enumArray_num.get(0);
		if(!enumArray_str.isEmpty()) return enumArray_str.get(0);
		
		return "ERROR";
	}

	@Override
	public Const assertionSeparation() {
		Const _const = new Const();
		_const.thereIsNull = thereIsNull;
		
		if(enumArray_str != null) _const.enumArray_str = new LinkedList<>(enumArray_str);
		
		if(enumArray_num != null) _const.enumArray_num = new LinkedList<>(enumArray_num);
		
		if(enumArray_bool != null) _const.enumArray_bool = new LinkedList<>(enumArray_bool);
		
		if(enumArray_array != null) _const.enumArray_array = new LinkedList<>(enumArray_array);
		
		_const.thereIsNull = this.thereIsNull;
		
		if(enumArray_obj != null) {
			for(JSONSchema s : enumArray_obj)
				_const.enumArray_obj.add(s.assertionSeparation());
		}
		
		return _const;
	}

	
}
