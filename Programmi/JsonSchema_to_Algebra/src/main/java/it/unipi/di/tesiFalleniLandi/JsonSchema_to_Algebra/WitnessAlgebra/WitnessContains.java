package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.AlgebraStrings;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Exist_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Type_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

import java.util.*;

public class WitnessContains implements WitnessAssertion{
    private static Logger logger = LogManager.getLogger(WitnessContains.class);

    private Double min, max;
    private WitnessAssertion contains;
    private boolean isAnArray;

    protected WitnessContains(){
        this.min = 0.0;
        this.max = Double.POSITIVE_INFINITY;
        isAnArray = false;
        logger.debug("Created a new WitnessContains: {}", this);
    }

    public WitnessContains(double min, double max, WitnessAssertion contains) {
        this.min = min;
        this.max = max;
        if(contains != null && contains.getClass() == WitnessAnd.class && ((WitnessAnd) contains).getIfUnitaryAnd() != null)
            contains = ((WitnessAnd) contains).getIfUnitaryAnd();
        this.contains = contains;
        logger.debug("Created a new WitnessContains: {}", this);
    }

    public WitnessContains(Long min, Long max, WitnessAssertion contains) {
        if(min == null)
            this.min = 0.0;
        else
            this.min = Double.parseDouble(min.toString());

        if(max == null)
            this.max = Double.POSITIVE_INFINITY;
        else
            this.max = Double.parseDouble(max.toString());

        if(contains != null && contains.getClass() == WitnessAnd.class && ((WitnessAnd) contains).getIfUnitaryAnd() != null)
            contains = ((WitnessAnd) contains).getIfUnitaryAnd();
        this.contains = contains;

        isAnArray = contains.getClass() == WitnessBoolean.class;
        logger.debug("Created a new WitnessContains: {}", this);
    }

    @Override
    public String toString() {
        return "WitnessContains{" +
                "min=" + min +
                ", max=" + max +
                ", contains=" + contains +
                '}';
    }

    @Override
    public void checkLoopRef(WitnessEnv env, Collection<WitnessVar> varList) throws RuntimeException {
        return;
    }

    @Override
    public void reachableRefs(Set<WitnessVar> collectedVar, WitnessEnv env) throws RuntimeException {
        if(contains != null)
            contains.reachableRefs(collectedVar, env);
    }

    @Override
    public WitnessAssertion mergeWith(WitnessAssertion a, WitnessVarManager varManager, WitnessPattReqManager pattReqManager) throws REException {
        logger.debug("Merging {} with {}", a, this);
        if(this.contains != null && this.contains.getClass() == WitnessBoolean.class) {
            if (!((WitnessBoolean)this.contains).getValue())
                return new WitnessBoolean(false);
        }
        if(a.getClass() == this.getClass()) return this.mergeElement((WitnessContains) a, varManager, pattReqManager);

        return null;
    }

    @Override
    public WitnessAssertion merge(WitnessVarManager varManager, WitnessPattReqManager pattReqManager) throws REException {
        WitnessContains newContains = this.clone();

        if(contains != null)
            newContains.contains = contains.merge(varManager, pattReqManager);

        return newContains;
    }

    public WitnessAssertion mergeElement(WitnessContains a, WitnessVarManager varManager, WitnessPattReqManager pattReqManager) throws REException {
        if(a.contains.getClass() == WitnessBoolean.class || isAnArray) {
            if (!((WitnessBoolean)a.contains).getValue()) {
                logger.debug("Merge result: false");
                return new WitnessBoolean(false);
            }
        }

        WitnessContains contains = new WitnessContains();

        contains.contains = contains.mergeWith(a.contains, varManager, pattReqManager);

        contains.min = (min < a.min) ? a.min : min;

        contains.max = (max > a.max) ? a.max : max;

        if(contains.min > contains.max){
            Type_Assertion type = new Type_Assertion();
            if(!contains.contains.equals(new WitnessBoolean(true)))
                type.add(AlgebraStrings.TYPE_OBJECT);
            else
                type.add(AlgebraStrings.TYPE_ARRAY);

            logger.debug("Merge result: {}", type.not());
            return type.not().toWitnessAlgebra(null, null, null);
        }else {
            logger.debug("Merge result: {}", contains);
            return contains;
        }
    }

    @Override
    public WitnessType getGroupType() {
        if(contains.getClass() == WitnessBoolean.class || isAnArray)
            return new WitnessType(AlgebraStrings.TYPE_ARRAY);
        return new WitnessType(AlgebraStrings.TYPE_OBJECT);
    }

    @Override
    public Assertion getFullAlgebra() {
        return new Exist_Assertion(min.longValue(),
                max == Double.POSITIVE_INFINITY ? null : max.longValue(),
                (contains != null) ? contains.getFullAlgebra() : null);
    }

    @Override
    public WitnessContains clone() {
        logger.debug("Cloning WitnessContains: {}", this);
        WitnessContains clone = new WitnessContains();
        clone.max = max;
        clone.min = min;
        clone.isAnArray = isAnArray;
        if(contains != null)
            clone.contains = contains.clone();

        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessContains that = (WitnessContains) o;

        if(that.isAnArray != this.isAnArray) return false;
        if (!Objects.equals(min, that.min)) return false;
        if (!Objects.equals(max, that.max)) return false;
        return Objects.equals(contains, that.contains);
    }


    @Override
    public WitnessAssertion not(WitnessEnv env) throws REException {
        WitnessAnd and = new WitnessAnd();

        if(min == 0 && max == null){
            and.add(new WitnessBoolean(false));
            return and;
        }

        WitnessType type = new WitnessType();
        type.add("arr");
        and.add(type);

        if(min != null && max != null) {
            WitnessOr or = new WitnessOr();
            if(min > 0)
                or.add(new WitnessContains(0., min - 1, contains));
            or.add(new WitnessContains(max + 1, Double.POSITIVE_INFINITY, contains));
            and.add(or);
            return and;
        }

        if(min != null && min > 0) {
            and.add(new WitnessContains(0., min - 1, contains));
            return and;
        }

        if(max != null) {
            and.add(new WitnessContains(max + 1, Double.POSITIVE_INFINITY, contains));
        }

        return and;
    }

    @Override
    public WitnessAssertion groupize() throws WitnessException, REException {
        WitnessContains contains = new WitnessContains();
        contains.min = min;
        contains.max = max;
        contains.isAnArray = isAnArray;

        if(contains != null) {
            if (this.contains.getClass() != WitnessAnd.class) {
                WitnessAnd and = new WitnessAnd();
                and.add(this.contains);
                contains.contains = and.groupize();
            } else
                contains.contains = this.contains.groupize();
        }

        return contains;
    }

    @Override
    public Float countVarWithoutBDD(WitnessEnv env, List<WitnessVar> visitedVar) {
        return contains.countVarWithoutBDD(env, visitedVar);
    }

    @Override
    public int countVarToBeExp(WitnessEnv env) {
        return 0;
    }

    @Override
    public List<Map.Entry<WitnessVar, WitnessAssertion>> varNormalization_separation(WitnessEnv env, WitnessVarManager varManager) throws WitnessException, REException {
        List<Map.Entry<WitnessVar, WitnessAssertion>> newDefinitions = new LinkedList<>();

        if(contains != null || !isAnArray) {
            if(contains != null && contains.getClass() != WitnessBoolean.class && contains.getClass() != WitnessVar.class) {

                newDefinitions.addAll(contains.varNormalization_separation(env, varManager));

                WitnessVar newVar = varManager.buildVar(varManager.getName(contains));

                newDefinitions.add(new AbstractMap.SimpleEntry<>(newVar, contains));

                contains = newVar;
            }
        }

        return newDefinitions;
    }

    @Override
    public WitnessAssertion varNormalization_expansion(WitnessEnv env) throws WitnessException {
        return this;
    }

    @Override
    public WitnessAssertion DNF() throws WitnessException {
        WitnessContains contains = clone();

        contains.contains = this.contains.DNF();

        return contains;
    }

    @Override
    public WitnessAssertion toOrPattReq()  {
        contains = contains.toOrPattReq();
        return this;
    }

    @Override
    public boolean isBooleanExp() {
        return false;
    }

    @Override
    public boolean isRecursive(WitnessEnv env, LinkedList<WitnessVar> visitedVar) {
        return contains.isRecursive(env, visitedVar);
    }

    @Override
    public WitnessVar buildOBDD(WitnessEnv env, WitnessVarManager varManager) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void getReport(ReportResults reportResults) {
        if(contains != null) contains.getReport(reportResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max, contains, isAnArray);
    }
}