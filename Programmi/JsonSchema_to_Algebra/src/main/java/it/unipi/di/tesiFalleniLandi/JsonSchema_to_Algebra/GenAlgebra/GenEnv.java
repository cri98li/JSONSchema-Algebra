package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessEnv;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GenEnv {

    private HashMap<GenVar, List<GenAssertion>> varList;
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

    public GenEnv(WitnessEnv env){
        varList = new HashMap<>();
//        for(Map.Entry me: env.getV)

    }

    /**
     * enforce invariant through setters
     * @param varList
     */
    public void setVarList(HashMap<GenEnv, List<GenAssertion>> varList) {

    }

    public JsonElement generate() {
        return null;
    }




}
