package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

public class WitnessException extends Exception{
    public final Boolean assertionStatus;

    public WitnessException(boolean msg) {
        assertionStatus = msg;
    }

    public WitnessException(String msg) {
        super(msg);
        this.assertionStatus = null;
    }

    public WitnessException(String message, Boolean assertionStatus) {
        super(message);
        this.assertionStatus = assertionStatus;
    }
}
