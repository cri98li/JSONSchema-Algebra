package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import org.junit.Test;
import patterns.Pattern;
import patterns.REException;

import static org.junit.Assert.*;

public class WitnessTypeTest {

    @Test
    public void mergeSameType(){
        WitnessType t1 = new WitnessType("num");
        WitnessType t2 = new WitnessType("num");
        WitnessType output = new WitnessType("num");

        assertEquals(t1.mergeElement(t2), output);
    }

    @Test
    public void mergeDifferentType(){
        WitnessType t1 = new WitnessType();
        t1.add("num");
        t1.add("bool");
        WitnessType t2 = new WitnessType();
        t2.add("str");
        t2.add("obj");
        WitnessBoolean output = new WitnessBoolean(false);

        assertEquals(t1.mergeElement(t2), output);
    }

    @Test
    public void mergeNumWithInt(){
        WitnessType t1 = new WitnessType();
        t1.add("num");
        t1.add("bool");
        WitnessType t2 = new WitnessType();
        t2.add("int");
        t2.add("obj");
        WitnessType output = new WitnessType("int");

        assertEquals(t1.mergeElement(t2), output);
    }

    @Test
    public void mergeNumWithNumNotInt(){
        WitnessType t1 = new WitnessType();
        t1.add("num");
        t1.add("bool");
        WitnessType t2 = new WitnessType();
        t2.add("numNotInt");
        t2.add("obj");
        WitnessType output = new WitnessType("numNotInt");

        assertEquals(t1.mergeElement(t2), output);
    }
}