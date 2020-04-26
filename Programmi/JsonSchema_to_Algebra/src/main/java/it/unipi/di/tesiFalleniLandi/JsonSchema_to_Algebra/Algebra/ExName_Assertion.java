package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class ExName_Assertion implements Assertion{
	private Assertion names;
	
	public ExName_Assertion(Assertion names) {
		this.names = names;
	}

	@Override
	public String toString() {
		return "Names_Assertion [" + names + "]";
	}

	@Override
	public Object toJSONSchema() {
		//throw new UnsupportedOperationException();
		return new Not_Assertion(new Names_Assertion(names));
	}
	
	@Override
	public Assertion not() {
		And_Assertion and = new And_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add("obj");
		and.add(type);
		if(names.not() != null)
			and.add(new Names_Assertion(names.not()));
		
		return and;
	}

	@Override
	public Assertion notElimination() {
		// TODO Auto-generated method stub
		return new ExName_Assertion(names.notElimination());
	}

	@Override
	public String toGrammarString() {
		return String.format(GrammarStringDefinitions.EXNAME, names.toGrammarString());
	}
}
