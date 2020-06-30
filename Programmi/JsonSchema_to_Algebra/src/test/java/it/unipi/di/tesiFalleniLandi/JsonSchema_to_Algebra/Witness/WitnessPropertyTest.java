package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import org.junit.Test;
import patterns.Pattern;
import patterns.REException;

import static org.junit.Assert.*;

public class WitnessPropertyTest {

    @Test
    public void mergePropertyTest1() throws REException {
        WitnessProperty p1 = new WitnessProperty(Pattern.createFromName("a"), new WitnessMof(2.0));
        WitnessProperty p2 = new WitnessProperty(Pattern.createFromName("a"), new WitnessMof(5.0));
        WitnessProperty output = new WitnessProperty(Pattern.createFromName("a"), new WitnessMof(10.0));

        assertEquals(p1.mergeElement(p2), output);
    }

    @Test
    public void mergePropertyTest2() throws REException {
        WitnessProperty p1 = new WitnessProperty(Pattern.createFromName("a"), new WitnessMof(2.0));
        WitnessProperty p2 = new WitnessProperty(Pattern.createFromName("b"), new WitnessMof(2.0));

        WitnessProperty output = new WitnessProperty(Pattern.createFromName("a").union(Pattern.createFromName("b")), new WitnessMof(2.0));

        assertEquals(p1.mergeElement(p2), output);
    }

    @Test
    public void mergePropertyTest3() throws REException {
        WitnessProperty p1 = new WitnessProperty(Pattern.createFromName("a"), new WitnessMof(2.0));
        WitnessProperty p2 = new WitnessProperty(Pattern.createFromName("b"), new WitnessMof(2.0));
        WitnessProperty p3 = new WitnessProperty(Pattern.createFromName("c"), new WitnessMof(2.0));

        WitnessProperty output = new WitnessProperty((Pattern.createFromName("a").union(Pattern.createFromName("b"))).union(Pattern.createFromName("c")),
                new WitnessMof(2.0));

        assertEquals((p1.mergeElement(p2)).mergeElement(p3), output);
    }

}