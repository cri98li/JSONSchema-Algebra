package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class NotMof_Assertion implements Assertion {
	private Long notMof;
	
	public NotMof_Assertion(Long notMof) {
		this.notMof = notMof;
	}

	@Override
	public String toString() {
		return "NotMof_Assertion [" + notMof + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "multipleOf";
	}

	@Override
	public Object toJSONSchema() {
		return notMof;
	}

	@Override
	public Assertion not() {
		And_Assertion mof = new And_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add("num");
		mof.add(type);
		mof.add(new Mof_Assertion(notMof));
		
		return mof;
	}

	@Override
	public Assertion notElimination() {
		return new NotMof_Assertion(notMof);
	}

	@Override
	public String toGrammarString() {
		return String.format(GrammarStringDefinitions.NOTMULTIPLEOF, notMof);
	}
}
