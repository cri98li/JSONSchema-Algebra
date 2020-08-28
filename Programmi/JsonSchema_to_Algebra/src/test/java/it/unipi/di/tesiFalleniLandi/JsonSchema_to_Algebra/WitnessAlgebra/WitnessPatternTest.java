package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.ComplexPattern.ComplexPattern;
import org.junit.Ignore;
import org.junit.Test;
import patterns.Pattern;
import patterns.REException;

import static org.junit.Assert.*;

public class WitnessPatternTest {

    @Test
    public void testMergePattern1() throws REException {
        WitnessPattern p1 = new WitnessPattern(ComplexPattern.createFromRegexp("^.{"+ 1 +"," + 10 + "}$"));
        WitnessPattern p2 = new WitnessPattern(ComplexPattern.createFromRegexp("^.{"+ 3 +"," + 7 + "}$"));
        WitnessPattern output = new WitnessPattern(ComplexPattern.createFromRegexp("^.{"+ 3 +"," + 7 + "}$"));

        assertEquals(p1.mergeElement(p2), output);
    }

    @Test
    public void testMergePattern2() throws REException {
        WitnessPattern p1 = new WitnessPattern(ComplexPattern.createFromRegexp("^.{"+ 1 +"," + 10 + "}$"));
        WitnessPattern p2 = new WitnessPattern(ComplexPattern.createFromRegexp("^.{"+ 11 +"," + 16 + "}$"));
        WitnessPattern output = new WitnessPattern(ComplexPattern.createFromRegexp(".*").complement()); //reject pattern (false)

        assertEquals(p1.mergeElement(p2), output);
    }

    @Test
    @Ignore //TODO: ask Stefanie: "should not have happened"
    public void testMergePattern3() {
        WitnessPattern p1 = new WitnessPattern(ComplexPattern.createFromName("aa"));
        WitnessPattern p2 = new WitnessPattern(ComplexPattern.createFromName("aa"));
        WitnessPattern output = new WitnessPattern(ComplexPattern.createFromName("aa"));

        assertEquals(p1.mergeElement(p2), output);
    }

    @Test
    public void testMergePattern4() throws REException {
        WitnessPattern p1 = new WitnessPattern(ComplexPattern.createFromRegexp("^(?:\\S+\\s+){0,99}\\S+$"));
        WitnessPattern p2 = new WitnessPattern(ComplexPattern.createFromRegexp("^(?:\\S+\\s+){80,120}\\S+$"));
        WitnessPattern output = new WitnessPattern(ComplexPattern.createFromRegexp("^(?:\\S+\\s+){80,99}\\S+$"));

        assertEquals(p1.mergeElement(p2), output);
    }

    @Test
    public void testMergePattern5() throws REException {
        Pattern a = Pattern.createFromRegexp("^.a");
        Pattern b = Pattern.createFromRegexp("^b");

        Pattern AminusB = a.intersect(b.complement());
        Pattern BminusA = b.intersect(a.complement());
        Pattern AandB = a.intersect(b);

        Pattern r1 = BminusA.intersect(AminusB);
        Pattern r2 = BminusA.intersect(AandB);
        Pattern r3 = AminusB.intersect(AandB);
        Pattern r4 = AandB.intersect(AandB); //banale

        System.out.println("R1: " + r1.domainSize());
        System.out.println("R2: " + r2.domainSize());
        System.out.println("R3: " + r3.domainSize());
        System.out.println("R4: " + r4.domainSize());

    }
}