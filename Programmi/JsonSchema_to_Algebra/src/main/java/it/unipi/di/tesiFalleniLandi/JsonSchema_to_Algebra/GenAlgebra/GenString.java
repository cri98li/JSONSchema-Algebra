package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;

import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.ComplexPattern.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessPattern;

import java.util.LinkedList;
import java.util.List;

public class GenString implements GenAssertion{
    private ComplexPattern pattern;

    @Override
    public String toString() {
        return "GenString{" +
                "pattern=" + pattern +_sep +
                '}';
    }

    public GenString() {
    }

    public void setPattern(ComplexPattern pattern) {
        this.pattern = pattern;
    }

    public GenString(WitnessPattern wp){
        pattern = wp.getPattern();
    }
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
    public List<GenVar> usedVars() {
        return new LinkedList<>();
    }


}
