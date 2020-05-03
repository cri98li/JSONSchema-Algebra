package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.NotMof_Assertion;

public class WitnessNotMof implements WitnessAssertion{
    private Double value;

    public WitnessNotMof(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "WitnessNotMof{" +
                "value=" + value +
                '}';
    }

    @Override
    public WitnessAssertion merge(WitnessAssertion a) { //caso base: tipi diversi => non dovrebbe mai succedere
        if(a.getClass() == this.getClass())
            return this.merge((WitnessNotMof) a);

        WitnessAnd and = new WitnessAnd();
        and.add(this);
        and.add(a);
        return and;
    }

    public WitnessAssertion merge(WitnessNotMof notMof) {
        Double val1 = notMof.value;
        Double val2 = this.value;

        if(val1 % val2 == 0)
            return new WitnessNotMof(val2);
        else if(val2 % val1 == 0)
            return new WitnessNotMof(val1);
        else{
            WitnessAnd and = new WitnessAnd();
            and.add(notMof);
            and.add(this);

            return and;
        }
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(GrammarStringDefinitions.TYPE_NUMBER);
    }

    @Override
    public Assertion getFullAlgebra() {
        return new NotMof_Assertion(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessNotMof that = (WitnessNotMof) o;

        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
