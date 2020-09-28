package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;

import java.util.List;

public class GenTypedAssertion {
    private List<GenAssertion> typedAssertion;

    public List<GenAssertion> getTypedAssertion() {
        return typedAssertion;
    }

    public GenTypedAssertion(List<GenAssertion> typedAssertion) {
        this.typedAssertion = typedAssertion;
    }

    public boolean containsBaseType() {
            long l = typedAssertion.stream().filter(t->(t instanceof GenNum ||
                    t instanceof GenNull || t instanceof GenString)).count();
            return l>0;
    }
}
