package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.MyPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.PosixPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessPattern;
import org.json.simple.JSONObject;

public class Pattern_Assertion implements Assertion{
	private PosixPattern pattern;

	public Pattern_Assertion(PosixPattern pattern) {
		this.pattern = pattern;
	}

	@Override
	public String toString() {
		return "Pattern_Assertion [" + pattern + "]";
	}

	public PosixPattern getValue(){
		return pattern;
	}

	@Override
	public Object toJSONSchema() {
		JSONObject obj = new JSONObject();
		obj.put("pattern", pattern.toString());

		return obj;
	}

	@Override
	public Assertion not() {
		AllOf_Assertion and = new AllOf_Assertion();
		and.add(new NotPattern_Assertion(pattern));
		Type_Assertion type = new Type_Assertion();
		type.add(GrammarStringDefinitions.TYPE_STRING);
		and.add(type);
		return and;
	}

	@Override
	public Assertion notElimination() {
		return new Pattern_Assertion(pattern);
	}

	@Override
	public String toGrammarString() {
		return String.format(GrammarStringDefinitions.PATTERN, pattern);
	}

	@Override
	public WitnessAssertion toWitnessAlgebra() {
		return new WitnessPattern(pattern);
	}
}
