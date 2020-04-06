package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public class NotMof_Assertion implements Assertion {
	private Long notMof;
	
	public NotMof_Assertion(Long notMof) {
		this.notMof = notMof;
	}

	@Override
	public String toString() {
		return "Mof_Assertion [" + notMof + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "multipleOf";
	}

	@Override
	public Object toJSONSchema() {
		return notMof;
	}

	@Override
	public Assertion not() {
		And_Assertion mof = new And_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add("num");
		mof.add(type);
		mof.add(new Mof_Assertion(notMof));
		return mof;
	}
}
