package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public class Not_Assertion implements Assertion{
	
	private Assertion value;
	
	public Not_Assertion(Assertion a) {
		value = a;
	}

	@Override
	public String toString() {
		return "Not_Assertion [" + value + "]";
	}
	
	@Override
	public String getJSONSchemaKeyword() {
		return "not";
	}

	@Override
	public Object toJSONSchema() {
		return value.toJSONSchema();
	}
}
