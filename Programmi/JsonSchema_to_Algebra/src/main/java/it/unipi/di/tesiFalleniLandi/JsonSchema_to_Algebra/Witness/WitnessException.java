package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

public class WitnessException extends Exception{
    public final boolean booleanValue;


    public WitnessException(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }
}
