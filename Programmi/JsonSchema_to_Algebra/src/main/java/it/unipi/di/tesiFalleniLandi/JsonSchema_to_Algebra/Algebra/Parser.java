package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.List;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaBaseVisitor;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaParser;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaParser.AssertionContext;

public class Parser extends GrammaticaBaseVisitor<Assertion>{


	@Override 
	public And_Assertion visitNewList(GrammaticaParser.NewListContext ctx) { 
		And_Assertion schema = (And_Assertion) visit(ctx.assertion_list());
		return schema;
	}
	
	@Override 
	public And_Assertion visitList(GrammaticaParser.ListContext ctx) {
		
		System.out.println("apro array: ");
		And_Assertion list = new And_Assertion();
		
		List<AssertionContext> keywords = ctx.assertion();
		
		for(AssertionContext key : keywords) {
			Assertion schema = visit(key);
			list.add(schema);
		}
		
		return list; 
	}
	
	public And_Assertion visit(GrammaticaParser.All_of_assertionContext ctx) {
		
		System.out.println("apro array: ");
		And_Assertion list = new And_Assertion();
		
		List<AssertionContext> keywords = ctx.assertion();
		
		for(AssertionContext key : keywords) {
			Assertion schema = visit(key);
			list.add(schema);
		}
		
		return list; 
	}
	
	@Override
	public Bet_Assertion visitBetweenAssertion(GrammaticaParser.BetweenAssertionContext ctx) {
		int min = Integer.valueOf(ctx.between_assertion().numeric_value(0).getText());
		int max = Integer.valueOf(ctx.between_assertion().numeric_value(1).getText());
		
		return new Bet_Assertion(min, max);
	}
	@Override
	public Type_Assertion visitTypeAssertion(GrammaticaParser.TypeAssertionContext ctx) { 
		System.out.println("type");
		String type = ctx.getText();
		
		return new Type_Assertion(type); 
	}
	
	@Override
	public Not_Assertion visitNot_assertion(GrammaticaParser.Not_assertionContext ctx) {
		Not_Assertion not = new Not_Assertion(visit(ctx.assertion_list()));
		
		return not;
	}
	
	
}
