package it.unipi.di.tesiFalleniLandi;

import java.util.List;

import it.unipi.di.tesiFalleniLandi.antlr4.GrammaticaParser;
import it.unipi.di.tesiFalleniLandi.antlr4.GrammaticaParser.SContext;

public class Between_Assertion extends S{
	public int min, max;
	
	public Between_Assertion() {
		
	}
	
	@Override
	public Between_Assertion visitBetweenAssertion(GrammaticaParser.BetweenAssertionContext ctx) {
		Between_Assertion bet = new Between_Assertion();
		
		//System.out.println(ctx.between().value(0));
		//visit(ctx.between());
		bet.min = Integer.valueOf(ctx.between().value(0).getText());
		bet.max = Integer.valueOf(ctx.between().value(1).getText());
		
		return bet;
	}
	@Override
	public String toString() {
		return "Between_Assertion [min=" + min + "\\r\\n max=" + max + "]";
	}
	
	
}
