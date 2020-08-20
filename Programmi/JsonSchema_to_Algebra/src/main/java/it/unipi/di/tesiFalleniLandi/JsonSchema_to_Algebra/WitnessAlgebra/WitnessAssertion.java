package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessException;
import patterns.REException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public interface WitnessAssertion extends Cloneable{
    /**
     * check for unguarded loop between ref
     * @param env
     * @param varList
     */
    void checkLoopRef(WitnessEnv env, Collection<WitnessVar> varList) throws WitnessException;

    /**
     * Perform the and-merging as described in the paper plus other little simplifications
     * @param a
     * @return null if the objects cannot be merged, otherwise it returns the merged object
     *              (if the object cannot be unified within a single assertion, it returns a WitnessAnd)
     */
    WitnessAssertion mergeWith(WitnessAssertion a) throws REException;

    /**
     * propagates merge over the schema
     * @return
     */
    WitnessAssertion merge() throws REException;

    /**
     * Return the relative Type of an assertion
     * @return if the assertion is related to a type return an instance of WitnessType, otherwise null
     */
    WitnessType getGroupType();

    /**
     * Translate the object from the witness-algebra representation to the full-algebra
     * @return a FullAlgebra object
     */
    Assertion getFullAlgebra();

    /**
     * Clone the object
     * @return a new clone assertion
     */
    WitnessAssertion clone();

    /**
     *
     * @return return the complement of the current object
     */
    WitnessAssertion not(WitnessEnv env) throws WitnessException, REException;

    /**
     * If this is and andAssertion, the method return a new andAssertion canonicalized
     * if this is a terminal node (mof, pattern...) the method return this, otherwise the method propagate to the contained assertion
     * @return return an object as described before
     * @throws WitnessException
     */
    WitnessAssertion groupize() throws WitnessException, REException;

    /**
     * count all variables in this
     * @return
     * @param env
     * @param visitedVar
     */
    Float countVarWithoutBDD(WitnessEnv env, List<WitnessVar> visitedVar);


    /**
     * count all the unguarded variables ref in this
     * @return
     * @param env
     */
    int countVarToBeExp(WitnessEnv env);

    /**
     * Collect and save all the definition to create in the variable normalization phase,
     * the name of the variable is: ClassName + hash of the value.
     * @param env collection of (variableName, variableContent) where the method add all the new variables
     */
    void varNormalization_separation(WitnessEnv env) throws WitnessException, REException;

    /**
     * Expands all the unguarded variables with their respective definition
     * @param env collection of (variableName, variableContent)*
     * @return  return this.clone if the assertion do not contain any unguarded variables, otherwise return a new assertion as described before
     * @throws WitnessException
     */
    WitnessAssertion varNormalization_expansion(WitnessEnv env) throws WitnessException;

    /**
     * Reduce the assertion in Disjunctive Normal Form
     * @return the same assertion reduced in Disjunctive Normal Form
     * @throws WitnessException
     */
    WitnessAssertion DNF() throws WitnessException;

    WitnessAssertion toOrPattReq();

    boolean isBooleanExp();

    boolean isRecursive(WitnessEnv env, LinkedList<WitnessVar> visitedVar);

    WitnessVar buildOBDD(WitnessEnv env) throws WitnessException;
}