package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import com.google.gson.JsonObject;

public class IfThenElse_Assertion implements Assertion{
	private Assertion ifStatement, thenStatement, elseStatement;

	public IfThenElse_Assertion(Assertion ifStatement, Assertion thenStatement, Assertion elseStatement) {
		this.ifStatement = ifStatement;
		this.thenStatement = thenStatement;
		this.elseStatement = elseStatement;
	}

	@Override
	public String toString() {
		return "IfThenElse_Assertion [ifStatement=" + ifStatement + ", thenStatement=" + thenStatement
				+ ", elseStatement=" + elseStatement + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "ifThenElse";
	}

	@Override
	public JsonObject toJSONSchema() {
		JsonObject obj = new JsonObject();
			
		if(ifStatement != null) obj.put("if", ifStatement.toJSONSchema());
		if(thenStatement != null) obj.put("then", thenStatement.toJSONSchema());
		if(elseStatement != null) obj.put("else", elseStatement.toJSONSchema());
			
		return obj;
	}
	
}
