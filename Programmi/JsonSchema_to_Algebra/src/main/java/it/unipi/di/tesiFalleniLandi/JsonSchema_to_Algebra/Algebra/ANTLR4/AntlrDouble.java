package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4;

public class AntlrDouble implements AntlrValue{
	private Object value;
	
	public AntlrDouble(Object value) {
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

}
