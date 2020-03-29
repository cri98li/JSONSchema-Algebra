package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public class Names_Assertion implements Assertion{
	private Assertion s;
	
	public Names_Assertion(Assertion s) {
		this.s = s;
	}

	@Override
	public String toString() {
		return "Names_Assertion [" + s + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "propertyNames";
	}

	@Override
	public Object toJSONSchema() {
		return s.toJSONSchema();
	}
	
	
}
