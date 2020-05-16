package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import com.github.javabdd.*;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Utils_FullAlgebra;

import javax.swing.*;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MainWitness {
    public static void main(String[] args) throws IOException {
        String path = "test.algebra";

        Assertion schema = Utils_FullAlgebra.parseFile(path);

        System.out.println(schema.toString());

        //WitnessEnv env = (WitnessEnv) schema.notElimination().toWitnessAlgebra();
        WitnessEnv env = Utils_FullAlgebra.getWitnessAlgebra(schema);

        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        System.out.println(Utils.beauty(env.notElimination().getFullAlgebra().toGrammarString()));

        //env = env.merge(null).groupize();

        //System.out.println("\r\n\r\n Merge: \r\n");

        //System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        System.out.println("\r\n\r\n DNF: \r\n");

        env = env.DNF();

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
        WitnessAnd and = new WitnessAnd();
        WitnessOr or1 = new WitnessOr();
        WitnessOr or2 = new WitnessOr();
        and.add(new WitnessType("str"));
        and.add(or1);
        and.add(or2);
        or1.add(new WitnessType("int"));
        or1.add(new WitnessType("bool"));
        or2.add(new WitnessType("str"));
        or2.add(new WitnessType("bool"));


        WitnessOr or = new WitnessOr();
        WitnessAnd and1 = new WitnessAnd();
        or.add(and1);
        or.add(new WitnessType("str"));
        and1.add(new WitnessType("int"));
        and1.add(new WitnessType("bool"));


        WitnessAnd and1 = new WitnessAnd();
        and1.add(new WitnessMof(1.0));

        WitnessMof mof = new WitnessMof(1.0);

        System.out.println(and1.hashCode());
        System.out.println(mof.hashCode()); */

    }
}
