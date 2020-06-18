package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAnd;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessOr;
import org.json.simple.JSONObject;
import patterns.REException;

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
	//NOT: (S1 ∧ ¬S2)
	//NOT: (not(A) or not(B)) and (A or not(C)) --? if A then not(C) else not(B) ???
	@Override
	public Assertion not() {	
		AllOf_Assertion and = new AllOf_Assertion();

		//if-then-else
		if(elseStatement != null) {
			AnyOf_Assertion orThen = new AnyOf_Assertion();
			AnyOf_Assertion orElse = new AnyOf_Assertion();
			orThen.add(ifStatement.not());
			orThen.add(thenStatement.not());
			and.add(orThen);
			orElse.add(ifStatement);
			orElse.add(elseStatement.not());
			and.add(orElse);
		}else{ //if-then
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

		if(ifStat == null) return thenStat; //if null == true ??
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

	@Override
	public WitnessAssertion toWitnessAlgebra() throws REException {
		WitnessOr or = new WitnessOr();
		if(elseStatement == null){
			or.add(ifStatement.not().toWitnessAlgebra());
			or.add(thenStatement.toWitnessAlgebra());
			return or;
		}

		WitnessAnd and = new WitnessAnd();
		and.add(ifStatement.toWitnessAlgebra());
		and.add(thenStatement.toWitnessAlgebra());

		WitnessAnd and2 = new WitnessAnd();
		and2.add(ifStatement.not().toWitnessAlgebra());
		and2.add(elseStatement.toWitnessAlgebra());

		or.add(and);
		or.add(and2);

		return or;
	}

}
