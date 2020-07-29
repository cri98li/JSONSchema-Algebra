package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import patterns.REException;

import java.util.Collection;

public interface WitnessAssertion extends Cloneable{
    /**
     * check for unguarded loop between ref
     * @param env
     * @param varList
     */
    public void checkLoopRef(WitnessEnv env, Collection<WitnessVar> varList) throws WitnessException;

    /**
     * Perform the and-merging as described in the paper plus other little simplifications
     * @param a
     * @return null if the objects cannot be merged, otherwise it returns the merged object
     *              (if the object cannot be unified within a single assertion, it returns a WitnessAnd)
     */
    public WitnessAssertion mergeWith(WitnessAssertion a) throws REException;

    /**
     * propagates merge over the schema
     * @return
     */
    public WitnessAssertion merge() throws REException;

    /**
     * Return the relative Type of an assertion
     * @return if the assertion is related to a type return an instance of WitnessType, otherwise null
     */
    public WitnessType getGroupType();

    /**
     * Translate the object from the witness-algebra representation to the full-algebra
     * @return a FullAlgebra object
     */
    public Assertion getFullAlgebra();

    /**
     * Clone the object
     * @return a new clone assertion
     */
    public WitnessAssertion clone();

    /**
     *
     * @return return the complement of the current object
     */
    public WitnessAssertion not() throws WitnessException, REException;

    /**
     *
     * @return apply the not-elimination/not-pushing step as described in the paper, then return the new instance
     */
    public WitnessAssertion notElimination() throws WitnessException, REException;

    //Se and, ritorna and sottoforma di gruppo, altrimenti propaga. se terminal node ritorna this

    /**
     * If this is and andAssertion, the method return a new andAssertion canonicalized
     * if this is a terminal node (mof, pattern...) the method return this, otherwise the method propagate to the contained assertion
     * @return return an object as described before
     * @throws WitnessException
     */
    public WitnessAssertion groupize() throws WitnessException, REException;

    /**
     * count the variable number contained in the assertion
     * @return
     * @param env
     */
    public int countVarToBeExp(WitnessEnv env);

    /**
     * Collect and save all the definition to create in the variable normalization phase,
     * the name of the variable is: ClassName + hash of the value.
     * @param env collection of (variableName, variableContent) where the method add all the new variables
     */
    public void varNormalization_separation(WitnessEnv env) throws WitnessException, REException;

    /**
     * Expands all the unguarded variables with their respective definition
     * @param env collection of (variableName, variableContent)*
     * @return  return this.clone if the assertion do not contain any unguarded variables, otherwise return a new assertion as described before
     * @throws WitnessException
     */
    public WitnessAssertion varNormalization_expansion(WitnessEnv env) throws WitnessException;

    /**
     * Reduce the assertion in Disjunctive Normal Form
     * @return the same assertion reduced in Disjunctive Normal Form
     * @throws WitnessException
     */
    public WitnessAssertion DNF() throws WitnessException;

    public boolean isBooleanExp();

    public WitnessVar buildOBDD(WitnessEnv env) throws WitnessException;
}