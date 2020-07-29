package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessMof;

public class Mof_Assertion implements Assertion{
	private Number mof;
	
	public Mof_Assertion(Object mof) {
		this.mof = (Number) mof;
	}

	@Override
	public String toString() {
		return "Mof_Assertion [" + mof + "]";
	}

	@Override
	public JsonElement toJSONSchema() {
		JsonObject obj = new JsonObject();
		obj.addProperty("multipleOf", mof);

		return obj;
	}

	@Override
	public Assertion not() {
		AllOf_Assertion notMof = new AllOf_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add(FullAlgebraString.TYPE_NUMBER);
		notMof.add(type);
		notMof.add(new NotMof_Assertion(mof));
		return notMof;
	}

	@Override
	public Assertion notElimination() {
		return new Mof_Assertion(mof);
	}

	@Override
	public String toGrammarString() {
		return FullAlgebraString.MULTIPLEOF(mof.toString());
	}

	@Override
	public WitnessMof toWitnessAlgebra() {
		return new WitnessMof(Double.parseDouble(mof.toString()));
	}


}
