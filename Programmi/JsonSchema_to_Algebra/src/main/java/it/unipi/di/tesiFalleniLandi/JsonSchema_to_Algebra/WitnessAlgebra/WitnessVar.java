package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Ref_Assertion;
import patterns.REException;

import java.util.*;

public class WitnessVar implements WitnessAssertion{
    private String name;

    public WitnessVar(String name) {
        this.name = name;
    }

    public String getValue(){
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
    public WitnessAssertion not() throws REException {
        return getFullAlgebra().not().toWitnessAlgebra();
    }

    @Override
    public WitnessAssertion notElimination() throws REException {
        return getFullAlgebra().notElimination().toWitnessAlgebra();
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
    public boolean isBooleanExp() {
        return true;
    }

    @Override
    public WitnessVar buildOBDD(WitnessEnv env) {
        env.obdd.createVar(this);
        return this;
    }

}