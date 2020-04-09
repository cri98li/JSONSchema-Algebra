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
			return this.toJsonTypeName(types.get(0));
		
		JSONArray array = new JSONArray();
		for(String s : types)
			array.add(this.toJsonTypeName(s));
				
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
	
	private String toJsonTypeName(String type) {
		switch(type) {
		case "arr": return "array";
		case "int": return "integer";
		case "num": return "number";
		case "str": return "string";
		case "obj": return "object";
		case "bool": return "boolean";
		case "null": return "null";
		//case "numnotint": return GrammarStringDefinitions.TYPE_NUMNOTINT; //bisogna modificare putContent o la keyword ritornata
		}
		return null;
	}
}
  