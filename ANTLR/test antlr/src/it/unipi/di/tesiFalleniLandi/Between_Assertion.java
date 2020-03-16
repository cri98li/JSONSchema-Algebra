package it.unipi.di.tesiFalleniLandi;

import java.util.List;

import it.unipi.di.tesiFalleniLandi.antlr4.GrammaticaBaseVisitor;
import it.unipi.di.tesiFalleniLandi.antlr4.GrammaticaParser;
import it.unipi.di.tesiFalleniLandi.antlr4.GrammaticaParser.SContext;

public class Between_Assertion extends S{
	private int min, max;
	
	public Between_Assertion() {
		
	}
	
	public Between_Assertion(int min, int max) {
		this.min = min;
		this.max = max;
	}
	
	@Override
	public String toString() {
		return "Between_Assertion [min=" + min + "\\r\\n max=" + max + "]";
	}
	
	
}
