package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.LinkedList;
import java.util.List;

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

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONSchema() {
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();

		for(Object element : _enum) {
			array.add(element);
		}

		obj.put("enum", array);
		return obj;
	}

	@Override
	public Assertion not() {
		And_Assertion notEnum = new And_Assertion();
		
		for(Object obj : _enum)
			notEnum.add((new Const_Assertion(obj)).not());
		
		return notEnum;
	}

	@Override
	public Assertion notElimination() {
		return new Enum_Assertion(_enum);
	}

	@Override
	public String toGrammarString() {
		return String.format(GrammarStringDefinitions.ENUM, toGrammarString(_enum));
	}

	private String toGrammarString(List<Object> list){
		String str = "";

		for(Object obj : list) {
			if(obj == null)
				str += GrammarStringDefinitions.COMMA + "null";
			else if (obj.getClass() == String.class)
				str += GrammarStringDefinitions.COMMA + "\"" +obj + "\"";
			else if(obj.getClass() == Long.class
				|| obj.getClass() == Double.class
				|| obj.getClass() == Boolean.class)
				str += GrammarStringDefinitions.COMMA + obj;
			else if (obj.getClass() == JSONObject.class)
				str += GrammarStringDefinitions.COMMA + ((JSONObject) obj).toJSONString();
			else
				str += GrammarStringDefinitions.COMMA + toGrammarString((List<Object>) obj);
		}


		return str.substring(GrammarStringDefinitions.COMMA.length());
	}
	
	
}
