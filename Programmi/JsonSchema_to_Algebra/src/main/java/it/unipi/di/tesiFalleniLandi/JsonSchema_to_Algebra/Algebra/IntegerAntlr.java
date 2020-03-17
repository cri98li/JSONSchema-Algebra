package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public class IntegerAntlr implements Assertion{

	Integer value;
	
	public IntegerAntlr() {
		
	}
	
	public IntegerAntlr(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
}
