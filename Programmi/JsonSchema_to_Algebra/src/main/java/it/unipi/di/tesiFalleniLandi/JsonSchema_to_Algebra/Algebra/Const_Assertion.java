package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.json.simple.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Const_Assertion implements Assertion{
	
	private Object value;

	public Const_Assertion(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Const_Assertion [" + value + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object toJSONSchema() {
		JSONObject obj = new JSONObject();

		obj.put("const", value);

		return obj;
	}

	@Override
	public Assertion not() {
		Type_Assertion type = new Type_Assertion();
		
		if(value == null) {
			type.add("null");
			
			return type.not();
		}

		if(value.getClass() == Boolean.class) {
			Or_Assertion or = new Or_Assertion();
			type.add("bool");
			or.add(type.not());
			or.add(new IfBoolThen_Assertion((Boolean) value).not());

			return or;
		}
		
		if(value.getClass() == Long.class || value.getClass() == Double.class) {
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
		if(value.getClass() == LinkedList.class) {
			@SuppressWarnings("unchecked")
			List<Object> array = (List<Object>) value;
			Items_Assertion items = new Items_Assertion();
			type.add("arr");
			Or_Assertion or = new Or_Assertion();
			//BetItems_Assertion betItems = new BetItems_Assertion((long) array.size(), (long) array.size());
			Exist_Assertion betItems = new Exist_Assertion((long) array.size(), (long) array.size(), new Boolean_Assertion(true));

			for (Object obj : array)
				items.add(new Const_Assertion(obj));

			or.add(betItems.not());
			or.add(items.not());
			or.add(type.not());

			return or;
		}
		
		
		//caso object
		JSONObject object = (JSONObject) value;
		Or_Assertion or = new Or_Assertion();
		Properties_Assertion properties = new Properties_Assertion();
		Long size = (long) object.size();
		Pro_Assertion pro = new Pro_Assertion(size, size);
		Required_Assertion req = new Required_Assertion();
		
		@SuppressWarnings("unchecked")
		Set<Map.Entry<String, ?>> keys = object.entrySet();
		for(Map.Entry<String, ?> entry : keys) {
			properties.addProperties(entry.getKey(), new Const_Assertion(entry.getValue()));
			req.add(entry.getKey());
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

	
	@Override
	public String toGrammarString() {
		if(value == null) return String.format(GrammarStringDefinitions.CONST, "null");

		if(value.getClass() == String.class
				|| value.getClass() == Long.class
				|| value.getClass() == Double.class
				|| value.getClass() == Boolean.class)
			return String.format(GrammarStringDefinitions.CONST, value);

		if(value.getClass() == JSONObject.class)
			return String.format(GrammarStringDefinitions.CONST, "\"" + ((JSONObject) value).toJSONString() + "\"");

		return toGrammarString((List<Object>) value);

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
				str += GrammarStringDefinitions.COMMA + "\"" + ((JSONObject) obj).toJSONString() + "\"";

			else
				str += GrammarStringDefinitions.COMMA + toGrammarString((List<Object>) obj);
		}

		if(str.isEmpty()) return "";
		return str.substring(GrammarStringDefinitions.COMMA.length());
	}
	
}
