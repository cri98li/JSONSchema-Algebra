<<<<<<< HEAD
package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.MyPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.PosixPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Pattern_Assertion;

public class WitnessPattern implements WitnessAssertion{
    private PosixPattern pattern;

    public WitnessPattern(PosixPattern pattern){
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return "WitnessPattern{" +
                "pattern=" + pattern +
                '}';
    }

    @Override
    public WitnessAssertion merge(WitnessAssertion a) {
        return null;
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(GrammarStringDefinitions.TYPE_STRING);
    }

    @Override
    public Assertion getFullAlgebra() {
        return new Pattern_Assertion(pattern);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessPattern that = (WitnessPattern) o;

        return pattern != null ? pattern.equals(that.pattern) : that.pattern == null;
    }

    @Override
    public int hashCode() {
        return pattern != null ? pattern.hashCode() : 0;
    }
}
=======
package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.MyPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.PosixPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Pattern_Assertion;

public class WitnessPattern implements WitnessAssertion{
    private PosixPattern pattern;

    public WitnessPattern(PosixPattern pattern){
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return "WitnessPattern{" +
                "pattern=" + pattern +
                '}';
    }

    @Override
    public WitnessAssertion merge(WitnessAssertion a) {
        WitnessAnd and = new WitnessAnd();
        and.add(this);
        and.add(a);
        return and;
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(GrammarStringDefinitions.TYPE_STRING);
    }

    @Override
    public Assertion getFullAlgebra() {
        return new Pattern_Assertion(pattern);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessPattern that = (WitnessPattern) o;

        return pattern != null ? pattern.equals(that.pattern) : that.pattern == null;
    }

    @Override
    public int hashCode() {
        return pattern != null ? pattern.hashCode() : 0;
    }
}
>>>>>>> 9be72e1ac293591d2d50b1d0779180c7b28dedeb
