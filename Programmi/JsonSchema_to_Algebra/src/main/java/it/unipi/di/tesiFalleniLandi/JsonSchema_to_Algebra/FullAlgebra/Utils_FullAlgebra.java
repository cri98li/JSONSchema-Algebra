package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.ANTLR4.AlgebraParser;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.ANTLR4.ErrorListener;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.ANTLR4.GrammaticaLexer;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.ANTLR4.GrammaticaParser;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessEnv;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessVar;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Utils_FullAlgebra {
    public static Defs_Assertion parseFile(String path) throws IOException {
        Reader reader = new FileReader(path);
        GrammaticaLexer lexer = new GrammaticaLexer(CharStreams.fromReader(reader));
        lexer.removeErrorListeners();
        lexer.addErrorListener(new ErrorListener());
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammaticaParser parser = new GrammaticaParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new ErrorListener());

        ParseTree tree = parser.assertion();
        AlgebraParser p = new AlgebraParser();
        Assertion schema = (Assertion) p.visit(tree);
        if (schema.getClass() != Defs_Assertion.class) {
            Defs_Assertion tmp = new Defs_Assertion();
            tmp.setRootDef(GrammarStringDefinitions.ROOTDEF_DEFAULTNAME, schema);
            schema = tmp;
        }

        return (Defs_Assertion) schema;
    }

    public static Defs_Assertion parseString(String toParse) {
        GrammaticaLexer lexer = new GrammaticaLexer(CharStreams.fromString(toParse));
        lexer.removeErrorListeners();
        lexer.addErrorListener(new ErrorListener());
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammaticaParser parser = new GrammaticaParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new ErrorListener());

        ParseTree tree = parser.assertion();
        AlgebraParser p = new AlgebraParser();
        Assertion schema = (Assertion) p.visit(tree);
        if (schema.getClass() != Defs_Assertion.class) {
            Defs_Assertion tmp = new Defs_Assertion();
            tmp.setRootDef(GrammarStringDefinitions.ROOTDEF_DEFAULTNAME, schema);
            schema = tmp;
        }

        return (Defs_Assertion) schema;
    }

        public static WitnessEnv getWitnessAlgebra(Assertion root){
        WitnessAssertion returnedValue = root.notElimination().toWitnessAlgebra();
        if(returnedValue.getClass() != WitnessEnv.class){
            WitnessEnv env = new WitnessEnv();
            env.setRootVar(new WitnessVar(GrammarStringDefinitions.ROOTDEF_DEFAULTNAME), returnedValue);
            return env;
        }
        return (WitnessEnv) returnedValue;
    }

}
