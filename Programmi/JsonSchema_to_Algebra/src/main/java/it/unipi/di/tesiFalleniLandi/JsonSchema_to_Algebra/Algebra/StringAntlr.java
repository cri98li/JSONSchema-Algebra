package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public class StringAntlr implements Assertion{
	
	String value;
	
	public StringAntlr() {
		
	}
	
	public StringAntlr(String str) {
		value = str;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
