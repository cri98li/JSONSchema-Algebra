<<<<<<< HEAD
package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Bet_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Type_Assertion;

public class WitnessBet implements WitnessAssertion{ //fare anche caso merge con xbet
    private Double min, max;

    protected WitnessBet(){
    }

    public WitnessBet(Double min, Double max) {
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
        return "WitnessBet{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }

    @Override
    public WitnessAssertion merge(WitnessAssertion a) { //caso base: tipi diversi => non dovrebbe mai succedere
        if(a == null) return this;
        if(a.getClass() == this.getClass())
            return this.merge((WitnessBet) a);
        if(a.getClass() == WitnessXBet.class)
            return this.merge((WitnessXBet) a);

        return null;
    }

    public WitnessAssertion merge(WitnessBet a) {
        if(a.min > max || a.max < min){
            Type_Assertion type = new Type_Assertion();
            type.add(GrammarStringDefinitions.TYPE_NUMBER);

            return type.not().toWitnessAlgebra();
        }

        Double m = (min < a.min) ? a.min : min;
        Double M = (max > a.max) ? a.max : max;

        WitnessXBet newBet = new WitnessXBet(m, M);
        return (this.equals(newBet) || a.equals(newBet)) ? null : newBet;
    }

    public WitnessAssertion merge(WitnessXBet a) { //TODO: check
        WitnessAnd and = new WitnessAnd();

        if(a.getMax() <= max && a.getMin() >= min) return new WitnessXBet(a.getMin(), a.getMax());
        if(a.getMax() > max && a.getMin() < min) return new WitnessBet(min, max);

        //caso non vittoria assoluta
        if(a.getMax() < max) and.add(new WitnessXBet(null, a.getMax()));
        else and.add(new WitnessBet(null, max));

        if(a.getMin() > min) and.add(new WitnessXBet(a.getMin(), null));
        else and.add(new WitnessBet(min, null));

        return and;

    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(GrammarStringDefinitions.TYPE_NUMBER);
    }

    @Override
    public Assertion getFullAlgebra() {
       return new Bet_Assertion(min == Double.NEGATIVE_INFINITY ? null : min, max == Double.POSITIVE_INFINITY ? null : max);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessBet that = (WitnessBet) o;

        if (min != null ? !min.equals(that.min) : that.min != null) return false;
        return max != null ? max.equals(that.max) : that.max == null;
    }

    @Override
    public int hashCode() {
        int result = min != null ? min.hashCode() : 0;
        result = 31 * result + (max != null ? max.hashCode() : 0);
        return result;
    }
}
=======
package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Bet_Assertion;

public class WitnessBet implements WitnessAssertion{ //fare anche caso merge con xbet
    private Double min, max;

    protected WitnessBet(){
    }

    public WitnessBet(Double min, Double max) {
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
        return "WitnessBet{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }

    @Override
    public WitnessAssertion merge(WitnessAssertion a) { //caso base: tipi diversi => non dovrebbe mai succedere
        if(a.getClass() == this.getClass())
            return this.merge((WitnessBet) a);
        if(a.getClass() == WitnessXBet.class)
            return this.merge((WitnessXBet) a);

        WitnessAnd and = new WitnessAnd();
        and.add(this);
        and.add(a);
        return and;
    }

    public WitnessAssertion merge(WitnessBet a) {
        Double m = (min < a.min) ? a.min : min;
        Double M = (max > a.max) ? a.max : max;

        return new WitnessBet(m, M);
    }

    public WitnessAssertion merge(WitnessXBet a) { //TODO: check
        WitnessAnd and = new WitnessAnd();

        /*if(a.getMax() != null && a.getMax() != null && min != null && max != null){*/
            if(a.getMax() <= max && a.getMin() >= min) return new WitnessXBet(a.getMin(), a.getMax());
            if(a.getMax() > max && a.getMin() < min) return new WitnessBet(min, max);

            //caso non vittoria assoluta
            if(a.getMax() < max) and.add(new WitnessXBet(null, a.getMax()));
            else and.add(new WitnessBet(null, max));

            if(a.getMin() > min) and.add(new WitnessXBet(a.getMin(), null));
            else and.add(new WitnessBet(min, null));

            return and;
        /*}

        if(a.getMin() == null && min != null){
            and.add(new WitnessBet(min, null));
        }
        if(a.getMin() != null && min == null){
            and.add(new WitnessXBet(a.getMin(), null));
        }

        if(a.getMax() == null && max != null){
            and.add(new WitnessBet(null, max));
        }
        if(a.getMax() != null && max == null){
            and.add(new WitnessXBet(null, a.getMax()));
        }



        return and;*/
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(GrammarStringDefinitions.TYPE_NUMBER);
    }

    @Override
    public Assertion getFullAlgebra() {
       return new Bet_Assertion(min == Double.NEGATIVE_INFINITY ? null : min, max == Double.POSITIVE_INFINITY ? null : max);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessBet that = (WitnessBet) o;

        if (min != null ? !min.equals(that.min) : that.min != null) return false;
        return max != null ? max.equals(that.max) : that.max == null;
    }

    @Override
    public int hashCode() {
        int result = min != null ? min.hashCode() : 0;
        result = 31 * result + (max != null ? max.hashCode() : 0);
        return result;
    }
}
>>>>>>> 9be72e1ac293591d2d50b1d0779180c7b28dedeb
