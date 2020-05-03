package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.PosixPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Defs_Assertion;

import java.util.*;

public class WitnessEnv implements WitnessAssertion {
    private HashMap<WitnessVar, WitnessAssertion> varList;
    private WitnessVar rootVar;

    public WitnessEnv(){
        varList = new HashMap<>();
    }

    public void add(WitnessVar key, WitnessAssertion value){
        WitnessAnd and = new WitnessAnd();
        and.add(value);
        varList.put(key, and);
    }

    public void add(WitnessVar key, WitnessAnd value){
        varList.put(key, value);
    }

    public void setRootVar(WitnessVar key, WitnessAssertion value){
        WitnessAnd and = new WitnessAnd();
        and.add(value);
        varList.put(key, and);
        rootVar = key;
    }

    public void setRootVar(WitnessVar key, WitnessAnd value){
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
    public WitnessAssertion merge(WitnessAssertion a) {
        Set<Map.Entry<WitnessVar, WitnessAssertion>> entrySet = varList.entrySet();
        WitnessEnv newEnv = new WitnessEnv();

        for(Map.Entry<WitnessVar, WitnessAssertion> entry : entrySet) {
            WitnessVar name = entry.getKey();

            if(name.equals(rootVar))
                newEnv.setRootVar(new WitnessVar(name.getValue()), varList.get(name).merge(null));
            else
                newEnv.add(new WitnessVar(name.getValue()), varList.get(name).merge(null));
        }

        return newEnv;
    }

    @Override
    public WitnessType getGroupType() {
        return null; //???
    }

    @Override
    public Assertion getFullAlgebra() {
        Defs_Assertion defs = new Defs_Assertion();
        defs.setRootDef(rootVar.getValue(), varList.get(rootVar).getFullAlgebra());

        Set<Map.Entry<WitnessVar, WitnessAssertion>> entrySet = varList.entrySet();
        for(Map.Entry<WitnessVar, WitnessAssertion> entry : entrySet) {
            if(entry.getKey() != rootVar)
                defs.add(entry.getKey().getValue(), entry.getValue().getFullAlgebra());
        }

        return defs;
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
            else
                return true;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = varList != null ? varList.hashCode() : 0;
        result = 31 * result + (rootVar != null ? rootVar.hashCode() : 0);
        return result;
    }
}
