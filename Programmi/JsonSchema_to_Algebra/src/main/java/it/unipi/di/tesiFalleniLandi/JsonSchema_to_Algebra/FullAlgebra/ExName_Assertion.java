package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessBoolean;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessPattReq;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExName_Assertion implements Assertion{
	private Assertion names;

	private static Logger logger = LogManager.getLogger(ExName_Assertion.class);

	public ExName_Assertion(Assertion names) {
		this.names = names;
		logger.trace("Created a new ExNames_Assertion: {}", this);
	}

	@Override
	public String toString() {
		return "Names_Assertion [" + names + "]";
	}

	@Override
	public JsonElement toJSONSchema() {
		return new Not_Assertion(new Names_Assertion(names)).toJSONSchema();
	}
	
	@Override
	public Assertion not() {
		AllOf_Assertion and = new AllOf_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add(FullAlgebraString.TYPE_OBJECT);
		and.add(type);
		if(names.not() != null)
			and.add(new Names_Assertion(names.not()));
		
		return and;
	}

	@Override
	public Assertion notElimination() {
		return new ExName_Assertion(names.notElimination());
	}

	@Override
	public String toGrammarString() {
		return FullAlgebraString.EXNAME(names.toGrammarString());
	}

	@Override
	public WitnessAssertion toWitnessAlgebra() {
		return WitnessPattReq.build(Utils_PattOfS.pattOfS(names), new WitnessBoolean(true));
	}
}
