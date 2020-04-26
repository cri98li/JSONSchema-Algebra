package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.AlgebraParser;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaLexer;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaParser;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ParsingException;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.BitSet;

public class MainClass_Algebra {
	public static void main(String[] args) throws IOException {
        String path = "test_grammatica.txt";
		
        try (Reader reader = new FileReader(path)){

				GrammaticaLexer lexer = new GrammaticaLexer(CharStreams.fromReader(reader));
				lexer.removeErrorListeners();
				lexer.addErrorListener(new ErrorListener());
				CommonTokenStream tokens = new CommonTokenStream(lexer);
				GrammaticaParser parser = new GrammaticaParser(tokens);
				parser.removeErrorListeners();
				parser.addErrorListener(new ErrorListener());

				ParseTree tree = parser.assertion();
				AlgebraParser p = new AlgebraParser();
				System.out.println(tree.toStringTree(parser));
				Assertion schema = (Assertion) p.visit(tree);

				System.out.println(schema.toString());

				JSONObject JSON = (JSONObject) schema.toJSONSchema();
				System.out.println(JSON.toJSONString());

				System.out.println(Utils.beauty(schema.notElimination().toGrammarString()));
        }
	}
}
