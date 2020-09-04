package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;

import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;

import java.util.List;

public class GenVar implements GenAssertion{

    private String name ;
    private List<GenVar> uses;
    private List<GenVar> isUsedBy;

    public boolean isOpen() {
        return false; //TODO implement
    }

    public boolean isEmpty() {
        return false; //TODO implement
    }

    public boolean isPop() {
        return false; //TODO implement
    }

    public boolean isTrue(){
        return false; //TODO implment
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
    public GenAssertion fromWitness(WitnessAssertion w) {
        return null;
    }
}
