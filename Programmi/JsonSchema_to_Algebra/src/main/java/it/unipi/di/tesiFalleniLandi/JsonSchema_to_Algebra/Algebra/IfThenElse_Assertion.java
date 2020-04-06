package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import org.json.simple.JSONObject;

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

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONSchema() {
		JSONObject obj = new JSONObject();
			
		if(ifStatement != null) obj.put("if", ifStatement.toJSONSchema());
		if(thenStatement != null) obj.put("then", thenStatement.toJSONSchema());
		if(elseStatement != null) obj.put("else", elseStatement.toJSONSchema());
			
		return obj;
	}

	//if A then B else C --> (A and B) or (not(a) and C) 
	//NEGATO: (not(A) or not(B)) and (A or not(C)) --? if A then not(C) else not(B) ???
	@Override
	public Assertion not() {	
		And_Assertion and = new And_Assertion();
		Or_Assertion orThen = new Or_Assertion();
		orThen.add(ifStatement.not());
		orThen.add(thenStatement.not());
		and.add(orThen);
		
		//caso if-then-else
		if(elseStatement != null) {
			Or_Assertion orElse = new Or_Assertion();
			orElse.add(ifStatement);
			orElse.add(elseStatement.not());
			and.add(orElse);
		}
		
		return and;
	}
	
}
