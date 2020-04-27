package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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

	@SuppressWarnings("unchecked")
	@Override
	public Object toJSONSchema() {
		JSONObject obj = new JSONObject();

		if(types.contains(GrammarStringDefinitions.TYPE_NUMNOTINT)){
			Or_Assertion or = new Or_Assertion();
			if(types.size() == 1){
				Type_Assertion type = new Type_Assertion();
				type.add(GrammarStringDefinitions.TYPE_INTEGER);
				return new Not_Assertion(type).toJSONSchema();
			}

			Type_Assertion type = new Type_Assertion();
			Type_Assertion typeNumNot = new Type_Assertion();
			typeNumNot.add(GrammarStringDefinitions.TYPE_INTEGER);
			or.add(new Not_Assertion(typeNumNot));
			or.add(type);
			for(String str : types)
				if(!str.equals(GrammarStringDefinitions.TYPE_NUMNOTINT))
					type.add(str);

			return or.toJSONSchema();
		}


		if(types.size() == 1){
			obj.put("type", toJsonTypeName(types.get(0)));
			return obj;
		}

		JSONArray array = new JSONArray();
		for(String str : types)
			array.add(toJsonTypeName(str));

		obj.put("type", array);

		return obj;
	}

	@Override
	public Assertion not() {
		Type_Assertion notType = new Type_Assertion();
		notType.add(GrammarStringDefinitions.TYPE_STRING);
		notType.add(GrammarStringDefinitions.TYPE_OBJECT);
		notType.add(GrammarStringDefinitions.TYPE_NUMBER);
		notType.add(GrammarStringDefinitions.TYPE_ARRAY);
		notType.add(GrammarStringDefinitions.TYPE_BOOLEAN);
		notType.add(GrammarStringDefinitions.TYPE_NULL);

		for(String type : types) {
			notType.types.remove(type);
			if(type.equals(GrammarStringDefinitions.TYPE_INTEGER)){
				notType.types.remove(GrammarStringDefinitions.TYPE_NUMBER);
				notType.types.add(GrammarStringDefinitions.TYPE_NUMNOTINT);
			}

			if(type.equals(GrammarStringDefinitions.TYPE_NUMNOTINT)) {
				notType.types.remove(GrammarStringDefinitions.TYPE_NUMBER);
				notType.types.add(GrammarStringDefinitions.TYPE_INTEGER);
			}
		}

		if(notType.types.isEmpty())	return null;

		return notType;
	}

	@Override
	public Assertion notElimination() {
		Type_Assertion t = new Type_Assertion();
		
		t.types.addAll(types);
		
		return t;
	}

	@Override
	public String toGrammarString() {
		String str = "";
		
		Iterator <String> it = types.iterator();
		
		while(it.hasNext()) {
			str += GrammarStringDefinitions.COMMA + it.next();
		}
		
		return String.format(GrammarStringDefinitions.TYPE, str.substring(GrammarStringDefinitions.COMMA.length()));
	}
	
	private String toJsonTypeName(String type) {
		switch(type) {
		case "arr": return "array";
		case "int": return "integer";
		case "num": return "number";
		case "str": return "string";
		case "obj": return "object";
		case "bool": return "boolean";
		case "null": return "null";
		}
		return null;
	}
}
  