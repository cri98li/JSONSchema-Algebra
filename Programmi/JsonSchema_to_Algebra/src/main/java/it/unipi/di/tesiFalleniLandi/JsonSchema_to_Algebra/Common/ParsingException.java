package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common;

import org.antlr.v4.runtime.ANTLRErrorListener;

public class ParsingException extends Exception {
    public ParsingException() {
        super();
    }

    public ParsingException(String message) {
        super(message);
    }
}
