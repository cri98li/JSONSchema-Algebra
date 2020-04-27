package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.json.simple.JSONObject;

public class UniqueItems_Assertion implements Assertion{
	
	public UniqueItems_Assertion() {
	}

	@Override
	public String toString() {
		return "UniqueItems_Assertion";
	}

	@Override
	public JSONObject toJSONSchema() {
		JSONObject obj = new JSONObject();
		obj.put("uniqueItems", true);

		return obj;
	}

	@Override
	public Assertion not() {
		And_Assertion and = new And_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add(GrammarStringDefinitions.TYPE_ARRAY);
		and.add(type);
		and.add(new RepeatedItems_Assertion());

		return and;
	}

	@Override
	public Assertion notElimination() {
		// TODO Auto-generated method stub
		return new UniqueItems_Assertion();
	}

	@Override
	public String toGrammarString() {
		return GrammarStringDefinitions.UNIQUEITEMS;
	}
	
}
