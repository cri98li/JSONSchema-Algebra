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

        /*
        System.out.println("\r\n\r\n DNF: \r\n");

        env = env.DNF();
        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));*/

        env = (WitnessEnv) env.merge();
        System.out.println("\r\n\r\n Merge: \r\n");
        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        //System.out.println("\r\n\r\n Groupize: \r\n");

        //System.out.println(Utils.beauty(env.groupize().getFullAlgebra().toGrammarString()));

        //System.out.println("\r\n\r\n Separation: \r\n");


        /*
        env.variableNormalization_separation();

        System.out.println("\r\n\r\n Separation: \r\n");

        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        System.out.println("\r\n\r\n Expansion: \r\n");

        env = env.variableNormalization_expansion(null);

        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

         */

        /*

        //Prove hash
        WitnessOr or = new WitnessOr();
        WitnessAnd and = new WitnessAnd();
        and.add(new WitnessType("str"));
        and.add(new WitnessType("int"));
        or.add(and);

        WitnessOr or1 = new WitnessOr();
        WitnessAnd and1 = new WitnessAnd();
        or1.add(new WitnessType("str"));
        or1.add(new WitnessType("int"));
        and1.add(or1);

        System.out.println(and1.hashCode());
        System.out.println(or.hashCode());

        WitnessType t = new WitnessType();
        t.add("str");
        t.add("int");

        WitnessType t1 = new WitnessType();
        t1.add("int");
        t1.add("str");

        System.out.println(t.hashCode());
        System.out.println(t1.hashCode());
        */

    }
}
