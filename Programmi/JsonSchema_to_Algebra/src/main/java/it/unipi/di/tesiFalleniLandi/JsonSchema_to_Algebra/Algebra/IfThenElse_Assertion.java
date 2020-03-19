package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public class IfThenElse_Assertion implements Assertion{
	private And_Assertion ifStatement, thenStatement, elseStatement;

	public IfThenElse_Assertion(And_Assertion ifStatement, And_Assertion thenStatement, And_Assertion elseStatement) {
		super();
		this.ifStatement = ifStatement;
		this.thenStatement = thenStatement;
		this.elseStatement = elseStatement;
	}

	@Override
	public String toString() {
		return "IfThenElse_Assertion [ifStatement=" + ifStatement + ", thenStatement=" + thenStatement
				+ ", elseStatement=" + elseStatement + "]";
	}
	
}
