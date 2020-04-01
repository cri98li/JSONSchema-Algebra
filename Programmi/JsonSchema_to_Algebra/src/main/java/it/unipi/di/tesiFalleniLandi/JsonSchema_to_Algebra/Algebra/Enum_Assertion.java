package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.JsonArray;

public class Enum_Assertion implements Assertion{

	private List<Object> _enum;
	
	public Enum_Assertion(List<Object> _enum) {
		this._enum = _enum;
	}

	public Enum_Assertion() {
		_enum = new LinkedList<>();
	}
	
	public void add(Object obj) {
		_enum.add(obj);
	}

	@Override
	public String toString() {
		return "Enum_Assertion [" + _enum + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "enum";
	}

	@Override
	public JsonArray toJSONSchema() {
		JsonArray array = new JsonArray();
		
		for(Object element : _enum) {
			array.add(element);
		}
		
		return array;
	}
	
	
}
