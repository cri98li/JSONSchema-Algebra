package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class pAnyOf implements ComplexPatternElement {
    private List<ComplexPatternElement> pAnyOf;

    public pAnyOf() {
        this.pAnyOf = new LinkedList<>();
    }

    public void add(ComplexPatternElement toAdd){
        if (toAdd.getClass() == this.getClass())
            for(ComplexPatternElement el : ((pAnyOf) toAdd).pAnyOf)
                add(el);
        else
            if(!contains(toAdd))
                pAnyOf.add(toAdd);
    }

    private boolean contains(ComplexPatternElement el){
        for(ComplexPatternElement a : pAnyOf)
            if(el.toString().equals(a.toString())) return true;

        return false;
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
