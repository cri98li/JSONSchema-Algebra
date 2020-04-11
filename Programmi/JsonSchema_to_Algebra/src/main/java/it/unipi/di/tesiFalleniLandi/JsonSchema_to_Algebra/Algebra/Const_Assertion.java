package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

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
				|| value.getClass() == Boolean.class
				|| value.getClass() == Long.class)
			return value;
		
		JSONArray JSONArray = new JSONArray();
		List<Object> array = null;
		
		//caso array
		try {
			array = (List<Object>) value;
			for(Object obj : array)
				JSONArray.add(obj);
			return array;
		}catch(ClassCastException e){	}
		
		//è un JsonObject
		return value;
		
	}

	//TODO: not di boolean, isboolvalue?
	@Override
	public Assertion not() {
		Type_Assertion type = new Type_Assertion();
		
		if(value == null) {
			type.add("null");
			
			return type.not();
		}

		if(value.getClass() == Boolean.class) {
			
		}
		
		if(value.getClass() == Long.class) {
			Or_Assertion or = new Or_Assertion();
			Bet_Assertion bet = new Bet_Assertion(value, value);
			type.add("num");
			or.add(type.not());
			or.add(bet.not());
			
			return or;
		}
		
		if(value.getClass() == String.class) {
			Or_Assertion or = new Or_Assertion();
			Pattern_Assertion pattern = new Pattern_Assertion((String) value);
			
			type.add("str");
			or.add(type.not());
			or.add(pattern.not());
			
			return or;
		}
		
		//caso array
		try {
			@SuppressWarnings("unchecked")
			List<Object> array = (List<Object>) value;
			Items_Assertion items = new Items_Assertion();
			type.add("arr");
			Or_Assertion or = new Or_Assertion();
			
			for(Object obj : array)
				items.add(new Const_Assertion(obj));
			
			or.add(type.not());
			or.add(items.not());
			
			return or;
		}catch(ClassCastException e){	}
		
		
		//caso object
		JSONObject object = (JSONObject) value;
		Or_Assertion or = new Or_Assertion();
		Properties_Assertion properties = new Properties_Assertion();
		Long size = (long) object.size();
		Pro_Assertion pro = new Pro_Assertion(size, size);
		Required_Assertion req = new Required_Assertion();
		
		@SuppressWarnings("unchecked")
		Set<String> keys = object.entrySet();
		for(String key : keys) {
			properties.add(key, new Const_Assertion(object.get(key)));
			req.add(key);
		}
		
		type.add("obj");
		or.add(properties.not());
		or.add(type.not());
		or.add(req.not());
		or.add(pro.not());
		
		return or;
	}

	@Override
	public Assertion notElimination() {
		return new Const_Assertion(value);
	}

	
	//TODO: va fatto il metodo ricorsivo (posso avere antlrArray contenente una lista di AntlrArray)
	@Override
	public String toGrammarString() {
		if(value.getClass() == String.class) {
			return String.format(GrammarStringDefinitions.CONST, "\""+value+"\"");
		}
		
		try {
			@SuppressWarnings("unchecked")
			List<Object> array = (List<Object>) value;
			String str = "";
			for(Object obj : array) {
				if(obj.getClass() == String.class)
					str += GrammarStringDefinitions.COMMA + "\"" + obj + "\"";
			}
		}catch(ClassCastException ex) {}
		
		return "";
	}
	
}
