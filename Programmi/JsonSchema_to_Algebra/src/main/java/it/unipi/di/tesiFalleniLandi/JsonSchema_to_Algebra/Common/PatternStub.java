package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common;

import java.util.Set;

public class PatternStub {

    public static <PosixPattern> PosixPattern complement(PosixPattern a) {
        return null; //not(A)
    }
    
    public static <PosixPattern> PosixPattern intersect(PosixPattern a, PosixPattern b) {
        return null; // a*b
    }

    public static <PosixPattern> PosixPattern union(PosixPattern a, PosixPattern b) {
        return null;// a+b
    }

    public static <PosixPattern> Integer domainSize(PosixPattern a) {
        /*
        if(dom(a) is finite) return |a|
        else return null or (n<0)
         */
        return null;
    }

    public static <PosixPattern> Set<String> generateWord(PosixPattern a) { //maybe we can use an iterator to generate a string only when we need
        /*
        if(dom(a) is finite) return some words that match the pattern (many of them, up to exhaustion of the domain of X)
        else
            if(a is not empty) return 1 word that match
            else return null
         */
        return null;
    }

    public static <PosixPattern> boolean match(PosixPattern a, String str) {
        //match str against str
        return false;
    }

    public static <PosixPattern> String toString(PosixPattern p) {
        //Something printable for debug
        return null;
    }
}
