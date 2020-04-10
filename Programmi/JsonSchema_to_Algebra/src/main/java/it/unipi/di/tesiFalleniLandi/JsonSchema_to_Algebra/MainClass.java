package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.MainClass_Algebra;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaLexer;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaParser;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.AlgebraParser;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.JSONSchema;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.Utils_JSONSchema;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.MainClass_JSONSchema;

public class MainClass 
{
    public static void main( String[] args ) throws FileNotFoundException, IOException, ParseException{	
    	String filename = "";
    	int op;
    
    	if(args.length != 0) {
    		try {
	    		Assertion schema;
	    		op = Integer.parseInt(args[0]);
	    		filename = args[1];
	    		
	    		try (Reader reader = new FileReader(filename)){
	    	        GrammaticaLexer lexer = new GrammaticaLexer(CharStreams.fromReader(reader));        
	    	        CommonTokenStream tokens = new CommonTokenStream(lexer);
	    	        GrammaticaParser parser = new GrammaticaParser(tokens);
	    	        
	    	        ParseTree tree =  parser.assertion();
	    	        AlgebraParser p = new AlgebraParser();
	    	        schema = (Assertion) p.visit(tree);
	            }
	    		
	    		switch(op) {
	    		
	    		//JSON --> ALGEBRA
	    		case 1:
	    			JSONObject json = (JSONObject)schema.toJSONSchema();
	    			JSONSchema jsonSchema = new JSONSchema(json);
	    			System.out.println(Utils_JSONSchema.toGrammarString(Utils_JSONSchema.normalize(jsonSchema)));
	    			break;
	    			
	    		//ALGEBRA --> JSON
	    		case 2:
	    			JSONObject JSON = (JSONObject)schema.toJSONSchema();
	    			System.out.println(JSON.toJSONString());
	    			break;
	    			
	    		//NOT ELIMINATION
	    		case 3:
	    			System.out.println(Utils.beauty(schema.notElimination().toGrammarString()));
	    			break;
	    			
	    		default:
	    			System.out.println("Not supported");
	    			break;
	    		}
	    		
    		}catch(Exception ex) {
    			ex.printStackTrace();
    			System.exit(0);
    		}
    		
    	}else {
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
				    	JSONObject object = (JSONObject) new JSONParser().parse(reader);
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
}
