package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
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

	@Override
	public Assertion not() {
		return not; //tolgo il not
	}

	@Override
	public Assertion notElimination() {
		Assertion not = this.not.not();
		if(not != null)
			return not.notElimination(); //applico il not ai successivi

		return null;
	}

	@Override
	public String toGrammarString() {
		return String.format(GrammarStringDefinitions.NOT, not.toGrammarString());
	}
}
