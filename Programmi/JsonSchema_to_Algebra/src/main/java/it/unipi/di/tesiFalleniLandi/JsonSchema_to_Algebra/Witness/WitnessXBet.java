package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Bet_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.XBet_Assertion;

public class WitnessXBet implements WitnessAssertion{
    Double min, max;

    public WitnessXBet(){}

    public WitnessXBet(Double min, Double max) {
        if(min == null)
            this.min = Double.NEGATIVE_INFINITY;
        else
            this.min = min;

        if(max == null)
            this.max = Double.POSITIVE_INFINITY;
        else
            this.max = max;
    }

    @Override
    public String toString() {
        return "WitnessXBet{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }

    @Override
    public WitnessAssertion merge(WitnessAssertion a) {
        if(a.getClass() == this.getClass())
            return this.merge((WitnessXBet) a);

        WitnessAnd and = new WitnessAnd();
        and.add(this);
        and.add(a);
        return and;
    }

    public WitnessXBet merge(WitnessXBet a) {
        Double m = (min < a.min) ? a.min : min;
        Double M = (max > a.max) ? a.max : max;

        return new WitnessXBet(m, M);
    }

    @Override
    public WitnessType getGroupType() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessXBet that = (WitnessXBet) o;

        if (min != null ? !min.equals(that.min) : that.min != null) return false;
        return max != null ? max.equals(that.max) : that.max == null;
    }

    @Override
    public int hashCode() {
        int result = min != null ? min.hashCode() : 0;
        result = 31 * result + (max != null ? max.hashCode() : 0);
        return result;
    }

    public Assertion getFullAlgebra() {
        return new XBet_Assertion(min == Double.NEGATIVE_INFINITY ? null : min, max == Double.POSITIVE_INFINITY ? null : max);
    }
}
