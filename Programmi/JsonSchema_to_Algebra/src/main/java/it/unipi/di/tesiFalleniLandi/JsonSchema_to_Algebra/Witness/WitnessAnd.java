package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.AllOf_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import patterns.REException;

import java.util.*;

public class WitnessAnd implements WitnessAssertion{
    /**
     * list of assertion in and, grouped by class to optimize the merge method
     *
     * Ex:
     *      < WitnessMof, <mof(1), mof(2), mof(3)>>
     *      < WitnessPattern, <pattern("..."), pattern("..."), ...>>
     *      ...
     */
    private HashMap<Object, List<WitnessAssertion>> andList;

    public WitnessAnd() {
        this.andList = new HashMap<>();
    }

    /*
    Add an assertion - el - to the andList.
    if el is an instance of WitnessAnd, we add every element of el.andList in this.andList
    if el is an instance of uniqueItems or repeatedItems, we check if andList do not contain it, then we add it
     */
    public boolean add(WitnessAssertion el){
        boolean b = false;
        if(el.getClass() == WitnessAnd.class) {
            for (Map.Entry<Object, List<WitnessAssertion>> entry : ((WitnessAnd) el).andList.entrySet())
                for (WitnessAssertion assertion : entry.getValue())
                    b |= this.add(assertion);
            return b;
        }

        if(el.getClass() == WitnessUniqueItems.class || el.getClass() == WitnessRepeateditems.class){
            if(!andList.containsKey(el.getClass())){
                List<WitnessAssertion> list = new LinkedList<>();
                list.add(el);
                andList.put(el.getClass(), list);
                return true;
            } else return false;
        }

        // inserisce l'asserzione nella rispettiva lista
        if(andList.containsKey(el.getClass())) {
            if(andList.get(el.getClass()).contains(el)) return false;
            andList.get(el.getClass()).add(el);
        }else {
            List<WitnessAssertion> list = new LinkedList<>();
            list.add(el);
            andList.put(el.getClass(), list);
        }
        return true;
    }

    @Override
    public String toString() {
        return "WitnessAnd{" + "\r\n" +
                "andList=" + andList.toString()
                 + "\r\n" +
                '}';
    }


    @Override
    public WitnessAssertion mergeElement(WitnessAssertion a) throws REException {
        if(this.add(a)) //se la lista è stata modificata faccio il merge
            return this.merge();
        return this;
    }


    @Override
    public WitnessAssertion merge() {
        WitnessAnd newAnd = new WitnessAnd();
        boolean modified = false;

        for (Map.Entry<Object, List<WitnessAssertion>> sameTypeAssertion : andList.entrySet()) {
            int size = sameTypeAssertion.getValue().size();
            WitnessAssertion merged = sameTypeAssertion.getValue().get(0).merge();

            for (int i = 1; i < size; i++) {
                WitnessAssertion oldMerge = merged;
                merged = merged.mergeElement(sameTypeAssertion.getValue().get(i).merge());
                if (merged != null) {
                    modified = true;
                    if (merged.getClass() == WitnessBoolean.class) {
                        WitnessBoolean b = (WitnessBoolean) merged;
                        if (!b.getValue())
                            return b;
                    }
                }
                else {
                    newAnd.add(sameTypeAssertion.getValue().get(i));
                    merged = oldMerge;
                }
            }

            if (merged != null)
                newAnd.add(merged);
        }

        //CASI SPECIALI
        if(newAnd.andList.containsKey(WitnessBet.class) && newAnd.andList.containsKey(WitnessXBet.class)){
            WitnessBet bet = (WitnessBet) newAnd.andList.remove(WitnessBet.class).get(0);
            WitnessXBet xBet = (WitnessXBet) newAnd.andList.remove(WitnessXBet.class).get(0);
            WitnessAssertion assertion = bet.mergeElement(xBet);
            if(assertion == null) {
                newAnd.add(bet);
                newAnd.add(xBet);
            }else{
                newAnd.add(assertion);
                modified = true;
            }
        }

        if(newAnd.andList.containsKey(WitnessMof.class) && newAnd.andList.containsKey(WitnessNotMof.class)){
            WitnessMof mof = (WitnessMof) newAnd.andList.remove(WitnessMof.class).get(0);
            List<WitnessAssertion> notMofList = newAnd.andList.remove(WitnessNotMof.class);

            for(WitnessAssertion tmp : notMofList){
                WitnessAssertion assertion = mof.mergeElement(tmp);
                if(assertion == null) {
                    newAnd.add(mof);
                    newAnd.add(tmp);
                }else{
                    newAnd.add(assertion);
                    modified = true;
                    break;
                }
            }

        }

        if(newAnd.andList.containsKey(WitnessUniqueItems.class) && newAnd.andList.containsKey(WitnessRepeateditems.class)){
            WitnessUniqueItems uni = (WitnessUniqueItems) newAnd.andList.remove(WitnessUniqueItems.class).get(0);
            WitnessRepeateditems rep = (WitnessRepeateditems) newAnd.andList.remove(WitnessRepeateditems.class).get(0);
            WitnessAssertion assertion = uni.mergeElement(rep);
            if(assertion == null) {
                newAnd.add(uni);
                newAnd.add(rep);
            }else{
                newAnd.add(assertion);
                modified = true;
            }
        }

        //TOFIX: newAnd.andList.get(WitnessOr.class).remove(or); de la lista è una sola bisogna rimuovere anche l'elemento dentro l'hashmap
        /*
        if(newAnd.andList.containsKey(WitnessOr.class)){
            for(Map.Entry<Object, List<WitnessAssertion>> sameTypeAssertion : andList.entrySet())
                if(sameTypeAssertion.getKey() != WitnessOr.class)
                    for(WitnessAssertion assertion : sameTypeAssertion.getValue()) {
                        for (WitnessAssertion or : newAnd.clone().andList.get(WitnessOr.class)) {
                            WitnessAssertion returnedAssertion = or.mergeElement(assertion);
                            if (returnedAssertion.getClass() == WitnessBoolean.class)
                                if (((WitnessBoolean) returnedAssertion).getValue() == true);
                                    newAnd.andList.get(WitnessOr.class).remove(or);
                                else
                                    return returnedAssertion; //WitnessBoolean false
                        }
                    }
        }
        */

        if(modified)
            return newAnd.merge();
        return newAnd;
    }

    @Override
    public WitnessType getGroupType() {
        return null;
    }

    @Override
    public Assertion getFullAlgebra() {
        AllOf_Assertion allOf = new AllOf_Assertion();
        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                allOf.add(assertion.getFullAlgebra());

        return allOf;
    }

    @Override
    public WitnessAnd clone() {
        WitnessAnd clone = new WitnessAnd();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                clone.add(assertion.clone());

        return clone;
    }

    @Override
    public WitnessAssertion not() throws REException {
        return getFullAlgebra().not().toWitnessAlgebra();
    }

    @Override
    public WitnessAssertion notElimination() throws REException {
        return getFullAlgebra().notElimination().toWitnessAlgebra();
    }

    /**
     * INPUT: lista di asserzioni in and
     * OUPUT: lista in and contenente solo istanze di WitnessGroup, WitnessOr e WitnessVar
     */
    @Override
    public WitnessAssertion groupize() throws WitnessException {
        WitnessAnd and = new WitnessAnd();
        WitnessGroup group = new WitnessGroup();

        /*
        For each assertion in andList:
            - if the assertion is an ITE, we call the method on the assertion and then we add the result to the group
            - if the assertion is not an ITE and is an OR assertion, we call the method on the assertion and then we add the result to the new andList
            - if the assertion is not an ITe and is not an OR it is a variable, so we add it to the new andList
         */
        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                if(assertion.getGroupType() != null)
                    try {
                        group.add(assertion.groupize());
                    }catch(WitnessException we){
                        return new WitnessBoolean(false);
                    }
                else if(assertion.getClass() == WitnessOr.class)
                    and.add(assertion.groupize());
                else
                    and.add(assertion);

        if(!group.isEmpty()){
            List<WitnessAssertion> groups = null;
            groups = group.canonicalize(); //split the group in N typed groups
            if(groups.size() == 1)
                and.andList.put(WitnessGroup.class, groups);
            else{
                WitnessOr or = new WitnessOr();
                for(WitnessAssertion tmp : groups)
                    or.add(tmp);
                and.add(or);
            }
        }

        return and;
    }

    @Override
    public void variableNormalization_separation(WitnessEnv env) {
        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()){
                assertion.variableNormalization_separation(env);
            }
    }

    @Override
    public WitnessAssertion variableNormalization_expansion(WitnessEnv env) throws WitnessException {
        WitnessAnd and = new WitnessAnd();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()){
                if(assertion.getClass() == WitnessVar.class)
                    and.add(env.getDefinition((WitnessVar) assertion));
                else
                    and.add(assertion.variableNormalization_expansion(env));
            }

        return and;
    }

    //TODO: check
    @Override
    public WitnessAssertion DNF() throws WitnessException {
        WitnessOr or = new WitnessOr();

        if(!andList.containsKey(WitnessOr.class)) {
            //Se non contiene un OR, creo un OR unitario contenente la lista in and --> mantengo l'invariante
            or.add(this.clone());
            return or;
        }

        or = (WitnessOr) andList.get(WitnessOr.class).remove(0);

        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet()) {
            for(WitnessAssertion assertion : entry.getValue()) {
                or = or.DNF(assertion);
            }
        }

        return or.DNF();
    }

    @Override
    public boolean equals(Object o) {
        boolean b = true;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessAnd witnessAnd = (WitnessAnd) o;

        if(witnessAnd.andList.size() != andList.size())
            return false;

        for(Map.Entry<Object, List<WitnessAssertion>> entry : witnessAnd.andList.entrySet()){
            if(!this.andList.containsKey(entry.getKey())) return false;
            List<WitnessAssertion> check = new LinkedList<>(witnessAnd.andList.get(entry.getKey()));
            check.removeAll(this.andList.get(entry.getKey()));

            b &= check.size() == 0;
        }

        return b;
    }

    @Override
    public int hashCode() {
        return andList != null ? andList.hashCode() : 0;
    }

    /*
    @Override
    public int hashCode() {
        int hash = 0;

        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                hash += assertion.hashCode();

        return hash;
    }
     */

}