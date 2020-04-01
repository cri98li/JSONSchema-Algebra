package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.google.gson.JsonObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.MainClass_Algebra;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaLexer;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaParser;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.AlgebraParser;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.JSONSchema;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.Utils_JSONSchema;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.MainClass_JSONSchema;

/**
 * Hello world!
 *
 */
public class MainClass 
{
    public static void main( String[] args ) throws FileNotFoundException, IOException
    {
    	System.out.println("[1] MainClass_JSONSchema");
    	System.out.println("[2] MainClass_Algebra");
    	System.out.println("[3] JSONSchema --> Algebra");
    	
    	try (Scanner s = new Scanner(System.in)) {
			switch(s.nextInt()) {
			case 1:
				MainClass_JSONSchema.main(args);
				break;
			case 2:
				MainClass_Algebra.main(args);
				break;
			case 3:
				String path = "test.json";
				JSONSchema root;
				
			    try (Reader reader = new FileReader(path)){
			    	JsonObject object = JsonObject.parse(reader.toString());
			    	root = new JSONSchema(object);
			    	
			    	Utils_JSONSchema.toGrammarString(root.assertionSeparation());
			    	
			    	GrammaticaLexer lexer = new GrammaticaLexer(CharStreams.fromString(Utils_JSONSchema.toGrammarString(root.assertionSeparation())));        
			        CommonTokenStream tokens = new CommonTokenStream(lexer);
			        GrammaticaParser parser = new GrammaticaParser(tokens);
			        ParseTree tree =  parser.assertion();
			        AlgebraParser p = new AlgebraParser();
			        Assertion schema = (Assertion) p.visit(tree);
			        System.out.println(schema.toString());
			    }
				break;
			}
		}
    }
}
