package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public class Type_Assertion implements Assertion{
	private String type;
	
	public Type_Assertion(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Type_Assertion [" + type + "]";
	}
	
	
}
  