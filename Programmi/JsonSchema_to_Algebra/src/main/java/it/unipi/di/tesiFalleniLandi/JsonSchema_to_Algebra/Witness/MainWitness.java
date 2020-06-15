package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Utils_FullAlgebra;

import java.io.IOException;

public class MainWitness {
    public static void main(String[] args) throws IOException, WitnessException {
        String path = "test.algebra";

        Assertion schema = Utils_FullAlgebra.parseFile(path);

        WitnessEnv env = Utils_FullAlgebra.getWitnessAlgebra(schema);

        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        //env = env.merge(null).groupize();

        //System.out.println("\r\n\r\n Merge: \r\n");

        //System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));


        env = (WitnessEnv) env.merge();
        System.out.println("\r\n\r\n Merge: \r\n");
        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        System.out.println("\r\n\r\n Groupize: \r\n");

        env = env.groupize();
        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        System.out.println("\r\n\r\n DNF: \r\n");
        env = env.DNF();
        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        env = (WitnessEnv) env.merge();
        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        /*
        System.out.println("\r\n\r\n Separation: \r\n");

        env.variableNormalization_separation();

        System.out.println("\r\n\r\n Separation: \r\n");

        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        System.out.println("\r\n\r\n Expansion: \r\n");

        env = env.variableNormalization_expansion(null);

        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

         */

    }
}
