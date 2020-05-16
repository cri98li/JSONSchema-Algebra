package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.RepeatedItems_Assertion;

import java.util.HashSet;
import java.util.Set;

public class WitnessRepeateditems implements WitnessAssertion{

    public WitnessRepeateditems() { }

    @Override
    public String toString() {
        return "WitnessRepeateditems{}";
    }

    @Override
    public WitnessAssertion merge(WitnessAssertion a) {
        return null;
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(GrammarStringDefinitions.TYPE_ARRAY);
    }

    @Override
    public Assertion getFullAlgebra() {
        return new RepeatedItems_Assertion();
    }

    @Override
    public WitnessAssertion clone() {
        return new WitnessRepeateditems();
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
