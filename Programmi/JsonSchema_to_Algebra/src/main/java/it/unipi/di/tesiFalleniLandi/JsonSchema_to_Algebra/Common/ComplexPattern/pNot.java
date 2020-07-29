package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern;

public class pNot implements ComplexPatternElement {
    ComplexPatternElement not;

    public pNot(ComplexPatternElement not) {
        this.not = not;
    }

    @Override
    public ComplexPatternElement clone() {
        return new pNot(not);
    }

    @Override
    public String toString() {
        return "pNot(" + not.toString()+")";
    }
}
