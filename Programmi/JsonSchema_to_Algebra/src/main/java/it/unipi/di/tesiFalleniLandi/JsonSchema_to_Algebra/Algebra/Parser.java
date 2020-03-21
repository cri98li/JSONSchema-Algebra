package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaBaseVisitor;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaParser;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaParser.AssertionContext;

public class Parser extends GrammaticaBaseVisitor<Assertion>{


	@Override 
	public And_Assertion visitNewList(GrammaticaParser.NewListContext ctx) { 
		 
		return (And_Assertion) visit(ctx.assertion_list());
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
	public Bet_Assertion visitNewXBetweenAssertion(GrammaticaParser.NewXBetweenAssertionContext ctx) {
		
		Bet_Assertion bet = (Bet_Assertion) visit(ctx.xbetween_assertion());

		return bet;
	}
	
	@Override
	public Bet_Assertion visitParseXBetweenAssertion(GrammaticaParser.ParseXBetweenAssertionContext ctx) {
		
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
	
	@Override 
	public And_Assertion visitNewAllOf(GrammaticaParser.NewAllOfContext ctx) { 

		return (And_Assertion) visit(ctx.all_of_assertion()); 
	}
	
	@Override 
	public And_Assertion visitParseAllOf(GrammaticaParser.ParseAllOfContext ctx) {
		
		return (And_Assertion) visit(ctx.assertion_list()); 
	}
	
	@Override 
	public Or_Assertion visitNewAnyOf(GrammaticaParser.NewAnyOfContext ctx) { 

		return (Or_Assertion) visit(ctx.any_of_assertion()); 
	}
	
	@Override 
	public Or_Assertion visitParseAnyOf(GrammaticaParser.ParseAnyOfContext ctx) {
		
		return (Or_Assertion) visit(ctx.assertion_list()); 
	}
	
	@Override 
	public Xor_Assertion visitNewOneOf(GrammaticaParser.NewOneOfContext ctx) { 

		return (Xor_Assertion) visit(ctx.one_of_assertion()); 
	}
	
	@Override 
	public Xor_Assertion visitParseOneOf(GrammaticaParser.ParseOneOfContext ctx) {
		
		return (Xor_Assertion) visit(ctx.assertion_list()); 
	}
	
	@Override 
	public Required_Assertion visitNewRequired(GrammaticaParser.NewRequiredContext ctx) { 
		
		return (Required_Assertion) visit(ctx.required_assertion());
	}
	
	@Override 
	public Required_Assertion visitParseRequired(GrammaticaParser.ParseRequiredContext ctx) {
		Required_Assertion req = new Required_Assertion();
				
		List<TerminalNode> strList = ctx.ID();
		
		for(TerminalNode str : strList) {
			req.add(str.getText());
		}
		
		return req;
	}
	@Override 
	public IfThenElse_Assertion visitNewIfThenElse(GrammaticaParser.NewIfThenElseContext ctx) { 
		
		return (IfThenElse_Assertion) visit(ctx.if_then_else_assertion());
	}
	
	@Override 
	public IfThenElse_Assertion visitParseIfThenElse(GrammaticaParser.ParseIfThenElseContext ctx) { 
		And_Assertion ifStat = (And_Assertion) visit(ctx.assertion_list(0));
		And_Assertion thenStat = (And_Assertion) visit(ctx.assertion_list(1));
		And_Assertion elseStat = (And_Assertion) visit(ctx.assertion_list(2));
		
		return  new IfThenElse_Assertion(ifStat, thenStat, elseStat);
	}
	
	@Override 
	public IfThenElse_Assertion visitParseIfThen(GrammaticaParser.ParseIfThenContext ctx) { 
		And_Assertion ifStat = (And_Assertion) visit(ctx.assertion_list(0));
		And_Assertion thenStat = (And_Assertion) visit(ctx.assertion_list(1));
		
		return  new IfThenElse_Assertion(ifStat, thenStat, null); 
	}
	
	
}
