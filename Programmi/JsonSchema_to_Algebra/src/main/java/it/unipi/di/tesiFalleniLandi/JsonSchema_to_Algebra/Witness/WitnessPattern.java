package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import patterns.Pattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Pattern_Assertion;
import patterns.REException;

import java.util.HashSet;
import java.util.Set;

public class WitnessPattern implements WitnessAssertion{
    private ComplexPattern pattern;

    public WitnessPattern(ComplexPattern pattern){
        this.pattern = pattern;
    }

    protected WitnessPattern() { }

    @Override
    public String toString() {
        return "WitnessPattern{" +
                "pattern=" + pattern +
                '}';
    }

    @Override
    public WitnessAssertion mergeElement(WitnessAssertion a) {
        if(a.getClass() == this.getClass())
            return mergeElement((WitnessPattern) a);

        return null;
    }

    @Override
    public WitnessAssertion merge() {
        return this;
    }

    public WitnessAssertion mergeElement(WitnessPattern a) {
        return new WitnessPattern(pattern.intersect(a.pattern));
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(GrammarStringDefinitions.TYPE_STRING);
    }

    @Override
    public Assertion getFullAlgebra() {
        return new Pattern_Assertion(pattern);
    }

    @Override
    public WitnessAssertion clone() {
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
    public WitnessAssertion not() throws REException {
        return getFullAlgebra().not().toWitnessAlgebra();
    }

    @Override
    public WitnessAssertion notElimination() throws REException {
        return getFullAlgebra().notElimination().toWitnessAlgebra();
    }

    @Override
    public WitnessAssertion groupize() {
        return this;
    }

    @Override
    public void variableNormalization_separation(WitnessEnv env) {
    }

    @Override
    public WitnessAssertion variableNormalization_expansion(WitnessEnv env) {
        return this.clone();
    }

    @Override
    public WitnessAssertion DNF() {
        return this.clone();
    }
}