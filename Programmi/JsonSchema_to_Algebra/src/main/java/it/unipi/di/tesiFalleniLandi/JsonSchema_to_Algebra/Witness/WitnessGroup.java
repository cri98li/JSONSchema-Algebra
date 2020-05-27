package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.AllOf_Assertion;

import java.util.*;

public class WitnessGroup implements WitnessAssertion{
    private List<WitnessType> types;
    private List<WitnessAssertion> typedAssertions; //WitnessAnd ??? (dopo è richiesto nuovamente di fare and merging)

    public WitnessGroup(){
        types = new LinkedList<>();
        typedAssertions = new LinkedList<>();
    }

    public void add(WitnessAssertion assertion) throws WitnessException {
        if(assertion.getClass() == WitnessType.class)
            if(types.isEmpty())
                types.addAll(((WitnessType) assertion).separeTypes());
            else
                if(types.contains((WitnessType) assertion)) {
                    types = new LinkedList<>();
                    types.add((WitnessType) assertion);
                }
                else
                    throw new WitnessException("Not possible allOf of two different type", false);
        else
            typedAssertions.add(assertion);
    }

    //Separa i gruppi e richiama typeElimination
    public List<WitnessAssertion> canonicalize() throws WitnessException {
        List<WitnessAssertion> returnList = new LinkedList<>();

        HashMap<WitnessType, WitnessGroup> groups = new HashMap<>();
        if(!types.isEmpty()) {
            for (WitnessType type : types){
                WitnessGroup group = new WitnessGroup();
                group.add(type);
                groups.put(type, group);
            }

            for(WitnessAssertion assertion : typedAssertions){
                WitnessGroup group = groups.get(assertion.getGroupType());
                if(group != null)
                    group.add(assertion);
            }
        }
        else{
            //no type specified

            WitnessGroup group = new WitnessGroup();
            WitnessType type = new WitnessType(GrammarStringDefinitions.TYPE_NUMBER);
            group.add(type);
            groups.put(type, group);

            group = new WitnessGroup();
            type = new WitnessType(GrammarStringDefinitions.TYPE_OBJECT);
            group.add(type);
            groups.put(type, group);

            group = new WitnessGroup();
            type = new WitnessType(GrammarStringDefinitions.TYPE_ARRAY);
            group.add(type);
            groups.put(type, group);

            group = new WitnessGroup();
            type = new WitnessType(GrammarStringDefinitions.TYPE_STRING);
            group.add(type);
            groups.put(type, group);

            group = new WitnessGroup();
            type = new WitnessType(GrammarStringDefinitions.TYPE_BOOLEAN);
            group.add(type);
            groups.put(type, group);

            group = new WitnessGroup();
            type = new WitnessType(GrammarStringDefinitions.TYPE_NULL);
            group.add(type);
            groups.put(type, group);

            for(WitnessAssertion assertion : typedAssertions)
                groups.get(assertion.getGroupType()).add(assertion);
        }

        for(Map.Entry<WitnessType, WitnessGroup> entry : groups.entrySet())
            returnList.add(entry.getValue());

        return returnList;
    }


    /*private WitnessGroup assertionTypeElimination() throws WitnessException {
        if(types == null || types.size() != 1) throw new WitnessException("You need to perform canonicalize first");
        WitnessType type = this.types.get(0);
        WitnessGroup typeEliminatedGroup = new WitnessGroup();
        typeEliminatedGroup.add(type);
        for(WitnessAssertion assertion : typedAssertions){
            WitnessType assertionType = assertion.getGroupType();
            if(assertionType != null && assertionType.equals(type))
                typeEliminatedGroup.add(assertion);
        }

        return typeEliminatedGroup;
    }*/

    @Override
    public WitnessAssertion mergeElement(WitnessAssertion a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public WitnessAssertion merge() {
        throw new UnsupportedOperationException();
    }

    @Override
    public WitnessType getGroupType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public AllOf_Assertion getFullAlgebra() {
        AllOf_Assertion allOf = new AllOf_Assertion();

        for(WitnessType type : types)
            allOf.add(type.getFullAlgebra());

        for(WitnessAssertion assertion : typedAssertions)
            allOf.add(assertion.getFullAlgebra());

        return allOf;
    }

    @Override
    public WitnessAssertion clone() {
        WitnessGroup clone = new WitnessGroup();

        for(WitnessType type : types)
            clone.types.add(type.clone());

        for(WitnessAssertion assertion : typedAssertions)
            clone.typedAssertions.add(assertion.clone());

        return clone;
    }

    @Override
    public WitnessAssertion not() throws WitnessException {
        return getFullAlgebra().not().toWitnessAlgebra().groupize();
    }

    @Override
    public WitnessAssertion notElimination() throws WitnessException {
        return getFullAlgebra().notElimination().toWitnessAlgebra().groupize();
    }

    @Override
    public WitnessAssertion groupize() {
        return this;
    }

    @Override
    public Set<WitnessAssertion> variableNormalization_separation() {
        Set<WitnessAssertion> set = new HashSet<>();

        for(WitnessAssertion assertion : typedAssertions)
            set.addAll(assertion.variableNormalization_separation());

        return set;
    }

    @Override
    public WitnessAssertion variableNormalization_expansion(WitnessEnv env) throws WitnessException {
        WitnessGroup newGroup = new WitnessGroup();
        newGroup.types = new LinkedList<>(types);
        for(WitnessAssertion assertion : typedAssertions){
            if(assertion.getClass() == WitnessVar.class)
                newGroup.add(env.getDefinition((WitnessVar) assertion));
            else
                newGroup.add(assertion.variableNormalization_expansion(env));
        }

        return newGroup;
    }

    @Override
    public WitnessAssertion DNF() throws WitnessException {
        WitnessGroup newGroup = new WitnessGroup();
        newGroup.types = new LinkedList<>(types);

        for(WitnessAssertion assertion : typedAssertions){
            newGroup.add(assertion.DNF());
        }

        return newGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WitnessGroup group = (WitnessGroup) o;

        LinkedList<WitnessAssertion> check = new LinkedList<>();
        check.addAll(typedAssertions);
        check.removeAll(group.typedAssertions);
        if(check.size() != 0) return false;

        check.addAll(types);
        check.removeAll(group.types);
        return check.size() == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(types, typedAssertions);
    }

    public boolean isEmpty(){
        return typedAssertions.isEmpty() && types.isEmpty();
    }
}
