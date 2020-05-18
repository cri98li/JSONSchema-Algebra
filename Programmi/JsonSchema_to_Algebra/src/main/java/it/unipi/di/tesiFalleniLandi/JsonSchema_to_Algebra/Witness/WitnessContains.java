package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Exist_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Type_Assertion;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class WitnessContains implements WitnessAssertion{
    private Double min, max;
    private WitnessAssertion contains;
    private boolean isAnArray;

    protected WitnessContains(){
        this.min = 0.0;
        this.max = Double.POSITIVE_INFINITY;
        isAnArray = false;
    }

    public WitnessContains(Long min, Long max, WitnessAssertion contains) {
        if(min == null)
            this.min = 0.0;
        else
            this.min = Double.parseDouble(min.toString());

        if(max == null)
            this.max = Double.POSITIVE_INFINITY;
        else
            this.max = Double.parseDouble(max.toString());

        this.contains = contains;

        isAnArray = contains.getClass() == WitnessBoolean.class;
    }

    @Override
    public String toString() {
        return "WitnessContains{" +
                "min=" + min +
                ", max=" + max +
                ", contains=" + contains +
                '}';
    }

    @Override
    public WitnessAssertion merge(WitnessAssertion a) {
        if(a == null) return this;
        if(this.contains != null && this.contains.getClass() == WitnessBoolean.class) {
            if (!((WitnessBoolean)this.contains).getValue())
                return new WitnessBoolean(false);
        }
        if(a.getClass() == this.getClass()) return this.merge((WitnessContains) a);

        return null;
    }

    public WitnessAssertion merge(WitnessContains a) {
        if(a.contains.getClass() == WitnessBoolean.class || isAnArray) {
            if (!((WitnessBoolean)a.contains).getValue())
                return new WitnessBoolean(false);
        }

        WitnessContains contains = new WitnessContains();

        contains.contains = contains.merge(a.contains);

        contains.min = (min < a.min) ? a.min : min;

        contains.max = (max > a.max) ? a.max : max;

        if(contains.min > contains.max){
            Type_Assertion type = new Type_Assertion();
            if(!contains.contains.equals(new WitnessBoolean(true)))
                type.add(GrammarStringDefinitions.TYPE_OBJECT);
            else
                type.add(GrammarStringDefinitions.TYPE_ARRAY);

            return type.not().toWitnessAlgebra();
        }else
            return contains;
    }

    @Override
    public WitnessType getGroupType() {
        if(contains.getClass() == WitnessBoolean.class || isAnArray)
            return new WitnessType(GrammarStringDefinitions.TYPE_ARRAY);
        return new WitnessType(GrammarStringDefinitions.TYPE_OBJECT);
    }

    @Override
    public Assertion getFullAlgebra() {
        return new Exist_Assertion(min.longValue(),
                max == Double.POSITIVE_INFINITY ? null : max.longValue(),
                (contains != null) ? contains.getFullAlgebra() : null);
    }

    @Override
    public WitnessContains clone() {
        WitnessContains clone = new WitnessContains();
        clone.max = max;
        clone.min = min;
        clone.isAnArray = isAnArray;
        if(contains != null)
            clone.contains = contains.clone();

        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessContains that = (WitnessContains) o;

        if(that.isAnArray != this.isAnArray) return false;
        if (!Objects.equals(min, that.min)) return false;
        if (!Objects.equals(max, that.max)) return false;
        return Objects.equals(contains, that.contains);
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
        WitnessContains contains = new WitnessContains();

        contains.min = min;
        contains.max = max;
        contains.isAnArray = isAnArray;
        if(contains != null) {
            if (this.contains.getClass() != WitnessAnd.class) {
                WitnessAnd and = new WitnessAnd();
                and.add(this.contains);
                contains.contains = and.groupize();
            } else
                contains.contains = this.contains.groupize();
        }

        return contains;
    }

    @Override
    public Set<WitnessAssertion> variableNormalization_separation() {
        Set<WitnessAssertion> set = new HashSet<>();

        if(contains != null || !isAnArray) {
            if(contains != null && contains.getClass() != WitnessBoolean.class) {
                set.addAll(contains.variableNormalization_separation());
                set.add(contains);
                contains = new WitnessVar(Utils_Witness.getName(contains));
            }else
                set.add(contains);
        }

        return set;
    }

    @Override
    public WitnessAssertion variableNormalization_expansion(WitnessEnv env) {
        WitnessContains contains = clone();

        contains.contains = this.contains.variableNormalization_expansion(env);

        return contains;
    }

    @Override
    public WitnessAssertion DNF() {
        WitnessContains contains = clone();

        contains.contains = this.contains.DNF();

        return contains;
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max, contains, isAnArray);
    }
}