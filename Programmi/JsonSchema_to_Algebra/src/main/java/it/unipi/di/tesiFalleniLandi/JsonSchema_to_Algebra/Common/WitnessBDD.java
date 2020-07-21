package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.*;
import jdd.bdd.BDD;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

public class WitnessBDD {
    private static HashMap<WitnessVar, Integer> indexNode;
    private static int nextIndex;

    public static void init(ConcurrentHashMap<WitnessVar, WitnessAssertion> varList){
        if(indexNode != null) throw new RuntimeException("This method must be called once!");

        indexNode = new HashMap<>();
        nextIndex = 0;
        for(Map.Entry<WitnessVar, ?> entry : varList.entrySet())
            indexNode.put(entry.getKey(), nextIndex++);
    }




    private BDD bdd;

    public WitnessBDD(){
        //creo il nuovo obdd, ma uso l'ordinamento già definito
        bdd = new BDD(10, 10);
    }

    public WitnessBDD(int nodesize, int cachesize){
        //creo il nuovo obdd, ma uso l'ordinamento già definito
        bdd = new BDD(nodesize, cachesize);
    }


    public WitnessVar and(WitnessEnv env, WitnessVar u1, WitnessVar u2) throws WitnessBDDException, WitnessException {
        Integer i1 = indexNode.get(u1);
        Integer i2 = indexNode.get(u2);

        if(i1 == null || i2 == null) throw new WitnessBDDException("Variable not found!");

        int newI = bdd.and(i1, i2);

        WitnessVar var = new WitnessVar("OBDD_"+newI);
        WitnessAnd and = new WitnessAnd();
        and.add(u1);
        and.add(u2);

         and = (WitnessAnd) and.variableNormalization_expansion(env); //TODO: CHECK

        env.add(var, and);

        indexNode.put(var, newI);

        return var;
    }


    public WitnessVar or(WitnessEnv env, WitnessVar u1, WitnessVar u2) throws WitnessBDDException, WitnessException {
        Integer i1 = indexNode.get(u1);
        Integer i2 = indexNode.get(u2);

        if(i1 == null || i2 == null) throw new WitnessBDDException("Variable not found!");

        int newI = bdd.or(i1, i2);

        WitnessVar var = new WitnessVar("OBDD_"+newI);
        WitnessOr or = new WitnessOr();
        or.add(u1);
        or.add(u2);

        or = (WitnessOr) or.variableNormalization_expansion(env); //TODO: CHECK

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


