package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import org.junit.Test;
import patterns.Pattern;
import patterns.REException;

import static org.junit.Assert.*;

public class WitnessPatternTest {

    @Test
    public void testMergePattern1() throws REException {
        WitnessPattern p1 = new WitnessPattern(Pattern.createFromRegexp("^.{"+ 1 +"," + 10 + "}$"));
        WitnessPattern p2 = new WitnessPattern(Pattern.createFromRegexp("^.{"+ 3 +"," + 7 + "}$"));
        WitnessPattern output = new WitnessPattern(Pattern.createFromRegexp("^.{"+ 3 +"," + 7 + "}$"));

        assertEquals(p1.mergeElement(p2), output);
    }

    @Test
    public void testMergePattern2() throws REException {
        WitnessPattern p1 = new WitnessPattern(Pattern.createFromRegexp("^.{"+ 1 +"," + 10 + "}$"));
        WitnessPattern p2 = new WitnessPattern(Pattern.createFromRegexp("^.{"+ 11 +"," + 16 + "}$"));
        WitnessPattern output = new WitnessPattern(Pattern.createFromRegexp("#")); //reject pattern (false)

        assertEquals(p1.mergeElement(p2), output);
    }

    @Test
    public void testMergePattern3() throws REException {
        WitnessPattern p1 = new WitnessPattern(Pattern.createFromName("aa"));
        WitnessPattern p2 = new WitnessPattern(Pattern.createFromName("aa"));
        WitnessPattern output = new WitnessPattern(Pattern.createFromName("aa"));

        assertEquals(p1.mergeElement(p2), output);
    }

    @Test
    public void testMergePattern4() throws REException {
        WitnessPattern p1 = new WitnessPattern(Pattern.createFromRegexp("^(?:\\S+\\s+){0,99}\\S+$"));
        WitnessPattern p2 = new WitnessPattern(Pattern.createFromRegexp("^(?:\\S+\\s+){80,120}\\S+$"));
        WitnessPattern output = new WitnessPattern(Pattern.createFromRegexp("^(?:\\S+\\s+){80,99}\\S+$"));

        assertEquals(p1.mergeElement(p2), output);
    }
}