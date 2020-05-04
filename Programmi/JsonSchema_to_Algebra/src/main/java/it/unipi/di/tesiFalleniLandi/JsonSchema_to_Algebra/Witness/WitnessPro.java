<<<<<<< HEAD
package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Pro_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Type_Assertion;

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
            return (pro.equals(this) || pro.equals(a)) ? null : pro;
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(GrammarStringDefinitions.TYPE_OBJECT);
    }

    @Override
    public Assertion getFullAlgebra() {
        return new Pro_Assertion(Double.valueOf(min).longValue(), max == Double.POSITIVE_INFINITY ? null : Double.valueOf(max).longValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessPro that = (WitnessPro) o;

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
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Pro_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Type_Assertion;

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
        if(a.getClass() == this.getClass())
            return this.merge((WitnessPro) a);

        WitnessAnd and = new WitnessAnd();
        and.add(this);
        and.add(a);
        return and;
    }

    public WitnessAssertion merge(WitnessPro a) {
        WitnessPro pro = new WitnessPro();

        pro.min = (this.min < a.min) ? a.min : min;

        pro.max = (this.max > a.max) ? a.max : max;

        if(pro.min > pro.max){
            WitnessOr or = new WitnessOr();
            Type_Assertion type = new Type_Assertion();
            type.add(GrammarStringDefinitions.TYPE_OBJECT);
            or.add(type.not().toWitnessAlgebra());
            or.add(new WitnessBoolean(false));

            return or;
        }else
            return pro;
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(GrammarStringDefinitions.TYPE_OBJECT);
    }

    @Override
    public Assertion getFullAlgebra() {
        return new Pro_Assertion(Double.valueOf(min).longValue(), max == Double.POSITIVE_INFINITY ? null : Double.valueOf(max).longValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessPro that = (WitnessPro) o;

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
