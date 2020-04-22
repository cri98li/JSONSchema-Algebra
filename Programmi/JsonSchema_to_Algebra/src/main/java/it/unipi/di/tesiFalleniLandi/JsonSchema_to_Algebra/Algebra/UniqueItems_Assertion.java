package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class UniqueItems_Assertion implements Assertion{
	
	public UniqueItems_Assertion() {
	}

	@Override
	public String toString() {
		return "UniqueItems_Assertion";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "uniqueItems";
	}

	@Override
	public Boolean toJSONSchema() {
		return true;
	}

	@Override
	public Assertion not() {
		return new RepeatedItems_Assertion();
	}

	@Override
	public Assertion notElimination() {
		// TODO Auto-generated method stub
		return new UniqueItems_Assertion();
	}

	@Override
	public String toGrammarString() {
		return GrammarStringDefinitions.UNIQUEITEMS;
	}
	
}
