package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Properties_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

import java.util.*;

public class WitnessProperty implements WitnessAssertion{
    private static Logger logger = LogManager.getLogger(WitnessProperty.class);

    private ComplexPattern key;
    private WitnessAssertion value;

    protected WitnessProperty() {
        logger.trace("Creating an empty WitnessProperty");
    }

    public WitnessProperty(ComplexPattern key, WitnessAssertion assertion){
        if(assertion != null && assertion.getClass() == WitnessAnd.class && ((WitnessAnd) assertion).getIfUnitaryAnd() != null)
            assertion = ((WitnessAnd) assertion).getIfUnitaryAnd();

        logger.trace("Creating a new WitnessPropertiy with key: {} and value: {}", key, assertion);
        this.key = key;
        value = assertion;
    }

    public WitnessAssertion getValue() {
        return value;
    }

    public ComplexPattern getPattern() {
        return key;
    }

    public void setPattern(ComplexPattern key) {
        logger.trace("Set {} as key of {}", key, this);
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
    public void checkLoopRef(WitnessEnv env, Collection<WitnessVar> varList) {
        return;
    }

    @Override
    public WitnessAssertion mergeWith(WitnessAssertion a) throws REException {
        logger.trace("Merging {} with {}", a, this);
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
        WitnessProperty result = null;

        if(a.key.toString().equals(this.key.toString()) && a.value.mergeWith(this.value) != null)
            result = new WitnessProperty(a.key.clone(), a.value.mergeWith(this.value));

        if(a.value.equals(this.value))
            result =  new WitnessProperty(a.key.union(this.key), this.value);

        logger.trace("Merge result: ", result);

        return result;
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
    public WitnessAssertion not(WitnessEnv env) throws REException, WitnessException {
        WitnessAnd and = new WitnessAnd();
        WitnessType type = new WitnessType();
        type.add(FullAlgebraString.TYPE_OBJECT);
        and.add(type);
        and.add(WitnessPattReq.build(key.clone(), value.not(env)));

        return and;
    }

    @Override
    public WitnessAssertion groupize() throws WitnessException, REException {
        WitnessProperty prop = new WitnessProperty();
        prop.key = key;

        if(value.getClass() != WitnessAnd.class) {
            WitnessAnd and = new WitnessAnd();
            and.add(value);
            prop.value = and.groupize();
        }else
            prop.value = value.groupize();

        return prop;
    }

    @Override
    public Float countVarWithoutBDD(WitnessEnv env, List<WitnessVar> visitedVar) {
        return value.countVarWithoutBDD(env, visitedVar);
    }

    @Override
    public int countVarToBeExp(WitnessEnv env) {
        return 0;
    }

    @Override
    public void varNormalization_separation(WitnessEnv env) throws WitnessException, REException {

        if (value.getClass() != WitnessBoolean.class && value.getClass() != WitnessVar.class) {
            value.varNormalization_separation(env);

            Map.Entry<WitnessVar, WitnessVar> result = env.addWithComplement(value);

            value = result.getKey();
        }

    }

    @Override
    public WitnessAssertion varNormalization_expansion(WitnessEnv env) {
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
    public WitnessAssertion toOrPattReq() {
        value = value.toOrPattReq();

        return this;
    }

    @Override
    public boolean isBooleanExp() {
        return false;
    }

    @Override
    public boolean isRecursive(WitnessEnv env, LinkedList<WitnessVar> visitedVar) {
        return value.isRecursive(env, visitedVar);
    }

    @Override
    public WitnessVar buildOBDD(WitnessEnv env) {
        throw new UnsupportedOperationException();
    }
}