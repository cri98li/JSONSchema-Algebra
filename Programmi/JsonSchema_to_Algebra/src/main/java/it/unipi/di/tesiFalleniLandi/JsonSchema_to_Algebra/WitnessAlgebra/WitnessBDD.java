package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessBDDException;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessException;
import jdd.bdd.BDD;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WitnessBDD {
    private static final int NODESIZE = 1000;
    private static final int CACHESIZE = 1000;

    private static BiMap<WitnessVar, Integer> indexNode;
    private static BDD bdd;

    private static WitnessVar trueVar;
    private static WitnessVar falseVar;

    public static WitnessVar getTrueVar(){
        return trueVar.clone();
    }

    public static WitnessVar getFalseVar(){
        return falseVar.clone();
    }

    private static Logger logger = LogManager.getLogger(WitnessBDD.class);

    static {
        indexNode = HashBiMap.create();
        //create new obdd, using the defined ordering
        bdd = new BDD(NODESIZE, CACHESIZE);


        trueVar = new WitnessVar("OBDD_true");
        falseVar = new WitnessVar("OBDD_false");
        indexNode.put(trueVar, bdd.createVar());
        indexNode.put(falseVar, bdd.createVar());
    }

    public static WitnessVar createVar(){
        int i = bdd.createVar();
        WitnessVar var = new WitnessVar("OBDD_"+i);
        indexNode.put(var, i);

        return var;
    }

    public static void createVar(WitnessVar var){
        if(indexNode.containsKey(var)) return;
        int i = bdd.createVar();
        indexNode.put(var, i);
    }

    public static WitnessVar and(WitnessEnv env, WitnessVar u1, WitnessVar u2) throws WitnessException {
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

        //and = (WitnessAnd) and.varNormalization_expansion(env); //TODO: CHECK su alcuni esempi viene fatta l'espansione prima che
                                                                    // siano state create tutte le variabili dentro ai ref.
                                                                    // Questo metodo viene richiamato dalla add a tempo di costruzione dell'env

        indexNode.put(var, newI);

        return var;
    }


    public static WitnessVar or(WitnessEnv env, WitnessVar u1, WitnessVar u2) throws WitnessBDDException, WitnessException {
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

        //or = (WitnessOr) or.varNormalization_expansion(env); //TODO: CHECK

        indexNode.put(var, newI);
        return var;
    }

    public static void rename(WitnessVar oldName, WitnessVar newName){
        if(!indexNode.containsKey(oldName))
            throw new RuntimeException("WitnessBDD rename element not in indexNode");

        indexNode.put(newName, indexNode.remove(oldName));
    }


    public static boolean contains(WitnessVar var){
        return indexNode.containsKey(var);
    }
}


