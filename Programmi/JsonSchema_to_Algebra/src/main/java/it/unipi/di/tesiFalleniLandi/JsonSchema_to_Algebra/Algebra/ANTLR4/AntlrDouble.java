package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4;

public class AntlrDouble extends AntlrValue{
	private Double value;
	
	public AntlrDouble(Double value) {
		this.value = value;
	}
	
	@Override
	public String getJSONSchemaKeyword() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object toJSONSchema() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object getValue() {
		return value;
	}

}
