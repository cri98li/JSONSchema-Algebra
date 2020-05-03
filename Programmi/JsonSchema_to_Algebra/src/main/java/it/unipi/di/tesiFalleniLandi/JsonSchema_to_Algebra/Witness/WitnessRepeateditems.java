package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.RepeatedItems_Assertion;

public class WitnessRepeateditems implements WitnessAssertion{

    public WitnessRepeateditems() {
    }

    @Override
    public String toString() {
        return "WitnessRepeateditems{}";
    }

    @Override
    public WitnessAssertion merge(WitnessAssertion a) {
        WitnessAnd and = new WitnessAnd();
        and.add(this);
        and.add(a);
        return and;
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
