package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Utils_FullAlgebra;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessException;
import patterns.REException;

import java.io.IOException;

public class MainWitness {
    public static void main(String[] args) throws IOException, WitnessException, REException {
        String path = "test.algebra";

        Assertion schema = Utils_FullAlgebra.parseFile(path);

        //System.out.println(schema.toGrammarString());

        WitnessEnv env = Utils_FullAlgebra.getWitnessAlgebra(schema); //fa anche not elimination

        //WitnessEnv env = (WitnessEnv) schema.toWitnessAlgebra();


        //env.notCompletition();
        env.notElimination();

        env.checkLoopRef(null, null);


        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        env = (WitnessEnv) env.merge();

        System.out.println("\r\n\r\n Merge: \r\n");
        System.out.flush();
        //System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        System.out.println("\r\n\r\n Groupize: \r\n");
        System.out.flush();

        env = env.groupize();
        //System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        System.out.println("\r\n\r\n DNF: \r\n");
        System.out.flush();

        env = (WitnessEnv) env.DNF();
        //System.out.println(env.getFullAlgebra().toGrammarString());

        System.out.println("\r\n\r\n Separation: \r\n");
        System.out.flush();

        env.varNormalization_separation(null);

        //System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        System.out.println("\r\n\r\n Expansion: \r\n");
        System.out.flush();

        env = env.varNormalization_expansion(null);

        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        System.out.println("\r\n\r\n objectPrepare: \r\n");
        System.out.flush();

        env = (WitnessEnv) env.merge();

        env.toOrPattReq();
        env.objectPrepare();

        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        System.out.println("\r\n\r\n FINE! \r\n");
        System.out.flush();


    }
}
