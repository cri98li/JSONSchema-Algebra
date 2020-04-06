package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public class RepeatedItems_Assertion implements Assertion{
	public RepeatedItems_Assertion() {
	}

	@Override
	public String toString() {
		return "UniqueItems_Assertion";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "uniqueItems";
	}

	@Override
	public Boolean toJSONSchema() {
		return true;
	}

	@Override
	public Assertion not() {
		return new UniqueItems_Assertion();
	}
}
