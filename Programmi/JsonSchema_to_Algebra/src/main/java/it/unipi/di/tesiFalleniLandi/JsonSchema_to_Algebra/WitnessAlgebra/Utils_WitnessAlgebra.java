package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicLong;

public class Utils_WitnessAlgebra {
    private static Logger logger = LogManager.getLogger(Utils_WitnessAlgebra.class);

    /**
     * Used by the variableNormalization phase to generate unique names for a new variable
     * @param assertion the assertion that needs a name
     * @return return a name generate as assertionSimpleName_assertion.incremental_number
     */
    private static AtomicLong count = new AtomicLong(0);
    protected static String getName(WitnessAssertion assertion){
        if(count.get() == Long.MAX_VALUE -1)
            count.set(0l);

        return assertion.getClass().getSimpleName()+ "_" + count.incrementAndGet();
    }
}
