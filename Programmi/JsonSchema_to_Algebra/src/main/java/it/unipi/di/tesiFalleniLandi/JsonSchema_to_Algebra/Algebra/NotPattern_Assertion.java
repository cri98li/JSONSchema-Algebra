package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class NotPattern_Assertion implements Assertion{
	private String notPattern;
	
	public NotPattern_Assertion() {	}

	public NotPattern_Assertion(String pattern) {
		this.notPattern = pattern;
	}

	@Override
	public String toString() {
		return "NotPattern_Assertion [" + notPattern + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "notPattern";
	}

	@Override
	public Object toJSONSchema() {
		return notPattern;
	}

	@Override
	public Assertion not() {
		return new Pattern_Assertion(notPattern);
	}

	@Override
	public Assertion notElimination() {
		return new NotPattern_Assertion(notPattern);
	}
	
	@Override
	public String toGrammarString() {
		return String.format(GrammarStringDefinitions.NOTPATTERN, notPattern);
	}
}
