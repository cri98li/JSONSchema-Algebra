<<<<<<< HEAD
package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.MyPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.PosixPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.PatternRequired_Assertion;

import java.util.Objects;

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
        if(a == null) return this;

        if(this.value.getClass() == WitnessBoolean.class) {
            if (!((WitnessBoolean)this.value).getValue())
                return new WitnessBoolean(false);
        }

        if(a.getClass() == this.getClass())
            return this.merge((WitnessPattReq) a);

        return null;
    }

    public WitnessAssertion merge(WitnessPattReq a) {
        if(a.value.getClass() == WitnessBoolean.class) {
            if (!((WitnessBoolean)a.value).getValue())
                return new WitnessBoolean(false);
        }

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

        if (!Objects.equals(key, that.key)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
=======
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
>>>>>>> 9be72e1ac293591d2d50b1d0779180c7b28dedeb
