package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Utils_FullAlgebra;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.*;
import org.junit.Test;
import patterns.REException;

import java.io.IOException;

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

        assertEquals(a1.mergeWith(a2), new WitnessBoolean(false));
    }

    @Test
    public void testMerge1() throws REException, IOException {
        Assertion in = Utils_FullAlgebra.parseFile("unit-test/merge/input_1.algebra");
        Assertion out = Utils_FullAlgebra.parseFile("unit-test/merge/output_1.algebra");
        WitnessAssertion input = in.toWitnessAlgebra();
        WitnessAssertion output = out.toWitnessAlgebra();

        assertEquals(input.merge(), output);
    }

    @Test
    public void testMerge2() throws REException, IOException {
        Assertion in = Utils_FullAlgebra.parseFile("unit-test/merge/input_2.algebra");
        Assertion out = Utils_FullAlgebra.parseFile("unit-test/merge/output_2.algebra");
        WitnessAssertion input = in.toWitnessAlgebra();
        WitnessAssertion output = out.toWitnessAlgebra();

        assertEquals(input.merge(), output);
    }

    @Test
    public void testMerge3() throws REException, IOException {
        Assertion in = Utils_FullAlgebra.parseFile("unit-test/merge/input_3.algebra");
        Assertion out = Utils_FullAlgebra.parseFile("unit-test/merge/output_3.algebra");
        WitnessAssertion input = in.toWitnessAlgebra();
        WitnessAssertion output = out.toWitnessAlgebra();

        assertEquals(input.merge(), output);
    }

    @Test
    public void testMerge4() throws REException, IOException {
        Assertion in = Utils_FullAlgebra.parseFile("unit-test/merge/input_4.algebra");
        Assertion out = Utils_FullAlgebra.parseFile("unit-test/merge/output_4.algebra");
        WitnessAssertion input = in.toWitnessAlgebra();
        WitnessAssertion output = out.toWitnessAlgebra();

        assertEquals(input.merge(), output);
    }

    @Test
    public void testCanonicalization1() throws REException, WitnessException {
    }


    @Test
    public void testDNF1() throws IOException, REException, WitnessException {
        Assertion in = Utils_FullAlgebra.parseFile("unit-test/dnf/input_1.algebra");
        Assertion out = Utils_FullAlgebra.parseFile("unit-test/dnf/output_1.algebra");
        WitnessAssertion input = in.toWitnessAlgebra();
        WitnessAssertion output = out.toWitnessAlgebra();

        assertEquals(input.DNF(), output);

    }

    @Test
    public void testDNF2() throws WitnessException, IOException, REException {
        Assertion in = Utils_FullAlgebra.parseFile("unit-test/dnf/input_2.algebra");
        Assertion out = Utils_FullAlgebra.parseFile("unit-test/dnf/output_2.algebra");
        WitnessAssertion input = in.toWitnessAlgebra();
        WitnessAssertion output = out.toWitnessAlgebra();

        assertEquals(input.DNF(), output);
    }

    @Test
    public void testDNF3() throws WitnessException, IOException, REException {
        Assertion in = Utils_FullAlgebra.parseFile("unit-test/dnf/input_3.algebra");
        Assertion out = Utils_FullAlgebra.parseFile("unit-test/dnf/output_3.algebra");
        WitnessAssertion input = in.toWitnessAlgebra();
        WitnessAssertion output = out.toWitnessAlgebra();

        assertEquals(input.DNF(), output);
    }

    @Test
    public void testDNF4() throws WitnessException, IOException, REException {
        Assertion in = Utils_FullAlgebra.parseFile("unit-test/dnf/input_4.algebra");
        Assertion out = Utils_FullAlgebra.parseFile("unit-test/dnf/output_4.algebra");
        WitnessAssertion input = in.toWitnessAlgebra();
        WitnessAssertion output = out.toWitnessAlgebra();

        assertEquals(input.DNF(), output);
    }

    @Test
    public void testDNF5() throws WitnessException, IOException, REException {
        Assertion in = Utils_FullAlgebra.parseFile("unit-test/dnf/input_5.algebra");
        Assertion out = Utils_FullAlgebra.parseFile("unit-test/dnf/output_5.algebra");
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