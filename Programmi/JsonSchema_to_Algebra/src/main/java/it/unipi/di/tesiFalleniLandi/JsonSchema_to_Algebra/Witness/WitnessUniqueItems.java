package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.*;

import java.util.HashSet;
import java.util.Set;

public class WitnessUniqueItems implements WitnessAssertion{

    public WitnessUniqueItems() { }

    @Override
    public String toString() {
        return "WitnessUniqueItems{}";
    }

    @Override
    public WitnessAssertion merge(WitnessAssertion a) {
        if(a == null) return this;

        if(a.getClass() == this.getClass()) return merge((WitnessUniqueItems) a);
        if(a.getClass() == WitnessRepeateditems.class) return merge((WitnessRepeateditems) a);

        return null;
    }

    public WitnessAssertion merge(WitnessUniqueItems a) {
        return a;
    }

    public WitnessAssertion merge(WitnessRepeateditems a) {
        Type_Assertion t = new Type_Assertion();
        t.add(GrammarStringDefinitions.TYPE_ARRAY);

        return new IfThenElse_Assertion(t, new Boolean_Assertion(false), null).toWitnessAlgebra();
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(GrammarStringDefinitions.TYPE_ARRAY);
    }

    @Override
    public Assertion getFullAlgebra() {
        return new UniqueItems_Assertion();
    }

    @Override
    public WitnessAssertion clone() {
        return new WitnessUniqueItems();
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
