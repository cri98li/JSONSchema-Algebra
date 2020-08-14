package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.*;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessException;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessFalseAssertionException;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessTrueAssertionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class WitnessUniqueItems implements WitnessAssertion{
    private static Logger logger = LogManager.getLogger(WitnessUniqueItems.class);

    public WitnessUniqueItems() {
        logger.trace("Creating a new WitnessUniqueItems");
    }

    @Override
    public String toString() {
        return "WitnessUniqueItems{}";
    }

    @Override
    public void checkLoopRef(WitnessEnv env, Collection<WitnessVar> varList) throws WitnessException {
        return;
    }

    @Override
    public WitnessAssertion mergeWith(WitnessAssertion a) throws REException {
        logger.trace("Merging {} with {}", a, this);
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
        t.add(FullAlgebraString.TYPE_ARRAY);

        //return new IfThenElse_Assertion(t, new Boolean_Assertion(false), null).toWitnessAlgebra();
        logger.trace("Merge returning {} ", t.not());
        return t.not().toWitnessAlgebra();
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(FullAlgebraString.TYPE_ARRAY);
    }

    @Override
    public Assertion getFullAlgebra() {
        return new UniqueItems_Assertion();
    }

    @Override
    public WitnessAssertion clone() {
        logger.trace("Cloning {}", this);
        return new WitnessUniqueItems();
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

    @Override
    public boolean equals(Object obj) {
        return this.getClass() == obj.getClass();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
