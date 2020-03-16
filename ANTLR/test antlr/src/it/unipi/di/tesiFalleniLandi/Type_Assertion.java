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
	public String toString() {
		return "Type_Assertion [type=" + type + "]";
	}
	
	
}
  