package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import org.json.simple.JSONObject;

public class IfThenElse_Assertion implements Assertion{
	private Assertion ifStatement, thenStatement, elseStatement;

	public IfThenElse_Assertion(Assertion ifStatement, Assertion thenStatement, Assertion elseStatement) {
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

	@Override
	public String getJSONSchemaKeyword() {
		return "ifThenElse";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object toJSONSchema() {
		JSONObject obj = new JSONObject();
			
		if(ifStatement != null) obj.put("if", ifStatement.toJSONSchema());
		if(thenStatement != null) obj.put("then", thenStatement.toJSONSchema());
		if(elseStatement != null) obj.put("else", elseStatement.toJSONSchema());
			
		return obj;
	}
	
}
