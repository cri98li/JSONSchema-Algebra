package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Ref_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessException;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessFalseAssertionException;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessTrueAssertionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

import java.lang.ref.WeakReference;
import java.util.*;

public class WitnessVar implements WitnessAssertion{
    private static Logger logger = LogManager.getLogger(WitnessVar.class);

    private static HashMap<String, String> rename;
    private static HashMap<String, LinkedList<WeakReference<WitnessVar>>> instances;


    static {
        rename = new HashMap<>();
        instances = new HashMap<>();
    }

    public static void rename(WitnessVar oldName, WitnessVar newName){
        rename(oldName.getName(), newName.getName());
    }

    public static void rename(String oldName, String newName){
        logger.trace("Renaming {} (old) to {} (new)", oldName, newName);
        newName = resolveName(newName); //mi assicuro che non sia un "rename intermedio"
        rename.put(oldName, newName); //aggiungo la ridenominazione

        //rinomino i vecchi
        renameInstances(newName, oldName);
    }


    /**
     * b,a --> add(a, bodya);           add(b, bodya) non creo b, scrivo su quelli giá istanziati a, e mi ricordo di cambiare nome alle nuove istanze nel costruttore
     * c,b
     *
     * resolve(c) --> resove(b) --> a
     */
    private static String resolveName(String oldName){
        if(rename.containsKey(oldName))    return resolveName(rename.get(oldName));

        return oldName;
    }

    private static void renameInstances(String newName, String oldName){
        List<WeakReference<WitnessVar>> instancesToBeRenamed = instances.remove(oldName);
        List<WeakReference<WitnessVar>> newNameInstances = instances.get(newName);

        if(instancesToBeRenamed == null) return;

        for(WeakReference<WitnessVar> weakReference : instancesToBeRenamed) {
            if(weakReference.get() == null) continue; //garbage collector

            weakReference.get().name = newName;
            newNameInstances.add(weakReference);
        }
    }



    private String name;

    public WitnessVar(String name) {
        String newName = resolveName(name);
        this.name = newName;

        if(instances.containsKey(name))
            instances.get(newName).add(new WeakReference<>(this));
        else {
            LinkedList<WeakReference<WitnessVar>> tmp = new LinkedList<>();
            tmp.add(new WeakReference<>(this));
            instances.put(newName, tmp);
        }

        logger.trace("Created a new WitnessVar with name=({}=resolveName({}))", newName, name);
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return "WitnessVar{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public WitnessAssertion merge() {
        return this;
    }

    @Override
    public void checkLoopRef(WitnessEnv env, Collection<WitnessVar> varList) throws WitnessException {
        if(varList.contains(this)){
            throw new WitnessException("Recursive definition!");
        }else{
            varList.add(this);
            env.getDefinition(this).checkLoopRef(env, varList);
            varList.remove(this);
        }
    }

    @Override
    public WitnessAssertion mergeWith(WitnessAssertion a) {
        if(a.getClass() == WitnessVar.class)
            return mergeElement((WitnessVar) a);

        return null;
    }

    public WitnessAssertion mergeElement(WitnessVar a) {
        if(a.name.equals(this.name)) return this;

        return null;
    }

    @Override
    public WitnessType getGroupType() {
        return null;
    }

    @Override
    public Assertion getFullAlgebra() {
        return new Ref_Assertion(name);
    }

    @Override
    public WitnessVar clone() {
        logger.trace("Cloning {}", this);
        return new WitnessVar(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessVar that = (WitnessVar) o;

        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public WitnessAssertion not(WitnessEnv env) throws REException, WitnessException {
        return env.getCoVarName(this);
    }

    @Override
    public WitnessAssertion groupize() {
        return this;
    }

    @Override
    public int countVarToBeExp(WitnessEnv env) {
        return env.getDefinition(this).countVarToBeExp(env);
    }

    @Override
    public Float countVarWithoutBDD(WitnessEnv env, List<WitnessVar> visitedVar) {
        if(visitedVar.contains(this))
            return Float.POSITIVE_INFINITY;

        if(WitnessBDD.contains(this))//se ha un obdd associato sono ok
            return 0f;

        visitedVar.add(this);
        return 1f + env.getDefinition(this).countVarWithoutBDD(env, visitedVar);
    }

    public boolean isRecursive(WitnessEnv env, LinkedList<WitnessVar> visitedVar){
        if(visitedVar.contains(this)) return true;

        visitedVar.add(this);
        return env.getDefinition(this).isRecursive(env, visitedVar);
    }

    @Override
    public void varNormalization_separation(WitnessEnv env) {
        return;
    }

    @Override
    public WitnessAssertion varNormalization_expansion(WitnessEnv env) {
        return this;
    }

    @Override
    public WitnessAssertion DNF() {
        return this.clone();
    }

    @Override
    public WitnessAssertion toOrPattReq() throws WitnessFalseAssertionException, WitnessTrueAssertionException {
        return this;
    }

    @Override
    public boolean isBooleanExp() {
        return true;
    }

    @Override
    public WitnessVar buildOBDD(WitnessEnv env) throws WitnessException {
        if(WitnessBDD.contains(new WitnessVar("forced_"+name)))
            return new WitnessVar("forced_"+name);

        if(!WitnessBDD.contains(this))
            throw new WitnessException("buildOBDD richiamato su variabile senza obdd associato (ne forzato): "+name);
        return this;
    }


    protected void forceOBDD(){
        WitnessBDD.createVar(new WitnessVar("forced_"+name));
    }
}