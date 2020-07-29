package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.UnsenseAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.PatternRequired_Assertion;
import patterns.REException;

import java.util.*;

public class WitnessPattReq implements WitnessAssertion{
    private static HashMap<Object, WitnessPattReq> instances;

    public static WitnessPattReq build(ComplexPattern key, WitnessAssertion assertion){
        if(instances == null) instances = new HashMap<>();

        if(assertion.getClass() == WitnessAnd.class && ((WitnessAnd) assertion).getIfUnitaryAnd() != null)
            assertion = ((WitnessAnd)assertion).getIfUnitaryAnd();

        WitnessPattReq tmp = new WitnessPattReq(key, assertion);

        if(instances.containsKey(tmp.toString()))
            return instances.get(tmp.toString());

        instances.put(tmp.toString(), tmp);
        return tmp;
    }


    private ComplexPattern key;
    private WitnessAssertion value;

    private List<WitnessOrPattReq> orpList;

    private WitnessPattReq(ComplexPattern key, WitnessAssertion assertion){
        this.key = key;
        value = assertion;
        orpList = new LinkedList<>();
    }

    public void fullConnect(WitnessOrPattReq orp){
        //if(orpList.contains(orp)) return;

        orpList.add(orp);
        orp.halfConnect(this);
    }

    public void halfConnect(WitnessOrPattReq orp){

        orpList.add(orp);
    }

    public void deConnect(WitnessOrPattReq orp){
        if(!orpList.contains(orp)) return;

        if(orpList.remove(orp))
            orp.deConnect(this);
    }

    public List<WitnessOrPattReq> getOrpList(){
        return orpList;
    }

    public WitnessAssertion getValue() {
        return value;
    }

    private WitnessPattReq() {
        orpList = new LinkedList<>();
    }

    public ComplexPattern getPattern() {
        return key;
    }

    public void setPattern(ComplexPattern key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "WitnessPattReq{" +
                "pattern=" + key +
                ", value=" + value +
                '}';
    }

    @Override
    public void checkLoopRef(WitnessEnv env, Collection<WitnessVar> varList) throws WitnessException {
        return;
    }

    @Override
    public WitnessAssertion mergeWith(WitnessAssertion a) {
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
        if(this.key.isSubsetOf(a.key) && this.value.equals(a.value)){
            return this;
        }

        if(a.key.isSubsetOf(this.key) && a.value.equals(this.value)){
            return a;
        }

        return null;
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(FullAlgebraString.TYPE_OBJECT);
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

        for(WitnessOrPattReq orp : orpList)
            clone.orpList.add((WitnessOrPattReq) orp.clone());

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
    public WitnessAssertion groupize() throws WitnessException, REException {
        WitnessPattReq pattReq = new WitnessPattReq();
        pattReq.key = key;

        if(pattReq != null){
            if(value.getClass() != WitnessAnd.class) {
                WitnessAnd and = new WitnessAnd();
                try {
                    and.add(value);
                }catch (UnsenseAssertion e){
                    pattReq.value = new WitnessBoolean(false);
                }
                pattReq.value = and.groupize();
            } else pattReq.value = value.groupize();
        }

        return pattReq;
    }

    @Override
    public int countVarToBeExp(WitnessEnv env) {
        return 0;
    }

    @Override
    public void varNormalization_separation(WitnessEnv env) throws WitnessException, REException {
        if (value != null) {
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
        /*WitnessPattReq pattReq = new WitnessPattReq();
        pattReq.key = this.key;
        if(value != null) pattReq.value = this.value.variableNormalization_expansion(env);

        return pattReq;*/
        return this;
    }

    @Override
    public WitnessAssertion DNF() {
        WitnessPattReq pattReq = new WitnessPattReq();
        pattReq.key = this.key;
        if(value != null) pattReq.value = this.value.clone();

        return pattReq;
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