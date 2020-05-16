package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAssertion;
import org.json.simple.JSONObject;

public class Not_Assertion implements Assertion{
	
	private Assertion not;
	
	public Not_Assertion(Assertion not) {
		this.not = not;
	}

	@Override
	public String toString() {
		return "Not_Assertion [" + not + "]";
	}

	@Override
	public Object toJSONSchema() {
		JSONObject obj = new JSONObject();
		obj.put("not", not.toJSONSchema());

		return obj;
	}

	public Assertion getValue() {
		return not;
	}

	@Override
	public Assertion not() {
		return not; //remove the not
	}

	@Override
	public Assertion notElimination() {
		Assertion not = this.not.not();
		if(not != null)
			return not.notElimination(); //apply not to next assertion

		return null;
	}

	@Override
	public String toGrammarString() {
		return String.format(GrammarStringDefinitions.NOT, not.toGrammarString());
	}

	@Override
	public WitnessAssertion toWitnessAlgebra() {
		return not.notElimination().toWitnessAlgebra();
	}
}
