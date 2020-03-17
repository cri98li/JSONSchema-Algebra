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
		And_Assertion list = new And_Assertion();
		
		List<AssertionContext> keywords = ctx.assertion();
		
		for(AssertionContext key : keywords) {
			Assertion schema = visit(key);
			list.add(schema);
		}
		
		return list; 
	}
	
	@Override
	public Bet_Assertion visitNewBetweenAssertion(GrammaticaParser.NewBetweenAssertionContext ctx) {
		
		Bet_Assertion bet = (Bet_Assertion) visit(ctx.between_assertion());

		return bet;
	}
	
	@Override
	public Bet_Assertion visitParseBetweenAssertion(GrammaticaParser.ParseBetweenAssertionContext ctx) {
		
		IntegerAntlr min = (IntegerAntlr) visit(ctx.numeric_value(0));
		IntegerAntlr max = (IntegerAntlr) visit(ctx.numeric_value(1));
		
		return new Bet_Assertion(min.getValue(), max.getValue());
	}
	

	@Override 
	public IntegerAntlr visitNumericValue(GrammaticaParser.NumericValueContext ctx) { 
		
		return new IntegerAntlr(Integer.valueOf(ctx.INT().getText()));
	}
	
	
	@Override
	public IntegerAntlr visitNullValue(GrammaticaParser.NullValueContext ctx) {
		IntegerAntlr value = new IntegerAntlr(null);

		return value;
	}

	@Override
	public Type_Assertion visitNewTypeAssertion(GrammaticaParser.NewTypeAssertionContext ctx) {  
		StringAntlr type = (StringAntlr) visit(ctx.type_assertion());
		
		return new Type_Assertion(type.getValue()); 
	}
	
	@Override
	public StringAntlr visitParseTypeAssertion(GrammaticaParser.ParseTypeAssertionContext ctx) { 
		String type = ctx.getText();
		
		return new StringAntlr(type); 
	}
	
	@Override
	public Not_Assertion visitNewNot(GrammaticaParser.NewNotContext ctx) {
		
		return (Not_Assertion) visit(ctx.not_assertion());
	}
	
	@Override
	public Not_Assertion visitParseNot(GrammaticaParser.ParseNotContext ctx) {
		
		return new Not_Assertion(visit(ctx.assertion_list()));
	}
	
	
}
