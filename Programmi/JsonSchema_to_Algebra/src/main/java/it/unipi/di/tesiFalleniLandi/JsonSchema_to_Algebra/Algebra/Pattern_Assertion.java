package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public class Pattern_Assertion implements Assertion{
	private String value;
	
	public Pattern_Assertion() {	}

	public Pattern_Assertion(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Pattern_Assertion [" + value + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "pattern";
	}

	@Override
	public Object toJSONSchema() {
		return value;
	}
	
	
	
}
