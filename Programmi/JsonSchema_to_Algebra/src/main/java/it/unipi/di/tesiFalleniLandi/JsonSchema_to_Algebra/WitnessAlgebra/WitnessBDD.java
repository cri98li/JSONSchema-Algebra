package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessBDDException;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessException;
import jdd.bdd.BDD;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WitnessBDD {
    private static Logger logger = LogManager.getLogger(WitnessBDD.class);
    private static final int NODESIZE = 1000;
    private static final int CACHESIZE = 1000;

    private BiMap<WitnessVar, Integer> indexNode;
    private BDD bdd;

    private final WitnessVar trueVar;
    private final WitnessVar falseVar;


    public WitnessVar getTrueVar(){
        return trueVar.clone();
    }
    public WitnessVar getFalseVar(){
        return falseVar.clone();
    }

    public WitnessBDD() {
        indexNode = HashBiMap.create();
        //create new obdd, using the defined ordering
        bdd = new BDD(NODESIZE, CACHESIZE);


        trueVar = new WitnessVar("OBDD_true");
        falseVar = new WitnessVar("OBDD_false");
        indexNode.put(trueVar, bdd.getOne());
        indexNode.put(falseVar, bdd.getZero());
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

    public WitnessVar and(WitnessEnv env, WitnessVar u1, WitnessVar u2) throws WitnessException {
        Integer i1 = indexNode.get(u1);
        Integer i2 = indexNode.get(u2);

        if(i1 == null || i2 == null) throw new WitnessBDDException("Variable not found!");

        int newI = bdd.and(i1, i2);

        WitnessVar var = indexNode.inverse().get(newI);

        if(var == null) {
            var = new WitnessVar("OBDD_" + newI);
            indexNode.put(var, newI);
        }

        return var;
    }


    public WitnessVar or(WitnessEnv env, WitnessVar u1, WitnessVar u2) throws WitnessBDDException, WitnessException {
        Integer i1 = indexNode.get(u1);
        Integer i2 = indexNode.get(u2);

        if(i1 == null || i2 == null) throw new WitnessBDDException("Variable not found!");

        int newI = bdd.or(i1, i2);

        WitnessVar var = indexNode.inverse().get(newI);

        if(var == null) {
            var = new WitnessVar("OBDD_" + newI);
            indexNode.put(var, newI);
        }

        return var;
    }

    public void rename(WitnessVar oldName, WitnessVar newName){
        if(!indexNode.containsKey(oldName)) {
            logger.warn("Renaming in BDD oldName: {} with newName: {}", oldName, newName);
            return;
        }

        indexNode.put(newName, indexNode.remove(oldName));
    }


    public boolean contains(WitnessVar var){
        return indexNode.containsKey(var);
    }
}


