package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.*;
import patterns.REException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class WitnessUniqueItems implements WitnessAssertion{

    public WitnessUniqueItems() { }

    @Override
    public String toString() {
        return "WitnessUniqueItems{}";
    }

    @Override
    public void checkLoopReferences(WitnessEnv env, Collection<WitnessVar> varList) throws WitnessException {
        return;
    }

    @Override
    public WitnessAssertion mergeElement(WitnessAssertion a) throws REException {
        if(a.getClass() == this.getClass()) return mergeElement((WitnessUniqueItems) a);
        if(a.getClass() == WitnessRepeateditems.class) return mergeElement((WitnessRepeateditems) a);

        return null;
    }

    @Override
    public WitnessAssertion merge() {
        return this;
    }

    public WitnessAssertion mergeElement(WitnessUniqueItems a) {
        return a;
    }

    public WitnessAssertion mergeElement(WitnessRepeateditems a) throws REException {
        Type_Assertion t = new Type_Assertion();
        t.add(GrammarStringDefinitions.TYPE_ARRAY);

        //return new IfThenElse_Assertion(t, new Boolean_Assertion(false), null).toWitnessAlgebra();
        return t.not().toWitnessAlgebra();
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

    @Override
    public boolean equals(Object obj) {
        return this.getClass() == obj.getClass();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
