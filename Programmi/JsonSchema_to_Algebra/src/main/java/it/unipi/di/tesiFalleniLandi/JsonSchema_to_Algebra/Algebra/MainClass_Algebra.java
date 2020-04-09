package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.json.simple.JSONObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaLexer;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaParser;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.AlgebraParser;

public class MainClass_Algebra {
	public static void main(String[] args) throws IOException {
        String path = "test_grammatica.txt";
		
        try (Reader reader = new FileReader(path)){
	        GrammaticaLexer lexer = new GrammaticaLexer(CharStreams.fromReader(reader));        
	        CommonTokenStream tokens = new CommonTokenStream(lexer);
	        GrammaticaParser parser = new GrammaticaParser(tokens);
	        
	        ParseTree tree =  parser.assertion();
	        AlgebraParser p = new AlgebraParser();
	        System.out.println(tree.toStringTree(parser));
	        Assertion schema = (Assertion) p.visit(tree);
			
			System.out.println(schema.toString());
			
			JSONObject JSON = (JSONObject)schema.toJSONSchema();
			System.out.println(JSON.toJSONString());
			
			//System.out.println(Utils.beauty(schema.notElimination().toGrammarString()));
        }
	}
}
