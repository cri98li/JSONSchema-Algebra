package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

public class Pattern implements JSONSchemaElement{

	private java.util.regex.Pattern pattern;
	
	public Pattern(String str) {
		pattern = java.util.regex.Pattern.compile(str);
	}
	
	
	
	public Pattern() {
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "Pattern [pattern=" + pattern + "]";
	}



	@Override
	public String toJSON() {
		return pattern.pattern();
	}

	@Override
	public String toGrammarString() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Pattern assertionSeparation() {
		Pattern obj = new Pattern();
		
		obj.pattern = pattern;
		
		return obj;
	}

}
