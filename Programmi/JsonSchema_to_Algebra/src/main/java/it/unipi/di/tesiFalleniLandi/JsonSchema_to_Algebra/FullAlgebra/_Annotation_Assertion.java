package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;
import patterns.REException;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO: not implemented
 */
public class _Annotation_Assertion implements Assertion{
	private HashMap<String, String> annotations;
	
	public _Annotation_Assertion() {
		annotations = new HashMap<>();
	}
	
	public void add(String key, String value) {
		annotations.put(key, value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public JsonElement toJSONSchema() {
		JsonObject obj = new JsonObject();
		
		for(Map.Entry<String, String> entry : annotations.entrySet())
			obj.addProperty(entry.getKey(), entry.getValue());
		
		return obj;
	}

	@Override
	public Assertion not() {
		return new Not_Assertion(this);
	}

	@Override
	public Assertion notElimination() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toGrammarString() {
		throw new UnsupportedOperationException();
	}

	@Override
	public WitnessAssertion toWitnessAlgebra() throws REException {
		return null;
	}

}
