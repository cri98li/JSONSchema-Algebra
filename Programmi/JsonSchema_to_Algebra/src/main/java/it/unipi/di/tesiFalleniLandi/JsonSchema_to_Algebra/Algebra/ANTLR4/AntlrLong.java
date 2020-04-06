package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4;

public class AntlrLong implements AntlrValue{

	private Long value;
	
	public AntlrLong() {	}
	
	public AntlrLong(Long value) {
		this.value = value;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}	
}
