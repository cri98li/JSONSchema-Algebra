package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessPattern;

public class Pattern_Assertion implements Assertion{
	private ComplexPattern pattern;

	public Pattern_Assertion(ComplexPattern pattern) {
		this.pattern = pattern;
	}

	@Override
	public String toString() {
		return "Pattern_Assertion [" + pattern + "]";
	}

	public ComplexPattern getValue(){
		return pattern;
	}

	@Override
	public JsonElement toJSONSchema() {
		JsonObject obj = new JsonObject();
		if(pattern.isComplex()) return pattern.toJSONSchema(); //TODO: devo forse chiuderlo dentro un allOf? come evito i duplicati?
		else obj.addProperty("pattern", pattern.getOriginalPattern());

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
		return String.format(GrammarStringDefinitions.PATTERN, pattern.getAlgebraString());
	}

	@Override
	public WitnessAssertion toWitnessAlgebra() {
		return new WitnessPattern(pattern);
	}
}
