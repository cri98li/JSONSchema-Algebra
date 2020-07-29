package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

public class Utils_WitnessAlgebra {
    /**
     * Used by the variableNormalization phase to generate unique names for a new variable
     * @param assertion the assertion that needs a name
     * @return return a name generate as assertionSimpleName_assertion.hashcode
     */
    private static long count = 0l;
    protected static String getName(WitnessAssertion assertion){
        return assertion.getClass().getSimpleName()+ "_" +count++;
    }
}
