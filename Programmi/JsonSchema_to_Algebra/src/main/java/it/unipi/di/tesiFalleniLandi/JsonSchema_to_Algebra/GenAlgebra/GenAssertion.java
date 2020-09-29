package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;

import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;

import java.util.LinkedList;
import java.util.List;

public interface GenAssertion extends Cloneable{
    public String _sep = "\r\n";
    public enum statuses {Open, Sleeping, Empty, Populated };
//    public String _status = "_status";
//    public int _noWitness = -1;



    public JsonElement generate();
    public JsonElement generateNext();
    public String toString();
    public WitnessAssertion toWitnessAlgebra();

    public List<GenVar> usedVars();


}
