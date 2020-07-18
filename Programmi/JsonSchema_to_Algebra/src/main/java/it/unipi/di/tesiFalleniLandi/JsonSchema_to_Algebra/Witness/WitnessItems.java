package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Items_Assertion;
import patterns.REException;

import java.util.*;

public class WitnessItems implements WitnessAssertion{
    private List<WitnessAssertion> items;
    private boolean blocked;
    private WitnessAssertion additionalItems;

    public WitnessItems() {
        blocked = false;
        items = new LinkedList<>();
    }

    public void setAdditionalItems(WitnessAssertion addItem){
        if(!blocked)
            additionalItems = addItem;
    }

    public void addItems(WitnessAssertion item){
        try{
            if(!((WitnessBoolean)item).getValue()) {
                blocked = true;
                additionalItems = item;
                return;
            }else{
                items.add(item);
            }
        }catch (ClassCastException e) {
            if (!blocked)
                items.add(item);
        }
    }

    @Override
    public String toString() {
        return "WitnessItems{" +
                "items=" + items +
                ", additionalItems=" + additionalItems +
                '}';
    }

    @Override
    public WitnessAssertion mergeElement(WitnessAssertion a) throws REException {
        if(items.size() == 0 && additionalItems.getClass() == WitnessBoolean.class)
            if(((WitnessBoolean)additionalItems).getValue())
                return additionalItems;
            else {
                WitnessAnd and = new WitnessAnd();
                and.add(a);
                and.add(new WitnessContains(0l,0l, new WitnessBoolean(true))); //TODO: testare se vado in ricorsione
                return and;
            }

        for(int i = items.size()-1; i >= 0; i--)
            if( additionalItems!= null && additionalItems.equals(items.get(i)))
                items.remove(i);
            else
                break;

        if(a.getClass() == this.getClass()) return this.mergeElement((WitnessItems) a);

        return null;
    }

    @Override
    public WitnessAssertion merge() throws REException {
        WitnessItems newWitnessItems = new WitnessItems();
        for(WitnessAssertion item : items)
            newWitnessItems.addItems(item.merge());

        if(additionalItems != null)
            newWitnessItems.setAdditionalItems(additionalItems.merge());

        newWitnessItems.blocked = blocked;

        return newWitnessItems;
    }

    public WitnessAssertion mergeElement(WitnessItems a) throws REException {
        if (a.items.size() == 0 && a.additionalItems.getClass() == WitnessBoolean.class)
            if (((WitnessBoolean) a.additionalItems).getValue())
                return a.additionalItems;
            else {
                WitnessAnd and = new WitnessAnd();
                and.add(a);
                and.add(new WitnessContains(0l, 0l, new WitnessBoolean(true))); //TODO: testare se vado in ricorsione
                return and;
            }

        for (int i = items.size() - 1; i >= 0; i--)
            if (additionalItems != null && additionalItems.equals(items.get(i)))
                items.remove(i);
            else if (items.get(i).getClass() == WitnessBoolean.class && ((WitnessBoolean) items.get(i)).getValue() == false) {
                for (int j = items.size() - 1; j >= i; j--)
                    items.remove(j);

                additionalItems = new WitnessBoolean(false);
            }

        WitnessItems ite = new WitnessItems();

        ite.additionalItems = a.additionalItems;
        if (a.additionalItems == null) ite.additionalItems = additionalItems;
        if (a.additionalItems != null && this.additionalItems != null)
            ite.additionalItems = a.additionalItems.clone().mergeElement(this.additionalItems);

        int i;
        int min = (a.items.size() < items.size()) ? a.items.size() : items.size();
        for (i = 0; i < min; i++) {
            WitnessAssertion tmp = items.get(i).mergeElement(a.items.get(i));
            if(tmp != null)
                ite.addItems(tmp);
            else{
                WitnessAnd and = new WitnessAnd();
                and.add(items.get(i));
                and.add(a.items.get(i));
                ite.addItems(and);
            }
        }



        for(; i < a.items.size(); i++) {
            WitnessAssertion tmp = a.items.get(i).mergeElement(this.additionalItems);
            if(tmp != null)
                ite.addItems(tmp);
            else
                ite.addItems(a.items.get(i));
        }


        for(; i < items.size(); i++) {
            WitnessAssertion tmp = items.get(i).mergeElement(a.additionalItems);
            if(tmp != null)
                ite.addItems(tmp);
            else
                ite.addItems(items.get(i));
        }


        return ite;
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(GrammarStringDefinitions.TYPE_ARRAY);
    }

    @Override
    public Assertion getFullAlgebra() {
        Items_Assertion items = new Items_Assertion();

        for(WitnessAssertion a : this.items)
            items.add(a.getFullAlgebra());

        if(additionalItems != null)
            items.setAdditionalItems(additionalItems.getFullAlgebra());

        return items;
    }

    @Override
    public WitnessAssertion clone() {
        WitnessItems clone = new WitnessItems();
        for(WitnessAssertion assertion : items)
            clone.items.add(assertion.clone());

        clone.blocked = blocked;

        if(additionalItems != null)
            clone.additionalItems = additionalItems.clone();

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

    @Override
    public WitnessAssertion groupize() throws WitnessException, REException {
        WitnessItems items = new WitnessItems();

        if(additionalItems != null)
            if(additionalItems.getClass() != WitnessAnd.class){
                WitnessAnd and = new WitnessAnd();
                and.add(additionalItems);
                items.additionalItems = and.groupize();
            }else
                items.additionalItems = additionalItems.groupize();

        for(WitnessAssertion assertion : this.items)
                if(assertion.getClass() != WitnessAnd.class){
                    WitnessAnd and = new WitnessAnd();
                    and.add(assertion);
                    items.addItems(and.groupize());
                }else
                    items.addItems(assertion.groupize());

        return items;
    }

    @Override
    public void variableNormalization_separation(WitnessEnv env) {
        if(items != null){
            for(int i = 0; i < items.size(); i++){
                if(items.get(i).getClass() != WitnessBoolean.class) {
                    items.get(i).variableNormalization_separation(env);
                    WitnessVar var = new WitnessVar(Utils_Witness.getName(items.get(i)));
                    env.add(var, items.get(i));

                    items.set(i, var);
                }
            }
        }

        if(additionalItems != null){
            if(additionalItems.getClass() != WitnessBoolean.class) {
                additionalItems.variableNormalization_separation(env);
                WitnessVar var = new WitnessVar(Utils_Witness.getName(additionalItems));
                env.add(var, additionalItems);
                additionalItems = var;
            }
        }
    }

    @Override
    public WitnessAssertion variableNormalization_expansion(WitnessEnv env) throws WitnessException {
        WitnessItems newItems = new WitnessItems();

        if(items != null){
            for(WitnessAssertion assertion : items)
                newItems.addItems(assertion.variableNormalization_expansion(env));
        }

        if(additionalItems != null)
            newItems.setAdditionalItems(additionalItems.variableNormalization_expansion(env));

        return newItems;
    }

    @Override
    public WitnessAssertion DNF() throws WitnessException {
        WitnessItems newItems = new WitnessItems();

        if(items != null){
            for(WitnessAssertion assertion : items)
                newItems.addItems(assertion.DNF());
        }

        if(additionalItems != null)
            newItems.setAdditionalItems(additionalItems.DNF());

        return newItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessItems that = (WitnessItems) o;

        if (blocked != that.blocked) return false;

        if(!Objects.equals(that.additionalItems, additionalItems)) return false;

        if(that.items.size() != items.size()) return false;

        List<WitnessAssertion> check = new LinkedList<>();
        if(this.items.size() >= that.items.size()) {
            check.addAll(this.items);
            check.removeAll(that.items);
        }else{
            check.addAll(that.items);
            check.removeAll(this.items);
        }

        return check.size() == 0;
    }

    @Override
    public int hashCode() {
        int result = items != null ? items.hashCode() : 0;
        result = 31 * result + (blocked ? 1 : 0);
        result = 31 * result + (additionalItems != null ? additionalItems.hashCode() : 0);
        return result;
    }
}