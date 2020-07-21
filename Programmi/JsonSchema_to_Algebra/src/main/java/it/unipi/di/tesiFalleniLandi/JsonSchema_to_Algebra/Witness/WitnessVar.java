package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

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
    public void checkLoopReferences(WitnessEnv env, Collection<WitnessVar> varList) throws WitnessException {
        if(varList.contains(this)){
            throw new WitnessException("Recursive definition!!");
        }else{
            varList.add(this);
            env.getDefinition(this).checkLoopReferences(env, varList);
            varList.remove(this);
        }
    }

    @Override
    public WitnessAssertion mergeElement(WitnessAssertion a) {
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
    public void variableNormalization_separation(WitnessEnv env) {
    }

    @Override
    public WitnessAssertion variableNormalization_expansion(WitnessEnv env) {
        return this.clone();
    }

    @Override
    public WitnessAssertion DNF() {
        return this.clone();
    }

}