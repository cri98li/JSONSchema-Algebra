package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Type_Assertion;

import java.util.LinkedList;
import java.util.List;

public class WitnessType implements WitnessAssertion{
    private String type;

    public WitnessType(String str){
        type = str;
    }

    @Override
    public String toString() {
        return "WitnessType{" +
                "type=" + type +
                '}';
    }

    @Override
    public WitnessAssertion merge(WitnessAssertion a) {
        if(a.getClass() == this.getClass())
            return this.merge((WitnessType) a);

        WitnessAnd and = new WitnessAnd();
        and.add(this);
        and.add(a);
        return and;
    }


    public WitnessAssertion merge(WitnessType a) {
        WitnessType tmp = (WitnessType) a;

        //stesso tipo
        if(a.equals(this))
            return this;

        // num, int
        if((tmp.type.equals(GrammarStringDefinitions.TYPE_INTEGER) && type.equals(GrammarStringDefinitions.TYPE_NUMBER)
            || tmp.type.equals(GrammarStringDefinitions.TYPE_NUMBER) && type.equals(GrammarStringDefinitions.TYPE_INTEGER)))
            return new WitnessType(GrammarStringDefinitions.TYPE_INTEGER);

        // numNotInt, num
        if((tmp.type.equals(GrammarStringDefinitions.TYPE_NUMBER) && type.equals(GrammarStringDefinitions.TYPE_NUMNOTINT)
                || tmp.type.equals(GrammarStringDefinitions.TYPE_NUMNOTINT) && type.equals(GrammarStringDefinitions.TYPE_NUMBER)))
            return new WitnessType(GrammarStringDefinitions.TYPE_NUMNOTINT);

        //tipi diversi
        if(!a.equals(this))
            return null;

        //type, S con type != ITE(S)
        if(!a.getGroupType().equals(this.getGroupType()))
            return null;

        WitnessAnd and = new WitnessAnd();
        and.add(this);
        and.add(a);

        return and;
    }

    @Override
    public WitnessType getGroupType() {
        return this;
    }

    @Override
    public Assertion getFullAlgebra() {
        Type_Assertion t = new Type_Assertion();
        t.add(type);
        return t;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessType that = (WitnessType) o;

        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        return type != null ? type.hashCode() : 0;
    }
}
