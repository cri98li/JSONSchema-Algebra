package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.PatternRequired_Assertion;
import patterns.REException;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class OrPattReq implements WitnessAssertion{

    List<WitnessPattReq> ORP;

    @Override
    public void checkLoopReferences(WitnessEnv env, Collection<WitnessVar> varList) throws WitnessException {
        throw new UnsupportedOperationException();
    }

    @Override
    public WitnessAssertion mergeElement(WitnessAssertion a) throws REException {
        throw new UnsupportedOperationException();
    }

    @Override
    public WitnessAssertion merge() throws REException {
        throw new UnsupportedOperationException();
    }

    @Override
    public WitnessType getGroupType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Assertion getFullAlgebra() {
        throw new UnsupportedOperationException();
    }

    @Override
    public WitnessAssertion clone() {
        throw new UnsupportedOperationException();
    }

    @Override
    public WitnessAssertion not() throws WitnessException, REException {
        throw new UnsupportedOperationException();
    }

    @Override
    public WitnessAssertion notElimination() throws WitnessException, REException {
        throw new UnsupportedOperationException();
    }

    @Override
    public WitnessAssertion groupize() throws WitnessException, REException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void variableNormalization_separation(WitnessEnv env) {
        throw new UnsupportedOperationException();
    }

    @Override
    public WitnessAssertion variableNormalization_expansion(WitnessEnv env) throws WitnessException {
        throw new UnsupportedOperationException();
    }

    @Override
    public WitnessAssertion DNF() throws WitnessException {
        throw new UnsupportedOperationException();
    }
}
