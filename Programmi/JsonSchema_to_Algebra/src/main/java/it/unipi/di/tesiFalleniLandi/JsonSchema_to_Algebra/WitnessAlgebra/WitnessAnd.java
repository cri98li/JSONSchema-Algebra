package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.UnsenseAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.*;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessException;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessFalseAssertionException;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessTrueAssertionException;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

import java.util.*;

public class WitnessAnd implements WitnessAssertion{
    private static Logger logger = LogManager.getLogger(WitnessAnd.class);

    /**
     * list of assertion in and, grouped by class to optimize the merge method
     *
     * Ex:
     *      < WitnessMof, <mof(1), mof(2), mof(3)>>
     *      < WitnessPattern, <pattern("..."), pattern("..."), ...>>
     *      ...
     */
    private LinkedHashMap<Object, List<WitnessAssertion>> andList;

    public WitnessAnd() {
        this.andList = new LinkedHashMap<>();
        //caso base AND
        //List list = new LinkedList<>();
        //list.add(new WitnessBoolean(true));
        //this.andList.put(WitnessBoolean.class, list);
        logger.trace("Created a new and: {}", this);
    }

    /**
     * Add an assertion - el - to the andList.
     * if el is an instance of WitnessAnd, we add every element of el.andList in this.andList
     * if el is an instance of uniqueItems or repeatedItems, we check if andList do not contain it, then we add it
     * if el is “false” or is a type assertion that is not compatible with the type assertion that is present, we raise the exception WitnessFalseAssertionException
     * @param el
     * @return
     */
    public boolean add(WitnessAssertion el) throws WitnessFalseAssertionException {

        if(el.getClass() == WitnessBoolean.class) //add boolean
            if(((WitnessBoolean) el).getValue()) //add true
                return false;
            else
                throw new WitnessFalseAssertionException("and.add(false)");

        if(el.getClass() == WitnessAnd.class) { //Flat and
            return add((WitnessAnd) el);
        }

        //Optimization: add uniqueItems or repeatedItems
        if(el.getClass() == WitnessUniqueItems.class || el.getClass() == WitnessRepeateditems.class)
            if(!andList.containsKey(el.getClass())){
                List<WitnessAssertion> list = new LinkedList<>();
                list.add(el);
                andList.put(el.getClass(), list);
                return true;
            } else
                return false;


        //Add type
        if(el.getClass() == WitnessType.class){
            if(andList.get(WitnessType.class) == null){ //if no type present
                List<WitnessAssertion> list = new LinkedList<>();
                list.add(el);
                andList.put(el.getClass(), list);
                return true;
            }

            //Optimization: try to merge two type
            List<WitnessAssertion> typeList = andList.remove(WitnessType.class);
            WitnessAssertion merged = ((WitnessType) el).mergeElement((WitnessType) typeList.get(0));

            if(merged.getClass() == WitnessBoolean.class && ((WitnessBoolean)merged).getValue() == false) //incompatible types
                throw new WitnessFalseAssertionException("add di 2 and incompatibili");
            else{ //compatible types
                List<WitnessAssertion> list = new LinkedList<>();
                list.add(merged);
                andList.put(el.getClass(), list);
                return true;
            }
        }

        if(andList.containsKey(el.getClass())) { //if andList already contains the key
            //if(andList.get(el.getClass()).contains(el)) return false; --> slow
            andList.get(el.getClass()).add(el); //insert the assertion in the respective list
        }else {
            List<WitnessAssertion> list = new LinkedList<>();
            list.add(el);
            andList.put(el.getClass(), list);
        }

        return true;
    }

    public boolean add(WitnessAnd and) throws WitnessFalseAssertionException {
        boolean b = false;

        for (Map.Entry<Object, List<WitnessAssertion>> entry : and.andList.entrySet())
            for (WitnessAssertion assertion : entry.getValue())
                b |= this.add(assertion);

        return b;
    }

    @Override
    public String toString() {
        return "WitnessAnd{" + "\r\n" +
                "andList=" + andList.toString()
                 + "\r\n" +
                '}';
    }


    @Override
    public void checkLoopRef(WitnessEnv env, Collection<WitnessVar> varList) throws WitnessException {
        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                assertion.checkLoopRef(env, varList);
    }

    @Override
    public WitnessAssertion mergeWith(WitnessAssertion a) throws REException {
        logger.trace("Merging {} with {}", a, this);
        try{
            if(this.add(a)) //if the list has been modified --> apply merge again
                return this.merge();
        }catch (WitnessFalseAssertionException e){
            return new WitnessBoolean(false);
        }
        return this;
    }


    @Override
    public WitnessAssertion merge() throws REException {
        WitnessAnd newAnd = new WitnessAnd();
        boolean modified = false;

        try {
            for (Map.Entry<Object, List<WitnessAssertion>> sameTypeAssertion : andList.entrySet()) {
                int size = sameTypeAssertion.getValue().size();
                WitnessAssertion merged = sameTypeAssertion.getValue().get(0).merge();

                for (int i = 1; i < size; i++) {
                    WitnessAssertion oldMerge = merged;
                    merged = merged.mergeWith(sameTypeAssertion.getValue().get(i).merge());
                    if (merged != null) {
                        modified = true;
                        if (merged.getClass() == WitnessBoolean.class) {
                            WitnessBoolean b = (WitnessBoolean) merged;
                            if (!b.getValue())
                                return b;
                        }
                    } else {
                        newAnd.add(sameTypeAssertion.getValue().get(i));
                        merged = oldMerge;
                    }
                }

                if (merged != null) {
                    newAnd.add(merged);
                }
            }

            //CASI SPECIALI
            if (newAnd.andList.containsKey(WitnessBet.class) && newAnd.andList.containsKey(WitnessXBet.class)) {
                WitnessBet bet = (WitnessBet) newAnd.andList.remove(WitnessBet.class).get(0);
                WitnessXBet xBet = (WitnessXBet) newAnd.andList.remove(WitnessXBet.class).get(0);
                WitnessAssertion assertion = bet.mergeElement(xBet);
                if (assertion == null) {
                    newAnd.add(bet);
                    newAnd.add(xBet);
                } else {
                    newAnd.add(assertion);
                    modified = true;
                }
            }

            if (newAnd.andList.containsKey(WitnessMof.class) && newAnd.andList.containsKey(WitnessNotMof.class)) {
                WitnessMof mof = (WitnessMof) newAnd.andList.remove(WitnessMof.class).get(0);
                List<WitnessAssertion> notMofList = newAnd.andList.remove(WitnessNotMof.class);

                for (WitnessAssertion tmp : notMofList) {
                    WitnessAssertion assertion = mof.mergeWith(tmp);
                    if (assertion == null) {
                        newAnd.add(mof);
                        newAnd.add(tmp);
                    } else {
                        newAnd.add(assertion);
                        modified = true;
                        break;
                    }
                }

            }

            if (newAnd.andList.containsKey(WitnessUniqueItems.class) && newAnd.andList.containsKey(WitnessRepeateditems.class)) {
                WitnessUniqueItems uni = (WitnessUniqueItems) newAnd.andList.remove(WitnessUniqueItems.class).get(0);
                WitnessRepeateditems rep = (WitnessRepeateditems) newAnd.andList.remove(WitnessRepeateditems.class).get(0);
                WitnessAssertion assertion = uni.mergeElement(rep);
                if (assertion == null) {
                    newAnd.add(uni);
                    newAnd.add(rep);
                } else {
                    newAnd.add(assertion);
                    modified = true;
                }
            }

            //TOFIX: newAnd.andList.get(WitnessOr.class).remove(or); de la lista è una sola bisogna rimuovere anche l'elemento dentro l'hashmap
        /*
        if(newAnd.andList.containsKey(WitnessOr.class)){
            for(Map.Entry<Object, List<WitnessAssertion>> sameTypeAssertion : andList.entrySet())
                if(sameTypeAssertion.getKey() != WitnessOr.class)
                    for(WitnessAssertion assertion : sameTypeAssertion.getValue()) {
                        for (WitnessAssertion or : newAnd.clone().andList.get(WitnessOr.class)) {
                            WitnessAssertion returnedAssertion = or.mergeElement(assertion);
                            if (returnedAssertion.getClass() == WitnessBoolean.class)
                                if (((WitnessBoolean) returnedAssertion).getValue() == true);
                                    newAnd.andList.get(WitnessOr.class).remove(or);
                                else
                                    return returnedAssertion; //WitnessBoolean false
                        }
                    }
        }
        */
        }catch (WitnessFalseAssertionException e){
            return new WitnessBoolean(false);
        }

        if (modified)
            return newAnd.merge();

        return newAnd;
    }

    /**
     * if and contains only one assertion, return that assertion
     * else return null
     */
    /*
    public WitnessAssertion getIfUnitaryAnd(){
        if(andList.size() == 0) return new WitnessBoolean(true);
        if(andList.size() == 1) {
            Iterator<Map.Entry<Object, List<WitnessAssertion>>> entry = andList.entrySet().iterator();
            Object key = entry.next().getKey();
            if(andList.get(key).size() == 1)
                return andList.get(key).get(0);
        }

        return null;
    }*/


    @Override
    public WitnessType getGroupType() {
        return null;
    }

    @Override
    public Assertion getFullAlgebra() {
        //Contiene solo 1 booleano
        if(andList.size() == 0)
            return new Boolean_Assertion(true);

        AllOf_Assertion allOf = new AllOf_Assertion();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                allOf.add(assertion.getFullAlgebra());

        return allOf;
    }

    @Override
    public WitnessAnd clone() {
        int debug = 0;
        WitnessAnd clone = new WitnessAnd();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()) {
                try {
                    clone.add(assertion.clone());
                    debug++;
                } catch (WitnessFalseAssertionException e) {
                    throw new RuntimeException(e); //impossible
                }
            }

        logger.trace("Cloned WitnessAnd of size debug");
        return clone;
    }

    @Override
    public WitnessAssertion not(WitnessEnv env) throws REException, WitnessException {
        WitnessOr or = new WitnessOr();

        for(Map.Entry<?, List<WitnessAssertion>> entry : andList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()){
                WitnessAssertion not = assertion.not(env);
                if(not != null) {
                    try {
                        or.add(not);
                    } catch (WitnessTrueAssertionException e) {
                        return new WitnessBoolean(true);
                    }
                }
        }

        return or;
    }

    @Override
    public WitnessAssertion groupize() throws WitnessException, REException {
        //caso base: se and vuoto --> and[true] --> ritorno true
        if(andList.size() == 0){
            logger.warn("Groupize on an empty WitnessAnd. Returning WitnessBoolean(true)");
            return new WitnessBoolean(true);
        }

        if (andList.containsKey(WitnessType.class)) {
            List<WitnessAssertion> types = andList.remove(WitnessType.class);
            WitnessAssertion type = types.remove(0);

            for (WitnessAssertion t : types)
                type = type.mergeWith(t);

            if(type.getClass() == WitnessBoolean.class)
                return type;

            //One type
            if (((WitnessType)type).separeTypes().size() == 1) return oneType((WitnessType) type);

            //More than one type
            else return multipleTypes((WitnessType) type);

        } else  //No type specified
            return noType();
    }

    @Override
    public Float countVarWithoutBDD(WitnessEnv env, List<WitnessVar> visitedVar) {
        Float count = 0f;

        for (Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet()) {
            for (WitnessAssertion assertion : entry.getValue()) {
                count += assertion.countVarWithoutBDD(env, visitedVar);
            }
        }

        return count;
    }

    public WitnessAssertion oneType(WitnessType type) throws WitnessException, REException {
        WitnessAnd and = new WitnessAnd();
        and.add(type);

        for (Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet()) {
            for (WitnessAssertion assertion : entry.getValue()) {
                if(assertion.getGroupType() != null) {
                    if(assertion.getGroupType().equals(type))
                        and.add(assertion.groupize());
                }else{
                    and.add(assertion.groupize());
                }
            }
        }

        return and;
    }

    public WitnessAssertion multipleTypes(WitnessType type) throws WitnessException, REException {
        WitnessOr or = new WitnessOr();
        HashMap<WitnessType, WitnessAnd> groups = new HashMap<>();

        for (WitnessType t : type.separeTypes()) {
            WitnessAnd and = new WitnessAnd();
            and.add(t);
            groups.put(t, and);
        }

        for (Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet()) {
            for (WitnessAssertion assertion : entry.getValue()) {
                if (assertion.getGroupType() != null) {
                    if (groups.containsKey(assertion.getGroupType()))
                        groups.get(assertion.getGroupType()).add(assertion.groupize());
                } else if (assertion.getGroupType() == null) {
                    WitnessAssertion groupizeResult = assertion.groupize();
                    for (Map.Entry<?, WitnessAnd> e : groups.entrySet())
                        e.getValue().add(groupizeResult);
                }
            }
        }

        for(Map.Entry<?, WitnessAnd> ands: groups.entrySet()){
            or.add(ands.getValue());
        }

        return or;
    }

    public WitnessAssertion noType() throws WitnessException, REException {
        WitnessOr or = new WitnessOr();
        WitnessAnd and = new WitnessAnd();
        HashMap<WitnessType, WitnessAnd> groups = new HashMap<>();

        WitnessType type = new WitnessType(FullAlgebraString.TYPE_NUMBER);
        and.add(type);
        groups.put(type, and);

        and = new WitnessAnd();
        type = new WitnessType(FullAlgebraString.TYPE_OBJECT);
        and.add(type);
        groups.put(type, and);

        and = new WitnessAnd();
        type = new WitnessType(FullAlgebraString.TYPE_ARRAY);
        and.add(type);
        groups.put(type, and);

        and = new WitnessAnd();
        type = new WitnessType(FullAlgebraString.TYPE_STRING);
        and.add(type);
        groups.put(type, and);

        and = new WitnessAnd();
        type = new WitnessType(FullAlgebraString.TYPE_BOOLEAN);
        and.add(type);
        groups.put(type, and);

        and = new WitnessAnd();
        type = new WitnessType(FullAlgebraString.TYPE_NULL);
        and.add(type);
        groups.put(type, and);

        for (Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet()) {
            for (WitnessAssertion assertion : entry.getValue()) {
                if (assertion.getGroupType() != null) {
                    if (groups.containsKey(assertion.getGroupType()))
                        groups.get(assertion.getGroupType()).add(assertion.groupize());
                } else if (assertion.getGroupType() == null) {
                    WitnessAssertion groupizeResult = assertion.groupize();
                    for (Map.Entry<?, WitnessAnd> e : groups.entrySet())
                        e.getValue().add(groupizeResult);
                }
            }
        }

        //Optimization: unitary and
        for(Map.Entry<?, WitnessAnd> ands: groups.entrySet()){
            or.add(ands.getValue());

        }

        return or;
    }

    @Override
    public int countVarToBeExp(WitnessEnv env) {
        int count = 0;
        List<WitnessAssertion> witnessOr = andList.get(WitnessOr.class);
        List<WitnessAssertion> witnessVar = andList.get(WitnessVar.class);

        if(witnessOr != null)
            for(WitnessAssertion assertion : witnessOr)
                count += assertion.countVarToBeExp(env);

        if(witnessVar != null)
            for(WitnessAssertion assertion : witnessVar)
                count += 1 + env.getDefinition((WitnessVar) assertion).countVarToBeExp(env);

        return count;
    }


    @Override
    public void varNormalization_separation(WitnessEnv env) throws WitnessException, REException {

        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()){
                assertion.varNormalization_separation(env);
            }
    }

    @Override
    public WitnessAssertion varNormalization_expansion(WitnessEnv env) throws WitnessException {
        WitnessAnd and = new WitnessAnd();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()){
                if(assertion.getClass() == WitnessVar.class) {
                    WitnessAssertion resolvedAssertion = env.getDefinition((WitnessVar) assertion);

                    if(resolvedAssertion != null)
                        try {
                            and.add(env.getDefinition((WitnessVar) assertion));
                        }catch(WitnessFalseAssertionException e){
                            return new WitnessBoolean(false);
                        }
                    else
                        throw new ParseCancellationException("Definition not found: "+assertion.toString());
                }else
                    try {
                        and.add(assertion.varNormalization_expansion(env));
                    }catch (UnsenseAssertion e){
                        return new WitnessBoolean(false);
                    }
            }

        return and;
    }

    //TODO: check
    @Override
    public WitnessAssertion DNF() throws WitnessException {
        WitnessOr or = null;

        if(!andList.containsKey(WitnessOr.class)) {
            return this;  //TODO: faccio così sennò creo or unitario e poi nella separation mi trovi WitnessOr_2132131 invece di WitnessAnd... ma non cambia nulla
            //Se non contiene un OR, creo un OR unitario contenente la lista in and --> mantengo l'invariante
            //or.add(this.clone());
            //or.add(this);
            //return or;
        }

        or = (WitnessOr) andList.get(WitnessOr.class).remove(0);

        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet()) {
            for(WitnessAssertion assertion : entry.getValue()) {
                or = or.DNF(assertion);
            }
        }

        return or.DNF();
    }

    @Override
    public WitnessAssertion toOrPattReq() throws WitnessFalseAssertionException, WitnessTrueAssertionException {
        WitnessAnd newElem = new WitnessAnd(); //to avoid ConcurrentModificationException

        for (Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet())
            for (WitnessAssertion assertion : entry.getValue())
                if (entry.getKey() == WitnessPattReq.class)
                    newElem.add(assertion.toOrPattReq());
                else
                    assertion.toOrPattReq();

        andList.remove(WitnessPattReq.class);
        this.add(newElem);

        return this;
    }

    @Override
    public boolean isBooleanExp() {
        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet()) {
            for(WitnessAssertion assertion : entry.getValue()) {
                if(!assertion.isBooleanExp()) return false;
            }
        }

        return true;
    }

    @Override
    public boolean isRecursive(WitnessEnv env, LinkedList<WitnessVar> visitedVar) {
        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet()) {
            for(WitnessAssertion assertion : entry.getValue()) {
                if(assertion.isRecursive(env, visitedVar)) return true;
            }
        }

        return false;
    }

    @Override
    public WitnessVar buildOBDD(WitnessEnv env) throws WitnessException {
        WitnessVar obbdVarName = null;

        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet()) {
            for(WitnessAssertion assertion : entry.getValue()) {
                if(obbdVarName == null)
                    obbdVarName = assertion.buildOBDD(env);
                else
                    obbdVarName = WitnessBDD.and(env, obbdVarName, assertion.buildOBDD(env));
            }
        }

        return obbdVarName;
    }

    @Override
    public boolean equals(Object o) {
        boolean b = true;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessAnd witnessAnd = (WitnessAnd) o;

        if(witnessAnd.andList.size() != andList.size())
            return false;

        for(Map.Entry<Object, List<WitnessAssertion>> entry : witnessAnd.andList.entrySet()){
            if(!this.andList.containsKey(entry.getKey())) return false;
            List<WitnessAssertion> check = null;

            if(witnessAnd.andList.get(entry.getKey()).size() >= this.andList.get(entry.getKey()).size()) {
                check = new LinkedList<>(witnessAnd.andList.get(entry.getKey()));
                check.removeAll(this.andList.get(entry.getKey()));
            }else{
                check = new LinkedList<>(this.andList.get(entry.getKey()));
                check.removeAll(witnessAnd.andList.get(entry.getKey()));
            }

            b &= check.size() == 0;
        }

        return b;
    }

    /**
     * Assuming that before was executed and-merging, groupize, separation, expansione, dnf
     *
     */
    public void objectPrepare(WitnessEnv env) throws REException, WitnessException {

        //check if is and object group
        if (andList.get(WitnessType.class) == null) { //and without type specified
            logger.debug("Preparing WitnessAnd without type specified");
            return;
        }
        if (andList.get(WitnessType.class) != null && !andList.get(WitnessType.class).contains(new WitnessType(FullAlgebraString.TYPE_OBJECT))) { //if is not an object type
            logger.debug("Preparing WitnessAnd without type object assertion");
            return;
        }
        if (andList.get(WitnessType.class).size() > 1) {//if contains more than one type
            logger.debug("Preparing WitnessAnd with more than one type specified");
            return;
        }

        List<WitnessAssertion> CPart = andList.remove(WitnessProperty.class); //List<WitnessProperty>
        /*WitnessPro minMax = null;

        if (andList.get(WitnessPro.class) != null)
            minMax = (WitnessPro) andList.get(WitnessPro.class).get(0);
        else
            minMax = new WitnessPro();
        */

        //complete and splitCPart --> da ottimizzare in una seconda fase
        if (CPart == null) {//vuota
            logger.debug("Complete empty CPart");
            CPart = new LinkedList<>();
            CPart.add(new WitnessProperty(ComplexPattern.createFromRegexp(".*"), new WitnessBoolean(true)));
            this.add(CPart.get(0));
        } else {
            logger.debug("Complete non-empty CPart");
            ComplexPattern p = ((WitnessProperty) CPart.get(0)).getPattern();

            for (int i = 1; i < CPart.size(); i++)
                p = p.union(((WitnessProperty) CPart.get(i)).getPattern());

            p = p.complement();
            if (!p.isEmptyDomain())
                CPart.add(new WitnessProperty(p, new WitnessBoolean(true)));

            //splitCPart
            CPart = splitClist(CPart);

            logger.debug("Adding completed property in CPart with key: {}", p.toString());

            for (WitnessAssertion tmp : CPart)
                this.add(tmp);
        }

        //Combine CP-RP
        //maps each element of the CP to the all related elements of the RP
        HashMap<WitnessAssertion, List<WitnessPattReq>> coReqs = new HashMap<>();

        List<WitnessAssertion> ORPart = andList.get(WitnessOrPattReq.class);

        for (WitnessAssertion c_assertion : CPart)
            coReqs.put(c_assertion, new LinkedList<>());

        //List<WitnessOrPattReq> newORList = new LinkedList<>(); //espandiamo tutte le R_Assertion che troviamo

        if (ORPart != null) {

            for (WitnessAssertion OR_assertion : ORPart) { //WitnessOrPattReq

                List<WitnessPattReq> newRList = new LinkedList<>();
                //newORList.add(newRList);

                for (WitnessPattReq R_Assertion : ((WitnessOrPattReq) OR_assertion).reqList) {
                    for (WitnessAssertion C_assertion : CPart) {

                        logger.debug("[CP-RP] Computing intersection between {} and {}", ((WitnessProperty) C_assertion).getPattern(), R_Assertion.getPattern());

                        ComplexPattern pattInt = ((WitnessProperty) C_assertion).getPattern().intersect(R_Assertion.getPattern());

                        if (!pattInt.isEmptyDomain()) {

                            logger.debug("[CP-RP] Creating new WitnessAnd");
                            WitnessAnd newAnd = new WitnessAnd();

                            logger.debug("[CP-RP] Adding {} to {}", ((WitnessProperty) C_assertion).getValue(), newAnd);
                            newAnd.add(((WitnessProperty) C_assertion).getValue());

                            logger.debug("[CP-RP] Adding {} to {}", ((WitnessPattReq) R_Assertion).getValue(), newAnd);
                            newAnd.add(((WitnessPattReq) R_Assertion).getValue());

                            if (newAnd.notObviouslyEmpty()) {
                                WitnessPattReq newReq = WitnessPattReq.build(pattInt, newAnd);

                                newRList.add(newReq);
                                coReqs.get(C_assertion).add(newReq);
                            }
                        }
                    }
                }

                //((WitnessOrPattReq) OR_assertion).setORP(newRList);
                ((WitnessOrPattReq) OR_assertion).setReqList(new LinkedList<>());

                //if ORP empty --> unsatisfiable object
                /*WitnessOrPattReq ORP = new WitnessOrPattReq();*/

                for (WitnessPattReq req : new LinkedList<>(newRList))   //concurrentModificationException
                    ((WitnessOrPattReq) OR_assertion).fullConnect(req);
            }


            //init coMatrix
            LinkedList<Map.Entry<WitnessPattReq, WitnessPattReq>> CoMatrix = new LinkedList<>();

            for (WitnessAssertion assertion : CPart) {

                List<WitnessPattReq> coList = coReqs.get(assertion);

                for (int i = 0; i < coList.size() - 1; i++) {
                    for (int j = i + 1; j < coList.size(); j++) {
                        logger.debug("Adding to coMatrix the entry < {} ; {} >", coList.get(i), coList.get(j));
                        CoMatrix.add(new AbstractMap.SimpleEntry<>(coList.get(i), coList.get(j)));
                    }
                }
            }

            //variable saparation
            this.varNormalization_separation(env); //da indagare

            splitOriginalRList(ORPart, CoMatrix, env);

            //Reduce compatible pairs
            //reduceCompatiblePairs(CoMatrix); //old version

            //variable saparation
            this.varNormalization_separation(env); //da indagare
        }
        /*for (WitnessAssertion a : CPart)
            this.add(a);*/
    }

    public boolean notObviouslyEmpty(){
        return true;
    }//TODO: x e not(x) nella stessa congiunzione??

    /**
     * Splits a CP list from index position forwards leaving position before index untouched
     * it returns a CP list that is equivalent to list but where all different
     * patterns are disjoint
     */
    private List<WitnessAssertion> splitClist(List<WitnessAssertion> list) throws WitnessException {
        //ITERATIVE
        /*LinkedList<WitnessAssertion> expandedCList = new LinkedList<>();
        for(int i = list.size() -1; i >= 0; i++){
            Map.Entry<WitnessProperty, LinkedList<WitnessAssertion>> subHead_reducedExpandedTail = reduceElemWithList((WitnessProperty) list.get(i), expandedCList);
            WitnessProperty subHead = subHead_reducedExpandedTail.getKey();
            LinkedList<WitnessAssertion> reducedExpandedTail = subHead_reducedExpandedTail.getValue();
            expandedCList = reducedExpandedTail;
            if(!subHead.getPattern().isEmptyDomain())
                expandedCList.addFirst(subHead);
        }

        return expandedCList;*/

        //RECURSIVE
        if(list.isEmpty()) return list;
        List expandedTail = splitClist(list.subList(1, list.size()));
        Map.Entry<WitnessProperty, LinkedList<WitnessAssertion>> subHead_reducedExpandedTail = reduceElemWithList((WitnessProperty) list.get(0), expandedTail);
        WitnessProperty subHead = subHead_reducedExpandedTail.getKey();
        LinkedList<WitnessAssertion> reducedExpandedTail = subHead_reducedExpandedTail.getValue();

        if(!subHead.getPattern().isEmptyDomain())
            reducedExpandedTail.addFirst(subHead);

        return reducedExpandedTail;

    }

    /**
     * given prop and list,
     * it returns a pair subProp, reducedList with the following properties:
     * - dom(subProp) = dom(prop) - dom(list) where dom(X) are the names
     *                  matched by the patterns in X
     * - reducedList is obtained by splitting all elements 'elem' of list that
     *   intersect with prop into a pair : ( (elem and prop) ; (elem minus prop) ),
     *   where the schema of (elem and prop) is (elem.schema and prop.schema)
     */
    private Map.Entry<WitnessProperty, LinkedList<WitnessAssertion>> reduceElemWithList(WitnessProperty prop, List<WitnessAssertion> list) {
        if(list.isEmpty())
            return new AbstractMap.SimpleEntry<>(prop, new LinkedList<>());

        Map.Entry<WitnessProperty, LinkedList<WitnessAssertion>> subProp_reducedTail = reduceElemWithList(prop, list.subList(1, list.size()));
        WitnessProperty subProp = subProp_reducedTail.getKey();
        LinkedList<WitnessAssertion> reducedTail= subProp_reducedTail.getValue();

        logger.debug("Reducing subProperty {} with reduceTail {} >", subProp, reducedTail);

        WitnessProperty head = (WitnessProperty) list.get(0);

        ComplexPattern intersection = subProp.getPattern().intersect(head.getPattern());
        if(intersection.isEmptyDomain()) {
            reducedTail.addFirst(head);
            return new AbstractMap.SimpleEntry<>(subProp, reducedTail);
        }

        ComplexPattern propMinHead = subProp.getPattern().minus(head.getPattern());
        ComplexPattern headMinProp = head.getPattern().minus(subProp.getPattern());

        WitnessProperty newSubProp = new WitnessProperty(propMinHead, subProp.getValue());

        WitnessAnd and = new WitnessAnd();
        try {
            and.add(subProp.getValue());
            and.add(head.getValue());
        }catch (WitnessFalseAssertionException e){
            throw new  RuntimeException(e); //impossibile o no??
        }
        LinkedList<WitnessAssertion> newReducedList = new LinkedList<>(reducedTail);
        newReducedList.addFirst(new WitnessProperty(intersection, and));

        if(!headMinProp.isEmptyDomain()){
            newReducedList.add(new WitnessProperty(headMinProp, head.getValue()));
        }

        return new AbstractMap.SimpleEntry<>(newSubProp, newReducedList);
    }


    /*
       In order to split the RList, we first transform each Request (p:A) into a pair
       p, {(p:A)}. When we generate an intersection pattern, we keep track of all the
       original requests that are its ancestors.
       Then, the function rewritePatternReqs, will explode each pair (p, {r1,...,rn})
       into all the different 2^n subcases
    */
    private void splitOriginalRList(List<WitnessAssertion> ORList, List<Map.Entry<WitnessPattReq, WitnessPattReq>> coMatrix, WitnessEnv env) throws WitnessException, REException {
        List<PatternReqs> ReqList = new LinkedList<>();

        for(WitnessAssertion or : ORList)
            for(WitnessPattReq req : ((WitnessOrPattReq) or).reqList)
                ReqList.add(new PatternReqs(req.getPattern(), creaSingoletto(req)));

        List<Map.Entry<PatternReqs, PatternReqs>> coList = new LinkedList<>();

        for(Map.Entry<WitnessPattReq, WitnessPattReq> entry : coMatrix) {
            PatternReqs x = new PatternReqs(entry.getKey().getPattern(), creaSingoletto(entry.getKey()));
            PatternReqs y = new PatternReqs(entry.getValue().getPattern(), creaSingoletto(entry.getValue()));
            logger.debug("Adding to coList the entry < {} ; {} >",  x, y);
            coList.add(new AbstractMap.SimpleEntry(x, y));
        }

        List<PatternReqs> splitList = splitRList(ReqList, coList);
        rewritePatternReqsList(splitList, env);
    }

    /*
       Splits the elements of list from the one with index "index"
       and returns the result of this operation.
       To this aim, it first splits the elements from index+1 onwards (the tail), and then it takes
       the element of position index, and it "reduces" it against all elements of the tail
    */
    private List<PatternReqs> splitRList(List<PatternReqs> list, List<Map.Entry<PatternReqs, PatternReqs>> coList) throws WitnessException {
        if(list.isEmpty()) return list;

        List<PatternReqs> expandedTail = splitRList(list.subList(1, list.size()), coList );

        Map.Entry<PatternReqs, List<PatternReqs>> subHead_reducedExpandedTail = reduceReqWithList(list.get(0), expandedTail, coList);
        PatternReqs subHead = subHead_reducedExpandedTail.getKey();
        List<PatternReqs> reducedExpandedTail = subHead_reducedExpandedTail.getValue();

        if(subHead.complexPattern.isEmptyDomain()) return reducedExpandedTail;
        else{
            reducedExpandedTail.add(subHead);
            return reducedExpandedTail;
        }
    }

    /*
      Returns a pair (PatternReqs,NewList) obtained by reducing list from position index onwards, and ignoring the
      first part
      A PatternReqs is a pair (pattern-List Of Requests) that indicates that, for each req in List Of Requests,
      its ORP should contain one element whose pattern is "pattern"
      Reducing "req" with "head" means that "head" is split into "head and req" and "head minus req",
      while req is reduced to what remais (req-head).
      Reduction only combines two elements that are compatible according to coList, and updates the coList,
      but this is just an optimization in order to reduce the amount of pattern intersections to compute
      coList is just an optimization
     */
    private Map.Entry<PatternReqs, List<PatternReqs>> reduceReqWithList(PatternReqs req, List<PatternReqs> list,
                                                                        List<Map.Entry<PatternReqs, PatternReqs>> coList) throws WitnessException {
        if(list.isEmpty())
            return new AbstractMap.SimpleEntry<>(req, new LinkedList<>());

        Map.Entry<PatternReqs, List<PatternReqs>> subReq_reducedTail = reduceReqWithList(req, list.subList(1, list.size()), coList);

        PatternReqs subReq = subReq_reducedTail.getKey();
        List<PatternReqs> reducedTail = subReq_reducedTail.getValue();

        logger.debug("Splitting subReq {} with {} reducedTail",  subReq, reducedTail);

        /* if dom(subReq) is already empty there is no reduction left to perform */
        PatternReqs head = list.get(0);
        ComplexPattern subReqPatt = subReq.complexPattern;

        if(!compatible(subReq, head, coList) || subReqPatt.isEmptyDomain()) {
            logger.debug("subReq {} is compatible with head {}", subReq, head);
            reducedTail.add(head);
            return new AbstractMap.SimpleEntry<>(subReq, reducedTail);
        }

        /* if we arrive here, then subReq is not empty and it is coList-compatible with head */
        coList.remove(new AbstractMap.SimpleEntry<>(subReq, head));
        coList.remove(new AbstractMap.SimpleEntry<>(head, subReq));

        logger.debug("Removing to coList the entry < {} ; {} >", subReq, head);

        ComplexPattern intersection = subReqPatt.intersect(head.complexPattern);

        if(intersection.isEmptyDomain()){
            reducedTail.add(head);
            return new AbstractMap.SimpleEntry<>(subReq, reducedTail);
        }

        List<WitnessPattReq> unionList = new LinkedList<>(head.list);
        unionList.addAll(subReq.list);
        PatternReqs intersectReq = new PatternReqs(intersection, unionList);
        LinkedList<PatternReqs> newReducedList = new LinkedList<>(reducedTail); //forse si può rimuovere se siamo ricorsivi

        newReducedList.add(intersectReq);
        addPairs(coList, intersectReq, intersect(compWith(coList, subReq), compWith(coList, head)) );

        /* we also reduce req - the reducedReq may have empty pattern*/
        ComplexPattern reqMinHead = subReqPatt.minus(head.complexPattern);
        PatternReqs newSubReq = new PatternReqs(reqMinHead, subReq.list); // req minus getValue is the reduced req
        if (!reqMinHead.isEmptyDomain()) { // no need to do this is reqMinHead is empty
            addPairs(coList, newSubReq, compWith(coList, subReq));
        }

        /* finally, we also generate headMinReq, in case it is not empty */
        ComplexPattern headMinReq = head.complexPattern.minus(subReqPatt);
        if(!headMinReq.isEmptyDomain()) {
            PatternReqs headMinReqReq = new PatternReqs(headMinReq, head.list);
            newReducedList.add(headMinReqReq); // right minus left : rightSchema goes in the list
            addPairs(coList, headMinReqReq, compWith(coList, head));
        }

        for(int i = coList.size() -1; i >= 0; i--) {
            Map.Entry<PatternReqs, PatternReqs> entry = coList.get(i);
            if (entry.getKey().equals(head) || entry.getValue().equals(head)
                    || entry.getKey().equals(subReq) || entry.getValue().equals(subReq)) {
                logger.debug("Removing to coList the entry < {} ; {} >", coList.get(i));
                coList.remove(i);
            }
        }

        return new AbstractMap.SimpleEntry<>(newSubReq, newReducedList);
    }

    private boolean compatible(PatternReqs r1, PatternReqs r2, List<Map.Entry<PatternReqs, PatternReqs>> coList){
        return coList.contains(new AbstractMap.SimpleEntry<>(r1,r2)) || coList.contains(new AbstractMap.SimpleEntry<>(r2,r1));
    }

    private List<PatternReqs> compWith(List<Map.Entry<PatternReqs, PatternReqs>> coList, PatternReqs req){
        List<PatternReqs> returnList = new LinkedList<>();

        for(Map.Entry<PatternReqs, PatternReqs> entry : coList)
            if(entry.getKey().equals(req)) returnList.add(entry.getValue());
            else if(entry.getValue().equals(req)) returnList.add(entry.getKey());

        logger.debug("CompableWith list of PatternReqs {} is {}", req, returnList);

        return returnList;
    }

    private List<PatternReqs> intersect(List<PatternReqs> l1, List<PatternReqs> l2){
        List<PatternReqs> returnPattReqs = new LinkedList<>();

        logger.debug("Computing PatternReqs intersection between {} and {}", l1, l2);

        for(PatternReqs pr1 : l1)
            for(PatternReqs pr2 : l2)
                if(pr1.equals(pr2))
                    returnPattReqs.add(pr1);

        logger.debug("PatternReqs intersection result: {}", returnPattReqs);

        return returnPattReqs;
    }

    private void addPairs(List<Map.Entry<PatternReqs, PatternReqs>> coList, PatternReqs req, List<PatternReqs> reqList){
        for(PatternReqs tmp : reqList){
            logger.debug("Adding to coList the entry < {} ; {} >",  req, tmp);
            coList.add(new AbstractMap.SimpleEntry<>(req, tmp));
        }
    }

    private void rewritePatternReqsList (List<PatternReqs> list, WitnessEnv env) throws WitnessException, REException {
        List<WitnessAssertion> oldOrPattRequests = this.andList.get(WitnessOrPattReq.class); //getAllOldRequests

        logger.debug("Old ORP size:", oldOrPattRequests.size());

        Set<WitnessPattReq> oldPattRequests = new HashSet<>();

        for(WitnessAssertion pr : oldOrPattRequests)
            oldPattRequests.addAll(((WitnessOrPattReq) pr).reqList);

        logger.debug("Old Pattern Requests size:", oldPattRequests.size());

        for (PatternReqs pReqs : list ) {
            rewritePatternReqs(pReqs, env);
        }

        for (WitnessAssertion oldReq : oldPattRequests ) {
            WitnessPattReq oldReq_ = (WitnessPattReq) oldReq;
            oldReq_.deConnectAll(new LinkedList<>(oldReq_.getOrpList()));
        }
    }

    private void rewritePatternReqs(PatternReqs reqs, WitnessEnv env) throws WitnessException, REException {
        logger.debug("Rewriting PatternReqs {}", reqs);

        ComplexPattern patt = reqs.complexPattern;
        List<WitnessPattReq> reqList = reqs.list;

        logger.debug("RequestList size:", reqList.size());

        if (reqList.size() == 0) { throw new UnsupportedOperationException("impossibile: reqList.size() == 0");}  // impossible case
        if (reqList.size() == 1 ) {
            WitnessPattReq clone = reqList.get(0).clone();
            clone.setPattern(reqs.complexPattern);

            for(WitnessOrPattReq orp : reqList.get(0).getOrpList())
                clone.fullConnect(orp);

            return;
        }
        /* non-trivial set */
        for (List<WitnessPattReq> subset : compSubsetsOf(new LinkedList<>(reqList))) { //subset: we create here a fragment that
            //satisfies all and only the assertions in "subset"
            //subset is never empty
            //subset: the set of Ancerstors that are compatible with this fragment
            WitnessAssertion schema = new WitnessAnd();
            List <WitnessPattReq> coSubset = new LinkedList<>(reqList);
            coSubset.removeAll(subset);

            for (WitnessPattReq oldReq : new LinkedList<>(subset) ) {
                try {
                    ((WitnessAnd) schema).add(oldReq.getValue());
                }catch (WitnessFalseAssertionException e){
                    schema = new WitnessBoolean(false);
                    break;
                }
            }//  schema = schema and schemaOf(oldReq)

            if(schema.getClass() == WitnessAnd.class)
                for (WitnessPattReq oldReq : coSubset ) {
                    try {
                        ((WitnessAnd) schema).add(oldReq.getValue().not(env));
                    }catch (WitnessFalseAssertionException e){
                        schema = new WitnessBoolean(false);
                        break;
                    }
                } //  schema = schema and not schemaOf(oldReq)

            WitnessPattReq fragment = WitnessPattReq.build(patt, schema);
            //fragment: satisfies subset and fails all assertions in coSubset
            for (WitnessPattReq compAncestor : subset ) {            //we put the fragment in all and only the ORPs that contain
                //this.add(fragment);
                for (WitnessOrPattReq orp : compAncestor.getOrpList() ) {   //one req that is satisfied
                    fragment.fullConnect(orp); //gli orp non sono i soliti TODO: INDAGARE
                }
            }
            //fermati
        }
    }

    /* this is a trivial version that generates all non-empty subsets with no compatibility check */
    List<List<WitnessPattReq>> compSubsetsOf(List<WitnessPattReq> reqList) {
        if (reqList.size() == 1) { return creaSingoletto(reqList); } // receives { r } and return {{r}}
        WitnessPattReq head = reqList.remove(0);
        List<List<WitnessPattReq>> subsetsNoHead = compSubsetsOf(reqList); // we compute the non-empty subsets of the reqList with no head
        List<List<WitnessPattReq>> returnList = new LinkedList<>(subsetsNoHead);

        for (List<WitnessPattReq> subNoHead : subsetsNoHead) {
            List<WitnessPattReq> subNoHead_clone = new LinkedList<>(subNoHead);
            subNoHead_clone.add(head);
            returnList.add(subNoHead_clone);
        }
        returnList.add(creaSingoletto(head)); // subsetsNoHead does not contain the empty set, hence we must add this singleton
        return returnList;
    }

    /** coMatrix contain all maybe-compatible pairs
       for each maybe-compatible pair left-right, if it is really compatible,
       we split left into three fragments, right into three fragments, and
       we substitute left and right with their fragments both in the orp
       to which they belong and in the coMatrix.
       The intersection fragment will belong to both orps
       RList contains all requests of the object. It is just the union of all
       orp's after DISTINCT elimination.
    */
    /*
    private void reduceCompatiblePairs(LinkedList<Map.Entry<WitnessPattReq, WitnessPattReq>> coMatrix) throws REException, WitnessException {
        while(!coMatrix.isEmpty()){
            WitnessPattReq left = coMatrix.getFirst().getKey();
            WitnessPattReq right = coMatrix.getFirst().getValue();
            coMatrix.removeFirst();

            ComplexPattern intersection = left.getPattern().intersect(right.getPattern());

            if(intersection.domainSize() == 0) continue;

            ComplexPattern leftMinRight = left.getPattern().minus(right.getPattern());
            ComplexPattern rightMinLeft = right.getPattern().minus(left.getPattern());

            WitnessAnd leftMinRightSchema = new WitnessAnd();
            leftMinRightSchema.add(left.getValue());
            leftMinRightSchema.add(right.getValue().not());

            WitnessAnd rightMinLeftSchema = new WitnessAnd();
            rightMinLeftSchema.add(right.getValue());
            rightMinLeftSchema.add(left.getValue().not());

            WitnessAnd intSchema = new WitnessAnd();
            intSchema.add(left.getValue());
            intSchema.add(right.getValue());

            List<WitnessPattReq> leftFragments = new LinkedList<>();
            List<WitnessPattReq> rightFragments = new LinkedList<>();

            if(intSchema.notObviouslyEmpty()){
                WitnessPattReq newRequest = WitnessPattReq.build(intersection, intSchema);
                leftFragments.add(newRequest);
                rightFragments.add(newRequest);
            }
            if(leftMinRightSchema.notObviouslyEmpty()){
                leftFragments.add(WitnessPattReq.build(intersection, leftMinRightSchema.getIfUnitaryAnd() != null ?
                        leftMinRightSchema.getIfUnitaryAnd() : leftMinRightSchema));
            }
            if(leftMinRight.domainSize() != 0){
                leftFragments.add(WitnessPattReq.build(leftMinRight, left.getValue()));
            }
            if(rightMinLeftSchema.notObviouslyEmpty()){
                rightFragments.add(WitnessPattReq.build(intersection, rightMinLeftSchema.getIfUnitaryAnd() != null ?
                        rightMinLeftSchema.getIfUnitaryAnd() : rightMinLeftSchema));
            }
            if(rightMinLeft.domainSize() != 0){
                rightFragments.add(WitnessPattReq.build(rightMinLeft, right.getValue()));
            }

            for(WitnessOrPattReq lOrp : new LinkedList<>(left.getOrpList())) { //to avoid concurrentModificationException
                for (WitnessPattReq lF : leftFragments)
                    lOrp.fullConnect(lF);

                lOrp.deConnect(left);
            }

            for(WitnessOrPattReq rOrp : new LinkedList<>(right.getOrpList())) {
                for (WitnessPattReq rF : rightFragments)
                    rOrp.fullConnect(rF);

                rOrp.deConnect(right);
            }

            //substitute left
            for(int i = coMatrix.size() -1; i >= 0 ; i--){
                WitnessPattReq coMatrix_i_key = coMatrix.get(i).getKey();
                WitnessPattReq coMatrix_i_value = coMatrix.get(i).getValue();

                if(coMatrix_i_key.equals(left)) {
                    coMatrix.remove(i);
                    for(WitnessPattReq tmp : leftFragments)
                        coMatrix.add(new AbstractMap.SimpleEntry<>(tmp, coMatrix_i_value));
                }
                if(coMatrix_i_value.equals(left)){
                    coMatrix.remove(i);
                    for(WitnessPattReq tmp : leftFragments)
                        coMatrix.add(new AbstractMap.SimpleEntry<>(tmp, coMatrix_i_key));
                }

                if (coMatrix_i_key.equals(right)) {
                    coMatrix.remove(i);
                    for (WitnessPattReq tmp : rightFragments)
                        coMatrix.add(new AbstractMap.SimpleEntry<>(tmp, coMatrix_i_value));
                }
                if (coMatrix_i_value.equals(right)) {
                    coMatrix.remove(i);
                    for (WitnessPattReq tmp : rightFragments)
                        coMatrix.add(new AbstractMap.SimpleEntry<>(tmp, coMatrix_i_key));
                }
            }

            System.out.println("CoMatrix size: " + coMatrix.size());
        }
    }
    */


    @Override
    public int hashCode() {
        return andList != null ? andList.hashCode() : 0;
    }

    private static <T> LinkedList<T> creaSingoletto(T elem){
        LinkedList<T> list = new LinkedList<>();
        list.add(elem);
        return list;
    }

}

/**
 * Coppia <pattern, lista antenati>
 *
 */
class PatternReqs{
    public ComplexPattern complexPattern;
    public List<WitnessPattReq> list;

    public PatternReqs() {
        list = new LinkedList<>();
    }

    public PatternReqs(ComplexPattern complexPattern, List<WitnessPattReq> list) {
        this.complexPattern = complexPattern;
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PatternReqs that = (PatternReqs) o;

        if (complexPattern != null ? !complexPattern.equals(that.complexPattern) : that.complexPattern != null)
            return false;

        List<WitnessPattReq> check = new LinkedList<>(this.list);
        check.removeAll(that.list);
        if(check.size() != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = complexPattern != null ? complexPattern.hashCode() : 0;
        result = 31 * result + (list != null ? list.hashCode() : 0);
        return result;
    }
}