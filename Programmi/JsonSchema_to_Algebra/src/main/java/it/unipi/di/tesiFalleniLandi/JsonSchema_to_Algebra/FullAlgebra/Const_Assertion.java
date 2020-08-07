package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.*;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessFalseAssertionException;
import patterns.REException;

import java.util.Map;
import java.util.Set;

//TODO: controlla cosa succede quando creiamo un pattern da una stringa che in verità è un pattern (se viene interpretata come stringa statica [corretto] o come pattern [sbagliato])
public class Const_Assertion implements Assertion{
	private JsonElement value;

	public Const_Assertion(JsonElement value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Const_Assertion [" + value + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JsonElement toJSONSchema() {
		JsonObject obj = new JsonObject();

		if(value.isJsonNull()) {
			obj.add("const", JsonNull.INSTANCE);
			return obj;
		}

		if(value.isJsonObject())
			obj.add("const", value);
		else if(value.isJsonArray())
			obj.add("const", value);
		else if(value.getAsJsonPrimitive().isString())
			obj.addProperty("const", value.getAsString());
		else if(value.getAsJsonPrimitive().isNumber())
			obj.addProperty("const", value.getAsNumber());
		else if(value.getAsJsonPrimitive().isBoolean())
			obj.addProperty("const", value.getAsBoolean());

		return obj;
	}

	@Override
	public Assertion not() {
		Type_Assertion type = new Type_Assertion();
		
		if(value.isJsonNull()) {
			type.add(FullAlgebraString.TYPE_NULL);
			return type.not();
		}

		if(value.isJsonObject()) {
			JsonObject object = value.getAsJsonObject();
			AllOf_Assertion and = new AllOf_Assertion();
			Properties_Assertion properties = new Properties_Assertion();
			Long size = (long) object.size();
			Pro_Assertion pro = new Pro_Assertion(size, size);
			Required_Assertion req = new Required_Assertion();

			@SuppressWarnings("unchecked")
			Set<Map.Entry<String, JsonElement>> keys = object.entrySet();

			for (Map.Entry<String, JsonElement> entry : keys) {
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

		//array
		if(value.isJsonArray()) {
			@SuppressWarnings("unchecked")
			JsonArray array = value.getAsJsonArray();
			Items_Assertion items = new Items_Assertion();
			type.add("arr");
			AnyOf_Assertion or = new AnyOf_Assertion();
			Exist_Assertion betItems = new Exist_Assertion((long) array.size(), (long) array.size(), new Boolean_Assertion(true));

			for (JsonElement element : array)
				items.add(new Const_Assertion(element));

			or.add(betItems.not());
			or.add(items.not());
			or.add(type.not());

			return or;
		}

		if(value.getAsJsonPrimitive().isBoolean()) {
			AnyOf_Assertion or = new AnyOf_Assertion();
			type.add("bool");
			or.add(type.not());
			or.add(new IfBoolThen_Assertion(value.getAsBoolean()).not());

			return or;
		}
		
		if(value.getAsJsonPrimitive().isNumber()) {
			AnyOf_Assertion or = new AnyOf_Assertion();
			Bet_Assertion bet = new Bet_Assertion(value.getAsNumber(), value.getAsNumber());
			type.add("num");
			or.add(type.not());
			or.add(bet.not());
			
			return or;
		}

		//String
		AnyOf_Assertion or = new AnyOf_Assertion();
		Pattern_Assertion pattern = new Pattern_Assertion(ComplexPattern.createFromName(value.getAsString())); //TODO: getAsString or toString?

		type.add("str");
		or.add(type.not());
		or.add(pattern.not());

		return or;
	}

	@Override
	public Assertion notElimination() {
		return new Const_Assertion(value);
	}

	
	@Override
	public String toGrammarString() {
		if(value.isJsonNull()) return FullAlgebraString.CONST("null");

		if(value.isJsonObject() || value.isJsonArray())
			return FullAlgebraString.CONST(value.toString());

		if(value.getAsJsonPrimitive().isString())
			return FullAlgebraString.CONST("\"" + value.getAsString() + "\""  );

		return FullAlgebraString.CONST(value.getAsString());
	}

	/*
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

			else if (obj.getClass() == JsonObject.class)
				str += GrammarStringDefinitions.COMMA + "\"" + ((JsonObject) obj).toString() + "\"";

			else
				str += GrammarStringDefinitions.COMMA + toGrammarString((List<Object>) obj);
		}

		if(str.isEmpty()) return "";
		return str.substring(GrammarStringDefinitions.COMMA.length());
	}*/

	@Override
	public WitnessAssertion toWitnessAlgebra() throws REException {
		if (value.isJsonNull()) return new WitnessType(FullAlgebraString.TYPE_NULL);

		try {
			if (value.isJsonObject()) {

				WitnessAnd and = new WitnessAnd();
				and.add(new WitnessType(FullAlgebraString.TYPE_OBJECT));
				Set<Map.Entry<String, JsonElement>> entrySet = ((JsonObject) value).entrySet();
				Required_Assertion req = new Required_Assertion();

				for (Map.Entry<String, JsonElement> entry : entrySet) {
					req.add(entry.getKey());
					and.add(new WitnessProperty(ComplexPattern.createFromName(entry.getKey()), new Const_Assertion(entry.getValue()).toWitnessAlgebra()));
				}
				and.add(req.toWitnessAlgebra());
				and.add(new WitnessPro(Double.parseDouble("" + entrySet.size()), Double.parseDouble("" + entrySet.size())));
				return and;
			}

			if (value.isJsonArray()) {
				WitnessAnd and = new WitnessAnd();
				and.add(new WitnessType(FullAlgebraString.TYPE_ARRAY));
				WitnessItems items = new WitnessItems();
				JsonArray array = value.getAsJsonArray();
				for (JsonElement element : array)
					items.addItems(new Const_Assertion(element).toWitnessAlgebra());

				and.add(new WitnessContains(Long.parseLong("" + array.size()), Long.parseLong("" + array.size()), new WitnessBoolean(true)));
				and.add(items);
				return and;
			}

			if (value.getAsJsonPrimitive().isString()) {
				WitnessAnd and = new WitnessAnd();
				and.add(new WitnessType(FullAlgebraString.TYPE_STRING));
				and.add(new WitnessPattern(ComplexPattern.createFromName(value.getAsString())));
				return and;
			}

			if (value.getAsJsonPrimitive().isBoolean()) {
				WitnessAnd and = new WitnessAnd();
				and.add(new WitnessType(FullAlgebraString.TYPE_BOOLEAN));
				and.add(new WitnessIfBoolThen(value.getAsBoolean()));
				return and;
			}

			WitnessAnd and = new WitnessAnd();
			and.add(new WitnessType(FullAlgebraString.TYPE_NUMBER));
			and.add(new WitnessBet(Double.parseDouble(value.toString()), Double.parseDouble(value.toString())));

			return and;
		} catch (WitnessFalseAssertionException e) {
			throw new RuntimeException(e); // impossible
		}
	}


	public Object getValue(){
		return value;
	}
}
