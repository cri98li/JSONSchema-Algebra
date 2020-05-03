package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.MyPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.PosixPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.PatternRequired_Assertion;

public class WitnessPattReq implements WitnessAssertion{
    private PosixPattern key;
    private WitnessAssertion value;

    public WitnessPattReq(PosixPattern key, WitnessAssertion assertion){
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
        return "WitnessPattReq{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }

    @Override
    public WitnessAssertion merge(WitnessAssertion a) {
        return null;
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(GrammarStringDefinitions.TYPE_OBJECT);
    }

    @Override
    public Assertion getFullAlgebra() {
        PatternRequired_Assertion pra = new PatternRequired_Assertion();
        pra.add(key, value.getFullAlgebra());
        return pra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessPattReq that = (WitnessPattReq) o;

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
