package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.UnsenseAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.AnyOf_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Boolean_Assertion;
import patterns.REException;

import java.util.*;

public class WitnessOr implements WitnessAssertion{

    private Map<Object, List<WitnessAssertion>> orList;

    public WitnessOr() {
        this.orList = new HashMap<>();
    }

    public boolean add(WitnessAssertion el){

        if(el.getClass() == this.getClass()) //flat OR
            return add((WitnessOr) el);

        else if(el.getClass() == WitnessUniqueItems.class || el.getClass() == WitnessRepeateditems.class)
            return addUni_Rep(el);

        else if(el.getClass() == WitnessBoolean.class) { //Add boolean
            if (((WitnessBoolean) el).getValue() == false) //Add false
                return false;
            else
                throw new UnsenseAssertion("or.add(true)");
        } else {
            if(orList.containsKey(el.getClass())) { //if orList already contains the key

                if (el.getClass() == WitnessType.class){ //Optimization: add type
                    if (orList.get(el.getClass()).contains(el))
                        return false;

                    orList.get(el.getClass()).add(el);
                    return true;
                } else {
                    orList.get(el.getClass()).add(el); //insert the assertion in the respective list
                    return true;
                }
            }else{
                List<WitnessAssertion> list = new LinkedList<>();
                list.add(el);
                orList.put(el.getClass(), list);
                return true;
            }
        }
    }

    public boolean add(WitnessOr or){
        boolean b = false;

        for(Map.Entry<Object, List<WitnessAssertion>> entry : or.orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                b |= add(assertion);

        return b;
    }

    public boolean addUni_Rep(WitnessAssertion el){
        if(!orList.containsKey(el.getClass())) {
            List<WitnessAssertion> list = new LinkedList<>();
            list.add(el);
            orList.put(el.getClass(), list);
            return true;
        }

        return false;
    }

    public boolean remove(WitnessAssertion assertion){
        if(orList.containsKey(assertion.getClass()))
            return orList.get(assertion.getClass()).remove(assertion);

        return false;
    }

    @Override
    public String toString() {
        return "WitnessOr{" + "\r\n" +
                "orList=" + orList +
                "\r\n" +
                '}';
    }

    @Override
    public WitnessAssertion merge() throws REException {
        List<WitnessAssertion> tmp = orList.remove(WitnessAnd.class);

        //propagate merge only below and assertion
        if(tmp != null) {
            for (WitnessAssertion and : tmp) {
                try {
                    WitnessAssertion returnedValue = and.merge();
                    this.add((returnedValue == null) ? and : returnedValue);
                }catch (UnsenseAssertion e){
                    //Nothing to do: (or+false = or)
                }
            }
        }

        return this;
    }

    @Override
    public void checkLoopRef(WitnessEnv env, Collection<WitnessVar> varList) throws WitnessException {
        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                assertion.checkLoopRef(env, varList);
    }


    @Override
    public WitnessAssertion mergeWith(WitnessAssertion a) throws REException {
        if(a != null && a.getClass() == this.getClass())
            return null;

        return this;

        /*
        a può essere un oggetto di un qualsiasi tipo,
            foreach( b = orList.get(a.class)[i])
                if(b.merge(a).equals(a)
                    when a is satisfied, b is also satisfied so the entire orAssertion is true

        if a.class = or?
         //this.size = n and a.size = m --> return size O(n*m)
        //Assorb
        if(a != null) {
            //List<WitnessAssertion> newList = new LinkedList<>();
            int notFalseElement = 0;

            for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet()) {
                notFalseElement += entry.getValue().size();

                for (WitnessAssertion assertion : entry.getValue()) {
                    WitnessAssertion merged = a.mergeElement(assertion);

                    if (merged == null) continue;

                    if (merged.getClass() == WitnessBoolean.class)
                        if (((WitnessBoolean) merged).getValue())
                            return new WitnessBoolean((true));
                        else {
                            notFalseElement--;
                            continue;
                        }

                    if (!merged.equals(a))
                        //newList.add(assertion);
                        this.add(assertion);
                    else return new WitnessBoolean(true);
                }
            }
            if(notFalseElement == 0)//all false
                return new WitnessBoolean(false);

            //orList.put(a.getClass(), newList);
        }
         */
    }

    @Override
    public WitnessType getGroupType() {
        return null;
    }

    @Override
    public Assertion getFullAlgebra() {
        //caso solo 1 false
        if(orList.size() == 0)
            return new Boolean_Assertion(false);

        AnyOf_Assertion anyOf = new AnyOf_Assertion();
        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                anyOf.add(assertion.getFullAlgebra());

        return anyOf;
    }

    @Override
    public WitnessAssertion clone() {
        WitnessOr clone = new WitnessOr();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                clone.add(assertion.clone());

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
    public WitnessAssertion not() throws REException {
        return getFullAlgebra().not().toWitnessAlgebra();
    }

    @Override
    public WitnessAssertion notElimination() throws REException {
        return getFullAlgebra().notElimination().toWitnessAlgebra();
    }

    @Override
    public WitnessAssertion groupize() throws WitnessException, REException {
        WitnessOr newOr = new WitnessOr();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()) {
                WitnessAnd tmp = null;

                //new and to create the group
                if (assertion.getClass() != WitnessAnd.class) { //TODO: top_groupize su singole asserzioni???
                    tmp = new WitnessAnd();
                    tmp.add(assertion);
                }else
                    tmp = (WitnessAnd) assertion;

                newOr.add(tmp.groupize());
            }

        return newOr;
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
    public void varNormalization_separation(WitnessEnv env) throws WitnessException, REException {
        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()) {
                assertion.varNormalization_separation(env);
            }
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
    public boolean isBooleanExp() {
        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()) {
                if(!assertion.isBooleanExp())
                    return false;
            }

        return true;
    }

    @Override
    public WitnessVar buildOBDD(WitnessEnv env) throws WitnessException {
        WitnessVar obbdVarName = null;

        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet()) {
            for(WitnessAssertion assertion : entry.getValue()) {
                if(obbdVarName == null)
                    obbdVarName = assertion.buildOBDD(env);
                obbdVarName = env.obdd.and(env, obbdVarName, assertion.buildOBDD(env));
            }
        }

        return obbdVarName;
    }

    protected WitnessOr DNF(WitnessAssertion toAdd) throws WitnessException {
        WitnessOr or = new WitnessOr();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : orList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()) {
                WitnessAnd and = new WitnessAnd();

                try {
                    and.add(assertion);
                    and.add(toAdd);
                }catch (UnsenseAssertion e){
                    continue;
                }

                or.add(and);

            }

        return or;
    }

    public void objectPrepare(WitnessEnv env) throws REException, WitnessException {
        if(orList.containsKey(WitnessAnd.class)){ //call object prepare only for AND assertion
            List<WitnessAssertion> ands = orList.get(WitnessAnd.class);
            for(WitnessAssertion assertion : ands) {
                ((WitnessAnd)assertion).objectPrepare(env);
            }
        }
    }

    @Override
    public int hashCode() {
        return orList != null ? orList.hashCode() : 0;
    }

}