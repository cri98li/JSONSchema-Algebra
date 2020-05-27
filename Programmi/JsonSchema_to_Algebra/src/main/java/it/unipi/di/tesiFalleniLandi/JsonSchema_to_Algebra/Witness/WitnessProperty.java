package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import patterns.Pattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.PosixPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Properties_Assertion;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class WitnessProperty implements WitnessAssertion{
    private PosixPattern key;
    private WitnessAssertion value;

    protected WitnessProperty() { }

    public WitnessProperty(PosixPattern key, WitnessAssertion assertion){
        this.key = key;
        value = assertion;
    }

    public PosixPattern getKey() {
        return key;
    }

    public void setKey(Pattern key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "WitnessProperty{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }

    @Override
    public WitnessAssertion mergeElement(WitnessAssertion a) {
        if(value.getClass() == WitnessBoolean.class && !((WitnessBoolean)value).getValue()) return value;
        if(a.getClass() == this.getClass())
            return this.mergeElement((WitnessProperty) a);

        return null;
    }

    @Override
    public WitnessAssertion merge() {
        WitnessProperty newProp = this.clone();

        newProp.value = value.merge();

        return newProp;
    }

    public WitnessAssertion mergeElement(WitnessProperty a) {
        //Boolean Rewritings
        if(a.value.getClass() == WitnessBoolean.class && !((WitnessBoolean)a.value).getValue()) return a.value;


        if(a.key.equals(this.key)){ //TODO: confronto
            WitnessAnd and = new WitnessAnd();
            and.add(a.value);
            and.add(this.value);
            return new WitnessProperty(new Pattern(a.key.toString()/*TODO: .getPattern()*/), and.mergeElement(null));
        }

        if(a.value.equals(this.value)){
            return new WitnessProperty(a.key.intersect(this.key), this.value); //TODO: or
        }

        return null;
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(GrammarStringDefinitions.TYPE_OBJECT);
    }

    @Override
    public Assertion getFullAlgebra() {
        Properties_Assertion p = new Properties_Assertion();
        p.addPatternProperties(key, value.getFullAlgebra());

        return p;
    }

    @Override
    public WitnessProperty clone() {
        WitnessProperty clone = new WitnessProperty();

        clone.key = key; //TODO: witnessProp
        clone.value = value.clone();

        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessProperty that = (WitnessProperty) o;

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
    public WitnessAssertion not() {
        return getFullAlgebra().not().toWitnessAlgebra();
    }

    @Override
    public WitnessAssertion notElimination() {
        return getFullAlgebra().notElimination().toWitnessAlgebra();
    }

    @Override
    public WitnessAssertion groupize() throws WitnessException {
        WitnessProperty prop = new WitnessProperty();

        prop.key = key;
        if(value != null ){
            if(value.getClass() != WitnessAnd.class) {
                WitnessAnd and = new WitnessAnd();
                and.add(value);
                prop.value = and.groupize();
            }else
                prop.value = value.groupize();
        }

        return prop;
    }

    @Override
    public Set<WitnessAssertion> variableNormalization_separation() {
        HashSet set = new HashSet<>();

        if(value != null) {
            //TODO: true <--> allOf[true] ?
            if(value.getClass() != WitnessBoolean.class) {
                set.addAll(value.variableNormalization_separation());
                set.add(value);
                value = new WitnessVar(Utils_Witness.getName(value));
            }else
                set.add(value);
        }

        return set;
    }

    @Override
    public WitnessAssertion variableNormalization_expansion(WitnessEnv env) throws WitnessException {
        WitnessProperty prop = this.clone();
        if(value != null)   prop.value = this.value.variableNormalization_expansion(env);

        return prop;
    }

    @Override
    public WitnessAssertion DNF() throws WitnessException {
        WitnessProperty prop = this.clone();
        if(value != null)   prop.value = this.value.DNF();

        return prop;
    }
}