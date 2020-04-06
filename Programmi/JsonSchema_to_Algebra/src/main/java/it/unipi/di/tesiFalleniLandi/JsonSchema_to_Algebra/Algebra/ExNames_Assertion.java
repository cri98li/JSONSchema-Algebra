package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public class ExNames_Assertion implements Assertion{
	private Assertion names;
	
	public ExNames_Assertion(Assertion names) {
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
	
	@Override
	public Assertion not() {
		And_Assertion and = new And_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add("obj");
		and.add(type);
		and.add(new Names_Assertion(names.not()));
		
		return and;
	}
}
