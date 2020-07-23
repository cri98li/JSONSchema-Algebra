package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.WitnessBDD;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Defs_Assertion;
import jdd.bdd.BDD;
import patterns.REException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class WitnessEnv implements WitnessAssertion {
    private ConcurrentHashMap<WitnessVar, WitnessAssertion> varList;
    private WitnessVar rootVar;

    public WitnessEnv(){
        varList = new ConcurrentHashMap<>();
    }

    public void add(WitnessVar key, WitnessAssertion value){
        //WitnessAnd and = new WitnessAnd();
        //and.add(value);
        if(WitnessAnd.class == value.getClass()){
            add(key, (WitnessAnd) value);
            return;
        }
        varList.put(key, value);
    }

    public void add(WitnessVar key, WitnessAnd value){
        if(value.isUnitaryAnd() == null)
            varList.put(key, value);
        else
            varList.put(key, value.isUnitaryAnd());
    }

    public WitnessAssertion getDefinition(WitnessVar var){
        return varList.get(var);
    }

    public void setRootVar(WitnessVar key, WitnessAssertion value){
        varList.put(key, value);
        rootVar = key;
    }

    @Override
    public String toString() {
        return "WitnessEnv{" +
                "var=" + varList +
                ", rootVar=" + rootVar +
                '}';
    }

    @Override
    public void checkLoopReferences(WitnessEnv env, Collection<WitnessVar> varList) throws WitnessException {

        for(Map.Entry<WitnessVar, WitnessAssertion> entry : this.varList.entrySet()) {
            varList = new HashSet<>();
            varList.add(entry.getKey());

            entry.getValue().checkLoopReferences(this, varList);
        }
    }



    @Override
    public WitnessEnv mergeElement(WitnessAssertion a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public WitnessAssertion merge() throws REException {
        Set<Map.Entry<WitnessVar, WitnessAssertion>> entrySet = varList.entrySet();
        WitnessEnv newEnv = new WitnessEnv();

        for(Map.Entry<WitnessVar, WitnessAssertion> entry : entrySet) {
            WitnessVar name = entry.getKey();

            if(name.equals(rootVar))
                newEnv.setRootVar(new WitnessVar(name.getValue()), varList.get(name).merge());
            else
                newEnv.add(new WitnessVar(name.getValue()), varList.get(name).merge());
        }

        return newEnv;
    }

    @Override
    public WitnessType getGroupType() {
        return null;
    }

    @Override
    public Assertion getFullAlgebra() {
        Defs_Assertion defs = new Defs_Assertion();
        if(rootVar != null) defs.setRootDef(rootVar.getValue(), varList.get(rootVar).getFullAlgebra());

        Set<Map.Entry<WitnessVar, WitnessAssertion>> entrySet = varList.entrySet();
        for(Map.Entry<WitnessVar, WitnessAssertion> entry : entrySet) {
            if(!entry.getKey().equals(rootVar))
                defs.add(entry.getKey().getValue(), entry.getValue().getFullAlgebra());
        }

        return defs;
    }

    @Override
    public WitnessEnv clone() {
        WitnessEnv clone = new WitnessEnv();

        clone.rootVar = rootVar;
        for(Map.Entry<WitnessVar, WitnessAssertion> entry : varList.entrySet())
            clone.varList.put(entry.getKey(), entry.getValue().clone());

        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessEnv that = (WitnessEnv) o;

        if(!that.rootVar.equals(this.rootVar))
            return false;

        if(that.varList.size() != this.varList.size())
            return false;

        for(Map.Entry<WitnessVar, WitnessAssertion> entry : that.varList.entrySet()) {
            if (!this.varList.containsKey(entry.getKey())) return false;
            if(!this.varList.get(entry.getKey()).equals(entry.getValue()))
                return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = varList != null ? varList.hashCode() : 0;
        result = 31 * result + (rootVar != null ? rootVar.hashCode() : 0);
        return result;
    }

    @Override
    public WitnessAssertion not() throws REException {
        return getFullAlgebra().not().toWitnessAlgebra();
    }

    @Override
    public WitnessAssertion notElimination() throws REException {
        return getFullAlgebra().notElimination().toWitnessAlgebra();
    }

    @Override
    public WitnessEnv groupize() throws WitnessException, REException {
        WitnessEnv env = new WitnessEnv();
        if(rootVar != null)
            env.rootVar = (WitnessVar) rootVar.groupize();

        for(Map.Entry<WitnessVar, WitnessAssertion> entry : this.varList.entrySet()) {
            env.add(entry.getKey(), entry.getValue().groupize());
        }

        return env;
    }

    @Override
    public void variableNormalization_separation(WitnessEnv env) {

        for(Map.Entry<WitnessVar, WitnessAssertion> entry : this.varList.entrySet())
            entry.getValue().variableNormalization_separation(this);
    }

    @Override
    public WitnessEnv variableNormalization_expansion(WitnessEnv env) throws WitnessException {
        WitnessEnv newEnv = new WitnessEnv();

        newEnv.rootVar = rootVar.clone();

        for(Map.Entry<WitnessVar, WitnessAssertion> entry : varList.entrySet())
            newEnv.add(entry.getKey(), entry.getValue().variableNormalization_expansion(this));

        if(!newEnv.equals(this))
            return newEnv.variableNormalization_expansion(null);

        return newEnv;
    }

    public WitnessEnv DNF() throws WitnessException {
        WitnessEnv dnf = new WitnessEnv();

        dnf.rootVar = rootVar;

        for(Map.Entry<WitnessVar, WitnessAssertion> entry : varList.entrySet())
            dnf.varList.put(entry.getKey(), (entry.getValue()).DNF());

        return dnf;
    }


    public void objectPrepare() throws REException, WitnessException {
        for(Map.Entry<WitnessVar, WitnessAssertion> entry : varList.entrySet())
            if(entry.getValue().getClass() == WitnessAnd.class)
                ((WitnessAnd) entry.getValue()).objectPrepare(this);
            else if(entry.getValue().getClass() == WitnessOr.class)
                ((WitnessOr) entry.getValue()).objectPrepare(this);
    }


    public WitnessEnv prepareTypes(){
        //deflist --> usiamo quella di env
        HashMap<BDD, WitnessVar> varhash = new HashMap<>();

        WitnessBDD.init(varList);

        //for(Map.Entry<WitnessVar, WitnessAssertion> pair : varList){
            //Per ogni gruppo G fai...
        //}

        return null;
    }

    private WitnessVar newVar(HashMap<BDD, WitnessVar> varhash, BDD boolExp){
        if(varhash.containsKey(boolExp)) return varhash.get(boolExp);
        else{


        }

        return null;
    }

    private void addVar(HashMap<BDD, WitnessVar> varhash, BDD BoolExp){

    }

    private WitnessAssertion BDDtoWitness(BDD boolExp){
        // dato l'obdd con i nomi delle variabili, restituire una rappresentazione in WintessAssertion
        // per aggiungere la nuova variabile

        return null;
    }

    private WitnessAssertion existsVar(HashMap<BDD, WitnessVar> varhash, WitnessAssertion boolExp){
        //BDD n = boolExp.getBDD();

        return null;
    }
}
