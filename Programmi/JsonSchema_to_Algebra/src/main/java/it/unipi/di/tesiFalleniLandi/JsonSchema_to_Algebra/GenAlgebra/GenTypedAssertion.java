package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;

import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;

import java.util.List;

public class GenTypedAssertion implements GenAssertion{
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


    @Override
    public JsonElement generate() {
        return null;
    }

    @Override
    public JsonElement generateNext() {
        return null;
    }

    @Override
    public WitnessAssertion toWitnessAlgebra() {
        return null;
    }

    @Override
    public List<GenVar> usedVars() {
        return null;
    }
}
