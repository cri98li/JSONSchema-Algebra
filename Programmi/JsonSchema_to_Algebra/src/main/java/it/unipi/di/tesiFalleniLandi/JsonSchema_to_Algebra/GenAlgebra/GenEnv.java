package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GenEnv implements GenAssertion{

    private HashMap<GenEnv, List<GenAssertion>> varList;
    private BiMap<GenVar,GenVar> coVar;
    private GenVar rootVar;
    private List<GenVar> openVars, sleepingVars, emptyVars, popVars;

    public GenEnv() {
        varList = new HashMap<>();
        coVar = HashBiMap.create();
        openVars = new LinkedList<>();
        sleepingVars = new LinkedList<>();
        emptyVars = new LinkedList<>();
        popVars = new LinkedList<>();
    }

    /**
     * enforce invariant through setters
     * @param varList
     */
    public void setVarList(HashMap<GenEnv, List<GenAssertion>> varList) {
        this.varList = varList;
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
}
