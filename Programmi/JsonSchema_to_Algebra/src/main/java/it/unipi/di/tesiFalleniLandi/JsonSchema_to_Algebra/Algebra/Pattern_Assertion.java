package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

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
	public String getJSONSchemaKeyword() {
		return "pattern";
	}

	@Override
	public Object toJSONSchema() {
		return pattern;
	}
}
