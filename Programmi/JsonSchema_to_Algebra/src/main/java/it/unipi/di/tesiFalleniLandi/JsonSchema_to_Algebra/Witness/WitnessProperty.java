package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.UnsenseAssertion;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import patterns.Pattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Properties_Assertion;
import patterns.REException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class WitnessProperty implements WitnessAssertion{
    private ComplexPattern key;
    private WitnessAssertion value;

    protected WitnessProperty() { }

    public WitnessProperty(ComplexPattern key, WitnessAssertion assertion){
        this.key = key;
        value = assertion;
    }

    public WitnessAssertion getValue() {
        return value;
    }

    public ComplexPattern getKey() {
        return key;
    }

    public void setKey(ComplexPattern key) {
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
    public void checkLoopReferences(WitnessEnv env, Collection<WitnessVar> varList) throws WitnessException {
        value.checkLoopReferences(env, varList);
    }

    @Override
    public WitnessAssertion mergeElement(WitnessAssertion a) throws REException {
        if(value.getClass() == WitnessBoolean.class && !((WitnessBoolean)value).getValue()) return value;
        if(a.getClass() == this.getClass())
            return this.mergeElement((WitnessProperty) a);

        return null;
    }

    @Override
    public WitnessAssertion merge() throws REException {
        //Boolean Rewritings
        if(value.getClass() == WitnessBoolean.class && ((WitnessBoolean) value).getValue()) return value;

        WitnessProperty newProp = this.clone();

        newProp.value = value.merge();

        return newProp;
    }

    public WitnessAssertion mergeElement(WitnessProperty a) throws REException {
        if(a.key.toString().equals(this.key.toString())){
            /*WitnessAnd and = new WitnessAnd();
            and.add(a.value);
            and.add(this.value);*/
            return new WitnessProperty(a.key.clone(), a.value.mergeElement(this.value));
        }

        if(a.value.equals(this.value)){
            return new WitnessProperty(a.key.union(this.key), this.value);
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

        clone.key = key.clone();
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
    public WitnessAssertion not() throws REException {
        return getFullAlgebra().not().toWitnessAlgebra();
    }

    @Override
    public WitnessAssertion notElimination() throws REException {
        return getFullAlgebra().notElimination().toWitnessAlgebra();
    }

    @Override
    public WitnessAssertion groupize() throws WitnessException, REException {
        WitnessProperty prop = new WitnessProperty();

        prop.key = key;
        if(value != null ){
            if(value.getClass() != WitnessAnd.class) {
                WitnessAnd and = new WitnessAnd();
                try {
                    and.add(value);
                    prop.value = and.groupize();
                }catch (UnsenseAssertion e){
                    prop.value = new WitnessBoolean(false);
                }
            }else
                prop.value = value.groupize();
        }

        return prop;
    }

    @Override
    public void variableNormalization_separation(WitnessEnv env) {

        if (value != null) {
            //TODO: true <--> allOf[true] ?
            if (value.getClass() != WitnessBoolean.class && value.getClass() != WitnessVar.class) {
                value.variableNormalization_separation(env);
                WitnessVar var = new WitnessVar(Utils_Witness.getName(value));
                env.add(var, value);
                value = var;
            }
        }
    }

    @Override
    public WitnessAssertion variableNormalization_expansion(WitnessEnv env) throws WitnessException {
        /*
        WitnessProperty prop = new WitnessProperty();
        prop.key = this.key;
        if(value != null)   prop.value = this.value.variableNormalization_expansion(env);
        */

        return this;
    }

    @Override
    public WitnessAssertion DNF() throws WitnessException {
        WitnessProperty prop = new WitnessProperty();
        prop.key = this.key;
        if(value != null)   prop.value = this.value.DNF();

        return prop;
    }
}