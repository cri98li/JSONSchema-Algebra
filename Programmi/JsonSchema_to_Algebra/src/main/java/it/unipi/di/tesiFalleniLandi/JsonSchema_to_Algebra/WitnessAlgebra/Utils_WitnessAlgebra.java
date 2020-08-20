package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils_WitnessAlgebra {
    private static Logger logger = LogManager.getLogger(Utils_WitnessAlgebra.class);

    /**
     * Used by the variableNormalization phase to generate unique names for a new variable
     * @param assertion the assertion that needs a name
     * @return return a name generate as assertionSimpleName_assertion.incremental_number
     */
    private static long count = 0l;
    protected static String getName(WitnessAssertion assertion){
        return assertion.getClass().getSimpleName()+ "_" +count++;
    }
}
