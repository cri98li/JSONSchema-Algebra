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
    public WitnessAssertion merge(WitnessAssertion a);

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


    public WitnessAssertion clone();

    public WitnessAssertion not();

    public WitnessAssertion notElimination();

    //Se and, ritorna and sottoforma di gruppo, altrimenti propaga. se terminal node ritorna this
    public WitnessAssertion groupize();

    //colleziona tutte le definizioni da creare nella fase di variable normalization, il nome della variabile è dato da:
    public Set<WitnessAssertion> variableNormalization_separation();

    //espande le variabili "unguarded" con la loro definizione
    public WitnessAssertion variableNormalization_expansion(WitnessEnv env);

    public WitnessAssertion DNF();
}