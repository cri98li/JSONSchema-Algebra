package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.OrPattReq_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessException;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessFalseAssertionException;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessTrueAssertionException;
import patterns.REException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class WitnessOrPattReq implements WitnessAssertion{

    List<WitnessPattReq> ORP;  // AnyOf[ req1, ..., reqN ]

    public WitnessOrPattReq() {
        ORP = new LinkedList<>();
    }

    public boolean add(WitnessPattReq toAdd) {
        return ORP.add(toAdd);
    }

    public void fullConnect(WitnessPattReq req){
        if(ORP == null) ORP = new LinkedList<>();

        //if(ORP.contains(req)) return;

        ORP.add(req);
        req.halfConnect(this);
    }

    public void halfConnect(WitnessPattReq req){
        if(ORP == null) ORP = new LinkedList<>();

        ORP.add(req);
    }

    public void deConnect(WitnessPattReq req){
        if(!ORP.contains(req)) return;

        if(ORP.remove(req))
            req.deConnect(this);
    }

    public void remove(WitnessPattReq req){
        ORP.remove(req);
    }

    @Override
    public void checkLoopRef(WitnessEnv env, Collection<WitnessVar> varList) throws WitnessException {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    @Override
    public Assertion getFullAlgebra() {
        OrPattReq_Assertion opr_a = new OrPattReq_Assertion();

        for(WitnessPattReq el : ORP ){
            opr_a.add(el.getPattern(), el.getValue().getFullAlgebra());
        }

        return opr_a;
    }

    @Override
    public WitnessAssertion clone() {
        WitnessOrPattReq clone = new WitnessOrPattReq();

        for(WitnessPattReq el : ORP){
            clone.add(WitnessPattReq.build(el.getPattern().clone(), el.getValue().clone()));
        }

        return clone;
    }

    @Override
    public WitnessAssertion not(WitnessEnv env) throws WitnessException, REException {
        throw new UnsupportedOperationException();
    }

    @Override
    public WitnessAssertion groupize() throws WitnessException, REException {
        throw new UnsupportedOperationException();
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
    public void varNormalization_separation(WitnessEnv env) throws WitnessException, REException {
        for(WitnessPattReq el : ORP){
            el.varNormalization_separation(env);
        }
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
    public WitnessAssertion toOrPattReq() throws WitnessFalseAssertionException, WitnessTrueAssertionException {
        return this;
    }

    @Override
    public boolean isBooleanExp() {
        return false;
    }

    @Override
    public boolean isRecursive(WitnessEnv env, LinkedList<WitnessVar> visitedVar) {
        for(WitnessPattReq el : ORP){
            if(el.isRecursive(env, visitedVar)) return true;
        }

        return false;
    }

    @Override
    public WitnessVar buildOBDD(WitnessEnv env) {
        throw new UnsupportedOperationException();
    }

    public void setORP(List<WitnessPattReq> ORP) {
        this.ORP = ORP;
    }
}
