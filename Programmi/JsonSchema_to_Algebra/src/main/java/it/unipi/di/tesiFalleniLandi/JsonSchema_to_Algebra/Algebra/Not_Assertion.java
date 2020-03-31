package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

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
	public String getJSONSchemaKeyword() {
		return "not";
	}

	@Override
	public Object toJSONSchema() {
		return not.toJSONSchema();
	}
}
