package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.AllOf_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Pattern_Assertion;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import patterns.Pattern;
import patterns.REException;

import java.util.*;

/**
 * support class for the canonicalization phase,
 * collect the assertion contained in AND, then proceeds with the separation of the group.
 * At the end of the process, every WitnessGroup, contains only one type and its related assertions.
 */
public class _WitnessGroup implements WitnessAssertion{
    private List<WitnessType> types; // type assertion list (like a OR)
    private List<WitnessAssertion> typedAssertions; // assertion list (excluded type assertion)

    public _WitnessGroup(){
        types = new LinkedList<>();
        typedAssertions = new LinkedList<>();
    }

    public void add(WitnessAssertion assertion) throws WitnessException {
        if(assertion.getClass() == WitnessType.class)
            if(types.isEmpty())
                types.addAll(((WitnessType) assertion).separeTypes());
            else
                if(types.contains(assertion)) {
                    types = new LinkedList<>();
                    types.add((WitnessType) assertion);
                }
                else
                    throw new WitnessException("Not possible allOf of two different type", false);
        else
            typedAssertions.add(assertion);
    }

    public List<WitnessAssertion> canonicalize() throws WitnessException {
        List<WitnessAssertion> returnList = new LinkedList<>();

        HashMap<WitnessType, _WitnessGroup> groups = new HashMap<>();
        if(!types.isEmpty()) {
            for (WitnessType type : types){
                _WitnessGroup group = new _WitnessGroup();
                group.add(type);
                groups.put(type, group);
            }

            for(WitnessAssertion assertion : typedAssertions){
                _WitnessGroup group = groups.get(assertion.getGroupType());
                if(group != null)
                    group.add(assertion);
            }
        }
        else{ //no type specified

            _WitnessGroup group = new _WitnessGroup();
            WitnessType type = new WitnessType(GrammarStringDefinitions.TYPE_NUMBER);
            group.add(type);
            groups.put(type, group);

            group = new _WitnessGroup();
            type = new WitnessType(GrammarStringDefinitions.TYPE_OBJECT);
            group.add(type);
            groups.put(type, group);

            group = new _WitnessGroup();
            type = new WitnessType(GrammarStringDefinitions.TYPE_ARRAY);
            group.add(type);
            groups.put(type, group);

            group = new _WitnessGroup();
            type = new WitnessType(GrammarStringDefinitions.TYPE_STRING);
            group.add(type);
            groups.put(type, group);

            group = new _WitnessGroup();
            type = new WitnessType(GrammarStringDefinitions.TYPE_BOOLEAN);
            group.add(type);
            groups.put(type, group);

            group = new _WitnessGroup();
            type = new WitnessType(GrammarStringDefinitions.TYPE_NULL);
            group.add(type);
            groups.put(type, group);

            for(WitnessAssertion assertion : typedAssertions)
                groups.get(assertion.getGroupType()).add(assertion);
        }

        for(Map.Entry<WitnessType, _WitnessGroup> entry : groups.entrySet())
            returnList.add(entry.getValue());

        return returnList;
    }

    @Override
    public WitnessAssertion mergeElement(WitnessAssertion a) {
        return null;
        //throw new UnsupportedOperationException();
    }

    @Override
    public WitnessAssertion merge() {
        /*WitnessAnd and = new WitnessAnd();
        and.add(types.get(0));

        for(WitnessAssertion assertion : typedAssertions)
            and.add(assertion);

        and = (WitnessAnd) and.merge();

        try {
            return and.groupize();
        }catch (WitnessException e){return null;}*/
        return this;
    }

    @Override
    public WitnessType getGroupType() {
        if(types.size() > 1)
            return null;
        else
            return types.get(0);
    }

    @Override
    public AllOf_Assertion getFullAlgebra() {
        AllOf_Assertion allOf = new AllOf_Assertion();

        for(WitnessType type : types)
            allOf.add(type.getFullAlgebra());

        for(WitnessAssertion assertion : typedAssertions)
            allOf.add(assertion.getFullAlgebra());

        allOf.add(new Pattern_Assertion(ComplexPattern.createFromName("sono un gruppo")));

        return allOf;
    }

    @Override
    public WitnessAssertion clone() {
        _WitnessGroup clone = new _WitnessGroup();

        for(WitnessType type : types)
            clone.types.add(type.clone());

        for(WitnessAssertion assertion : typedAssertions)
            clone.typedAssertions.add(assertion.clone());

        return clone;
    }

    @Override
    public WitnessAssertion not() throws WitnessException, REException {
        return getFullAlgebra().not().toWitnessAlgebra().groupize();
    }

    @Override
    public WitnessAssertion notElimination() throws WitnessException, REException {
        return getFullAlgebra().notElimination().toWitnessAlgebra().groupize();
    }

    @Override
    public WitnessAssertion groupize() {
        return this;
    }

    @Override
    public void variableNormalization_separation(WitnessEnv env) {
        for(WitnessAssertion assertion : typedAssertions)
            assertion.variableNormalization_separation(env);
    }

    @Override
    public WitnessAssertion variableNormalization_expansion(WitnessEnv env) throws WitnessException {
        _WitnessGroup newGroup = new _WitnessGroup();
        newGroup.types = new LinkedList<>(types);

        for(WitnessAssertion assertion : typedAssertions){
            if(assertion.getClass() == WitnessVar.class) {
                WitnessAssertion resolvedAssertion = env.getDefinition((WitnessVar) assertion);
                if(resolvedAssertion != null)
                    newGroup.add(env.getDefinition((WitnessVar) assertion));
                else
                    throw new ParseCancellationException("Definition not found: "+assertion.toString());
            }else
                newGroup.add(assertion.variableNormalization_expansion(env));
        }

        return newGroup;
    }

    @Override
    public WitnessAssertion DNF() throws WitnessException {
        _WitnessGroup newGroup = new _WitnessGroup();
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
        _WitnessGroup group = (_WitnessGroup) o;

        LinkedList<WitnessAssertion> check = new LinkedList<>();

        if(typedAssertions.size() >= group.typedAssertions.size()) {
            check.addAll(typedAssertions);
            check.removeAll(group.typedAssertions);
        }else{
            check.addAll(group.typedAssertions);
            check.removeAll(typedAssertions);
        }

        if(check.size() != 0) return false;

        check = new LinkedList<>();

        if(types.size() >= group.types.size()) {
            check.addAll(types);
            check.removeAll(group.types);
        }else{
            check.addAll(group.types);
            check.removeAll(types);
        }

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
