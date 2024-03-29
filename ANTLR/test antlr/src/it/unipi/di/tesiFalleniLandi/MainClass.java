package it.unipi.di.tesiFalleniLandi;

import java.io.IOException;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.Parser;
import it.unipi.di.tesiFalleniLandi.antlr4.*;

public class MainClass {
	public static void main(String[] args) throws IOException {
		System.out.println("Hello world!");
		CharStream input = CharStreams.fromStream(System.in); //inserisci input e poi premi CTRL+D
		
		long start = System.currentTimeMillis();
		
        GrammaticaLexer lexer = new GrammaticaLexer(input);        
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammaticaParser parser = new GrammaticaParser(tokens);
        
        //parser.addParseListener(new GrammaticaBaseListener_impl());
        
        ParseTree tree =  parser.assertion_list();
        Parser p = new Parser();
        And_Assertion schema = (And_Assertion) p.visit(tree);
        
		System.out.println("Tempo di esecuzione [ms]: "+ (System.currentTimeMillis()-start));
		
		System.out.println(tree.toStringTree(parser));
		System.out.println(schema.toString());
		
		//System.out.println(tree.getChild(arg0));
	}
}
