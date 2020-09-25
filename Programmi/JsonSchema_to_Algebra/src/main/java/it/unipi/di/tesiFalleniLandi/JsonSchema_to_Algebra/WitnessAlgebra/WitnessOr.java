package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.AnyOf_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

import java.util.*;

public class WitnessOr implements WitnessAssertion{
    private static Logger logger = LogManager.getLogger(WitnessOr.class);

    private Map<Object, List<WitnessAssertion>> orList;
    private boolean block;

    public WitnessOr() {
        this.orList = new HashMap<>();
        //base case OR
        List list = new LinkedList<>();
        list.add(new WitnessBoolean(false));
        this.orList.put(WitnessBoolean.class, list);
        block = false;

        logger.trace("Creating an empty WitnessOr");
    }

    public Map<Object, List<WitnessAssertion>> getOrList() {
        return orList;
    }

    public boolean add(WitnessAssertion el) {
        if(block) return false;

        if(el.getClass() == WitnessBoolean.class) { //Add boolean
            logger.trace("Adding boolean {} in {}", el, this);

            if (((WitnessBoolean) el).getValue() == false) //Add false
                return false;
            else {
                orList = new HashMap<>();
                block = true;
            }
        }

        if(orList.get(WitnessBoolean.class) != null)
            orList.remove(WitnessBoolean.class);

        if(el.getClass() == WitnessOr.class) { //flat OR
            logger.trace("Adding flat {} in {}", el, this);
            return add((WitnessOr) el);
        }

        //Optimization: add uniqueItems or repeatedItems once
        if(el.getClass() == WitnessUniqueItems.class || el.getClass() == WitnessRepeateditems.class) {
            logger.trace("Adding unique {} in {}", el, this);
            if(!orList.containsKey(el.getClass())) {
                List<WitnessAssertion> list = new LinkedList<>();
                list.add(el);
                orList.put(el.getClass(), list);
                return true;
            }else
                return false;
        }

        //
        if(el.getClass() == WitnessAnd.class)
            return add((WitnessAnd) el);

        logger.trace("Adding {} in {}", el, this);
        if(orList.containsKey(el.getClass())) { //if orList already contains the key
            /*if (el.getClass() == WitnessType.class){ //Optimization: add type
                WitnessType unionType = (WitnessType) orList.get(el.getClass()).get(0);
                unionType.add((WitnessType) el);

                if(unionType.separeTypes().size() == 6) //all possible types
                    return this.add(new WitnessBoolean(true));

                return true;
            } else {*/
                orList.get(el.getClass()).add(el); //insert the assertion in the respective list
                return true;
            //}
        }else{
            List<WitnessAssertion> list = new LinkedList<>();
            list.add(el);
            orList.put(el.getClass(), list);
            return true;
        }

    }

    private boolean add(WitnessAnd el){
        if(el.isAGroup() && orList.containsKey(WitnessAnd.class) && orList.get(WitnessAnd.class).contains(el)) return false; //TODO: we can maybe use a Hashmap of <Obj, SET<>>

        if(el.getIfUnitaryAnd() != null) //se è un and unitario e non è un gruppo aggiungo il suo contenuto
            return add(el.getIfUnitaryAnd());
        else {
            if (!orList.containsKey(WitnessAnd.class))
                orList.put(WitnessAnd.class, new LinkedList<>());
            orList.get(el.getClass()).add(el); //insert the assertion in the respective list
        }
        return true;
    }

    public boolean add(WitnessOr or) {
        boolean b = false;

        for(Map.Entry<Object, List<WitnessAssertion>> entry : or.orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                b |= add(assertion);

        return b;
    }

    public boolean remove(WitnessAssertion assertion){
        logger.trace("Removing {} from {}", assertion, this);

        if(orList.containsKey(assertion.getClass()))
            return orList.get(assertion.getClass()).remove(assertion);

        return false;
    }

    @Override
    public String toString() {
        return "Or{" + "\r\n" +
                "orList=" + orList +
                "\r\n" +
                '}';
    }

    @Override
    public WitnessAssertion merge(WitnessVarManager varManager, WitnessPattReqManager pattReqManager) throws REException {
        /*List<WitnessAssertion> tmp = orList.remove(WitnessAnd.class);

        if(tmp != null) {
            for (WitnessAssertion and : tmp) {
                WitnessAssertion returnedValue = and.merge();
                this.add((returnedValue == null) ? and : returnedValue);
            }
        }

        return this;*/
        WitnessOr newOr = new WitnessOr();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                newOr.add(assertion.merge(varManager, pattReqManager));

        return newOr;
    }

    @Override
    public WitnessAssertion mergeWith(WitnessAssertion a, WitnessVarManager varManager, WitnessPattReqManager pattReqManager) throws REException {
        logger.trace("Merging {} with {}", a, this);

        if(a != null && a.getClass() == this.getClass())
            return null;

        return this;
    }

    @Override
    public void checkLoopRef(WitnessEnv env, Collection<WitnessVar> varList) throws RuntimeException {
        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                assertion.checkLoopRef(env, varList);
    }

    @Override
    public void reachableRefs(Set<WitnessVar> collectedVar, WitnessEnv env) throws RuntimeException {
        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                assertion.reachableRefs(collectedVar, env);
    }

    @Override
    public WitnessType getGroupType() {
        return null;
    }

    @Override
    public Assertion getFullAlgebra() {
        if(orList.containsKey(WitnessBoolean.class) && orList.size() == 1)
            return orList.get(WitnessBoolean.class).get(0).getFullAlgebra();

        AnyOf_Assertion anyOf = new AnyOf_Assertion();
        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                anyOf.add(assertion.getFullAlgebra());

        return anyOf;
    }

    @Override
    public WitnessAssertion clone() {
        int count = 0;
        WitnessOr clone = new WitnessOr();

        clone.block = block;

        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()) {
                clone.add(assertion.clone());
                count++;
            }

        logger.trace("Cloning WitnessOr of size {}", count);
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        boolean b = true;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessOr witnessOr = (WitnessOr) o;

        if(witnessOr.orList.size() != orList.size())
            return false;

        for(Map.Entry<Object, List<WitnessAssertion>> entry : witnessOr.orList.entrySet()){
            if(!this.orList.containsKey(entry.getKey())) return false;
            List<WitnessAssertion> check = null;

            if(witnessOr.orList.get(entry.getKey()).size() >= this.orList.get(entry.getKey()).size()) {
                check = new LinkedList<>(witnessOr.orList.get(entry.getKey()));
                check.removeAll(this.orList.get(entry.getKey()));
            }else{
                check = new LinkedList<>(this.orList.get(entry.getKey()));
                check.removeAll(witnessOr.orList.get(entry.getKey()));
            }

            b &= check.size() == 0;
        }

        return b;
    }

    @Override
    public WitnessAssertion not(WitnessEnv env) throws REException {
        WitnessAnd and = new WitnessAnd();

        for(Map.Entry<?, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()){
                WitnessAssertion not = assertion.not(env);
                and.add(not);
            }

        return and;
    }

    @Override
    public WitnessAssertion groupize() throws WitnessException, REException {
        WitnessOr newOr = new WitnessOr();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()) {
                WitnessAnd tmp = null;

                //new and to create the group
                if (assertion.getClass() != WitnessAnd.class) {
                    tmp = new WitnessAnd();
                    tmp.add(assertion);
                }else
                    tmp = (WitnessAnd) assertion;

                newOr.add(tmp.groupize());
            }

        return newOr;
    }

    @Override
    public Float countVarWithoutBDD(WitnessEnv env, List<WitnessVar> visitedVar) {
        Float count = 0f;

        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                count += assertion.countVarWithoutBDD(env, visitedVar);

        return count;
    }

    @Override
    public int countVarToBeExp(WitnessEnv env) {
        int count = 0;
        List<WitnessAssertion> witnessAnd = orList.get(WitnessAnd.class);
        List<WitnessAssertion> witnessVar = orList.get(WitnessVar.class);

        if(witnessAnd != null)
            for(WitnessAssertion assertion : witnessAnd)
                count += assertion.countVarToBeExp(env);

        if(witnessVar != null)
            for(WitnessAssertion assertion : witnessVar)
                count += 1 + env.getDefinition((WitnessVar) assertion).countVarToBeExp(env);

        return count;
    }

    @Override
    public List<Map.Entry<WitnessVar, WitnessAssertion>> varNormalization_separation(WitnessEnv env, WitnessVarManager varManager) throws WitnessException, REException {
        List<Map.Entry<WitnessVar, WitnessAssertion>> newDefinitions = new LinkedList<>();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                newDefinitions.addAll(assertion.varNormalization_separation(env, varManager));

        return newDefinitions;
    }

    @Override
    public WitnessAssertion varNormalization_expansion(WitnessEnv env) throws WitnessException {
        WitnessOr or = new WitnessOr();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()) {
                if(assertion.getClass() == WitnessVar.class)
                    or.add(env.getDefinition((WitnessVar) assertion));
                else
                    or.add(assertion.varNormalization_expansion(env));
            }

        return or;
    }

    @Override
    public WitnessAssertion DNF() throws WitnessException {
        WitnessOr or = new WitnessOr();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()) {
                or.add(assertion.DNF());
            }

        return or;
    }

    @Override
    public WitnessAssertion toOrPattReq() {
        WitnessOr newElem = new WitnessOr(); //to avoid ConcurrentModificationException

        for (Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for (WitnessAssertion assertion : entry.getValue())
                if (entry.getKey() == WitnessPattReq.class)
                    newElem.add(assertion.toOrPattReq());
                else
                    assertion.toOrPattReq();

        orList.remove(WitnessPattReq.class);
        this.add(newElem);

        return this;
    }

    @Override
    public boolean isBooleanExp() {
        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                if(!assertion.isBooleanExp())
                    return false;

        return true;
    }

    @Override
    public boolean isRecursive(WitnessEnv env, LinkedList<WitnessVar> visitedVar) {
        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet()) {
            for(WitnessAssertion assertion : entry.getValue()) {
                if(!assertion.isBooleanExp()) return false;
            }
        }

        return true;
    }

    @Override
    public WitnessVar buildOBDD(WitnessEnv env, WitnessVarManager varManager) throws WitnessException {
        WitnessVar obbdVarName = null;

        if(orList.size() == 0)
            logger.fatal("Or vuoto");

        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet()) {
            for(WitnessAssertion assertion : entry.getValue()) {
                if(obbdVarName == null)
                    obbdVarName = assertion.buildOBDD(env, varManager);
                else
                    obbdVarName = env.bdd.or(env, obbdVarName, assertion.buildOBDD(env, varManager));
            }
        }

        return obbdVarName;
    }

    @Override
    public void getReport(ReportResults reportResults) {
        for (Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet()) {
            for (WitnessAssertion assertion : entry.getValue()) {
                reportResults.increaseNumOfElementInAnyOf();
                assertion.getReport(reportResults);
            }
        }
    }

    protected WitnessOr DNF(WitnessAssertion toAdd) throws WitnessException {
        WitnessOr or = new WitnessOr();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()) {
                WitnessAnd and = new WitnessAnd();
                and.add(assertion);
                and.add(toAdd);
                or.add(and);
            }

        return or;
    }

    public List<Map.Entry<WitnessVar, WitnessAssertion>> objectPrepare(WitnessEnv env) throws REException, WitnessException {
        List<Map.Entry<WitnessVar, WitnessAssertion>> newDefinitions = new LinkedList<>();

        if(orList.containsKey(WitnessAnd.class)){ //call object prepare only for AND assertion
            List<WitnessAssertion> ands = orList.get(WitnessAnd.class);
            for(WitnessAssertion assertion : ands) {
                newDefinitions.addAll(((WitnessAnd)assertion).objectPrepare(env));
            }
        }

        return newDefinitions;
    }

    public List<Map.Entry<WitnessVar, WitnessAssertion>> arrayPreparation(WitnessEnv env) throws WitnessException, REException {
        List<Map.Entry<WitnessVar, WitnessAssertion>> newDefinitions = new LinkedList<>();

        if(orList.containsKey(WitnessAnd.class)){ //call object prepare only for AND assertion
            List<WitnessAssertion> ands = orList.get(WitnessAnd.class);
            for(WitnessAssertion assertion : ands) {
                newDefinitions.addAll(((WitnessAnd)assertion).arrayPreparation(env));
            }
        }

        return newDefinitions;
    }

    @Override
    public int hashCode() {
        return orList != null ? orList.hashCode() : 0;
    }

}