package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Const_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Exist_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Type_Assertion;

public class WitnessContains implements WitnessAssertion{
    private Double min, max;
    private WitnessAssertion contains;

    protected WitnessContains(){

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
        if(a.getClass() == this.getClass()) return this.merge((WitnessContains) a);

        WitnessAnd and = new WitnessAnd();
        and.add(this);
        and.add(a);
        return and;
    }

    public WitnessAssertion merge(WitnessContains a) {
        WitnessContains contains = new WitnessContains();

        contains.contains = contains.merge(a.contains);

        contains.min = (min < a.min) ? a.min : min;

        contains.max = (max > a.max) ? a.max : max;

        if(contains.min > contains.max){
            WitnessOr or = new WitnessOr();
            Type_Assertion type = new Type_Assertion();
            if(!contains.contains.equals(new WitnessBoolean(true)))
                type.add(GrammarStringDefinitions.TYPE_OBJECT);
            else
                type.add(GrammarStringDefinitions.TYPE_ARRAY);

            or.add(type.not().toWitnessAlgebra());
            or.add(new WitnessBoolean(false));

            return or;
        }else
            return contains;
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(GrammarStringDefinitions.TYPE_OBJECT);
    }

    @Override
    public Assertion getFullAlgebra() {
        return new Exist_Assertion(Long.parseLong(min.toString()), max == Double.POSITIVE_INFINITY ? null : Long.parseLong(max.toString()), contains.getFullAlgebra());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessContains that = (WitnessContains) o;

        if (min != null ? !min.equals(that.min) : that.min != null) return false;
        if (max != null ? !max.equals(that.max) : that.max != null) return false;
        return contains != null ? contains.equals(that.contains) : that.contains == null;
    }

    @Override
    public int hashCode() {
        int result = min != null ? min.hashCode() : 0;
        result = 31 * result + (max != null ? max.hashCode() : 0);
        result = 31 * result + (contains != null ? contains.hashCode() : 0);
        return result;
    }
}
