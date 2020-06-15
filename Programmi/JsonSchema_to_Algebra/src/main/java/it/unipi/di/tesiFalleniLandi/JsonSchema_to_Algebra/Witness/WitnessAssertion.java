package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;

import java.util.Set;

public interface WitnessAssertion extends Cloneable{
    /**
     * Perform the and-merging as described in the paper plus other little simplifications
     * @param a
     * @return null if the objects cannot be merged, otherwise it returns the merged object
     *              (if the object cannot be unified within a single assertion, it returns a WitnessAnd)
     */
    public WitnessAssertion mergeElement(WitnessAssertion a);

    /**
     * propagates merge over the schema
     * @return
     */
    public WitnessAssertion merge();

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
    public WitnessAssertion not() throws WitnessException;

    /**
     *
     * @return apply the not-elimination/not-pushing step as described in the paper, then return the new instance
     */
    public WitnessAssertion notElimination() throws WitnessException;

    //Se and, ritorna and sottoforma di gruppo, altrimenti propaga. se terminal node ritorna this

    /**
     * If this is and andAssertion, the method return a new andAssertion with witnessgroup[0..*], witnessVar[0..*], WitnessOr[0..*].
     * if this is a terminal node (mof, pattern...) the method return this, otherwise the method propagate to the contained assertion
     * @return return an object as described before
     * @throws WitnessException
     */
    public WitnessAssertion groupize() throws WitnessException;

    /**
     * Collect and save all the definition to create in the variable normalization phase,
     * the name of the variable is: ClassName + hash of the value.
     * @param env collection of (variableName, variableContent) where the method add all the new variables
     */
    public void variableNormalization_separation(WitnessEnv env);

    /**
     * Expands all the unguarded variables with their respective definition
     * @param env collection of (variableName, variableContent)*
     * @return  return this.clone if the assertion do not contain any unguarded variables, otherwise return a new assertion as described before
     * @throws WitnessException
     */
    public WitnessAssertion variableNormalization_expansion(WitnessEnv env) throws WitnessException;

    /**
     * Reduce the assertion in Disjunctive Normal Form
     * @return the same assertion reduced in Disjunctive Normal Form
     * @throws WitnessException
     */
    public WitnessAssertion DNF() throws WitnessException;
}