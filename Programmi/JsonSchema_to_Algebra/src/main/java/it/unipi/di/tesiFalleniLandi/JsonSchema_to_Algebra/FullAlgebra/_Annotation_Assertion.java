package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAssertion;
import org.json.simple.JSONObject;

import java.util.HashMap;

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
	public JSONObject toJSONSchema() {
		JSONObject obj = new JSONObject();
		
		obj.putAll(annotations);
		
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
	public WitnessAssertion toWitnessAlgebra() {
		return null;
	}

}
