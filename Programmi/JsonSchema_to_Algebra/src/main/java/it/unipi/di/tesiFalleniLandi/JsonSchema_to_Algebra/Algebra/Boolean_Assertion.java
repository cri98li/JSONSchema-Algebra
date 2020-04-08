package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public class Boolean_Assertion implements Assertion{
	private boolean value;
	
	public Boolean_Assertion(boolean value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Boolean_Assertion [value=" + value + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object toJSONSchema() {
		return value;
	}

	@Override
	public Assertion not() {
		return new Boolean_Assertion(!value);
	}

	@Override
	public Assertion notElimination() {
		return new Boolean_Assertion(value);
	}

	@Override
	public String toGrammarString() {
		return value+"";
	}

}
