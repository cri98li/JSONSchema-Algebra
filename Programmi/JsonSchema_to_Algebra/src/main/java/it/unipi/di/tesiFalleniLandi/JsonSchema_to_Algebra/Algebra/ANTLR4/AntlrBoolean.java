package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4;

public class AntlrBoolean extends AntlrValue{

	private boolean value;
	
	public AntlrBoolean(boolean value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "AntlrBoolean [" + value + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object toJSONSchema() {
		return value;
	}

	@Override
	public Object getValue() {
		return value;
	}

}
