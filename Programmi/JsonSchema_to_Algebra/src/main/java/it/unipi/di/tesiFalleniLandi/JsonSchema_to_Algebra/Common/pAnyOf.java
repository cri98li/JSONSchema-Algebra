package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.AlgebraParserElement;

import java.util.LinkedList;
import java.util.List;

public class pAnyOf implements ComplexPatternElement {
    private List<ComplexPatternElement> pAnyOf;

    public pAnyOf() {
        this.pAnyOf = new LinkedList<>();
    }

    public void add(ComplexPatternElement el){
        pAnyOf.add(el);
    }

    @Override
    public ComplexPatternElement clone() {
        pAnyOf clone = new pAnyOf();
        clone.pAnyOf = new LinkedList<>(pAnyOf);
        return clone;
    }

    @Override
    public String toString() {
        return "pAnyOf" + pAnyOf.toString();
    }
}
