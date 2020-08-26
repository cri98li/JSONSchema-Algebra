package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.NotMof_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

import java.util.*;

public class WitnessNotMof implements WitnessAssertion{
    private static Logger logger = LogManager.getLogger(WitnessNotMof.class);

    private Double value;

    public WitnessNotMof(Double value) {
        this.value = value;
        logger.trace("Created a new WitnessNotMof: {}", this);
    }

    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "WitnessNotMof{" +
                "value=" + value +
                '}';
    }

    @Override
    public void checkLoopRef(WitnessEnv env, Collection<WitnessVar> varList) throws RuntimeException {
        return;
    }

    @Override
    public void reachableRefs(Set<WitnessVar> collectedVar, WitnessEnv env) throws RuntimeException {
        return;
    }

    @Override
    public WitnessAssertion mergeWith(WitnessAssertion a) throws REException { //caso base: tipi diversi => non dovrebbe mai succedere
        logger.trace("Merging {} with {}", a, this);

        if(a.getClass() == this.getClass())
            return this.mergeElement((WitnessNotMof) a);

        return null;
    }

    @Override
    public WitnessAssertion merge() {
        return this;
    }

    public WitnessAssertion mergeElement(WitnessNotMof notMof) {
        WitnessNotMof result = null;

        Double val1 = notMof.value;
        Double val2 = this.value;

        if(val1 % val2 == 0)
            result = new WitnessNotMof(val2);
        else if(val2 % val1 == 0)
            result = new WitnessNotMof(val1);

        logger.trace("Merge result: {}", result);
        return result;
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(FullAlgebraString.TYPE_NUMBER);
    }

    @Override
    public Assertion getFullAlgebra() {
        return new NotMof_Assertion(value);
    }

    @Override
    public WitnessAssertion clone() {
        return new WitnessNotMof(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessNotMof that = (WitnessNotMof) o;

        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public WitnessAssertion not(WitnessEnv env) throws REException {
        return getFullAlgebra().not().toWitnessAlgebra();
    }

    @Override
    public WitnessAssertion groupize() {
        return this;
    }

    @Override
    public Float countVarWithoutBDD(WitnessEnv env, List<WitnessVar> visitedVar) {
        return 0f;
    }

    @Override
    public int countVarToBeExp(WitnessEnv env) {
        return 0;
    }

    @Override
    public List<Map.Entry<WitnessVar, WitnessAssertion>> varNormalization_separation(WitnessEnv env) {
        return new LinkedList<>();
    }

    @Override
    public WitnessAssertion varNormalization_expansion(WitnessEnv env) {
        return this.clone();
    }

    @Override
    public WitnessAssertion DNF() {
        return this.clone();
    }

    @Override
    public WitnessAssertion toOrPattReq() {
        return this;
    }

    @Override
    public boolean isBooleanExp() {
        return false;
    }

    @Override
    public boolean isRecursive(WitnessEnv env, LinkedList<WitnessVar> visitedVar) {
        return false;
    }

    @Override
    public WitnessVar buildOBDD(WitnessEnv env) throws WitnessException {
        throw new UnsupportedOperationException();
    }
}