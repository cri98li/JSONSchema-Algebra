package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.MyPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.PosixPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Properties_Assertion;

public class WitnessProperty implements WitnessAssertion{
    private PosixPattern key;
    private WitnessAssertion value;

    public WitnessProperty(PosixPattern key, WitnessAssertion assertion){
        this.key = key;
        value = assertion;
    }

    public PosixPattern getKey() {
        return key;
    }

    public void setKey(MyPattern key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "WitnessProperty{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }


    @Override
    public WitnessAssertion merge(WitnessAssertion a) {
        if(a.getClass() == this.getClass())
            return this.merge((WitnessProperty) a);

        WitnessAnd and = new WitnessAnd();
        and.add(this);
        and.add(a);
        return and;
    }

    public WitnessAssertion merge(WitnessProperty a) {
        if(a.key.getPattern().equals(this.key.getPattern())){
            WitnessAnd and = new WitnessAnd();
            and.add(a.value);
            and.add(this.value);
            return new WitnessProperty(new MyPattern(a.key.getPattern()), and.merge(null));
        }

        WitnessAnd and = new WitnessAnd();
        and.add(this);
        and.add(a);

        return and;
    }


    @Override
    public WitnessType getGroupType() {
        return new WitnessType(GrammarStringDefinitions.TYPE_OBJECT);
    }

    @Override
    public Assertion getFullAlgebra() {
        Properties_Assertion p = new Properties_Assertion();

        p.addPatternProperties(key, value.getFullAlgebra());

        return p;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessProperty that = (WitnessProperty) o;

        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
