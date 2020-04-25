package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.json.simple.JSONObject;

public class RepeatedItems_Assertion implements Assertion{
	public RepeatedItems_Assertion() {
	}

	@Override
	public String toString() {
		return "RepeatedItems_Assertion";
	}

	@Override
	public JSONObject toJSONSchema() {
		JSONObject obj = new JSONObject();
		Type_Assertion type = new Type_Assertion();
		type.add("arr");
		Not_Assertion not = new Not_Assertion(new UniqueItems_Assertion());
		IfThenElse_Assertion ifThen = new IfThenElse_Assertion(type, not, null);

		return ifThen.toJSONSchema();
	}

	@Override
	public Assertion not() {
		return new UniqueItems_Assertion();
	}

	@Override
	public Assertion notElimination() {
		return new RepeatedItems_Assertion();
	}

	@Override
	public String toGrammarString() {
		return GrammarStringDefinitions.REPEATEDITEMS;
	}
}
