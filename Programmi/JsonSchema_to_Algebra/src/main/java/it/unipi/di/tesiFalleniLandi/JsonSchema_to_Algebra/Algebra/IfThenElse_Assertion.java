package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
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

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONSchema() {
		JSONObject obj = new JSONObject();

		if(ifStatement != null)
			obj.put("if", ifStatement.toJSONSchema());

		if(thenStatement != null)
			obj.put("then", thenStatement.toJSONSchema());

		if(elseStatement != null)
			obj.put("else", elseStatement.toJSONSchema());

			
		return obj;
	}

	//if A then B else C --> (A and B) or (not(a) and C)
	//(S1 ∧ S2) ∨ ((¬S1) ∧ S3)
	//(¬S1) ∨ (S2)
	//NEGATO: (S1 ∧ ¬S2)
	//NEGATO: (not(A) or not(B)) and (A or not(C)) --? if A then not(C) else not(B) ???
	@Override
	public Assertion not() {	
		And_Assertion and = new And_Assertion();

		//caso if-then-else
		if(elseStatement != null) {
			Or_Assertion orThen = new Or_Assertion();
			Or_Assertion orElse = new Or_Assertion();
			orThen.add(ifStatement.not());
			orThen.add(thenStatement.not());
			and.add(orThen);
			orElse.add(ifStatement);
			orElse.add(elseStatement.not());
			and.add(orElse);
		}else{
			and.add(ifStatement);
			and.add(thenStatement.not());
		}

		return and;
	}

	@Override
	public Assertion notElimination() {
		Assertion ifStat = ifStatement.notElimination();
		Assertion thenStat = thenStatement.notElimination();
		Assertion elseStat = null;
		if(elseStatement != null) elseStat = elseStatement.notElimination();
		
		return new IfThenElse_Assertion(ifStat, thenStat, elseStat);
	}

	@Override
	public String toGrammarString() {
		String if_str = "", then_str = "", else_str = "";
		if(ifStatement != null) { 
			if_str = ifStatement.toGrammarString();
			then_str = thenStatement.toGrammarString();
		}
		if(elseStatement != null)
			else_str = elseStatement.toGrammarString();
		else
			return String.format(GrammarStringDefinitions.IF_THEN, if_str, then_str);
		
		return String.format(GrammarStringDefinitions.IF_THEN_ELSE, if_str, then_str, else_str);
	}
	
}
