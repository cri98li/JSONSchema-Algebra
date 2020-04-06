package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

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
		return ref;
	}


	@Override
	public Assertion not() {
		// TODO Auto-generated method stub
		return new Ref_Assertion("not_"+ref);
	}

}
