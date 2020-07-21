package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.AnyOf_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import patterns.REException;

import java.util.*;

public class WitnessOr implements WitnessAssertion{

    private Map<Object, List<WitnessAssertion>> orList;

    public WitnessOr() {
        this.orList = new HashMap<>();
    }

    public boolean add(WitnessAssertion el){
        if(el.getClass() == this.getClass())
            return add((WitnessOr) el);

        else if(el.getClass() == WitnessUniqueItems.class || el.getClass() == WitnessRepeateditems.class)
            return addUni_Rep(el);

        else if(el.getClass() == WitnessBoolean.class && ((WitnessBoolean) el).getValue() == false) return false;

        else {
            if(orList.containsKey(el.getClass())) {
                if (el.getClass() == WitnessType.class){
                    if (orList.get(el.getClass()).contains(el))
                        return false;

                    orList.get(el.getClass()).add(el);
                    return true;
                } else {
                    orList.get(el.getClass()).add(el);
                    return true;
                }
            }else{
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

    @Override
    public WitnessAssertion merge() throws REException {
        List<WitnessAssertion> tmp = orList.remove(WitnessAnd.class);

        if(tmp != null) {
            for (WitnessAssertion and : tmp) {
                WitnessAssertion returnedValue = and.merge();
                this.add((returnedValue == null) ? and : returnedValue);
            }
        }

        return this;
    }

    @Override
    public void checkLoopReferences(WitnessEnv env, Collection<WitnessVar> varList) throws WitnessException {
        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                assertion.checkLoopReferences(env, varList);
    }

    /*
        a può essere un oggetto di un qualsiasi tipo,
            foreach( b = orList.get(a.class)[i])
                if(b.merge(a).equals(a)
                    when a is satisfied, b is also satisfied so the entire orAssertion is true

        if a.class = or?
         */
    @Override
    public WitnessAssertion mergeElement(WitnessAssertion a) throws REException {
        if(a != null && a.getClass() == this.getClass())
            return null; //this.size = n and a.size = m --> return size O(n*m)

        //Assorb
        if(a != null) {
            //List<WitnessAssertion> newList = new LinkedList<>();
            int notFalseElement = 0;

            for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet()) {
                notFalseElement += entry.getValue().size();

                for (WitnessAssertion assertion : entry.getValue()) {
                    WitnessAssertion merged = a.mergeElement(assertion);

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
            List<WitnessAssertion> check = null;

            if(witnessOr.orList.get(entry.getKey()).size() >= this.orList.get(entry.getKey()).size()) {
                check = new LinkedList<>(witnessOr.orList.get(entry.getKey()));
                check.removeAll(this.orList.get(entry.getKey()));
            }else{
                check = new LinkedList<>(this.orList.get(entry.getKey()));
                check.removeAll(witnessOr.orList.get(entry.getKey()));
            }

            b &= check.size() == 0;
        }

        return b;
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
    public WitnessAssertion groupize() throws WitnessException, REException {
        WitnessOr newOr = new WitnessOr();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()) {
                WitnessAnd tmp = null;

                //Creo un and per poi fare il gruppo
                if (assertion.getClass() != WitnessAnd.class) { //TODO: top_groupize su singole asserzioni???
                    tmp = new WitnessAnd();
                    tmp.add(assertion);
                }else
                    tmp = (WitnessAnd) assertion;

                newOr.add(tmp.groupize());
            }

        return newOr;
    }

    @Override
    public void variableNormalization_separation(WitnessEnv env) {

        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()) {
                assertion.variableNormalization_separation(env);
            }
    }

    @Override
    public WitnessAssertion variableNormalization_expansion(WitnessEnv env) throws WitnessException {
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
    public WitnessAssertion DNF() throws WitnessException {
        WitnessOr or = new WitnessOr();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()) {
                or.add(assertion.DNF());
            }

        return or;
    }

    protected WitnessOr DNF(WitnessAssertion toAdd) throws WitnessException {
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

    public void objectPrepare() throws REException, WitnessException {
        if(orList.containsKey(WitnessAnd.class)){
            List<WitnessAssertion> ands = orList.get(WitnessAnd.class);
            for(WitnessAssertion assertion : ands) {
                ((WitnessAnd)assertion).objectPrepare();
            }
        }
    }

    @Override
    public int hashCode() {
        return orList != null ? orList.hashCode() : 0;
    }

    /*
    @Override
    public int hashCode() {
        int hash = 0;

        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                hash += Objects.hash(assertion);

        return hash;
    }

     */

}