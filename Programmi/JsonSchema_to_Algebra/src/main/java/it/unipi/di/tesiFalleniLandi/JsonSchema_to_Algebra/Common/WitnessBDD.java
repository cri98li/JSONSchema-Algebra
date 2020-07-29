package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.*;
import jdd.bdd.BDD;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WitnessBDD {
    private static WitnessBDD instance;

    public static WitnessBDD getInstance(){
        if(instance != null) return instance;

        instance = new WitnessBDD(10000, 10000);
        return instance;
    }

    private BiMap<WitnessVar, Integer> indexNode;
    private BDD bdd;

    public final WitnessVar trueVar;
    public final WitnessVar falseVar;


    private WitnessBDD(int nodesize, int cachesize){
        indexNode = HashBiMap.create();
        //creo il nuovo obdd, ma uso l'ordinamento già definito
        bdd = new BDD(nodesize, cachesize);


        trueVar = new WitnessVar("OBDD_true");
        falseVar = new WitnessVar("OBDD_false");
        indexNode.put(trueVar, bdd.createVar());
        indexNode.put(falseVar, bdd.createVar());
    }

    public WitnessVar createVar(){
        int i = bdd.createVar();
        WitnessVar var = new WitnessVar("OBDD_"+i);
        indexNode.put(var, i);

        return var;
    }

    public void createVar(WitnessVar var){
        if(indexNode.containsKey(var)) return;
        int i = bdd.createVar();
        indexNode.put(var, i);
    }

    public WitnessVar and(WitnessEnv env, WitnessVar u1, WitnessVar u2) throws WitnessBDDException, WitnessException {
        Integer i1 = indexNode.get(u1);
        Integer i2 = indexNode.get(u2);

        if(i1 == null || i2 == null) throw new WitnessBDDException("Variable not found!");

        int newI = bdd.and(i1, i2);


        WitnessVar var = indexNode.inverse().get(newI);
        if(var == null)
            var = new WitnessVar("OBDD_"+newI);
        WitnessAnd and = new WitnessAnd();
        and.add(u1);
        and.add(u2);

         and = (WitnessAnd) and.varNormalization_expansion(env); //TODO: CHECK

        env.add(var, and);

        indexNode.put(var, newI);

        return var;
    }


    public WitnessVar or(WitnessEnv env, WitnessVar u1, WitnessVar u2) throws WitnessBDDException, WitnessException {
        Integer i1 = indexNode.get(u1);
        Integer i2 = indexNode.get(u2);

        if(i1 == null || i2 == null) throw new WitnessBDDException("Variable not found!");

        int newI = bdd.or(i1, i2);

        WitnessVar var = indexNode.inverse().get(newI);
        if(var == null)
            var = new WitnessVar("OBDD_"+newI);
        WitnessOr or = new WitnessOr();
        or.add(u1);
        or.add(u2);

        or = (WitnessOr) or.varNormalization_expansion(env); //TODO: CHECK

        env.add(var, or);

        indexNode.put(var, newI);

        return var;
    }

}

class WitnessBDDException extends WitnessException{
    public WitnessBDDException(boolean msg) {
        super(msg);
    }

    public WitnessBDDException(String msg) {
        super(msg);
    }

    public WitnessBDDException(String message, Boolean assertionStatus) {
        super(message, assertionStatus);
    }
}


