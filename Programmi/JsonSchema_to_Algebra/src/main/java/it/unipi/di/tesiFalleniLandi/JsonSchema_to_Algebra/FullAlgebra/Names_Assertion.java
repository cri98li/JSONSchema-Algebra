package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.AlgebraStrings;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

public class Names_Assertion implements Assertion{
	private Assertion names;

	private static Logger logger = LogManager.getLogger(Names_Assertion.class);
	
	public Names_Assertion(Assertion names) {
		this.names = names;
		logger.trace("Created a new Names_Assertion: {}", this);

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
		type.add(AlgebraStrings.TYPE_OBJECT);
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
		return AlgebraStrings.PROPERTYNAMES(names.toGrammarString());
	}

	@Override
	public WitnessAssertion toWitnessAlgebra() throws REException {
		Properties_Assertion pro = new Properties_Assertion();
		pro.addPatternProperties(Utils_PattOfS.pattOfS(names.not()), new Boolean_Assertion(false));
		return pro.toWitnessAlgebra();
	}

}
