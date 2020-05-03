package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.AllOf_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import org.antlr.v4.runtime.atn.RangeTransition;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WitnessAnd implements WitnessAssertion{
    private HashMap<Object, List<WitnessAssertion>> andList;

    public WitnessAnd() {
        this.andList = new HashMap<>();
    }

    public void add(WitnessAssertion el){
        if(el.getClass() == WitnessAnd.class) {
            for (Map.Entry<Object, List<WitnessAssertion>> entry : ((WitnessAnd) el).andList.entrySet())
                for (WitnessAssertion assertion : entry.getValue())
                    this.add(assertion);
            return;
        }

        if(el.getClass() == WitnessUniqueItems.class || el.getClass() == WitnessRepeateditems.class)
            if(!andList.containsKey(el.getClass())){
                List<WitnessAssertion> list = new LinkedList<>();
                list.add(el);
                andList.put(el.getClass(), list);
                return;
            }

        if(andList.containsKey(el.getClass()))
            andList.get(el.getClass()).add(el);
        else {
            List<WitnessAssertion> list = new LinkedList<>();
            list.add(el);
            andList.put(el.getClass(), list);
        }
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
        if(a != null){
            this.add(a);
            return this.merge(null);
        }


        for(Map.Entry<Object, List<WitnessAssertion>> sameTypeAssertion : andList.entrySet()){
            int size = sameTypeAssertion.getValue().size();
            WitnessAssertion merged = sameTypeAssertion.getValue().get(0); //indagare caso ritorna and
            for(int i = 1; i < size; i++) {
                merged = merged.merge(sameTypeAssertion.getValue().get(i)); //pensa al caso di ritorno di una andList.................. notMulOf non mergiabili
                if(merged == null) return new WitnessBoolean(false); //TODO: far ritornare null come gli altri?
            }

            newAnd.add(merged); //Così considero il fatto che potrebbe ritornare un tipo diverso da quello originale (uniq+repeated --> type(Arr)=> false)
        }



        //CASI SPECIALI
        if(newAnd.andList.containsKey(WitnessBet.class) && newAnd.andList.containsKey(WitnessXBet.class)){
            WitnessBet bet = (WitnessBet) newAnd.andList.remove(WitnessBet.class).get(0);
            WitnessXBet xBet = (WitnessXBet) newAnd.andList.remove(WitnessXBet.class).get(0);
            newAnd.add(bet.merge(xBet));
        }

        if(newAnd.andList.containsKey(WitnessMof.class) && newAnd.andList.containsKey(WitnessNotMof.class)){
            WitnessMof mof = (WitnessMof) newAnd.andList.remove(WitnessMof.class).get(0);
            WitnessNotMof notMof = (WitnessNotMof) newAnd.andList.remove(WitnessNotMof.class).get(0);
            newAnd.add(mof.merge(notMof));
        }

        if(newAnd.andList.containsKey(WitnessUniqueItems.class) && newAnd.andList.containsKey(WitnessRepeateditems.class)){
            WitnessUniqueItems uni = (WitnessUniqueItems) newAnd.andList.remove(WitnessUniqueItems.class).get(0);
            WitnessRepeateditems rep = (WitnessRepeateditems) newAnd.andList.remove(WitnessRepeateditems.class).get(0);
            newAnd.add(uni.merge(rep));
        }

        return newAnd;
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(GrammarStringDefinitions.TYPE_OBJECT); //??
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessAnd witnessAnd = (WitnessAnd) o;

        if(witnessAnd.andList.size() != andList.size())
            return false;

        for(Map.Entry<Object, List<WitnessAssertion>> entry : witnessAnd.andList.entrySet()){
            if(!this.andList.containsKey(entry.getKey())) return false;
            List<WitnessAssertion> check = new LinkedList<>(witnessAnd.andList.get(entry.getKey()));
            check.removeAll(this.andList.get(entry.getKey()));

            return check.size() == 0;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return andList != null ? andList.hashCode() : 0;
    }
}
