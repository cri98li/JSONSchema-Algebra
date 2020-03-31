package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public class Mof_Assertion implements Assertion{
	private Long mof;
	
	public Mof_Assertion(Long mof) {
		this.mof = mof;
	}

	@Override
	public String toString() {
		return "Mof_Assertion [" + mof + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "multipleOf";
	}

	@Override
	public Object toJSONSchema() {
		return mof;
	}
	
	
}
