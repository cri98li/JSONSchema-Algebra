package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessBoolean;
import java.util.Random;

import java.util.LinkedList;
import java.util.List;

import static it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra.GenAssertion.statuses.Populated;

public class GenBool implements GenAssertion{
    private JsonElement witness;
    @Override
    public String toString() {
        return "GenBool";
    }

    @Override
    public JsonElement getWitness() {
        return witness;
    }

    @Override
    public statuses generate() {
        Random r = new Random();
        int chance = r.nextInt(2);
        if (chance == 1) {
            witness = new JsonPrimitive(true);
        } else {
            witness = new JsonPrimitive(false);
        }
        return statuses.Populated;
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
