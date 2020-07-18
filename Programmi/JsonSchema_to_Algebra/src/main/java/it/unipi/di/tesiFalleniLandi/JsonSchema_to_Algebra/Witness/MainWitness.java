package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Utils_FullAlgebra;
import patterns.REException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MainWitness {
    public static void main(String[] args) throws IOException, WitnessException, REException {
        String path = "test.algebra";

        Assertion schema = Utils_FullAlgebra.parseFile(path);

        //System.out.println(schema.toGrammarString());

        //WitnessEnv env = Utils_FullAlgebra.getWitnessAlgebra(schema);

        WitnessEnv env = (WitnessEnv) schema.toWitnessAlgebra();

        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));


        env = (WitnessEnv) env.merge();

        System.out.println("\r\n\r\n Merge: \r\n");
        System.out.flush();
        //System.out.println(env.getFullAlgebra().toGrammarString());

        System.out.println("\r\n\r\n Groupize: \r\n");
        System.out.flush();

        env = env.groupize();
        //System.out.println(env.getFullAlgebra().toGrammarString());

        System.out.println("\r\n\r\n DNF: \r\n");
        System.out.flush();

        env = (WitnessEnv) env.DNF();
        //System.out.println(env.getFullAlgebra().toGrammarString());

        System.out.println("\r\n\r\n Separation: \r\n");
        System.out.flush();

        env.variableNormalization_separation(null);

        //System.out.println(env.getFullAlgebra().toGrammarString());

        System.out.println("\r\n\r\n Expansion: \r\n");
        System.out.flush();

        env = env.variableNormalization_expansion(null);

        //System.out.println(env.getFullAlgebra().toGrammarString());

        System.out.println("\r\n\r\n FINE! \r\n");
        System.out.flush();


    }
}
