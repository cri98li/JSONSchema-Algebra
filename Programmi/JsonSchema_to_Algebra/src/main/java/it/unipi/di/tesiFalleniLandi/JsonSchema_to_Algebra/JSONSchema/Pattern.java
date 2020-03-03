package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

public class Pattern implements JSONSchemaElement{
	
	
	private java.util.regex.Pattern pattern;
	
	public Pattern(String str) {
		pattern = java.util.regex.Pattern.compile(str);
	}
	
	
	
	@Override
	public String toString() {
		return "Pattern [pattern=" + pattern + "]";
	}



	@Override
	public String toJSONString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toGrammarString() {
		// TODO Auto-generated method stub
		return null;
	}

}
