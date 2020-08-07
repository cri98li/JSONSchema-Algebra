package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Bet_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Type_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessException;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessFalseAssertionException;
import patterns.REException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class WitnessBet implements WitnessAssertion{
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
    public WitnessAssertion merge() {
        return this;
    }

    @Override
    public void checkLoopRef(WitnessEnv env, Collection<WitnessVar> varList) throws WitnessException {
        return;
    }

    @Override
    public WitnessAssertion mergeWith(WitnessAssertion a) throws REException { //caso base: tipi diversi => non dovrebbe mai succedere
        if(min > max) {
            Type_Assertion type = new Type_Assertion();
            type.add(FullAlgebraString.TYPE_NUMBER);

            return type.not().toWitnessAlgebra();
        }

        if(a.getClass() == this.getClass())
            return this.mergeElement((WitnessBet) a);
        if(a.getClass() == WitnessXBet.class)
            return this.mergeElement((WitnessXBet) a);

        return null;
    }



    public WitnessAssertion mergeElement(WitnessBet a) throws REException {
        if(a.min > max || a.max < min){
            Type_Assertion type = new Type_Assertion();
            type.add(FullAlgebraString.TYPE_NUMBER);

            return type.not().toWitnessAlgebra();
        }

        Double m = (min < a.min) ? a.min : min;
        Double M = (max > a.max) ? a.max : max;

        WitnessBet newBet = new WitnessBet(m, M);
        return (this.equals(newBet) && a.equals(newBet)) ? null : newBet;
    }

    public WitnessAssertion mergeElement(WitnessXBet a) throws REException { //TODO: check
        if(a.min >= max || a.max <= min){
            Type_Assertion type = new Type_Assertion();
            type.add(FullAlgebraString.TYPE_NUMBER);

            return type.not().toWitnessAlgebra();
        }
        WitnessAnd and = new WitnessAnd();

        if(a.getMax() <= max && a.getMin() >= min)
            return new WitnessXBet(a.getMin(), a.getMax());
        if(a.getMax() > max && a.getMin() < min)
            return new WitnessBet(min, max);

        WitnessAnd andTmp = new WitnessAnd();
        try {
            //caso non vittoria assoluta
            if (a.getMax() > max)
                and.add(new WitnessBet(null, max));
            else
                and.add(new WitnessXBet(null, a.getMax()));

            if (a.getMin() < min)
                and.add(new WitnessBet(min, null));
            else
                and.add(new WitnessXBet(a.getMin(), null));

            //check if the output is the same as the input
            andTmp.add(this);
            andTmp.add(a);
        }catch (WitnessFalseAssertionException e){
            throw  new RuntimeException(e); //impossible
        }

        return and.equals(andTmp) ? null : and;

    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(FullAlgebraString.TYPE_NUMBER);
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
    public WitnessAssertion not(WitnessEnv env) throws REException {
        return getFullAlgebra().not().toWitnessAlgebra();
    }

    @Override
    public WitnessAssertion groupize() {
        return this;
    }

    @Override
    public Float countVarWithoutBDD(WitnessEnv env, List<WitnessVar> visitedVar) {
        return 0f;
    }

    @Override
    public int countVarToBeExp(WitnessEnv env) {
        return 0;
    }

    @Override
    public void varNormalization_separation(WitnessEnv env) {
    }

    @Override
    public WitnessAssertion varNormalization_expansion(WitnessEnv env) {
        return clone();
    }

    @Override
    public WitnessAssertion DNF() {
        return this.clone();
    }

    @Override
    public WitnessAssertion toOrPattReq() throws WitnessFalseAssertionException {
        return this;
    }

    @Override
    public boolean isBooleanExp() {
        return false;
    }

    @Override
    public boolean isRecursive(WitnessEnv env, LinkedList<WitnessVar> visitedVar) {
        return false;
    }

    @Override
    public WitnessVar buildOBDD(WitnessEnv env) throws WitnessException {
        throw new UnsupportedOperationException();
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