package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAssertion;
import patterns.REException;

public class Names_Assertion implements Assertion{
	private Assertion names;
	
	public Names_Assertion(Assertion names) {
		this.names = names;
	}

	@Override
	public String toString() {
		return "Names_Assertion [" + names + "]";
	}

	@Override
	public JsonElement toJSONSchema() {
		JsonObject obj = new JsonObject();
		obj.add("propertyNames", names.toJSONSchema());

		return obj;
	}
	
	@Override
	public Assertion not() {
		AllOf_Assertion and = new AllOf_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add(GrammarStringDefinitions.TYPE_OBJECT);
		and.add(type);
		if(names.not() != null)
			and.add(new ExName_Assertion(names.not()));
		
		return and;
	}

	@Override
	public Assertion notElimination() {
		Assertion tmp = names.notElimination();
		
		return new Names_Assertion(tmp);
	}

	@Override
	public String toGrammarString() {
		return String.format(GrammarStringDefinitions.PROPERTYNAMES, names.toGrammarString());
	}

	@Override
	public WitnessAssertion toWitnessAlgebra() throws REException {
		Properties_Assertion pro = new Properties_Assertion();
		pro.addPatternProperties(Utils_PattOfS.pattOfS(names.not()), new Boolean_Assertion(false));
		return pro.toWitnessAlgebra();
	}

}
