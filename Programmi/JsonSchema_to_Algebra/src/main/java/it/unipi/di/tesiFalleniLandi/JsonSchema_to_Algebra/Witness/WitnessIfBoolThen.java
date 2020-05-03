package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.IfBoolThen_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.IfThenElse_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Type_Assertion;

public class WitnessIfBoolThen implements WitnessAssertion{
    private boolean value;

    public WitnessIfBoolThen(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "WitnessIfBoolThen{" +
                "value=" + value +
                '}';
    }

    @Override
    public WitnessAssertion merge(WitnessAssertion a) {
        if(a.getClass() == this.getClass()) return this.merge((WitnessIfBoolThen) a);

        WitnessAnd and = new WitnessAnd();
        and.add(this);
        and.add(a);
        return and;
    }

    public WitnessAssertion merge(WitnessIfBoolThen a) {
        if(a.value == value) return this;
        else{
            WitnessOr or = new WitnessOr();
            Type_Assertion type = new Type_Assertion();
            type.add(GrammarStringDefinitions.TYPE_BOOLEAN);
            or.add(type.not().toWitnessAlgebra());
            or.add(new WitnessBoolean(false));

            return or;
        }
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(GrammarStringDefinitions.TYPE_BOOLEAN); //??
    }

    @Override
    public Assertion getFullAlgebra() {
        return new IfBoolThen_Assertion(value);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessIfBoolThen that = (WitnessIfBoolThen) o;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return (value ? 1 : 0);
    }
}
