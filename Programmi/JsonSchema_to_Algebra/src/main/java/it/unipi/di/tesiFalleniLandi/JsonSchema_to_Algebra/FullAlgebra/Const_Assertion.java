package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import patterns.Pattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.*;
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
			AnyOf_Assertion or = new AnyOf_Assertion();
			type.add("bool");
			or.add(type.not());
			or.add(new IfBoolThen_Assertion((Boolean) value).not());

			return or;
		}
		
		if(value.getClass() == Long.class || value.getClass() == Double.class) {
			AnyOf_Assertion or = new AnyOf_Assertion();
			Bet_Assertion bet = new Bet_Assertion(value, value);
			type.add("num");
			or.add(type.not());
			or.add(bet.not());
			
			return or;
		}
		
		if(value.getClass() == String.class) {
			AnyOf_Assertion or = new AnyOf_Assertion();
			Pattern_Assertion pattern = new Pattern_Assertion(new Pattern((String) value));
			
			type.add("str");
			or.add(type.not());
			or.add(pattern.not());
			
			return or;
		}
		
		//array
		if(value.getClass() == LinkedList.class) {
			@SuppressWarnings("unchecked")
			List<Object> array = (List<Object>) value;
			Items_Assertion items = new Items_Assertion();
			type.add("arr");
			AnyOf_Assertion or = new AnyOf_Assertion();
			//BetItems_Assertion betItems = new BetItems_Assertion((long) array.size(), (long) array.size());
			Exist_Assertion betItems = new Exist_Assertion((long) array.size(), (long) array.size(), new Boolean_Assertion(true));

			for (Object obj : array)
				items.add(new Const_Assertion(obj));

			or.add(betItems.not());
			or.add(items.not());
			or.add(type.not());

			return or;
		}
		
		
		//object
		JSONObject object = (JSONObject) value;
		AllOf_Assertion and = new AllOf_Assertion();
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
		and.add(properties);
		and.add(type);
		and.add(req);
		and.add(pro);
		
		return and.not();
	}

	@Override
	public Assertion notElimination() {
		return new Const_Assertion(value);
	}

	
	@Override
	public String toGrammarString() {
		if(value == null) return String.format(GrammarStringDefinitions.CONST, "null");

		if(value.getClass() == String.class)
			return String.format(GrammarStringDefinitions.CONST, "\"" + value + "\"");

		if(value.getClass() == Long.class
				|| value.getClass() == Double.class
				|| value.getClass() == Boolean.class)
			return String.format(GrammarStringDefinitions.CONST, value);

		if(value.getClass() == JSONObject.class)
			return String.format(GrammarStringDefinitions.CONST, ((JSONObject) value).toJSONString());

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

	@Override
	public WitnessAssertion toWitnessAlgebra() {
		if(value == null) return new WitnessType(GrammarStringDefinitions.TYPE_NULL);

		if(value.getClass() == String.class) {
			WitnessAnd and = new WitnessAnd();
			and.add(new WitnessType(GrammarStringDefinitions.TYPE_STRING));
			and.add(new WitnessPattern(new Pattern("^"+(String) value+"$")));
			return and;
		}

		if(value.getClass() == Boolean.class){
			WitnessAnd and = new WitnessAnd();
			and.add(new WitnessType(GrammarStringDefinitions.TYPE_BOOLEAN));
			and.add(new WitnessIfBoolThen((Boolean) value));
			return and;
		}

		if(value.getClass() == Long.class
				|| value.getClass() == Double.class){
			WitnessAnd and = new WitnessAnd();
			and.add(new WitnessType(GrammarStringDefinitions.TYPE_NUMBER));
			and.add(new WitnessBet(Double.parseDouble(value.toString()), Double.parseDouble(value.toString())));
			return and;
		}

		if(value.getClass() == JSONObject.class) {
			WitnessAnd and = new WitnessAnd();
			and.add(new WitnessType(GrammarStringDefinitions.TYPE_OBJECT));
			Set<Map.Entry<String, ?>> entrySet = ((JSONObject) value).entrySet();
			Required_Assertion req = new Required_Assertion();
			for(Map.Entry<String, ?> entry : entrySet){
				req.add(entry.getKey());
				and.add(new WitnessProperty(new Pattern(entry.getKey()), new Const_Assertion(entry.getValue()).toWitnessAlgebra()));
			}
			and.add(req.toWitnessAlgebra());
			and.add(new WitnessPro(Double.parseDouble(""+entrySet.size()), Double.parseDouble(""+entrySet.size())));
			return and;
		}

		//array
		WitnessAnd and = new WitnessAnd();
		and.add(new WitnessType(GrammarStringDefinitions.TYPE_ARRAY));
		WitnessItems items = new WitnessItems();
		LinkedList<Object> array = (LinkedList) value;
		for(Object obj : array)
			items.addItems(new Const_Assertion(obj).toWitnessAlgebra());

		and.add(new WitnessContains(Long.parseLong(""+array.size()), Long.parseLong(""+array.size()), new WitnessBoolean(true)));
		and.add(items);
		return and;
	}


	public Object getValue(){
		return value;
	}
}
