package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessUniqueItems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UniqueItems_Assertion implements Assertion{
	private static Logger logger = LogManager.getLogger(UniqueItems_Assertion.class);

	public UniqueItems_Assertion() {
		logger.trace("Created a new UniqueItems_Assertion");
	}

	@Override
	public String toString() {
		return "UniqueItems_Assertion{" +
				'}';
	}

	@Override
	public JsonObject toJSONSchema() {
		JsonObject obj = new JsonObject();
		obj.addProperty("uniqueItems", true);

		return obj;
	}

	@Override
	public Assertion not() {
		AllOf_Assertion and = new AllOf_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add(FullAlgebraString.TYPE_ARRAY);
		and.add(type);
		and.add(new RepeatedItems_Assertion());

		return and;
	}

	@Override
	public Assertion notElimination() {
		return new UniqueItems_Assertion();
	}

	@Override
	public String toGrammarString() {
		return FullAlgebraString.UNIQUEITEMS;
	}

	@Override
	public WitnessAssertion toWitnessAlgebra() {
		return new WitnessUniqueItems();
	}

}
