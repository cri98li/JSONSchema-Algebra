package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.*;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessOr;
import patterns.REException;

public class Enum_Assertion implements Assertion{

	//private List<JsonElement> _enum;
	private JsonArray _enum;
	
	public Enum_Assertion(JsonArray _enum) {
		this._enum = _enum;
	}

	public Enum_Assertion() {
		_enum = new JsonArray();
	}
	
	public void add(JsonElement obj) {
		_enum.add(obj);
	}

	@Override
	public String toString() {
		return "Enum_Assertion [" + _enum + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JsonElement toJSONSchema() {
		JsonObject obj = new JsonObject();
		JsonArray array = new JsonArray();

		for(JsonElement element : _enum) {
			if(element.isJsonNull())
				array.add(JsonNull.INSTANCE);

			else if(element.isJsonObject())
				array.add(element.getAsJsonObject());

			else if(element.isJsonArray())
				array.add(element.getAsJsonArray());

			else if(element.getAsJsonPrimitive().isString())
				array.add(element.getAsString());

			else if(element.getAsJsonPrimitive().isNumber())
				array.add(element.getAsNumber());

			else
				array.add(element.getAsBoolean());
		}

		obj.add("enum", array);
		return obj;
	}

	@Override
	public Assertion not() {
		AllOf_Assertion notEnum = new AllOf_Assertion();
		
		for(JsonElement element : _enum)
			notEnum.add((new Const_Assertion(element)).not());
		
		return notEnum;
	}

	@Override
	public Assertion notElimination() {
		return new Enum_Assertion(_enum);
	}

	@Override
	public String toGrammarString() {
		if(_enum.size() == 0)
			return "";

		String tmp = _enum.toString();
		tmp = tmp.substring(1, tmp.length()-1);
		return FullAlgebraString.ENUM(tmp);
	}

	@Override
	public WitnessAssertion toWitnessAlgebra() throws REException {
		WitnessOr or = new WitnessOr();
		for(JsonElement element : _enum)
			or.add(new Const_Assertion(element).toWitnessAlgebra());

		return or;
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
				str += GrammarStringDefinitions.COMMA + ((JsonObject) obj).toString();
			else
				str += GrammarStringDefinitions.COMMA + toGrammarString((List<Object>) obj);
		}


		return str.substring(GrammarStringDefinitions.COMMA.length());
	}
	*/
	
	
}
