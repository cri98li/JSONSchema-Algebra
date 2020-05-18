package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Mof_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Type_Assertion;

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
    public WitnessAssertion merge(WitnessAssertion a) {
        if(a == null) return this;
        if(a.getClass() == WitnessMof.class) {
            WitnessMof mof = (WitnessMof) a;
            return new WitnessMof(mof.value * (value / gcd(mof.value, value)));
        }else if(a.getClass() == WitnessNotMof.class) {
            WitnessNotMof notMof = (WitnessNotMof) a;
            Double val1 = notMof.getValue();
            Double val2 = this.value;

                if(val2 % val1 == 0) {
                    Type_Assertion type = new Type_Assertion();
                    type.add(GrammarStringDefinitions.TYPE_NUMBER);

                    return type.not().toWitnessAlgebra();
                }else
                    return null;
        }else
            return null;
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
        return Objects.hash(value);
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
        return this.clone();
    }

    @Override
    public WitnessAssertion DNF() {
        return this.clone();
    }
}