package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.OrPattReq_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WitnessOrPattReq implements WitnessAssertion{
    private static Logger logger = LogManager.getLogger(WitnessOrPattReq.class);

    List<WitnessPattReq> reqList;  // AnyOf[ req1, ..., reqN ]

    public WitnessOrPattReq() {
        reqList = new LinkedList<>();
    }

    public boolean add(WitnessPattReq toAdd) {
        logger.trace("Adding {} as  in {}", toAdd, this);
        return reqList.add(toAdd);
    }

    public void fullConnect(WitnessPattReq req){
        if(reqList == null) reqList = new LinkedList<>();

        //if(ORP.contains(req)) return;
        logger.trace("Connecting {} to {}", req, this);

        reqList.add(req);
        req.halfConnect(this);
    }

    public void halfConnect(WitnessPattReq req){
        if(reqList == null) reqList = new LinkedList<>();

        reqList.add(req);
    }

    public void deConnect(WitnessPattReq req){
        if(!reqList.contains(req)) return;

        logger.trace("De-Connecting {} to {}", req, this);

        if(reqList.remove(req))
            req.deConnect(this);
    }

    public void remove(WitnessPattReq req){
        reqList.remove(req);
    }

    @Override
    public void checkLoopRef(WitnessEnv env, Collection<WitnessVar> varList) throws WitnessException {
        for(WitnessAssertion assertion : reqList)
            assertion.checkLoopRef(env, varList);
    }

    @Override
    public WitnessAssertion mergeWith(WitnessAssertion a) throws REException {
        return null;
    }

    @Override
    public WitnessAssertion merge() throws REException {
        return this;
    }

    @Override
    public WitnessType getGroupType() {
        return null;
    }

    @Override
    public Assertion getFullAlgebra() {
        OrPattReq_Assertion opr_a = new OrPattReq_Assertion();

        for(WitnessPattReq el : reqList){
            opr_a.add(el.getPattern(), el.getValue().getFullAlgebra());
        }

        return opr_a;
    }

    @Override
    public WitnessAssertion clone() {
        logger.trace("Cloning WitnessOrPattReq: {}", this);
        WitnessOrPattReq clone = new WitnessOrPattReq();

        for(WitnessPattReq el : reqList){
            clone.add(WitnessPattReq.build(el.getPattern().clone(), el.getValue().clone()));
        }

        return clone;
    }

    @Override
    public WitnessAssertion not(WitnessEnv env) throws WitnessException, REException {
        WitnessAnd and = new WitnessAnd();

        for(WitnessPattReq req : reqList)
            and.add(req.not(env));

        return and;
    }

    @Override
    public WitnessAssertion groupize() throws WitnessException, REException {
        WitnessOrPattReq orp = new WitnessOrPattReq();

        for(WitnessPattReq req : reqList)
            orp.add((WitnessPattReq) req.groupize());

        return orp;
    }

    @Override
    public Float countVarWithoutBDD(WitnessEnv env, List<WitnessVar> visitedVar) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int countVarToBeExp(WitnessEnv env) {
        return 0;
    }

    @Override
    public List<Map.Entry<WitnessVar, WitnessAssertion>> varNormalization_separation(WitnessEnv env) throws WitnessException, REException {
        List<Map.Entry<WitnessVar, WitnessAssertion>> newDefinitions = new LinkedList<>();

        for(WitnessPattReq el : reqList){
            newDefinitions.addAll(el.varNormalization_separation(env));
        }

        return newDefinitions;
    }

    @Override
    public WitnessAssertion varNormalization_expansion(WitnessEnv env) throws WitnessException {
        return this;
    }

    @Override
    public WitnessAssertion DNF() throws WitnessException {
        return this;
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
        for(WitnessPattReq el : reqList){
            if(el.isRecursive(env, visitedVar)) return true;
        }

        return false;
    }

    @Override
    public WitnessVar buildOBDD(WitnessEnv env) {
        throw new UnsupportedOperationException();
    }

    public void setReqList(List<WitnessPattReq> reqList) {
        this.reqList = reqList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessOrPattReq that = (WitnessOrPattReq) o;

        List<WitnessPattReq> check = new LinkedList<>(that.reqList);
        check.removeAll(this.reqList);

        return check.size() == 0;
    }

    @Override
    public String toString() {
        return "WitnessOrPattReq{" +
                "reqList=" + reqList +
                '}';
    }

    @Override
    public int hashCode() {
        return reqList != null ? reqList.hashCode() : 0;
    }
}
