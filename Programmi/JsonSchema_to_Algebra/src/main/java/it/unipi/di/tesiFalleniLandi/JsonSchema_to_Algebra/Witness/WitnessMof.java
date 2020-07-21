package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Mof_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Type_Assertion;
import patterns.REException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class WitnessMof implements WitnessAssertion{ //fare anche il caso merge con notMof
    private Double value;

    public WitnessMof(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "WitnessMof{" +
                "value=" + value +
                '}';
    }

    @Override
    public void checkLoopReferences(WitnessEnv env, Collection<WitnessVar> varList) throws WitnessException {
        return;
    }

    @Override
    public WitnessAssertion mergeElement(WitnessAssertion a) throws REException {

        if(a.getClass() == WitnessMof.class)
            return this.mergeElement((WitnessMof) a);
        else if(a.getClass() == WitnessNotMof.class)
            return this.mergeElement((WitnessNotMof) a);

        return null;
    }

    public WitnessAssertion mergeElement(WitnessMof a) {
        return new WitnessMof(a.value * (value / gcd(a.value, value)));
    }

    public WitnessAssertion mergeElement(WitnessNotMof a) throws REException {
            WitnessNotMof notMof = a;
            Double val1 = notMof.getValue();
            Double val2 = this.value;

            if(val2 % val1 == 0) {
                Type_Assertion type = new Type_Assertion();
                type.add(GrammarStringDefinitions.TYPE_NUMBER);

                return type.not().toWitnessAlgebra();
            }else
                return null;
    }

    @Override
    public WitnessAssertion merge() {
        return this;
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(GrammarStringDefinitions.TYPE_NUMBER);
    }

    @Override
    public Assertion getFullAlgebra() {
        return new Mof_Assertion(value);
    }

    @Override
    public WitnessAssertion clone() {
        return new WitnessMof(value);
    }

    private static Double gcd(Double a, Double b)
    {
        while (b > 0)
        {
            Double temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessMof that = (WitnessMof) o;

        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    /*
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

     */

    @Override
    public WitnessAssertion not() throws REException {
        return getFullAlgebra().not().toWitnessAlgebra();
    }

    @Override
    public WitnessAssertion notElimination() throws REException {
        return getFullAlgebra().notElimination().toWitnessAlgebra();
    }

    @Override
    public WitnessAssertion groupize() {
        return this;
    }

    @Override
    public void variableNormalization_separation(WitnessEnv env) {
    }

    @Override
    public WitnessAssertion variableNormalization_expansion(WitnessEnv env) {
        return this.clone();
    }

    @Override
    public WitnessAssertion DNF() {
        return this.clone();
    }
}