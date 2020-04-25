package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.json.simple.JSONObject;

public class Mof_Assertion implements Assertion{
	private Object mof;
	
	public Mof_Assertion(Object mof) {
		this.mof = mof;
	}

	@Override
	public String toString() {
		return "Mof_Assertion [" + mof + "]";
	}

	@Override
	public Object toJSONSchema() {
		JSONObject obj = new JSONObject();
		obj.put("multipleOf", mof);

		return obj;
	}

	@Override
	public Assertion not() {
		And_Assertion notMof = new And_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add("num");
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
		return String.format(GrammarStringDefinitions.MULTIPLEOF, mof);
	}
	
	
}
