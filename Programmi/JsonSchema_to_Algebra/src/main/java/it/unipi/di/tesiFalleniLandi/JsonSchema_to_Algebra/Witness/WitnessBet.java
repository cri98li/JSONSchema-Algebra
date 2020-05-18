package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Bet_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Type_Assertion;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class WitnessBet implements WitnessAssertion{ //fare anche caso merge con xbet
    private Double min, max;

    protected WitnessBet(){ }

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
        if(min > max) {
            Type_Assertion type = new Type_Assertion();
            type.add(GrammarStringDefinitions.TYPE_NUMBER);

            return type.not().toWitnessAlgebra();
        }

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

        WitnessBet newBet = new WitnessBet(m, M);
        return (this.equals(newBet) && a.equals(newBet)) ? null : newBet;
    }

    public WitnessAssertion merge(WitnessXBet a) { //TODO: check
        if(a.min >= max || a.max <= min){
            Type_Assertion type = new Type_Assertion();
            type.add(GrammarStringDefinitions.TYPE_NUMBER);

            return type.not().toWitnessAlgebra();
        }
        boolean invariati = true;
        WitnessAnd and = new WitnessAnd();

        if(a.getMax() <= max && a.getMin() >= min) return new WitnessXBet(a.getMin(), a.getMax());
        if(a.getMax() > max && a.getMin() < min) return new WitnessBet(min, max);

        //caso non vittoria assoluta
        if(a.getMax() > max) {
            and.add(new WitnessBet(null, max));
            invariati = false;
        }

        if(a.getMin() < min){
            and.add(new WitnessBet(min, null));
            invariati = false;
        }

        return invariati ? null : and;

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
    public WitnessAssertion clone() {
        return new WitnessBet(min, max);
    }

    @Override
    public WitnessAssertion not() {
        return getFullAlgebra().not().toWitnessAlgebra();
    }

    @Override
    public WitnessAssertion notElimination() {
        return getFullAlgebra().notElimination().toWitnessAlgebra();
    }

    @Override
    public WitnessAssertion groupize() {
        return this;
    }

    @Override
    public Set<WitnessAssertion> variableNormalization_separation() {
        return new HashSet<>();
    }

    @Override
    public WitnessAssertion variableNormalization_expansion(WitnessEnv env) {
        return clone();
    }

    @Override
    public WitnessAssertion DNF() {
        return this.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessBet that = (WitnessBet) o;

        if (!Objects.equals(min, that.min)) return false;
        return Objects.equals(max, that.max);
    }

    @Override
    public int hashCode() {
        int result = min != null ? min.hashCode() : 0;
        result = 31 * result + (max != null ? max.hashCode() : 0);
        return result;
    }
}