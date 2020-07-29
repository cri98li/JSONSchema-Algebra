package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.UnsenseAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import patterns.REException;

import java.util.*;

public class WitnessAnd implements WitnessAssertion{
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
    }

    /**
     *     Add an assertion - el - to the andList.
     *     if el is an instance of WitnessAnd, we add every element of el.andList in this.andList
     *     if el is an instance of uniqueItems or repeatedItems, we check if andList do not contain it, then we add it
     * @param el
     * @return
     */
    public boolean add(WitnessAssertion el) {

        if(el.getClass() == WitnessBoolean.class) //add boolean
            if(((WitnessBoolean) el).getValue()) //add true
                return false;
            else
                throw new UnsenseAssertion("and.add(false)");

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
                throw new UnsenseAssertion("add di 2 and incompatibili");
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

    public boolean add(WitnessAnd and){
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
        if(this.add(a)) //if the list has been modified --> apply merge again
            return this.merge();
        return this;
    }


    @Override
    public WitnessAssertion merge() throws REException {
        WitnessAnd newAnd = new WitnessAnd();
        boolean modified = false;

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

        //if is unitary and, return only the value
        WitnessAssertion value = getIfUnitaryAnd();
        if (value != null)
            return value;

        if (modified)
            return newAnd.merge();

        return newAnd;
    }

    /**
     * if and contains only one assertion, return that assertion
     * else return null
     */
    public WitnessAssertion getIfUnitaryAnd(){
        if(andList.size() == 0) return new WitnessBoolean(true);
        if(andList.size() == 1) {
            Iterator<Map.Entry<Object, List<WitnessAssertion>>> entry = andList.entrySet().iterator();
            Object key = entry.next().getKey();
            if(andList.get(key).size() == 1)
                return andList.get(key).get(0);
        }

        return null;
    }


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
        WitnessAnd clone = new WitnessAnd();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()) {
                clone.add(assertion.clone());
            }

        return clone;
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
        //caso base: se and vuoto --> and[true] --> ritorno true
        if(andList.size() == 0) return new WitnessBoolean(true);

        if (andList.containsKey(WitnessType.class)) {
            List<WitnessAssertion> types = andList.remove(WitnessType.class);
            WitnessAssertion type = types.remove(0);

            for (WitnessAssertion t : types)
                type = type.mergeWith(t);

            if(type.getClass() == WitnessBoolean.class)
                return type;

            //One type
            if (((WitnessType)type).separeTypes().size() == 1) return unTipo((WitnessType) type);

            //More than one type
            else return piuDiUnTipo((WitnessType) type);

        } else  //No type specified
            return noType();
    }

    public WitnessAssertion unTipo(WitnessType type) throws WitnessException, REException {
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

    public WitnessAssertion piuDiUnTipo(WitnessType type) throws WitnessException, REException {
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

        //Optimization: unitary and
        for(Map.Entry<?, WitnessAnd> ands: groups.entrySet()){
            WitnessAssertion tmp = ands.getValue().getIfUnitaryAnd();
            if(tmp == null)
                or.add(ands.getValue());
            else
                or.add(tmp);
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
            WitnessAssertion tmp = ands.getValue().getIfUnitaryAnd();
            if(tmp == null)
                or.add(ands.getValue());
            else
                or.add(tmp);
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
                        }catch(UnsenseAssertion e){
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
        WitnessOr or;

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
    public boolean isBooleanExp() {
        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet()) {
            for(WitnessAssertion assertion : entry.getValue()) {
                if(!assertion.isBooleanExp()) return false;
            }
        }

        return true;
    }

    @Override
    public WitnessVar buildOBDD(WitnessEnv env) throws WitnessException {
        WitnessVar obbdVarName = null;

        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet()) {
            for(WitnessAssertion assertion : entry.getValue()) {
                if(obbdVarName == null)
                    obbdVarName = assertion.buildOBDD(env);
                obbdVarName = env.obdd.and(env, obbdVarName, assertion.buildOBDD(env));
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
        if(andList.get(WitnessType.class) == null) //and without type specified
            return;
        if(andList.get(WitnessType.class) != null && !andList.get(WitnessType.class).contains(new WitnessType(FullAlgebraString.TYPE_OBJECT))) //if is not an object type
            return;
        if(andList.get(WitnessType.class).size() > 1) //if contains more than one type
            return; //throw new ParseCancellationException("and non gruppizzato");

        List<WitnessAssertion> CPart = andList.remove(WitnessProperty.class); //List<WitnessProperty>
        WitnessPro minMax = null;

        if(andList.get(WitnessPro.class) != null)
            minMax = (WitnessPro) andList.get(WitnessPro.class).get(0);
        else
            minMax = new WitnessPro();

        //complete and splitCPart --> da ottimizzare in una seconda fase
       if(CPart == null) {//vuota
            this.add(new WitnessProperty(ComplexPattern.createFromRegexp(".*"), new WitnessBoolean(true)));
            CPart = andList.get(WitnessProperty.class);
        }
        else { //TODO: chiedere additional properties
            ComplexPattern p = ((WitnessProperty) CPart.get(0)).getPattern();

            for (int i = 1; i < CPart.size(); i++)
                p = p.union(((WitnessProperty) CPart.get(i)).getPattern());

            p = p.complement();
            if(!p.isEmptyDomain()) {
                this.add(new WitnessProperty(p, new WitnessBoolean(true)));
                CPart.add(new WitnessProperty(p, new WitnessBoolean(true)));
            }

            //splitCPart
            CPart = splitClist(CPart);

            for(WitnessAssertion tmp : CPart)
                this.add(tmp);

        }

        //Combine CP-RP
        //maps each element of the CP to the all related elements of the RP
        HashMap<WitnessAssertion, List<WitnessPattReq>> coReqs = new HashMap<>();

        //TODO: considerare anche ORP
        List<WitnessAssertion> RPart = andList.get(WitnessPattReq.class);

        for(WitnessAssertion c_assertion : CPart)
            coReqs.put(c_assertion, new LinkedList<>());

        List<WitnessPattReq> NewRList = null;

        if(RPart != null) {

            for (WitnessAssertion R_assertion : RPart) {

                NewRList = new LinkedList<>();

                for (WitnessAssertion C_assertion : CPart) {

                    ComplexPattern pattInt = ((WitnessProperty) C_assertion).getPattern().intersect(((WitnessPattReq) R_assertion).getPattern());

                    if (!pattInt.isEmptyDomain()) {

                        WitnessAnd newAnd = new WitnessAnd();
                        newAnd.add(((WitnessProperty) C_assertion).getValue());
                        newAnd.add(((WitnessPattReq) R_assertion).getValue());

                        if (newAnd.notObviouslyEmpty()) {
                            WitnessPattReq newReq = WitnessPattReq.build(pattInt, newAnd.getIfUnitaryAnd() != null ? newAnd.getIfUnitaryAnd() : newAnd );

                            NewRList.add(newReq);
                            coReqs.get(C_assertion).add(newReq);
                        }
                    }
                }

                //if ORP empty --> unsatisfiable object
                WitnessOrPattReq ORP = new WitnessOrPattReq();

                for(WitnessPattReq req : NewRList)
                    ORP.fullConnect(req);

                this.add(ORP);
            }
        }

        //init coMatrix
        LinkedList<Map.Entry<WitnessPattReq, WitnessPattReq>> CoMatrix = new LinkedList<>();

        for(WitnessAssertion assertion : CPart){

            List<WitnessPattReq> coList = coReqs.get(assertion);

            for (int i=0; i < coList.size() - 1 ; i++) {
                for (int j = i + 1; j < coList.size(); j++) {
                    CoMatrix.add(new AbstractMap.SimpleEntry<>(coList.get(i), coList.get(j)));
                }
            }
        }

        //variable saparation
        this.varNormalization_separation(env);

        //Reduce compatible pairs
        reduceCompatiblePairs(CoMatrix);

        //variable saparation
        this.varNormalization_separation(env);
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

        if(subHead.getPattern().isEmptyDomain())
            return expandedTail;
        else {
            reducedExpandedTail.addFirst(subHead);
            return reducedExpandedTail;

        }

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
        and.add(subProp.getValue());
        and.add(head.getValue());
        LinkedList<WitnessAssertion> newReducedList = new LinkedList<>(reducedTail);
        newReducedList.addFirst(new WitnessProperty(intersection, and));

        if(!headMinProp.isEmptyDomain()){
            newReducedList.add(new WitnessProperty(headMinProp, head.getValue()));
        }

        return new AbstractMap.SimpleEntry<>(newSubProp, newReducedList);
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


    @Override
    public int hashCode() {
        return andList != null ? andList.hashCode() : 0;
    }

}