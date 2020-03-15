package it.unipi.di.tesiFalleniLandi;

import it.unipi.di.tesiFalleniLandi.antlr4.GrammaticaBaseVisitor;
import it.unipi.di.tesiFalleniLandi.antlr4.GrammaticaParser;

public class S extends GrammaticaBaseVisitor<S> {


	@Override 
	public And_Assertion visitNewList(GrammaticaParser.NewListContext ctx) { 
		And_Assertion schema = (And_Assertion) visit(ctx.assertion_list());
		
		return schema;
	}
	
	
	
	@Override
	public String toString() {
		return "S []";
	}
	
}
