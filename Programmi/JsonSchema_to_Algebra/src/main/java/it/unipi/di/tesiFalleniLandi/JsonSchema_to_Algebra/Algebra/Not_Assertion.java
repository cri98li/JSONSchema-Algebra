package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;

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
	public String getJSONSchemaKeyword() {
		return "not";
	}

	@Override
	public Object toJSONSchema() {
		JSONObject obj = new JSONObject();

		if(not.getClass() == Boolean_Assertion.class)
			return not.toJSONSchema();
		else
			Utils.putContent(obj, not.getJSONSchemaKeyword(), not.toJSONSchema());

		return obj;
	}

	@Override
	public Assertion not() {
		return not.not();
	}

	@Override
	public Assertion notElimination() {
		return this.not().notElimination();
	}

	@Override
	public String toGrammarString() {
		return String.format(GrammarStringDefinitions.NOT, not.toGrammarString());
	}
}
