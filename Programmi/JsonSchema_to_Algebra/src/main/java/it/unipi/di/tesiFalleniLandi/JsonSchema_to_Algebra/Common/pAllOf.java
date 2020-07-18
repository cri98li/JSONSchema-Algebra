package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.AlgebraParserElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;

import java.util.LinkedList;
import java.util.List;

public class pAllOf implements ComplexPatternElement {

    List<ComplexPatternElement> list;

    public pAllOf() {
        this.list = new LinkedList<>();
    }

    public void add(ComplexPatternElement element) {
        if (element.getClass() == this.getClass())
            list.addAll(((pAllOf) element).list);
        else
            list.add(element);
    }


    @Override
    public ComplexPatternElement clone() {
        pAllOf clone = new pAllOf();
        clone.list = new LinkedList<>(this.list);
        return clone;
    }

    @Override
    public String toString() {
        return "pAllOf" + list.toString();
    }
}
