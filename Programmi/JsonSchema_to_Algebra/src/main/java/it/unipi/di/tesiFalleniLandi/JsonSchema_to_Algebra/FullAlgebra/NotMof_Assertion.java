package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessNotMof;

public class NotMof_Assertion implements Assertion {
	private Object notMof;
	
	public NotMof_Assertion(Object notMof) {
		this.notMof = notMof;
	}

	@Override
	public String toString() {
		return "NotMof_Assertion [" + notMof + "]";
	}

	@Override
	public Object toJSONSchema() {
		return new Not_Assertion(new Mof_Assertion(notMof)).toJSONSchema();
	}

	@Override
	public Assertion not() {
		AllOf_Assertion mof = new AllOf_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add(GrammarStringDefinitions.TYPE_NUMBER);
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

	@Override
	public WitnessNotMof toWitnessAlgebra() {
		return new WitnessNotMof(Double.parseDouble(notMof.toString()));
	}
}
