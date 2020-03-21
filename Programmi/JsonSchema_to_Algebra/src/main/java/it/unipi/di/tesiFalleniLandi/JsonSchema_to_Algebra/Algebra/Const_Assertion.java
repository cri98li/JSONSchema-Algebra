package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public class Const_Assertion implements Assertion{
	
	private String value;

	public Const_Assertion(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Const_Assertion [value=" + value + "]";
	}
	
	
}
