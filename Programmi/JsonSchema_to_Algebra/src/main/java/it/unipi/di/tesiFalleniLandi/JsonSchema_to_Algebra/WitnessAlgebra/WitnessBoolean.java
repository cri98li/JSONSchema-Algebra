package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Boolean_Assertion;
import patterns.REException;

import java.util.Collection;

public class WitnessBoolean implements WitnessAssertion{
    private boolean value;

    public WitnessBoolean(boolean val){
        this.value = val;
    }

    @Override
    public String toString() {
        return "WitnessBoolean{" +
                "value=" + value +
                '}';
    }

    @Override
    public void checkLoopRef(WitnessEnv env, Collection<WitnessVar> varList) throws WitnessException {
        return;
    }

    @Override
    public WitnessAssertion mergeWith(WitnessAssertion a) {
        if(a.getClass() == this.getClass()) return this.mergeElement((WitnessBoolean) a);

        if(this.value) return a; // S AND true = S
        else return this; // S AND false = false
    }

    @Override
    public WitnessAssertion merge() {
        return this;
    }

    public WitnessBoolean mergeElement(WitnessBoolean a) {
        return new WitnessBoolean(a.value && value);
    }

    @Override
    public WitnessType getGroupType() {
        return null;
    }

    @Override
    public Assertion getFullAlgebra() {
        return new Boolean_Assertion(value);
    }

    @Override
    public WitnessAssertion clone() {
        return new WitnessBoolean(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessBoolean that = (WitnessBoolean) o;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return (value ? 1 : 0);
    }

    public boolean getValue() {
        return value;
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
    public int countVarToBeExp(WitnessEnv env) {
        return 0;
    }

    @Override
    public void varNormalization_separation(WitnessEnv env) {

    }

    @Override
    public WitnessAssertion varNormalization_expansion(WitnessEnv env) {
        return clone();
    }

    @Override
    public WitnessAssertion DNF() {
        return this.clone();
    }

    @Override
    public boolean isBooleanExp() {
        return true;
    }

    @Override
    public WitnessVar buildOBDD(WitnessEnv env) {
        return value ? env.obdd.trueVar : env.obdd.falseVar;
    }
}