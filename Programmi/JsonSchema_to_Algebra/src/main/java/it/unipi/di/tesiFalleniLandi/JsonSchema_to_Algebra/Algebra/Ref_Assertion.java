package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
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

		if(ref.equals("rootdef")) {
			obj.put("ref", "#" + ref);
		}else {
			obj.put("ref", "#/$defs/" + ref);
		}

		return obj;
	}


	@Override
	public Assertion not() {
		return new Ref_Assertion(GrammarStringDefinitions.NOT_DEFS+ref);
	}


	@Override
	public Assertion notElimination() {
		return new Ref_Assertion(ref);
	}
	
	@Override
	public String toGrammarString() {
		return String.format(GrammarStringDefinitions.REF, ref);
	}

}
