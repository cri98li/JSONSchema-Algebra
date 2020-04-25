package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.json.simple.JSONObject;

public class Pattern_Assertion implements Assertion{
	private String pattern;
	
	public Pattern_Assertion() {	}

	public Pattern_Assertion(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public String toString() {
		return "Pattern_Assertion [" + pattern + "]";
	}

	@Override
	public Object toJSONSchema() {
		JSONObject obj = new JSONObject();
		obj.put("pattern", pattern);

		return obj;
	}

	@Override
	public Assertion not() {
		return new NotPattern_Assertion(pattern);
	}

	@Override
	public Assertion notElimination() {
		return new Pattern_Assertion(pattern);
	}

	@Override
	public String toGrammarString() {
		return String.format(GrammarStringDefinitions.PATTERN, pattern);
	}
}
