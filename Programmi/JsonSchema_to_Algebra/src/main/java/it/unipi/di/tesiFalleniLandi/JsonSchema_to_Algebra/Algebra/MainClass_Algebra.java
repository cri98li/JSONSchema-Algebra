package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaLexer;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaParser;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.JSONSchema;

public class MainClass_Algebra {
	public static void main(String[] args) throws IOException {
        String path = "test_grammatica.txt";
		
        //CharStream input = CharStreams.fromStream(System.in); //inserisci input e poi premi CTRL+D
		
        try (Reader reader = new FileReader(path)){
        
			//long start = System.currentTimeMillis();
			
	        GrammaticaLexer lexer = new GrammaticaLexer(CharStreams.fromReader(reader));        
	        CommonTokenStream tokens = new CommonTokenStream(lexer);
	        GrammaticaParser parser = new GrammaticaParser(tokens);
	        
	        //parser.addParseListener(new GrammaticaBaseListener_impl());
	        
	        ParseTree tree =  parser.assertion();
	        Parser p = new Parser();
	        System.out.println(tree.toStringTree(parser));
	        Assertion schema = (Assertion) p.visit(tree);
	        
			//System.out.println("Tempo di esecuzione [ms]: "+ (System.currentTimeMillis()-start));
			
			
			System.out.println(schema.toString());
			
			JSONObject JSON = (JSONObject)schema.toJSONSchema();
			System.out.println(JSON.toJSONString());
        }
	}
}
