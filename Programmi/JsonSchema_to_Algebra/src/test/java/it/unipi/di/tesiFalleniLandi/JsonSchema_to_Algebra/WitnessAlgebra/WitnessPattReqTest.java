package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern.ComplexPattern;
import org.junit.Ignore;
import org.junit.Test;
import patterns.REException;

import static org.junit.Assert.*;

public class WitnessPattReqTest {

    @Test
    public void testPattReqMerge1() throws REException {
        WitnessPattReq p1 = WitnessPattReq.build(ComplexPattern.createFromRegexp(".*"), new WitnessBoolean(true));
        WitnessPattReq p2 = WitnessPattReq.build(ComplexPattern.createFromName("aaa"), new WitnessBoolean(true));

        WitnessPattReq output = WitnessPattReq.build(ComplexPattern.createFromName("aaa"), new WitnessBoolean(true));

        assertEquals(p1.mergeElement(p2), output);
    }

    @Test
    @Ignore
    public void testPattReqMerge2() throws REException {
        WitnessPattReq p1 = WitnessPattReq.build(ComplexPattern.createFromRegexp(".*"), new WitnessBoolean(true));
        WitnessPattReq p2 = WitnessPattReq.build(ComplexPattern.createFromRegexp("#"), new WitnessBoolean(true));

        assertEquals(p1.mergeElement(p2), null);
    }
}