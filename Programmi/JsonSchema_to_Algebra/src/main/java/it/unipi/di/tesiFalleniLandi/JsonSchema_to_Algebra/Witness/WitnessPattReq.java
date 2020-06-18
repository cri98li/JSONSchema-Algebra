package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import patterns.Pattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.PatternRequired_Assertion;
import patterns.REException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class WitnessPattReq implements WitnessAssertion{
    private Pattern key;
    private WitnessAssertion value;

    public WitnessPattReq(Pattern key, WitnessAssertion assertion){
        this.key = key;
        value = assertion;
    }

    protected WitnessPattReq() { }

    public Pattern getKey() {
        return key;
    }

    public void setKey(Pattern key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "WitnessPattReq{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }

    @Override
    public WitnessAssertion mergeElement(WitnessAssertion a) {
        if(a.getClass() == this.getClass())
            return this.mergeElement((WitnessPattReq) a);

        return null;
    }

    @Override
    public WitnessAssertion merge() throws REException {
        WitnessPattReq clone = this.clone();

        //Boolean rewritings
        if((this.value.getClass() == WitnessBoolean.class) && (!((WitnessBoolean)this.value).getValue()))
            return new WitnessBoolean(false);

        clone.value = value.merge();

        return clone;
    }

    public WitnessAssertion mergeElement(WitnessPattReq a) {

        return null;
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(GrammarStringDefinitions.TYPE_OBJECT);
    }

    @Override
    public Assertion getFullAlgebra() {
        PatternRequired_Assertion pra = new PatternRequired_Assertion();
        pra.add(key, this.value.getFullAlgebra());
        return pra;
    }

    @Override
    public WitnessPattReq clone() {
        WitnessPattReq clone = new WitnessPattReq();

        clone.key = key.clone();
        clone.value = value.clone();

        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessPattReq that = (WitnessPattReq) o;

        if (!Objects.equals(key, that.key)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public WitnessAssertion not() throws REException {
        return getFullAlgebra().not().toWitnessAlgebra();
    }

    @Override
    public WitnessAssertion notElimination() throws REException {
        return getFullAlgebra().notElimination().toWitnessAlgebra();
    }

    @Override
    public WitnessAssertion groupize() throws WitnessException {
        WitnessPattReq pattReq = new WitnessPattReq();

        pattReq.key = key;
        if(pattReq != null){
            if(value.getClass() != WitnessAnd.class) {
                WitnessAnd and = new WitnessAnd();
                and.add(value);
                pattReq.value = and.groupize();
            }
            else pattReq.value = value.groupize();
        }

        return pattReq;
    }

    @Override
    public void variableNormalization_separation(WitnessEnv env) {

        if (value != null) {
            if (value.getClass() != WitnessBoolean.class) {
                value.variableNormalization_separation(env);
                WitnessVar var = new WitnessVar(Utils_Witness.getName(value));
                env.add(var, value);
                value = var;
            }
        }
    }

    @Override
    public WitnessAssertion variableNormalization_expansion(WitnessEnv env) throws WitnessException {
        WitnessPattReq pattReq = new WitnessPattReq();
        pattReq.key = this.key;
        if(value != null) pattReq.value = this.value.variableNormalization_expansion(env);

        return pattReq;
    }

    @Override
    public WitnessAssertion DNF() {
        WitnessPattReq pattReq = new WitnessPattReq();
        pattReq.key = this.key;
        if(value != null) pattReq.value = this.value.clone();

        return pattReq;
    }
}