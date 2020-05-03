package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Boolean_Assertion;

public class WitnessBoolean implements WitnessAssertion{
    private boolean value;

    public WitnessBoolean(boolean val){
        this.value = val;
    }

    @Override
    public String toString() {
        return "WitnessBoolean{" +
                "value=" + value +
                '}';
    }

    @Override
    public WitnessAssertion merge(WitnessAssertion a) {
        if(a.getClass() == this.getClass()) return this.merge((WitnessBoolean) a);

        if(this.value) return a; //se vero lo posso ignorare
        else return this; //se falso mi rende tutto falso
    }

    public WitnessBoolean merge(WitnessBoolean a) {
        return new WitnessBoolean(a.value && value);
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(GrammarStringDefinitions.TYPE_BOOLEAN);
    }

    @Override
    public Assertion getFullAlgebra() {
        return new Boolean_Assertion(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessBoolean that = (WitnessBoolean) o;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return (value ? 1 : 0);
    }
}
