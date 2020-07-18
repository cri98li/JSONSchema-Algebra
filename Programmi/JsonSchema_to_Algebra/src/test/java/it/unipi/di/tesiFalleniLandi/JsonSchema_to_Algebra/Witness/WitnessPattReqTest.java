package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern;
import org.junit.Ignore;
import org.junit.Test;
import patterns.Pattern;
import patterns.REException;

import static org.junit.Assert.*;

public class WitnessPattReqTest {

    @Test
    public void testPattReqMerge1() throws REException {
        WitnessPattReq p1 = new WitnessPattReq(ComplexPattern.createFromRegexp(".*"), new WitnessBoolean(true));
        WitnessPattReq p2 = new WitnessPattReq(ComplexPattern.createFromName("aaa"), new WitnessBoolean(true));

        WitnessPattReq output = new WitnessPattReq(ComplexPattern.createFromName("aaa"), new WitnessBoolean(true));

        assertEquals(p1.mergeElement(p2), output);
    }

    @Test
    @Ignore
    public void testPattReqMerge2() throws REException {
        WitnessPattReq p1 = new WitnessPattReq(ComplexPattern.createFromRegexp(".*"), new WitnessBoolean(true));
        WitnessPattReq p2 = new WitnessPattReq(ComplexPattern.createFromRegexp("#"), new WitnessBoolean(true));

        assertEquals(p1.mergeElement(p2), null);
    }
}