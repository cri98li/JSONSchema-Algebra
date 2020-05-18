package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Type_Assertion;

import java.util.*;

public class WitnessType implements WitnessAssertion{
    private List<String> type;

    protected WitnessType(){
        type = new LinkedList<>();
    }

    public WitnessType(String str){
        type = new LinkedList<>();
        type.add(str);
    }

    public WitnessType(List<String> list){
        type = new LinkedList<>();
        type.addAll(list);
    }

    public void add(String type){
        this.type.add(type);
    }

    public List<WitnessType> separeTypes(){
        List<WitnessType> returnList = new LinkedList<>();

        for(String str : type)
            returnList.add(new WitnessType(str));

        return returnList;
    }

    @Override
    public String toString() {
        return "WitnessType{" +
                "type=" + type +
                '}';
    }

    @Override
    public WitnessAssertion merge(WitnessAssertion a) {
        if(a == null) return this;

        if(a.getClass() == WitnessOr.class || a.getClass() == WitnessAnd.class) return a.merge(this);

        if(a.getClass() == this.getClass())
            return this.merge((WitnessType) a);

        return null;
    }


    public WitnessAssertion merge(WitnessType a) {

        WitnessType tmp = a;
        WitnessType newType = new WitnessType();

        //same type
        if(a.equals(this))
            return this;

        for(String str : type){//**
            if(a.type.contains(str)) {
                newType.add(str);
                continue;
            }

            if(a.type.contains(GrammarStringDefinitions.TYPE_INTEGER) && str.equals(GrammarStringDefinitions.TYPE_NUMBER)
                || a.type.contains(GrammarStringDefinitions.TYPE_NUMBER) && str.equals(GrammarStringDefinitions.TYPE_INTEGER))
                newType.add(GrammarStringDefinitions.TYPE_INTEGER);

            if(a.type.contains(GrammarStringDefinitions.TYPE_NUMBER) && str.equals(GrammarStringDefinitions.TYPE_NUMNOTINT)
                    || a.type.contains(GrammarStringDefinitions.TYPE_NUMNOTINT) && str.equals(GrammarStringDefinitions.TYPE_NUMBER))
                newType.add(GrammarStringDefinitions.TYPE_NUMNOTINT);
        }

        if(newType.type.isEmpty())
            return new WitnessBoolean(false);

        return newType;
    }

    @Override
    public WitnessType getGroupType() {
        return this;
    }

    @Override
    public Assertion getFullAlgebra() {
        Type_Assertion t = new Type_Assertion();

        for(String str : type)
            t.add(str);

        return t;
    }

    @Override
    public WitnessType clone() {
        return new WitnessType(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WitnessType type1 = (WitnessType) o;

        LinkedList<String> list = new LinkedList();
        list.addAll(this.type);
        list.removeAll(type1.type);

        return list.size() == 0;

    }

    @Override
    public int hashCode() {
        int hash = 0;

        for(String str : type)
            hash += Objects.hash(str);

        return hash;
    }

    @Override
    public WitnessAssertion not() {
        return getFullAlgebra().not().toWitnessAlgebra();
    }

    @Override
    public WitnessAssertion notElimination() {
        return getFullAlgebra().notElimination().toWitnessAlgebra();
    }

    @Override
    public WitnessAssertion groupize() {
        return this;
    }

    @Override
    public Set<WitnessAssertion> variableNormalization_separation() {
        return new HashSet<>();
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