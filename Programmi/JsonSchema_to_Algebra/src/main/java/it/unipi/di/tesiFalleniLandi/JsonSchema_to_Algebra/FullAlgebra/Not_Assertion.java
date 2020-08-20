package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

public class Not_Assertion implements Assertion{
	
	private Assertion not;

	private static Logger logger = LogManager.getLogger(Not_Assertion.class);
	
	public Not_Assertion(Assertion not) {
		this.not = not;
		logger.trace("Created a new Not_Assertion: {}", this);

	}

	@Override
	public String toString() {
		return "Not_Assertion [" + not + "]";
	}

	@Override
	public JsonObject toJSONSchema() {
		JsonObject obj = new JsonObject();
		obj.add("not", not.toJSONSchema());

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
		String tmp = not.toGrammarString();
		if(tmp.isEmpty()) return "";

		return FullAlgebraString.NOT(tmp);
	}

	@Override
	public WitnessAssertion toWitnessAlgebra() throws REException {
		return not.notElimination().toWitnessAlgebra();
	}
}
