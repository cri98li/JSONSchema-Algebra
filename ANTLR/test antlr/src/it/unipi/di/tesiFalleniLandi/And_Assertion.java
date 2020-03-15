package it.unipi.di.tesiFalleniLandi;

import java.util.LinkedList;
import java.util.List;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import it.unipi.di.tesiFalleniLandi.antlr4.GrammaticaBaseVisitor;
import it.unipi.di.tesiFalleniLandi.antlr4.GrammaticaLexer;
import it.unipi.di.tesiFalleniLandi.antlr4.GrammaticaParser;
import it.unipi.di.tesiFalleniLandi.antlr4.GrammaticaParser.SContext;

public class And_Assertion extends S {
	private List<S> andList;
	
	public And_Assertion() {
		andList = new LinkedList<>();
	}
	
	public void add(S s) {
		andList.add(s);
	}
	
	
	@Override 
	public And_Assertion visitList(GrammaticaParser.ListContext ctx) {
		
		System.out.println("apro array: ");
		And_Assertion list = new And_Assertion();
		
		List<SContext> keywords = ctx.s();
		
		for(SContext key : keywords) {
			S schema = visit(key);
			list.add(schema);
		}
		
		return list; 
	}

	@Override
	public String toString() {
		return "And_Assertion [andList=" + andList + "]";
	}

	
}
