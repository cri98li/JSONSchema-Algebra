package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.List;

import org.json.simple.JSONArray;

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
		if(value.getClass() == String.class
				|| value.getClass() == boolean.class
				|| value.getClass() == Integer.class)
			return value;
		
		
		JSONArray JSONArray = new JSONArray();
		List<Object> array = (List<Object>) value;
		for(Object obj : array)
			JSONArray.add(obj);
		
		return array;
	}

	
	
}
