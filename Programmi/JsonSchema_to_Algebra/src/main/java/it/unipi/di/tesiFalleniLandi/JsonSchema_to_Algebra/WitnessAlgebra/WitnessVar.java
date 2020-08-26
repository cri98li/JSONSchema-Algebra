package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Ref_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

import java.lang.ref.WeakReference;
import java.util.*;

public class WitnessVar implements WitnessAssertion{
    private static Logger logger = LogManager.getLogger(WitnessVar.class);

    private static HashMap<String, String> renamed; //map oldName --> newName
    private static HashMap<String, LinkedList<WeakReference<WitnessVar>>> instances; //map of all instances of WitnessVar

    //We use WeakReference to avoid memory leaks. An object stored in WeakReference do not increment the numbers of pointer
    //that refer to that object, so if the object is pointed only by a WeakReference, the garbage collector can delete it

    static {
        renamed = new HashMap<>();
        instances = new HashMap<>();
    }

    public static void rename(WitnessVar oldName, WitnessVar newName) {
        if(oldName == null || newName == null)
            System.out.println("QUI");

        rename(oldName.getName(), newName.getName());
    }

    //rename all the instances of ref(oldName) into ref(newName)
    public static void rename(String oldName, String newName){
        if(oldName.equals(newName)) return;

        logger.trace("Renaming {} (old) to {} (new)", oldName, newName);

        newName = resolveName(newName); //get the "real" name to be renamed.
        renamed.put(oldName, newName); //save the rename operation.

        //rename instances
        renameInstances(newName, oldName);
    }


    /**
     * example
     * if b=a --> add(a, body_a);        add(b, body_a) do not create b,
     *                                   rewrite all instances of ref(b) as ref(a),
     *                                   remember that if someone want to create ref(b), we must instead return ref(a)
     * c=b
     *
     * resolve(c) --> resolve(b) --> a
     */
    private static String resolveName(String oldName){
        if(renamed.containsKey(oldName))    return resolveName(renamed.get(oldName));

        return oldName;
    }

    /**
     * Rename all the instances of oldName with newName
     * @param newName
     * @param oldName
     */
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

        //Add the new instace to the list of all the instaces
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
    public void checkLoopRef(WitnessEnv env, Collection<WitnessVar> varList) throws RuntimeException {
        if(varList.contains(this)){
            throw new RuntimeException("Recursive definition: " + name);
        }else{
            varList.add(this);
            env.getDefinition(this).checkLoopRef(env, varList);
            varList.remove(this);
        }
    }

    @Override
    public void reachableRefs(Set<WitnessVar> collectedVar, WitnessEnv env) throws RuntimeException {
        if(!env.containsVar(this))
            throw new RuntimeException("Unreachable ref: " + name);
        else{
            if(collectedVar.contains(this)) return;//loop
            collectedVar.add(this);
            env.getDefinition(this).reachableRefs(collectedVar, env);
        }
    }

    @Override
    public WitnessAssertion mergeWith(WitnessAssertion a) throws REException {
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
    public WitnessAssertion not(WitnessEnv env) throws REException {
        return env.getCoVar(this);
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

        if(env.bdd.contains(this))//if there is an obdd with the same name i can return 0;
            return 0f;

        visitedVar.add(this);
        Float count = env.getDefinition(this).countVarWithoutBDD(env, visitedVar);
        visitedVar.remove(this);
        return 1f + count;
    }

    public boolean isRecursive(WitnessEnv env, LinkedList<WitnessVar> visitedVar){
        if(visitedVar.contains(this)) return true;

        visitedVar.add(this);
        boolean tmp = env.getDefinition(this).isRecursive(env, visitedVar);
        visitedVar.remove(this);
        return tmp;
    }

    @Override
    public List<Map.Entry<WitnessVar, WitnessAssertion>> varNormalization_separation(WitnessEnv env) {
        return new LinkedList<>();
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
    public WitnessAssertion toOrPattReq() {
        return this;
    }

    @Override
    public boolean isBooleanExp() {
        return true;
    }

    @Override
    public WitnessVar buildOBDD(WitnessEnv env) throws WitnessException {
        if(env.bdd.contains(new WitnessVar("forced_"+name)))
            return new WitnessVar("forced_"+name);

        if(!env.bdd.contains(this)) {
            throw new WitnessException("buildOBDD richiamato su variabile senza obdd associato (ne forzato): " + name);
        }

        return this;
    }


    protected void forceOBDD(WitnessEnv env){
        logger.warn("forced the creation of {}, that is not ok", new WitnessVar("forced_"+name));
        env.bdd.createVar(new WitnessVar("forced_"+name));
    }
}