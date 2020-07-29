package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessPattern;

public class NotPattern_Assertion implements Assertion{
	private ComplexPattern notPattern;

	public NotPattern_Assertion(ComplexPattern pattern) {
		this.notPattern = pattern;
	}

	@Override
	public String toString() {
		return "NotPattern_Assertion [" + notPattern + "]";
	}

	@Override
	public JsonElement toJSONSchema() {
		Type_Assertion t = new Type_Assertion();
		t.add(FullAlgebraString.TYPE_STRING);
		return new IfThenElse_Assertion(t, new Not_Assertion(new Pattern_Assertion(notPattern)), null).toJSONSchema();

	}

	@Override
	public Assertion not() {
		AllOf_Assertion and = new AllOf_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add(FullAlgebraString.TYPE_STRING);
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
		return FullAlgebraString.NOTPATTERN(notPattern.getAlgebraString());
	}

	@Override
	public WitnessAssertion toWitnessAlgebra() {
		return new WitnessPattern(notPattern.complement());
	}
}
