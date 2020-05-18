package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.ANTLR4.AlgebraParser;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.ANTLR4.ErrorListener;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.ANTLR4.GrammaticaLexer;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.ANTLR4.GrammaticaParser;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.MainClass_Algebra;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.JSONSchema;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.MainClass_JSONSchema;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.Utils_JSONSchema;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class MainClass
{
	public static void main( String[] args ) throws IOException, ParseException{
		String filename = "";
		int op;

		if(args.length != 0) {
			try {
				op = Integer.parseInt(args[0]);
				filename = args[1];

				switch(op) {

					//JSON --> ALGEBRA
					case 1:
						JSONSchema JsonSchema;
						try (Reader reader = new FileReader(filename)) {
							JSONObject object = (JSONObject) new JSONParser().parse(reader);
							JsonSchema = new JSONSchema(object);
						}

						System.out.println(Utils.beauty(Utils_JSONSchema.toGrammarString(Utils_JSONSchema.normalize(JsonSchema))));
						break;

					//ALGEBRA --> JSON
					case 2:
						Assertion schema;
						try (Reader reader = new FileReader(filename)){
							GrammaticaLexer lexer = new GrammaticaLexer(CharStreams.fromReader(reader));
							lexer.removeErrorListeners();
							lexer.addErrorListener(new ErrorListener());
							CommonTokenStream tokens = new CommonTokenStream(lexer);
							GrammaticaParser parser = new GrammaticaParser(tokens);
							parser.removeErrorListeners();
							parser.addErrorListener(new ErrorListener());

							ParseTree tree =  parser.assertion();
							AlgebraParser p = new AlgebraParser();
							schema = (Assertion) p.visit(tree);
						}

						JSONObject JSON = (JSONObject)schema.toJSONSchema();
						System.out.println(JSON.toJSONString());
						break;

					//NOT ELIMINATION
					case 3:
						Assertion _schema;
						try (Reader reader = new FileReader(filename)){
							GrammaticaLexer lexer = new GrammaticaLexer(CharStreams.fromReader(reader));
							lexer.removeErrorListeners();
							lexer.addErrorListener(new ErrorListener());
							CommonTokenStream tokens = new CommonTokenStream(lexer);
							GrammaticaParser parser = new GrammaticaParser(tokens);
							parser.removeErrorListeners();
							parser.addErrorListener(new ErrorListener());

							ParseTree tree =  parser.assertion();
							AlgebraParser p = new AlgebraParser();
							_schema = (Assertion) p.visit(tree);
						}

						System.out.println(Utils.beauty(_schema.notElimination().toGrammarString()));
						break;

					case 4:
						try (Reader reader = new FileReader(filename)){
							GrammaticaLexer lexer = new GrammaticaLexer(CharStreams.fromReader(reader));
							lexer.removeErrorListeners();
							lexer.addErrorListener(new ErrorListener());
							CommonTokenStream tokens = new CommonTokenStream(lexer);
							GrammaticaParser parser = new GrammaticaParser(tokens);
							parser.removeErrorListeners();
							parser.addErrorListener(new ErrorListener());

							ParseTree tree =  parser.assertion();
							AlgebraParser p = new AlgebraParser();
							_schema = (Assertion) p.visit(tree);
						}

						System.out.println(Utils.beauty(_schema.notElimination().toWitnessAlgebra().merge(null).getFullAlgebra().toGrammarString()));
						break;

					default:
						System.out.println("Not supported");
						break;
				}

			}catch(Exception ex) {
				ex.printStackTrace();
				System.exit(-1);
			}

		}else {
			System.out.println("[1] MainClass_JSONSchema");
			System.out.println("[2] MainClass_Algebra");
			System.out.println("[3] JSONSchema to Full Algebra to JSONSchema");

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