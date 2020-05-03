package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Mof_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Type_Assertion;

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
        if(a.getClass() == WitnessMof.class) {
            WitnessMof mof = (WitnessMof) a;
            return new WitnessMof(mof.value * (value / gcd(mof.value, value)));
        }else{
            WitnessNotMof notMof = (WitnessNotMof) a;
            Double val1 = notMof.getValue();
            Double val2 = this.value;

                if((val1 % val2 == 0) || (val2 % val1 == 0)) { //PER ORA SI FA COSI??
                    WitnessOr or = new WitnessOr();
                    Type_Assertion type = new Type_Assertion();
                    type.add(GrammarStringDefinitions.TYPE_NUMBER);
                    or.add(type.not().toWitnessAlgebra());
                    or.add(new WitnessBoolean(false));

                    return or;
                }else{
                    WitnessAnd and = new WitnessAnd();
                    and.add(notMof);
                    and.add(this);

                    return and;
                }
        }
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(GrammarStringDefinitions.TYPE_NUMBER);
    }

    @Override
    public Assertion getFullAlgebra() {
        return new Mof_Assertion(value);
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

        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
