package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.*;

public class WitnessUniqueItems implements WitnessAssertion{ //fare anche il caso merge con repeatedItems

    public WitnessUniqueItems() {
    }

    @Override
    public String toString() {
        return "WitnessUniqueItems{}";
    }

    @Override
    public WitnessAssertion merge(WitnessAssertion a) {
        if(a.getClass() == this.getClass()) return merge((WitnessUniqueItems) a);
        if(a.getClass() == WitnessRepeateditems.class) return merge((WitnessRepeateditems) a);

        WitnessAnd and = new WitnessAnd();
        and.add(this);
        and.add(a);
        return and;
    }

    public WitnessAssertion merge(WitnessUniqueItems a) {
        return a;
    }

    public WitnessAssertion merge(WitnessRepeateditems a) {
        Type_Assertion t = new Type_Assertion();
        t.add(GrammarStringDefinitions.TYPE_ARRAY);

        return new IfThenElse_Assertion(t, new Boolean_Assertion(false), null).toWitnessAlgebra();
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(GrammarStringDefinitions.TYPE_ARRAY);
    }

    @Override
    public Assertion getFullAlgebra() {
        return new RepeatedItems_Assertion();
    }

}
