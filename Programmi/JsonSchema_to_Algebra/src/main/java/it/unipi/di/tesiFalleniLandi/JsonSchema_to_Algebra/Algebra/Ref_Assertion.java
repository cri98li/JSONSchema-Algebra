package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

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
	public String getJSONSchemaKeyword() {
		return "$ref";
	}

	@Override
	public String toJSONSchema() {
		if(ref.equals("rootdef")) {
			return "#";
		}else {
			return "#/$defs/"+ref;
		}
	}


	@Override
	public Assertion not() {
		return new Ref_Assertion("not_"+ref);
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
