package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Boolean_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class WitnessBoolean implements WitnessAssertion{
    private static Logger logger = LogManager.getLogger(WitnessBoolean.class);

    private boolean value;

    public WitnessBoolean(boolean val){
        this.value = val;
        logger.debug("Created a new WitnessBoolean: {}", this);
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
    public WitnessAssertion not(WitnessEnv env) throws REException {
        return getFullAlgebra().not().toWitnessAlgebra();
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
        return clone();
    }

    @Override
    public WitnessAssertion DNF() {
        return this.clone();
    }

    @Override
    public WitnessAssertion toOrPattReq(){
        return this;
    }

    @Override
    public boolean isBooleanExp() {
        return true;
    }

    @Override
    public boolean isRecursive(WitnessEnv env, LinkedList<WitnessVar> visitedVar) {
        return false;
    }

    @Override
    public WitnessVar buildOBDD(WitnessEnv env) {
        return value ? env.bdd.getTrueVar() : env.bdd.getFalseVar();
    }
}