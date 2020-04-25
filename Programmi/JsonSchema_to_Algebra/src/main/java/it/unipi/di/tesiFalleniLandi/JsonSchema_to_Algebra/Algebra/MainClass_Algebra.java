package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.AlgebraParser;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaLexer;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaParser;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTree;
import org.json.simple.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.BitSet;

public class MainClass_Algebra {
	public static void main(String[] args) throws IOException {
        String path = "test_grammatica.txt";
		
        try (Reader reader = new FileReader(path)){
	        GrammaticaLexer lexer = new GrammaticaLexer(CharStreams.fromReader(reader));
	        lexer.addErrorListener(new Errore());
	        CommonTokenStream tokens = new CommonTokenStream(lexer);
	        GrammaticaParser parser = new GrammaticaParser(tokens);
	        parser.addErrorListener(new Errore());
	        
	        ParseTree tree =  parser.assertion();
	        AlgebraParser p = new AlgebraParser();
	        System.out.println(tree.toStringTree(parser));
	        Assertion schema = (Assertion) p.visit(tree);

			System.out.println(schema.toString());
			
			JSONObject JSON = (JSONObject)schema.toJSONSchema();
			System.out.println(JSON.toJSONString());
			
			System.out.println(Utils.beauty(schema.notElimination().toGrammarString()));
        }
	}
}

class Errore implements ANTLRErrorListener {

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s, RecognitionException e) {
		System.out.println("ERRORE RILEVATO syntax: ");
		System.out.println("\triga: "+ i +"\r\n\tcolonna:"+  i1 + "\r\n\t\t"+ s);
	}

	@Override
	public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean b, BitSet bitSet, ATNConfigSet atnConfigSet) {
		System.out.println("ERRORE RILEVATO ambiguo");
		System.out.println("\triga: "+ i +"\r\n\tcolonna:"+  i1 + "\r\n\t\t"+ atnConfigSet.getAlts().toString());
	}

	@Override
	public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitSet, ATNConfigSet atnConfigSet) {
		System.out.println("ERRORE RILEVATO full context");
		System.out.println("\triga: "+ i +"\r\n\tcolonna:"+  i1 + "\r\n\t\t"+ bitSet);
	}

	@Override
	public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atnConfigSet) {
		System.out.println("ERRORE RILEVATO context sensitivity");
System.out.println("\triga: "+ i +"\r\n\tcolonna:"+  i1 + "\r\n\t\t"+ atnConfigSet);
	}
}
