package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;

public class Type_Assertion implements Assertion{
	private List<String> type;
	
	public Type_Assertion() {
		type = new LinkedList<>();
	}
	
	public void add(String toAdd) {
		type.add(toAdd);
	}

	@Override
	public String toString() {
		return "Type_Assertion [" + type + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "type";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object toJSONSchema() {
		if(type.size() == 1)
			return type.get(0);
		
		JSONArray array = new JSONArray();
		for(String s : type)
			array.add(s);
			
		return array;
	}
	
	
}
  