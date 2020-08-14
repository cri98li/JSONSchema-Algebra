package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Pattern_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessException;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessFalseAssertionException;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessTrueAssertionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class WitnessPattern implements WitnessAssertion{
    private static Logger logger = LogManager.getLogger(WitnessPattern.class);

    private ComplexPattern pattern;

    public WitnessPattern(ComplexPattern pattern){
        this.pattern = pattern;
        logger.trace("Created a new WitnessPattern {}", this);
    }

    private WitnessPattern() { }

    @Override
    public String toString() {
        return "WitnessPattern{" +
                "pattern=" + pattern +
                '}';
    }

    @Override
    public void checkLoopRef(WitnessEnv env, Collection<WitnessVar> varList) throws WitnessException {
        return;
    }

    @Override
    public WitnessAssertion mergeWith(WitnessAssertion a) {
        logger.trace("Merging {} with {}", a, this);
        if(a.getClass() == this.getClass())
            return mergeElement((WitnessPattern) a);

        return null;
    }

    @Override
    public WitnessAssertion merge() {
        return this;
    }

    public WitnessAssertion mergeElement(WitnessPattern a) {
        WitnessPattern result = new WitnessPattern(pattern.intersect(a.pattern));

        logger.trace("Merge result {}", this);

        return result;
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(FullAlgebraString.TYPE_STRING);
    }

    @Override
    public Assertion getFullAlgebra() {
        return new Pattern_Assertion(pattern);
    }

    @Override
    public WitnessAssertion clone() {
        logger.trace("Cloning WitnessPattern {}", this);
        WitnessPattern clone = new WitnessPattern();
        clone.pattern = pattern.clone();

        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessPattern that = (WitnessPattern) o;

        return pattern.isEquivalent(that.pattern);
    }

    @Override
    public int hashCode() {
        return pattern != null ? pattern.hashCode() : 0;
    }

    @Override
    public WitnessAssertion not(WitnessEnv env) throws REException {
        return new WitnessPattern(pattern.complement());
    }

    @Override
    public WitnessAssertion groupize() {
        return this;
    }

    @Override
    public Float countVarWithoutBDD(WitnessEnv env, List<WitnessVar> visitedVar) {
        return 0f;
    }

    @Override
    public int countVarToBeExp(WitnessEnv env) {
        return 0;
    }

    @Override
    public void varNormalization_separation(WitnessEnv env) {
    }

    @Override
    public WitnessAssertion varNormalization_expansion(WitnessEnv env) {
        return this.clone();
    }

    @Override
    public WitnessAssertion DNF() {
        return this.clone();
    }

    @Override
    public WitnessAssertion toOrPattReq() throws WitnessFalseAssertionException, WitnessTrueAssertionException {
        return this;
    }

    @Override
    public boolean isBooleanExp() {
        return false;
    }

    @Override
    public boolean isRecursive(WitnessEnv env, LinkedList<WitnessVar> visitedVar) {
        return false;
    }

    @Override
    public WitnessVar buildOBDD(WitnessEnv env) {
        throw new UnsupportedOperationException();
    }
}