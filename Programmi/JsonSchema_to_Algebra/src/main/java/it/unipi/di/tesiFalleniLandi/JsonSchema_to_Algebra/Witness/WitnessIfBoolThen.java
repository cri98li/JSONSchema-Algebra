package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.IfBoolThen_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Type_Assertion;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class WitnessIfBoolThen implements WitnessAssertion{
    private boolean value;

    public WitnessIfBoolThen(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "WitnessIfBoolThen{" +
                "value=" + value +
                '}';
    }

    @Override
    public WitnessAssertion merge(WitnessAssertion a) {
        if(a == null) return this;
        if(a.getClass() == this.getClass()) return this.merge((WitnessIfBoolThen) a);

        return null;
    }

    public WitnessAssertion merge(WitnessIfBoolThen a) {
        if(a.value == value) return this;
        else{
            Type_Assertion type = new Type_Assertion();
            type.add(GrammarStringDefinitions.TYPE_BOOLEAN);

            return type.not().toWitnessAlgebra();
        }
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(GrammarStringDefinitions.TYPE_BOOLEAN); //??
    }

    @Override
    public Assertion getFullAlgebra() {
        return new IfBoolThen_Assertion(value);
    }

    @Override
    public WitnessAssertion clone() {
        return new WitnessIfBoolThen(value);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessIfBoolThen that = (WitnessIfBoolThen) o;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return (value ? 1 : 0);
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