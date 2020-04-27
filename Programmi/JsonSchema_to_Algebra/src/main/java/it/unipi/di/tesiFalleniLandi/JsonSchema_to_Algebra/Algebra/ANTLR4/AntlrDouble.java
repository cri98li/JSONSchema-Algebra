package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4;

public class AntlrDouble implements AntlrValue{
	private Double value;
	
	public AntlrDouble(Double value) {
		this.value = value;
	}

	@Override
	public Double getValue() {
		return value;
	}

}
