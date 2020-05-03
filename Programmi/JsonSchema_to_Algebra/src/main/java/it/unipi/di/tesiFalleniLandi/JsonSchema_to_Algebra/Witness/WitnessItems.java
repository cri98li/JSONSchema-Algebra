package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Items_Assertion;

import java.util.LinkedList;
import java.util.List;

public class WitnessItems implements WitnessAssertion{
    private List<WitnessAssertion> items;
    private WitnessAssertion additionalItems;

    public WitnessItems() {
        items = new LinkedList<>();
    }

    public void setAdditionalItems(WitnessAssertion addItem){
        additionalItems = addItem;
    }

    public void addItems(WitnessAssertion item){
        items.add(item);
    }

    @Override
    public String toString() {
        return "WitnessItems{" +
                "items=" + items +
                ", additionalItems=" + additionalItems +
                '}';
    }

    @Override
    public WitnessAssertion merge(WitnessAssertion a) {
        if(a.getClass() == this.getClass()) return this.merge((WitnessItems) a);

        WitnessAnd and = new WitnessAnd();
        and.add(this);
        and.add(a);
        return and;
    }

    public WitnessAssertion merge(WitnessItems a) {
        WitnessItems ite = new WitnessItems();

        ite.additionalItems = a;
        if(a.additionalItems == null) ite.additionalItems = additionalItems;
        if(a.additionalItems != null && additionalItems != null) ite.additionalItems = a.additionalItems.merge(additionalItems);

        int i;
        int min = (a.items.size() < items.size()) ? a.items.size() : items.size();
        for(i = 0; i < min; i++)
            ite.addItems(items.get(i).merge(a.items.get(i)));

        for(; i < a.items.size(); i++)
            ite.addItems(a.items.get(i));

        for(; i < items.size(); i++)
            ite.addItems(items.get(i));

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
}
