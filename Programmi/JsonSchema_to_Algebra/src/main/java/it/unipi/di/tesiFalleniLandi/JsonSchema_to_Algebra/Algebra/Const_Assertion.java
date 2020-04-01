package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.List;

import com.google.gson.JsonArray;

public class Const_Assertion implements Assertion{
	
	private Object value;

	public Const_Assertion(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Const_Assertion [" + value + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "const";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object toJSONSchema() {
		//const di tipi primitivi
		if(value.getClass() == String.class
				|| value.getClass() == boolean.class
				|| value.getClass() == Integer.class)
			return value;
		
		JsonArray JsonArray = new JsonArray();
		List<Object> array = null;
		
		//caso array
		try {
			array = (List<Object>) value;
			for(Object obj : array)
				JsonArray.add(obj);
			return array;
		}catch(ClassCastException e){	}
		
		//è un JsonObject
		return value;
		
	}

	
	
}
