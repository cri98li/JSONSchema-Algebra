package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class IfBoolThen_Assertion implements Assertion {
    private boolean value;

    public  IfBoolThen_Assertion(boolean value){
        this.value = value;
    }

    @Override
    public Object toJSONSchema() {
        Type_Assertion t = new Type_Assertion();
        t.add("bool");
        return new IfThenElse_Assertion(t, new Const_Assertion(value), null);
    }

    @Override
    public Assertion not() {
        return new IfBoolThen_Assertion(!value);
    }

    @Override
    public Assertion notElimination() {
        return new IfBoolThen_Assertion(!value);
    }

    @Override
    public String toGrammarString() {
        return String.format(GrammarStringDefinitions.IFBOOLTHEN, value);
    }
}
