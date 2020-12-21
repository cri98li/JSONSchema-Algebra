package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;

import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;

import java.util.LinkedList;
import java.util.List;

public class GenArray implements GenAssertion {
    private JsonElement witness;

    private Double minItems, maxItems;
    private enum GenArrayType {NOCONT, ONECONT, MANYSIMPLECONT, GENERALCASE};
    private List<GenVar> items;
    private GenVar additionalItems;

    public JsonElement getWitness() {
        return witness;
    }


    @Override
    public statuses generate() {
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
    public List<GenVar> usedVars() {
        return new LinkedList<>();
    }


}
