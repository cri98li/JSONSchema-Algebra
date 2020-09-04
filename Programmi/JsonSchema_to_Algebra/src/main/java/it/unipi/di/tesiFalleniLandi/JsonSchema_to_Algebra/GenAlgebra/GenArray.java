package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;

import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;

import java.util.List;

public class GenArray implements GenAssertion {

    private Double minItems, maxItems;
    private enum GenArrayType {NOCONT, ONECONT, MANYSIMPLECONT, GENERALCASE};
    private List<GenVar> items;
    private GenVar additionalItems;


    @Override
    public JsonElement generate() {
        return null;
    }

    @Override
    public JsonElement generateNext() {
        return null;
    }

    @Override
    public WitnessAssertion toWitnessAlgebra() {
        return null;
    }

    @Override
    public GenAssertion fromWitness(WitnessAssertion w) {
        return null;
    }
}
