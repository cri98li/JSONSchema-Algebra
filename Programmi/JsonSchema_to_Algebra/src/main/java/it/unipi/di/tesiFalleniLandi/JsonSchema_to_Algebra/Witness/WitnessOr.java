package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.AllOf_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.AnyOf_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.AnyOf;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WitnessOr implements WitnessAssertion{
    private Map<Object, List<WitnessAssertion>> orList;

    public WitnessOr() {
        this.orList = new HashMap<>();
    }

    public void add(WitnessAssertion el){
        if(el.getClass() == this.getClass())add((WitnessOr) el);
        else if(el.getClass() == WitnessUniqueItems.class || el.getClass() == WitnessRepeateditems.class)
            addUni_Rep(el);
        else {
            if(orList.containsKey(el.getClass()))
                orList.get(el.getClass()).add(el);
            else{
                List<WitnessAssertion> list = new LinkedList<>();
                list.add(el);
                orList.put(el.getClass(), list);
            }
        }
    }

    public void add(WitnessOr el){
        for(Map.Entry<Object, List<WitnessAssertion>> entry : el.orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                add(assertion);
    }

    public void addUni_Rep(WitnessAssertion el){
        if(!orList.containsKey(el.getClass()))
        {
            List<WitnessAssertion> list = new LinkedList<>();
            list.add(el);
            orList.put(el.getClass(), list);
        }
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

        if(a.getClass() == this.getClass()){
            WitnessAnd and = new WitnessAnd();
            and.add(a);
            and.add(this);
            return and;
        }


        if(orList.containsKey(a.getClass())) {
            List<WitnessAssertion> newList = new LinkedList<>();

            for (WitnessAssertion assertion : orList.get(a.getClass()))
                if (!a.merge(assertion).equals(a))
                    newList.add(assertion);

            orList.put(a.getClass(), newList);
        }
        return null;
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(GrammarStringDefinitions.TYPE_OBJECT);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessOr witnessOr = (WitnessOr) o;

        if(witnessOr.orList.size() != orList.size())
            return false;

        for(Map.Entry<Object, List<WitnessAssertion>> entry : witnessOr.orList.entrySet()){
            if(!this.orList.containsKey(entry.getKey())) return false;
            List<WitnessAssertion> check = new LinkedList<>(witnessOr.orList.get(entry.getKey()));
            check.removeAll(this.orList.get(entry.getKey()));

            return check.size() == 0;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return orList != null ? orList.hashCode() : 0;
    }
}
