package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.ANTLR4.AlgebraParser;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.ANTLR4.ErrorListener;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.ANTLR4.GrammaticaLexer;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.ANTLR4.GrammaticaParser;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Defs_Assertion;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.json.simple.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class MainWitness {
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
            Defs_Assertion schema = (Defs_Assertion) p.visit(tree);

            System.out.println(schema.toString());

<<<<<<< HEAD
            WitnessEnv env = schema.notElimination().toWitnessAlgebra();
=======
            WitnessEnv env = schema.toWitnessAlgebra();
>>>>>>> 9be72e1ac293591d2d50b1d0779180c7b28dedeb

            System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString() + "\r\n\r\n\n" + "AND MERGING:" +"\r\n"));

            env = (WitnessEnv) env.merge(null);

            System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));
        }
    }
}
