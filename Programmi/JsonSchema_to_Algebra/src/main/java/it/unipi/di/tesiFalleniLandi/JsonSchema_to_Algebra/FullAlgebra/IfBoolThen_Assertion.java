package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessIfBoolThen;

public class IfBoolThen_Assertion implements Assertion {
    private boolean value;

    public  IfBoolThen_Assertion(boolean value){
        this.value = value;
    }

    @Override
    public JsonElement toJSONSchema() {
        Type_Assertion t = new Type_Assertion();
        t.add("bool");
        return new IfThenElse_Assertion(t, new Const_Assertion(new JsonPrimitive(value)), null).toJSONSchema();
    }

    @Override
    public Assertion not() {
        return new IfBoolThen_Assertion(!value);
    }

    @Override
    public Assertion notElimination() {
        return new IfBoolThen_Assertion(value);
    }

    @Override
    public String toGrammarString() {
        return FullAlgebraString.IFBOOLTHEN(value+"");
    }

    @Override
    public WitnessIfBoolThen toWitnessAlgebra() {
        return new WitnessIfBoolThen(value);
    }
}
