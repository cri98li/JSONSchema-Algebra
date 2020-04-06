package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;

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

	@SuppressWarnings("unchecked")
	@Override
	public Object toJSONSchema() {
		if(types.size() == 1)
			return types.get(0);
		
		JSONArray array = new JSONArray();
		for(String s : types)
			array.add(s);
			
		return array;
	}

	@Override
	public Assertion not() {
		Type_Assertion notType = new Type_Assertion();
		notType.add("str");
		notType.add("obj");
		notType.add("num");
		notType.add("int");
		notType.add("arr");
		notType.add("bool");
		notType.add("null");
		
		for(String type : types)
			notType.types.remove(type);
		
		return notType;
	}
	
	
}
  