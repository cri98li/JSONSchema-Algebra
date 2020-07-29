package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.UnsenseAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Properties_Assertion;
import patterns.REException;

import java.util.Collection;
import java.util.Objects;

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

    public ComplexPattern getPattern() {
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
    public void checkLoopRef(WitnessEnv env, Collection<WitnessVar> varList) throws WitnessException {
        return;
    }

    @Override
    public WitnessAssertion mergeWith(WitnessAssertion a) throws REException {
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
        if(a.key.toString().equals(this.key.toString()) && a.value.mergeWith(this.value) != null){
            /*WitnessAnd and = new WitnessAnd();
            and.add(a.value);
            and.add(this.value);*/
            return new WitnessProperty(a.key.clone(), a.value.mergeWith(this.value));
        }

        if(a.value.equals(this.value)){
            return new WitnessProperty(a.key.union(this.key), this.value);
        }

        return null;
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(FullAlgebraString.TYPE_OBJECT);
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
                }catch (UnsenseAssertion e){
                    prop.value = new WitnessBoolean(false);
                }
                prop.value = and.groupize();
            }else
                prop.value = value.groupize();
        }

        return prop;
    }

    @Override
    public int countVarToBeExp(WitnessEnv env) {
        return 0;
    }

    @Override
    public void varNormalization_separation(WitnessEnv env) throws WitnessException, REException {

        if (value != null) {
            //TODO: true <--> allOf[true] ?
            if (value.getClass() != WitnessBoolean.class && value.getClass() != WitnessVar.class) {
                value.varNormalization_separation(env);
                WitnessVar var = new WitnessVar(Utils_WitnessAlgebra.getName(value));
                env.add(var, value);

                WitnessVar notVar = new WitnessVar(FullAlgebraString.NOT_DEFS + var.getValue());
                env.add(notVar, value.not());

                value = var;
            }
        }
    }

    @Override
    public WitnessAssertion varNormalization_expansion(WitnessEnv env) throws WitnessException {
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

    @Override
    public boolean isBooleanExp() {
        return false;
    }

    @Override
    public WitnessVar buildOBDD(WitnessEnv env) {
        throw new UnsupportedOperationException();
    }
}