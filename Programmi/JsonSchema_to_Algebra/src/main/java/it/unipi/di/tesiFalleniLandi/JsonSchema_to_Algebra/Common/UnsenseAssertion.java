package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common;

public class UnsenseAssertion extends RuntimeException{
    public UnsenseAssertion() {
    }

    public UnsenseAssertion(String message) {
        super(message);
    }

    public UnsenseAssertion(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsenseAssertion(Throwable cause) {
        super(cause);
    }

    public UnsenseAssertion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
