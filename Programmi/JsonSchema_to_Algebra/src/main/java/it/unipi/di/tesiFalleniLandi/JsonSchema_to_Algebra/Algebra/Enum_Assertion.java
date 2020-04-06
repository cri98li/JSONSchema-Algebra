package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;

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

	@SuppressWarnings("unchecked")
	@Override
	public JSONArray toJSONSchema() {
		JSONArray array = new JSONArray();
		
		for(Object element : _enum) {
			array.add(element);
		}
		
		return array;
	}

	@Override
	public Assertion not() {
		And_Assertion notEnum = new And_Assertion();
		
		for(Object obj : _enum)
			notEnum.add((new Const_Assertion(obj)).not());
		
		return notEnum;
	}
	
	
}
