package it.unipi.di.tesiFalleniLandi;

import it.unipi.di.tesiFalleniLandi.antlr4.GrammaticaBaseVisitor;
import it.unipi.di.tesiFalleniLandi.antlr4.GrammaticaParser;
import it.unipi.di.tesiFalleniLandi.antlr4.GrammaticaParser.ValueContext;

public class Type_Assertion extends S{
	private String type;
	
	public Type_Assertion(String type) {
		this.type = type;
	}
	
	@Override
	public Type_Assertion visitTypeAssertion(GrammaticaParser.TypeAssertionContext ctx) { 
		System.out.println("type");
		String type = ctx.getText();
		
		return new Type_Assertion(type); 
	}
	
	@Override
	public String toString() {
		return "Type_Assertion [type=" + type + "]";
	}
	
	
}
  