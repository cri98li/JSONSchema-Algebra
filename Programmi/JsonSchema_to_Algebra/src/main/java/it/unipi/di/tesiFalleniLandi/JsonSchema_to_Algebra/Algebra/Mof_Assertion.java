package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public class Mof_Assertion implements Assertion{
	private int mof;
	
	public Mof_Assertion(int mof) {
		this.mof = mof;
	}

	@Override
	public String toString() {
		return "Mof_Assertion [" + mof + "]";
	}
	
	
}
