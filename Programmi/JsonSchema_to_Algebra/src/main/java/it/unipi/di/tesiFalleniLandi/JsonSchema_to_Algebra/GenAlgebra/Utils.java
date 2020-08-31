package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessEnv;

public class Utils {
    public static final String NOCONT = "NOCONT";
    public static final String ONECONT = "ONECONT";
    public static final String MANYSIMPLECONT = "MANYSIMPLECONT";
    public static final String GENERALCASE = "GENERALCASE";

    public GenEnv fromWitnessEnv(WitnessEnv env) {
        return new GenEnv();

    }
}
