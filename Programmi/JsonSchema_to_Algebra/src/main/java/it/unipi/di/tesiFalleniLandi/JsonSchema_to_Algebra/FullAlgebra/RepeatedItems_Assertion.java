package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessRepeateditems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class RepeatedItems_Assertion implements Assertion{

	private static Logger logger = LogManager.getLogger(RepeatedItems_Assertion.class);

	public RepeatedItems_Assertion() {
		logger.trace("Created a new RepeatedItems_Assertion");
	}

	@Override
	public String toString() {
		return "RepeatedItems_Assertion";
	}

	@Override
	public JsonElement toJSONSchema() {
		Type_Assertion type = new Type_Assertion();
		type.add("arr");
		Not_Assertion not = new Not_Assertion(new UniqueItems_Assertion());
		IfThenElse_Assertion ifThen = new IfThenElse_Assertion(type, not, null);

		return ifThen.toJSONSchema();
	}

	@Override
	public Assertion not() {
		AllOf_Assertion and = new AllOf_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add(FullAlgebraString.TYPE_ARRAY);
		and.add(type);
		and.add(new UniqueItems_Assertion());

		return and;
	}

	@Override
	public Assertion notElimination() {
		return new RepeatedItems_Assertion();
	}

	@Override
	public String toGrammarString() {
		return FullAlgebraString.REPEATEDITEMS;
	}

	@Override
	public WitnessAssertion toWitnessAlgebra() {
		return new WitnessRepeateditems();
	}
}
