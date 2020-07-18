package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.*;


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
	public JsonElement toJSONSchema() {
		JsonObject obj = new JsonObject();

		if(types.contains(GrammarStringDefinitions.TYPE_NUMNOTINT)){
			AnyOf_Assertion or = new AnyOf_Assertion();
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
			obj.addProperty("type", toJsonTypeName(types.get(0)));
			return obj;
		}

		JsonArray array = new JsonArray();
		for(String str : types)
			array.add(toJsonTypeName(str));

		obj.add("type", array);

		return obj;
	}

	@Override
	public Assertion not() {
		// add all types
		Type_Assertion notType = new Type_Assertion();
		notType.add(GrammarStringDefinitions.TYPE_STRING);
		notType.add(GrammarStringDefinitions.TYPE_OBJECT);
		notType.add(GrammarStringDefinitions.TYPE_NUMBER);
		notType.add(GrammarStringDefinitions.TYPE_ARRAY);
		notType.add(GrammarStringDefinitions.TYPE_BOOLEAN);
		notType.add(GrammarStringDefinitions.TYPE_NULL);

		// remove the type contained in this
		for(String type : types) {
			notType.types.remove(type);
		}

		if(types.contains(GrammarStringDefinitions.TYPE_INTEGER) && !types.contains(GrammarStringDefinitions.TYPE_NUMNOTINT)){
			notType.types.remove(GrammarStringDefinitions.TYPE_NUMBER);
			notType.types.add(GrammarStringDefinitions.TYPE_NUMNOTINT);
		}
		if(types.contains(GrammarStringDefinitions.TYPE_NUMNOTINT) && !types.contains(GrammarStringDefinitions.TYPE_INTEGER)) {
			notType.types.remove(GrammarStringDefinitions.TYPE_NUMBER);
			notType.types.add(GrammarStringDefinitions.TYPE_INTEGER);
		}

		if(types.contains(GrammarStringDefinitions.TYPE_NUMBER)) {
			notType.types.remove(GrammarStringDefinitions.TYPE_NUMNOTINT);
			notType.types.remove(GrammarStringDefinitions.TYPE_INTEGER);
		}

		if(notType.types.contains(GrammarStringDefinitions.TYPE_NUMBER)) {
			notType.types.remove(GrammarStringDefinitions.TYPE_INTEGER);
		}

		if(types.contains(GrammarStringDefinitions.TYPE_NUMNOTINT) && types.contains(GrammarStringDefinitions.TYPE_INTEGER))
			notType.types.remove(GrammarStringDefinitions.TYPE_NUMBER);

		// not of a type with all possibile types
		if(notType.types.isEmpty()) {
			AllOf_Assertion a =new AllOf_Assertion();
			a.add(new Boolean_Assertion(false));
			return a;
		}

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

	@Override
	public WitnessAssertion toWitnessAlgebra() {
		if(types.size() == 1) {
			if(types.get(0).equals(GrammarStringDefinitions.TYPE_INTEGER)){
				WitnessAnd and = new WitnessAnd();
				and.add(new WitnessMof(1.0));
				and.add(new WitnessType(GrammarStringDefinitions.TYPE_NUMBER));
				return and;
			}
			return new WitnessType(types.get(0));
		}

		if(types.contains(GrammarStringDefinitions.TYPE_INTEGER)){
			types.remove(GrammarStringDefinitions.TYPE_INTEGER);
			WitnessAnd and = new WitnessAnd();
			and.add(new WitnessMof(1.0));
			and.add(new WitnessType(GrammarStringDefinitions.TYPE_NUMBER));
			WitnessOr or = new WitnessOr();
			or.add(new WitnessType(types));
			or.add(and);

			return or;
		}
		return new WitnessType(types);
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

	public boolean contains(String type){
		return types.contains(type);
	}
}
  