package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public class Properties_Assertion implements Assertion{
	
	private String key;
	private Assertion value;
	
	public Properties_Assertion() {
		
	}

	@Override
	public String toString() {
		return "Properties_Assertion [key=" + key + ", value=" + value + "]";
	}
	
	
}
