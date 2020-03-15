

import it.unipi.di.tesiFalleniLandi.antlr4.GrammaticaBaseVisitor;
import it.unipi.di.tesiFalleniLandi.antlr4.GrammaticaParser;

public class S extends GrammaticaBaseVisitor<S> {

	@Override
	public Type_Assertion visitTypeAssertion(GrammaticaParser.TypeAssertionContext ctx) { 
		System.out.println("type");
		String type = ctx.getText();
		
		return new Type_Assertion(type); 
	}

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
