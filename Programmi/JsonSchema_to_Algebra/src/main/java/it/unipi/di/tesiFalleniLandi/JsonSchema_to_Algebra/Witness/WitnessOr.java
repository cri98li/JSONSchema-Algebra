package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.AnyOf_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;

import java.util.*;

public class WitnessOr implements WitnessAssertion{
    private Map<Object, List<WitnessAssertion>> orList;

    public WitnessOr() {
        this.orList = new HashMap<>();
    }

    public boolean add(WitnessAssertion el){
        if(el.getClass() == this.getClass()) return add((WitnessOr) el);
        else if(el.getClass() == WitnessUniqueItems.class || el.getClass() == WitnessRepeateditems.class)
            return addUni_Rep(el);
        else {
            if(orList.containsKey(el.getClass()))
                if(orList.get(el.getClass()).contains(el))
                    return false;
                else{
                    orList.get(el.getClass()).add(el);
                    return true;
                }
            else{
                List<WitnessAssertion> list = new LinkedList<>();
                list.add(el);
                orList.put(el.getClass(), list);
                return true;
            }
        }
    }

    public boolean add(WitnessOr el){
        boolean b = false;
        for(Map.Entry<Object, List<WitnessAssertion>> entry : el.orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                b |= add(assertion);

        return b;
    }

    public boolean addUni_Rep(WitnessAssertion el){
        if(!orList.containsKey(el.getClass()))
        {
            List<WitnessAssertion> list = new LinkedList<>();
            list.add(el);
            orList.put(el.getClass(), list);
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "WitnessOr{" + "\r\n" +
                "orList=" + orList +
                "\r\n" +
                '}';
    }

    /*
    a può essere un oggetto di un qualsiasi tipo,
        per ogni( b = orList.get(a.class)[i])
            if(b.merge(a).equals(a)
                quando è soddisfatto a è soddisfatto anche l'elemento dentro all'or

    nel caso di a.class = or?
     */
    @Override
    public WitnessAssertion merge(WitnessAssertion a) {
        if(a != null && a.getClass() == this.getClass())
            return null; //this.size = n and a.size = m --> return size O(n*m)

        //Assorb
        if(a != null) {
            //List<WitnessAssertion> newList = new LinkedList<>();
            int notFalseElement = 0;

            for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet()) {
                notFalseElement += entry.getValue().size();

                for (WitnessAssertion assertion : entry.getValue()) {
                    WitnessAssertion merged = a.merge(assertion);

                    if (merged == null) continue;

                    if (merged.getClass() == WitnessBoolean.class)
                        if (((WitnessBoolean) merged).getValue())
                            return new WitnessBoolean((true));
                        else {
                            notFalseElement--;
                            continue;
                        }

                    if (!merged.equals(a))
                        //newList.add(assertion);
                        this.add(assertion);
                    else return new WitnessBoolean(true);
                }
            }
            if(notFalseElement == 0)//all false
                return new WitnessBoolean(false);

            //orList.put(a.getClass(), newList);
        }

        List<WitnessAssertion> tmp = orList.remove(WitnessAnd.class);
        if(tmp != null) {
            for (WitnessAssertion and : tmp) {
                WitnessAssertion returnedValue = and.merge(null);
                this.add((returnedValue == null) ? and : returnedValue);
            }
        }

        return this;
    }

    @Override
    public WitnessType getGroupType() {
        return null;
    }

    @Override
    public Assertion getFullAlgebra() {
        AnyOf_Assertion anyOf = new AnyOf_Assertion();
        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                anyOf.add(assertion.getFullAlgebra());

        return anyOf;
    }

    @Override
    public WitnessAssertion clone() {
        WitnessOr clone = new WitnessOr();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                clone.add(assertion.clone());

        return clone;
    }

    @Override
    public boolean equals(Object o) {
        boolean b = true;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessOr witnessOr = (WitnessOr) o;

        if(witnessOr.orList.size() != orList.size())
            return false;

        for(Map.Entry<Object, List<WitnessAssertion>> entry : witnessOr.orList.entrySet()){
            if(!this.orList.containsKey(entry.getKey())) return false;
            List<WitnessAssertion> check = new LinkedList<>(witnessOr.orList.get(entry.getKey()));
            check.removeAll(this.orList.get(entry.getKey()));

            b &= check.size() == 0;
        }

        return b;
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
        WitnessOr newOr = new WitnessOr();
        WitnessGroup group = new WitnessGroup();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                if(assertion.getGroupType() != null)
                    group.add(assertion.groupize());
                else if(assertion.getClass() == WitnessAnd.class)
                    newOr.add(assertion.groupize());
                else
                    newOr.add(assertion);

        if(!group.isEmpty()){
            newOr.orList.put(WitnessGroup.class, group.canonicalize());
        }

        return newOr;
    }

    @Override
    public Set<WitnessAssertion> variableNormalization_separation() {
        Set<WitnessAssertion> set = new HashSet<>();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()) {
                set.addAll(assertion.variableNormalization_separation());
            }

        return set;
    }

    @Override
    public WitnessAssertion variableNormalization_expansion(WitnessEnv env) {
        WitnessOr or = new WitnessOr();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()) {
                if(assertion.getClass() == WitnessVar.class)
                    or.add(env.getDefinition((WitnessVar) assertion));
                else
                    or.add(assertion.variableNormalization_expansion(env));
            }

        return or;
    }

    @Override
    public WitnessAssertion DNF() {
        WitnessOr or = new WitnessOr();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()) {
                or.add(assertion.DNF());
            }

        return or;
    }

    protected WitnessOr DNF(WitnessAssertion toAdd) {
        WitnessOr or = new WitnessOr();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()) {
                WitnessAnd and = new WitnessAnd();

                and.add(assertion);
                and.add(toAdd);

                or.add(and);
            }

        return or;
    }

    @Override
    public int hashCode() {
        int hash = 0;

        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                hash += Objects.hash(assertion);

        return hash;
    }
}