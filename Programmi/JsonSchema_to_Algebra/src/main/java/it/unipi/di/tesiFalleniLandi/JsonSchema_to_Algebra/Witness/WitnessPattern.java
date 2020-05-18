package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.MyPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.PosixPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Pattern_Assertion;

import java.util.HashSet;
import java.util.Set;

public class WitnessPattern implements WitnessAssertion{
    private PosixPattern pattern;

    public WitnessPattern(PosixPattern pattern){
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
    public WitnessAssertion merge(WitnessAssertion a) {
        if(a == null) return this;
        if(a.getClass() == this.getClass())
            return merge((WitnessPattern) a);

        return null;
    }

    public WitnessAssertion merge(WitnessPattern a) {
        return new WitnessPattern(pattern.and(a.pattern));
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
        clone.pattern = new MyPattern((MyPattern) pattern);

        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessPattern that = (WitnessPattern) o;

        return pattern.equals(that.pattern);
    }

    @Override
    public int hashCode() {
        return pattern != null ? pattern.hashCode() : 0;
    }

    @Override
    public WitnessAssertion not() {
        return getFullAlgebra().not().toWitnessAlgebra();
    }

    @Override
    public WitnessAssertion notElimination() {
        return getFullAlgebra().notElimination().toWitnessAlgebra();
    }

    @Override
    public WitnessAssertion groupize() {
        return this;
    }

    @Override
    public Set<WitnessAssertion> variableNormalization_separation() {
        return new HashSet<>();
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