package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;

import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;

public interface GenAssertion extends Cloneable{
    public JsonElement generate();
    public JsonElement generateNext();
    public String toString();
    public WitnessAssertion toWitnessAlgebra();

}
