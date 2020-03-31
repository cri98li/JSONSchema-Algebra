package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public class Names_Assertion implements Assertion{
	private Assertion names;
	
	public Names_Assertion(Assertion names) {
		this.names = names;
	}

	@Override
	public String toString() {
		return "Names_Assertion [" + names + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "propertyNames";
	}

	@Override
	public Object toJSONSchema() {
		return names.toJSONSchema();
	}
}
