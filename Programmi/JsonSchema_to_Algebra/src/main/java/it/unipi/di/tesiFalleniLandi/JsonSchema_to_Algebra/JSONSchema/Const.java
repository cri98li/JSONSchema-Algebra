package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

public class Const extends Enum {

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
	
	
}
