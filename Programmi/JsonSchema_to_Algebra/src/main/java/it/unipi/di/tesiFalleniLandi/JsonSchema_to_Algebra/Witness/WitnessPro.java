package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Pro_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Type_Assertion;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class WitnessPro implements WitnessAssertion{
    private Double min, max;

    public WitnessPro(Double min, Double max) {
        if(min == null)
            this.min = 0.0;
        else
            this.min = min;

        if(max == null)
            this.max = Double.POSITIVE_INFINITY;
        else
            this.max = max;
    }

    public WitnessPro(){

    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }

    @Override
    public String toString() {
        return "WitnessPro{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }

    @Override
    public WitnessAssertion merge(WitnessAssertion a) {
        if(a == null) return this;

        if(a.getClass() == this.getClass())
            return this.merge((WitnessPro) a);

        return null;
    }

    public WitnessAssertion merge(WitnessPro a) {
        WitnessPro pro = new WitnessPro();

        pro.min = (this.min < a.min) ? a.min : min;

        pro.max = (this.max > a.max) ? a.max : max;

        if(pro.min > pro.max){
            Type_Assertion type = new Type_Assertion();
            type.add(GrammarStringDefinitions.TYPE_OBJECT);

            return type.not().toWitnessAlgebra();
        }else
            //return (pro.equals(this) || pro.equals(a)) ? null : pro; ???
            return pro;
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(GrammarStringDefinitions.TYPE_OBJECT);
    }

    @Override
    public Assertion getFullAlgebra() {
        return new Pro_Assertion(min.longValue(), max == Double.POSITIVE_INFINITY ? null : max.longValue());
    }

    @Override
    public WitnessAssertion clone() {
        return new WitnessPro(min, max);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessPro that = (WitnessPro) o;

        if (!Objects.equals(min, that.min)) return false;
        return Objects.equals(max, that.max);
    }

    @Override
    public int hashCode() {
        int result = min != null ? min.hashCode() : 0;
        result = 31 * result + (max != null ? max.hashCode() : 0);
        return result;
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