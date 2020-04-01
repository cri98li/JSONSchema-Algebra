package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.JsonArray;

public class Type_Assertion implements Assertion{
	private List<String> types;
	
	public Type_Assertion() {
		types = new LinkedList<>();
	}
	
	public void add(String toAdd) {
		types.add(toAdd);
	}

	@Override
	public String toString() {
		return "Type_Assertion [" + types + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "type";
	}

	@Override
	public Object toJSONSchema() {
		if(types.size() == 1)
			return types.get(0);
		
		JsonArray array = new JsonArray();
		for(String s : types)
			array.add(s);
			
		return array;
	}
	
	
}
  