package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

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
			switch(s) {
			case "str":
				array.add("String");
				break;
				
			case "obj":
				array.add("Object");
				break;
				
			case "num":
				array.add("Number");
				break;
				
			case "int":
				array.add("Integer");
				break;
				
			case "arr":
				array.add("Array");
				break;
				
			case "bool":
				array.add("Boolean");
				break;
				
			case "null":
				array.add("Null");
				break;
				
			case "numnotint":
				//DA PENSARE
				/*Type_Assertion type = new Type_Assertion();
				type.add("int");
				Not_Assertion not = new Not_Assertion(type);
				array.add(not.toJSONSchema());*/
				break;
			}
			
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
		notType.add("numnotint");
		
		for(String type : types)
			notType.types.remove(type);
		
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
		if(it.hasNext())
			str += String.format(GrammarStringDefinitions.TYPE, jsonTypeToGrammar(it.next()));
		
		while(it.hasNext()) {
			str += GrammarStringDefinitions.OR + String.format(GrammarStringDefinitions.TYPE, jsonTypeToGrammar(it.next()));
		}
		
		return str;
	}
	
	private String jsonTypeToGrammar(String type) {
		switch(type) {
		case "arr": return GrammarStringDefinitions.TYPE_ARRAY;
		case "int": return GrammarStringDefinitions.TYPE_INTEGER;
		case "num": return GrammarStringDefinitions.TYPE_NUMBER;
		case "str": return GrammarStringDefinitions.TYPE_STRING;
		case "obj": return GrammarStringDefinitions.TYPE_OBJECT;
		case "bool": return GrammarStringDefinitions.TYPE_BOOLEAN;
		case "null": return GrammarStringDefinitions.TYPE_NULL;
		case "numnotint": return GrammarStringDefinitions.TYPE_NUMNOTINT;
		}
		return null;
	}
	
	
}
  