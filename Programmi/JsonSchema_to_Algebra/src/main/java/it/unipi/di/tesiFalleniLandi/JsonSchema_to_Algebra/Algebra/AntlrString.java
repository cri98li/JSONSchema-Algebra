package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public class AntlrString extends AntlrValue{
	
	String value;
	
	public AntlrString() {
		
	}
	
	public AntlrString(String str) {
		value = str;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "AntlrString [" + value + "]";
	}
	
	
}
