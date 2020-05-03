package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessBoolean;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessPattReq;

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
		return new Not_Assertion(new Names_Assertion(names)).toJSONSchema();
	}
	
	@Override
	public Assertion not() {
		AllOf_Assertion and = new AllOf_Assertion();
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

	@Override
	public WitnessAssertion toWitnessAlgebra() {
		return new WitnessPattReq(Utils_PattOfS.pattOfS(names), new WitnessBoolean(true));
	}
}
