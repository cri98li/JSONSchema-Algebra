package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.ComplexPattern.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessPattern;

import java.util.LinkedList;
import java.util.List;

public class GenString implements GenAssertion{
    private ComplexPattern pattern;
    private JsonElement witness;


    @Override
    public String toString() {
        return "GenString{" +
                "pattern=" + pattern +_sep +
                '}';
    }

    public GenString() {
    }

    public JsonElement getWitness() {
        return witness;
    }


    public void setPattern(ComplexPattern pattern) {
        this.pattern = pattern;
    }

    public GenString(WitnessPattern wp){
        pattern = wp.getPattern();
    }
    @Override
    public statuses generate() {
        witness = new JsonPrimitive(pattern.generateWords().iterator().next());
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
