package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessVar;
import org.json.simple.JSONObject;

public class Ref_Assertion implements Assertion{
	private String ref;
	
	public Ref_Assertion(String ref) {
		this.ref = ref;
	}
	
	@Override
	public String toString() {
		return "Ref_Assertion [" + ref + "]";
	}

	@Override
	public JSONObject toJSONSchema() {
		JSONObject obj = new JSONObject();

		if(Defs_Assertion.env != null && ref.equals(Defs_Assertion.env.getRootName())) {
			obj.put("ref", "#" + ref);
		}else {
			obj.put("ref", "#/$defs/" + ref);
		}

		return obj;
	}

	@Override
	public Assertion not() {
		if(ref.startsWith(GrammarStringDefinitions.NOT_DEFS))
			return new Ref_Assertion(ref.substring(GrammarStringDefinitions.NOT_DEFS.length()));
		else  return new Ref_Assertion(GrammarStringDefinitions.NOT_DEFS+ref);
	}

	@Override
	public Assertion notElimination() {
		return new Ref_Assertion(ref);
	}
	
	@Override
	public String toGrammarString() {
		return String.format(GrammarStringDefinitions.REF, ref);
	}

	@Override
	public WitnessAssertion toWitnessAlgebra() {
		return new WitnessVar(ref);
	}

	public String getRef(){
		return ref;
	}
}
