package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.AntlrArray;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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

	@Override
	public Assertion notElimination() {
		return new Enum_Assertion(_enum);
	}

	@Override
	public String toGrammarString() {
		String str = "";

		for(Object value : _enum) {
			if(value == null)
				str += GrammarStringDefinitions.COMMA + "\"" +value + "\"";
			if (value.getClass() == String.class)
				str += GrammarStringDefinitions.COMMA + "\"" +value + "\"";
			if( value.getClass() == Long.class || value.getClass() == Double.class || value.getClass() == Boolean.class)
				str += GrammarStringDefinitions.COMMA + value;

			if (value.getClass() == JSONObject.class)
				str += GrammarStringDefinitions.COMMA +((JSONObject) value).toJSONString();

			if (value.getClass() == List.class)
				str += GrammarStringDefinitions.COMMA + toGrammarString((List<Object>) value);

		}

		if(str.isEmpty()) return "";
		return str;
	}

	private String toGrammarString(List<Object> list){
		String str = "";

		for(Object obj : list) {
			if (obj.getClass() == String.class)
				str += GrammarStringDefinitions.COMMA + "\"" +obj + "\"";
			if(obj.getClass() == Long.class
				|| obj.getClass() == Double.class
				|| obj.getClass() == Boolean.class)
				str += GrammarStringDefinitions.COMMA + obj;

			if (obj.getClass() == JSONObject.class)
				str += GrammarStringDefinitions.COMMA + "\"" + ((JSONObject) obj).toJSONString() + "\"";

			if (obj.getClass() == AntlrArray.class)
				str += GrammarStringDefinitions.COMMA + toGrammarString((List<Object>) obj);
		}


		return str.substring(GrammarStringDefinitions.COMMA.length());
	}
	
	
}
