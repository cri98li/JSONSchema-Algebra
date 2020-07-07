package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Len_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Utils_FullAlgebra;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import patterns.REException;

import java.io.IOException;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class WitnessAndTest {


    //Test and appiattito
    @Test
    public void testAddAnd() throws REException {
        WitnessAnd a1 = new WitnessAnd();
        a1.add(new WitnessBoolean(true));
        a1.add(new WitnessMof(3.0));

        WitnessAnd a2 = new WitnessAnd();
        a2.add(new WitnessBoolean(true));
        a2.add(new WitnessMof(5.0));

        WitnessAnd output = new WitnessAnd();
        output.add(new WitnessBoolean(true));
        output.add(new WitnessMof(3.0));
        output.add(new WitnessMof(5.0));

        a1.add(a2);

        assertEquals(a1, output);
    }

    @Test
    public void mergeFalse() throws REException {
        WitnessAnd a1 = new WitnessAnd();
        a1.add(new WitnessMof(3.0));
        a1.add(new WitnessBoolean(false));

        WitnessAnd a2 = new WitnessAnd();
        a2.add(new WitnessBoolean(true));

        assertEquals(a1.mergeElement(a2), new WitnessBoolean(false));
    }

    @Test
    public void testMerge1() throws REException, IOException {
        Assertion in = Utils_FullAlgebra.parse("unit-test/merge/input_1.algebra");
        Assertion out = Utils_FullAlgebra.parse("unit-test/merge/output_1.algebra");
        WitnessAssertion input = in.toWitnessAlgebra();
        WitnessAssertion output = out.toWitnessAlgebra();

        assertEquals(input.merge(), output);
    }

    @Test
    public void testMerge2() throws REException, IOException {
        Assertion in = Utils_FullAlgebra.parse("unit-test/merge/input_2.algebra");
        Assertion out = Utils_FullAlgebra.parse("unit-test/merge/output_2.algebra");
        WitnessAssertion input = in.toWitnessAlgebra();
        WitnessAssertion output = out.toWitnessAlgebra();

        assertEquals(input.merge(), output);
    }

    @Test
    public void testMerge3() throws REException, IOException {
        Assertion in = Utils_FullAlgebra.parse("unit-test/merge/input_3.algebra");
        Assertion out = Utils_FullAlgebra.parse("unit-test/merge/output_3.algebra");
        WitnessAssertion input = in.toWitnessAlgebra();
        WitnessAssertion output = out.toWitnessAlgebra();

        assertEquals(input.merge(), output);
    }

    @Test
    public void testMerge4() throws REException, IOException {
        Assertion in = Utils_FullAlgebra.parse("unit-test/merge/input_4.algebra");
        Assertion out = Utils_FullAlgebra.parse("unit-test/merge/output_4.algebra");
        WitnessAssertion input = in.toWitnessAlgebra();
        WitnessAssertion output = out.toWitnessAlgebra();

        assertEquals(input.merge(), output);
    }

    @Test
    public void testCanonicalization1() throws REException, WitnessException {
        WitnessAnd a = new WitnessAnd();
        a.add(new WitnessMof(3.0));
        a.add(new Len_Assertion(3L,7L).toWitnessAlgebra());
        a.add(new WitnessPro(1.0,3.0));
        a.add(new WitnessUniqueItems());
        a.add(new WitnessIfBoolThen(true));

        WitnessGroup g1 = new WitnessGroup();
        g1.add(new WitnessType("num"));
        g1.add(new WitnessMof(3.0));
        WitnessGroup g2 = new WitnessGroup();
        g2.add(new WitnessType("str"));
        g2.add(new Len_Assertion(3L,7L).toWitnessAlgebra());
        WitnessGroup g3 = new WitnessGroup();
        g3.add(new WitnessType("obj"));
        g3.add(new WitnessPro(1.0,3.0));
        WitnessGroup g4 = new WitnessGroup();
        g4.add(new WitnessType("arr"));
        g4.add(new WitnessUniqueItems());
        WitnessGroup g5 = new WitnessGroup();
        g5.add(new WitnessType("bool"));
        g5.add(new WitnessIfBoolThen(true));
        WitnessGroup g6 = new WitnessGroup();
        g6.add(new WitnessType("null"));

        WitnessAnd output = new WitnessAnd();
        WitnessOr or = new WitnessOr();
        or.add(g1);
        or.add(g2);
        or.add(g3);
        or.add(g4);
        or.add(g5);
        or.add(g6);
        output.add(or);

        assertEquals(a.groupize(), output);
    }


    @Test
    public void testDNF1() throws IOException, REException, WitnessException {
        Assertion in = Utils_FullAlgebra.parse("unit-test/dnf/input_1.algebra");
        Assertion out = Utils_FullAlgebra.parse("unit-test/dnf/output_1.algebra");
        WitnessAssertion input = in.toWitnessAlgebra();
        WitnessAssertion output = out.toWitnessAlgebra();

        assertEquals(input.DNF(), output);

    }

    @Test
    public void testDNF2() throws WitnessException, IOException, REException {
        Assertion in = Utils_FullAlgebra.parse("unit-test/dnf/input_2.algebra");
        Assertion out = Utils_FullAlgebra.parse("unit-test/dnf/output_2.algebra");
        WitnessAssertion input = in.toWitnessAlgebra();
        WitnessAssertion output = out.toWitnessAlgebra();

        assertEquals(input.DNF(), output);
    }

    @Test
    public void testDNF3() throws WitnessException, IOException, REException {
        Assertion in = Utils_FullAlgebra.parse("unit-test/dnf/input_3.algebra");
        Assertion out = Utils_FullAlgebra.parse("unit-test/dnf/output_3.algebra");
        WitnessAssertion input = in.toWitnessAlgebra();
        WitnessAssertion output = out.toWitnessAlgebra();

        assertEquals(input.DNF(), output);
    }

    @Test
    public void testDNF4() throws WitnessException, IOException, REException {
        Assertion in = Utils_FullAlgebra.parse("unit-test/dnf/input_4.algebra");
        Assertion out = Utils_FullAlgebra.parse("unit-test/dnf/output_4.algebra");
        WitnessAssertion input = in.toWitnessAlgebra();
        WitnessAssertion output = out.toWitnessAlgebra();

        assertEquals(input.DNF(), output);
    }

    @Test
    public void testDNF5() throws WitnessException, IOException, REException {
        Assertion in = Utils_FullAlgebra.parse("unit-test/dnf/input_5.algebra");
        Assertion out = Utils_FullAlgebra.parse("unit-test/dnf/output_5.algebra");
        WitnessAssertion input = in.toWitnessAlgebra();
        WitnessAssertion output = out.toWitnessAlgebra();

        assertEquals(input.DNF(), output);
    }

    @Test
    public void testEquals(){
        WitnessAnd a1 = new WitnessAnd();
        a1.add(new WitnessMof(3.0));
        a1.add(new WitnessMof(4.0));

        WitnessAnd a2 = new WitnessAnd();
        a2.add(new WitnessMof(4.0));
        a2.add(new WitnessMof(3.0));

        assertEquals(a1, a2);

    }

}