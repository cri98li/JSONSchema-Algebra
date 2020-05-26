package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.PosixPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessPattern;

public class NotPattern_Assertion implements Assertion{
	private PosixPattern notPattern;

	public NotPattern_Assertion(PosixPattern pattern) {
		this.notPattern = pattern;
	}

	@Override
	public String toString() {
		return "NotPattern_Assertion [" + notPattern + "]";
	}

	@Override
	public Object toJSONSchema() {
		Type_Assertion t = new Type_Assertion();
		t.add(GrammarStringDefinitions.TYPE_STRING);
		return new IfThenElse_Assertion(t, new Not_Assertion(new Pattern_Assertion(notPattern)), null).toJSONSchema();

	}

	@Override
	public Assertion not() {
		AllOf_Assertion and = new AllOf_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add(GrammarStringDefinitions.TYPE_STRING);
		and.add(type);
		and.add(new Pattern_Assertion(notPattern));

		return and;
	}

	@Override
	public Assertion notElimination() {
		return new NotPattern_Assertion(notPattern);
	}
	
	@Override
	public String toGrammarString() {
		return String.format(GrammarStringDefinitions.NOTPATTERN, notPattern);
	}

	@Override
	public WitnessAssertion toWitnessAlgebra() {
		return new WitnessPattern(notPattern);//TODO: complement
	}
}
