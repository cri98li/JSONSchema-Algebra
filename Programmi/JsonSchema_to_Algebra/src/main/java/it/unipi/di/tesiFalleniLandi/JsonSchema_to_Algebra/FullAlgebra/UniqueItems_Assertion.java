package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessUniqueItems;

public class UniqueItems_Assertion implements Assertion{
	
	public UniqueItems_Assertion() { }

	@Override
	public String toString() {
		return "UniqueItems_Assertion";
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
		type.add(GrammarStringDefinitions.TYPE_ARRAY);
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
		return GrammarStringDefinitions.UNIQUEITEMS;
	}

	@Override
	public WitnessAssertion toWitnessAlgebra() {
		return new WitnessUniqueItems();
	}

}
