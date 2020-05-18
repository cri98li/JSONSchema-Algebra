package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.AllOf_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;

import java.util.*;

public class WitnessAnd implements WitnessAssertion{
    private HashMap<Object, List<WitnessAssertion>> andList;

    public WitnessAnd() {
        this.andList = new HashMap<>();
    }

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
    public WitnessAssertion merge(WitnessAssertion a) {
        WitnessAnd newAnd = new WitnessAnd();
        boolean modified = false;
        if(a != null){
            if(this.add(a))//se la lista è stata modificata faccio il merge
                return this.merge(null);
            return this;
        }


            for (Map.Entry<Object, List<WitnessAssertion>> sameTypeAssertion : andList.entrySet()) {
                int size = sameTypeAssertion.getValue().size();
                WitnessAssertion merged = sameTypeAssertion.getValue().get(0).merge(null);//indagare caso ritorna and

                for (int i = 1; i < size; i++) {
                    merged = merged.merge(sameTypeAssertion.getValue().get(i)); //pensa al caso di ritorno di una andList.................. notMulOf non mergiabili
                    if (merged != null)
                        modified |= true;
                    else {
                        newAnd.add(sameTypeAssertion.getValue().get(i));
                        merged = sameTypeAssertion.getValue().get(0);
                        continue;
                    }
                    if (merged.getClass() == WitnessBoolean.class) {
                        WitnessBoolean b = (WitnessBoolean) merged;
                        if (!b.getValue())
                            return b;
                    }
                }

                if (merged != null)
                    newAnd.add(merged); //Così considero il fatto che potrebbe ritornare un tipo diverso da quello originale (uniq+repeated --> type(Arr)=> false)
            }



        //CASI SPECIALI
        if(newAnd.andList.containsKey(WitnessBet.class) && newAnd.andList.containsKey(WitnessXBet.class)){
            WitnessBet bet = (WitnessBet) newAnd.andList.remove(WitnessBet.class).get(0);
            WitnessXBet xBet = (WitnessXBet) newAnd.andList.remove(WitnessXBet.class).get(0);
            WitnessAssertion assertion = bet.merge(xBet);
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
            WitnessNotMof notMof = (WitnessNotMof) newAnd.andList.remove(WitnessNotMof.class).get(0);
            WitnessAssertion assertion = mof.merge(notMof);
            if(assertion == null) {
                newAnd.add(mof);
                newAnd.add(notMof);
            }else{
                newAnd.add(assertion);
                modified = true;
            }
        }

        if(newAnd.andList.containsKey(WitnessUniqueItems.class) && newAnd.andList.containsKey(WitnessRepeateditems.class)){
            WitnessUniqueItems uni = (WitnessUniqueItems) newAnd.andList.remove(WitnessUniqueItems.class).get(0);
            WitnessRepeateditems rep = (WitnessRepeateditems) newAnd.andList.remove(WitnessRepeateditems.class).get(0);
            WitnessAssertion assertion = uni.merge(rep);
            if(assertion == null) {
                newAnd.add(uni);
                newAnd.add(rep);
            }else{
                newAnd.add(assertion);
                modified = true;
            }
        }

        if(newAnd.andList.containsKey(WitnessOr.class)){
            for(Map.Entry<Object, List<WitnessAssertion>> sameTypeAssertion : andList.entrySet())
                if(sameTypeAssertion.getKey() != WitnessOr.class)
                    for(WitnessAssertion assertion : sameTypeAssertion.getValue()) {
                        for (WitnessAssertion or : newAnd.clone().andList.get(WitnessOr.class)) {
                            WitnessAssertion returnedAssertion = or.merge(assertion);
                            if (returnedAssertion.getClass() == WitnessBoolean.class)
                                if (((WitnessBoolean) returnedAssertion).getValue() == true)
                                    newAnd.andList.get(WitnessOr.class).remove(or);
                                else
                                    return returnedAssertion; //WitnessBoolean false
                        }
                    }
        }

        if(modified)
            return newAnd.merge(null);
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
    public WitnessAssertion not() {
        return getFullAlgebra().not().toWitnessAlgebra();
    }

    @Override
    public WitnessAssertion notElimination() {
        return getFullAlgebra().notElimination().toWitnessAlgebra();
    }

    @Override
    public WitnessAssertion groupize() {
        WitnessAnd and = new WitnessAnd();
        WitnessGroup group = new WitnessGroup();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                if(assertion.getGroupType() != null)
                    group.add(assertion.groupize());
                else if(assertion.getClass() == WitnessOr.class)
                    and.add(assertion.groupize());
                else
                    and.add(assertion);

        if(!group.isEmpty()){
            List<WitnessAssertion> groups = group.canonicalize();
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
    public Set<WitnessAssertion> variableNormalization_separation() {
        Set<WitnessAssertion> set = new HashSet<>();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()){
                set.addAll(assertion.variableNormalization_separation());
            }

        return set;
    }

    @Override
    public WitnessAssertion variableNormalization_expansion(WitnessEnv env) {
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

    @Override
    public WitnessAssertion DNF(){
        WitnessOr or = new WitnessOr();

        if(!andList.containsKey(WitnessOr.class)) {
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
        int hash = 0;

        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                hash += assertion.hashCode();

        return hash;
    }

}