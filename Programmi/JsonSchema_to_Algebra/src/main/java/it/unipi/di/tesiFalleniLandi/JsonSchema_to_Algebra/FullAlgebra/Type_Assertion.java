package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.*;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessFalseAssertionException;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessTrueAssertionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Type_Assertion implements Assertion{
	private List<String> types;

	private static Logger logger = LogManager.getLogger(Type_Assertion.class);

	public Type_Assertion() {
		logger.trace("Creating an empty Type_Assertion");
		types = new LinkedList<>();
	}

	public Type_Assertion(String type) {
		this();
		types.add(type);
		logger.trace("Created a new Type_Assertion: {}", this);
	}
	
	public void add(String toAdd) {
		logger.trace("Adding {} to {}", toAdd, this);
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

		if(types.contains(FullAlgebraString.TYPE_NUMNOTINT)){
			AnyOf_Assertion or = new AnyOf_Assertion();
			if(types.size() == 1){
				Type_Assertion type = new Type_Assertion();
				type.add(FullAlgebraString.TYPE_INTEGER);
				return new Not_Assertion(type).toJSONSchema();
			}

			Type_Assertion type = new Type_Assertion();
			Type_Assertion typeNumNot = new Type_Assertion();
			typeNumNot.add(FullAlgebraString.TYPE_INTEGER);
			or.add(new Not_Assertion(typeNumNot));
			or.add(type);
			for(String str : types)
				if(!str.equals(FullAlgebraString.TYPE_NUMNOTINT))
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
		notType.add(FullAlgebraString.TYPE_STRING);
		notType.add(FullAlgebraString.TYPE_OBJECT);
		notType.add(FullAlgebraString.TYPE_NUMBER);
		notType.add(FullAlgebraString.TYPE_ARRAY);
		notType.add(FullAlgebraString.TYPE_BOOLEAN);
		notType.add(FullAlgebraString.TYPE_NULL);

		// remove the type contained in this
		for(String type : types) {
			notType.types.remove(type);
		}

		if(types.contains(FullAlgebraString.TYPE_INTEGER) && !types.contains(FullAlgebraString.TYPE_NUMNOTINT)){
			notType.types.remove(FullAlgebraString.TYPE_NUMBER);
			notType.types.add(FullAlgebraString.TYPE_NUMNOTINT);
		}
		if(types.contains(FullAlgebraString.TYPE_NUMNOTINT) && !types.contains(FullAlgebraString.TYPE_INTEGER)) {
			notType.types.remove(FullAlgebraString.TYPE_NUMBER);
			notType.types.add(FullAlgebraString.TYPE_INTEGER);
		}

		if(types.contains(FullAlgebraString.TYPE_NUMBER)) {
			notType.types.remove(FullAlgebraString.TYPE_NUMNOTINT);
			notType.types.remove(FullAlgebraString.TYPE_INTEGER);
		}

		if(notType.types.contains(FullAlgebraString.TYPE_NUMBER)) {
			notType.types.remove(FullAlgebraString.TYPE_INTEGER);
		}

		if(types.contains(FullAlgebraString.TYPE_NUMNOTINT) && types.contains(FullAlgebraString.TYPE_INTEGER))
			notType.types.remove(FullAlgebraString.TYPE_NUMBER);

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
		StringBuilder str = new StringBuilder();
		
		Iterator <String> it = types.iterator();
		
		while(it.hasNext()) {
			str.append(FullAlgebraString.COMMA)
					.append(it.next());
		}
		
		return FullAlgebraString.TYPE(str.substring(FullAlgebraString.COMMA.length()));
	}

	@Override
	public WitnessAssertion toWitnessAlgebra() {
		if(types.size() == 1) {
			if(types.get(0).equals(FullAlgebraString.TYPE_INTEGER)){
				WitnessAnd and = new WitnessAnd();
				try {
					and.add(new WitnessMof(1.0));
					and.add(new WitnessType(FullAlgebraString.TYPE_NUMBER));
				}catch (WitnessFalseAssertionException e){
					return new WitnessBoolean(false);
				}
				return and;
			}
			return new WitnessType(types.get(0));
		}

		if(types.contains(FullAlgebraString.TYPE_INTEGER)){
			types.remove(FullAlgebraString.TYPE_INTEGER);
			WitnessAnd and = new WitnessAnd();
			try {
				and.add(new WitnessMof(1.0));
				and.add(new WitnessType(FullAlgebraString.TYPE_NUMBER));
			}catch (WitnessFalseAssertionException e){
				return new WitnessBoolean(false);
			}
			WitnessOr or = new WitnessOr();
			try {
				or.add(new WitnessType(types));
				or.add(and);
			}catch (WitnessTrueAssertionException e){
				return new WitnessBoolean(true);
			}

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
  