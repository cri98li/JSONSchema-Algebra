package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

public class Utils_Witness {
    protected static String getName(WitnessAssertion assertion){
        return assertion.getClass().getSimpleName()+ "_" +assertion.hashCode();
    }
}
