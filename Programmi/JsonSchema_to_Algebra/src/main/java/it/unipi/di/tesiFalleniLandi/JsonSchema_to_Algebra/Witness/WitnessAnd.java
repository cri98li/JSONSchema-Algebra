package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.UnsenseAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.AllOf_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
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

    /*
    Add an assertion - el - to the andList.
    if el is an instance of WitnessAnd, we add every element of el.andList in this.andList
    if el is an instance of uniqueItems or repeatedItems, we check if andList do not contain it, then we add it
     */
    public boolean add(WitnessAssertion el) {
        boolean b = false;

        if(el.getClass() == WitnessBoolean.class)
            if(((WitnessBoolean) el).getValue())
                return false;
            else
                throw new UnsenseAssertion("and.add(false)");

        if(el.getClass() == WitnessAnd.class) {
            for (Map.Entry<Object, List<WitnessAssertion>> entry : ((WitnessAnd) el).andList.entrySet())
                for (WitnessAssertion assertion : entry.getValue())
                    b |= this.add(assertion);
            return b;
        }

        if(el.getClass() == WitnessUniqueItems.class || el.getClass() == WitnessRepeateditems.class){
            if(!andList.containsKey(el.getClass())){
                List<WitnessAssertion> list = new LinkedList<>();
                list.add(el);
                andList.put(el.getClass(), list);
                return true;
            } else return false;
        }

        // inserisce l'asserzione nella rispettiva lista
        if(andList.containsKey(el.getClass())) {
            //if(andList.get(el.getClass()).contains(el)) return false;
            andList.get(el.getClass()).add(el);
        }else {
            List<WitnessAssertion> list = new LinkedList<>();
            list.add(el);
            andList.put(el.getClass(), list);
        }
        return true;
    }

    @Override
    public String toString() {
        return "WitnessAnd{" + "\r\n" +
                "andList=" + andList.toString()
                 + "\r\n" +
                '}';
    }


    @Override
    public void checkLoopReferences(WitnessEnv env, Collection<WitnessVar> varList) throws WitnessException {
        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                assertion.checkLoopReferences(env, varList);
    }

    @Override
    public WitnessAssertion mergeElement(WitnessAssertion a) throws REException {
        if(this.add(a)) //se la lista è stata modificata faccio il merge
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
                merged = merged.mergeElement(sameTypeAssertion.getValue().get(i).merge());
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
                WitnessAssertion assertion = mof.mergeElement(tmp);
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

        //se è un and unitario restituisco solo quel valore
        WitnessAssertion value = isUnitaryAnd();
        if (value != null)
            return value;
        //return (modified) ? value.merge() : value; //TODO: giusto??

        if (modified)
            return newAnd.merge();

        return newAnd;
    }

    public WitnessAssertion isUnitaryAnd(){
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

    /**
     * INPUT: lista di asserzioni in and
     * OUPUT: lista in and contenente solo istanze di WitnessGroup, WitnessOr e WitnessVar
     */
    @Override
    public WitnessAssertion groupize() throws WitnessException, REException {
        /**
         * non contiene type -- si restituisce un or di and
         * contene 1 type -- si eliminano tutte le asserzioni che non coincidono
         * contiene + type -- return false
         */
        //WitnessAssertion first = null;

        if (andList.containsKey(WitnessType.class)) {
            List<WitnessAssertion> types = andList.remove(WitnessType.class);
            WitnessAssertion type = types.remove(0);
            for (WitnessAssertion t : types)
                type = type.mergeElement(t);

            if(type.getClass() == WitnessBoolean.class)
                return type;

            //è 1 tipo
            if (((WitnessType)type).separeTypes().size() == 1) {
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
                        /*first = entry.getValue().get(0); //TODO: check
                    if(first.getGroupType() != null) {
                        if (first.getGroupType().equals(type.getGroupType()))
                            and.andList.put(entry.getKey(), entry.getValue()); //TODO: commento

                    else if (first.getGroupType() == null) //variabile o or
                        for (WitnessAssertion tmp : entry.getValue())
                            and.add(tmp.groupize()); //TODO: controlla che non ritorni and
                }*/

                }

                return and;

                //più di un tipo
            } else {
                WitnessOr or = new WitnessOr();

                HashMap<WitnessType, WitnessAnd> groups = new HashMap<>();

                for (WitnessType t : ((WitnessType) type).separeTypes()) {
                    WitnessAnd and = new WitnessAnd();
                    and.add(t);
                    groups.put(t, and);
                    //or.add(and);
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
                    /*
                    first = entry.getValue().get(0); //TODO: check
                    if (first.getGroupType() != null) {
                        if (groups.containsKey(first.getGroupType()))
                            groups.get(first.getGroupType()).andList.put(first.getClass(), entry.getValue());

                    }else if (first.getGroupType() == null) //variabile o or
                        for (WitnessAssertion tmp : entry.getValue()) {
                            WitnessAssertion grupizeResult = tmp.groupize();
                            for (Map.Entry<?, WitnessAnd> e : groups.entrySet())
                                e.getValue().add(grupizeResult);
                        }
                }*/
                    }
                }

                //Elimino gli and unitari
                for(Map.Entry<?, WitnessAnd> ands: groups.entrySet()){
                    WitnessAssertion tmp = ands.getValue().isUnitaryAnd();
                    if(tmp == null)
                        or.add(ands.getValue());
                    else
                        or.add(tmp);
                }

                return or;
            }
        }

        else { //Nessun tipo
            WitnessOr or = new WitnessOr();
            WitnessAnd and = new WitnessAnd();
            HashMap<WitnessType, WitnessAnd> groups = new HashMap<>();

            WitnessType type = new WitnessType(GrammarStringDefinitions.TYPE_NUMBER);
            and.add(type);
            groups.put(type, and);
            //or.add(and);

            and = new WitnessAnd();
            type = new WitnessType(GrammarStringDefinitions.TYPE_OBJECT);
            and.add(type);
            groups.put(type, and);
            //or.add(and);

            and = new WitnessAnd();
            type = new WitnessType(GrammarStringDefinitions.TYPE_ARRAY);
            and.add(type);
            groups.put(type, and);
            //or.add(and);

            and = new WitnessAnd();
            type = new WitnessType(GrammarStringDefinitions.TYPE_STRING);
            and.add(type);
            groups.put(type, and);
            //or.add(and);

            and = new WitnessAnd();
            type = new WitnessType(GrammarStringDefinitions.TYPE_BOOLEAN);
            and.add(type);
            groups.put(type, and);
            //or.add(and);

            and = new WitnessAnd();
            type = new WitnessType(GrammarStringDefinitions.TYPE_NULL);
            and.add(type);
            groups.put(type, and);
            //or.add(and);

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
            /*
            for (Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet()) {
                first = entry.getValue().get(0);
                if (first.getGroupType() != null) {
                    groups.get(first.getGroupType()).andList.put(first.getClass(), entry.getValue());

                }else if (first.getGroupType() == null) //variabile o or
                    for (WitnessAssertion tmp : entry.getValue()) {
                        WitnessAssertion groupizeResult = tmp.groupize();

                        for (Map.Entry<?, WitnessAnd> e : groups.entrySet())
                            e.getValue().add(groupizeResult);
                    }
            }
            */


            //Elimino gli and unitari
            for(Map.Entry<?, WitnessAnd> ands: groups.entrySet()){
                WitnessAssertion tmp = ands.getValue().isUnitaryAnd();
                if(tmp == null)
                    or.add(ands.getValue());
                else
                    or.add(tmp);
            }

            return or;
        }
    }


    @Override
    public void variableNormalization_separation(WitnessEnv env) {

        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()){
                assertion.variableNormalization_separation(env);
            }
    }

    @Override
    public WitnessAssertion variableNormalization_expansion(WitnessEnv env) throws WitnessException {
        WitnessAnd and = new WitnessAnd();

        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet())
            for(WitnessAssertion assertion : entry.getValue()){
                if(assertion.getClass() == WitnessVar.class) {
                    WitnessAssertion resolvedAssertion = env.getDefinition((WitnessVar) assertion);

                    if(resolvedAssertion != null)
                        and.add(env.getDefinition((WitnessVar) assertion));
                    else
                        throw new ParseCancellationException("Definition not found: "+assertion.toString());
                }else
                    and.add(assertion.variableNormalization_expansion(env));
            }

        return and;
    }

    //TODO: check
    @Override
    public WitnessAssertion DNF() throws WitnessException {
        WitnessOr or = new WitnessOr();

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

    //ipotizziamo che sia stata già effettuato l'and merging
    public void objectPrepare() throws REException, WitnessException {
        List<WitnessAssertion> CPart = andList.get(WitnessProperty.class); //List<WitnessProperty>
        WitnessPro minMax = new WitnessPro();

        if(andList.get(WitnessPro.class) != null)
            minMax = (WitnessPro) andList.get(WitnessPro.class).get(0);

        if(andList.get(WitnessType.class) != null && !andList.get(WitnessType.class).contains(new WitnessType(GrammarStringDefinitions.TYPE_OBJECT))){ //se non è un gruppo object
            return;
        }


        //complete and splitCPart --> da ottimizzare in una seconda fase
        if(CPart == null) {//vuota
            this.add(new WitnessProperty(ComplexPattern.createFromRegexp(".*"), new WitnessBoolean(true)));
            CPart = andList.get(WitnessProperty.class);
        }
        else { //TODO: chiedere additional properties
            ComplexPattern p = ((WitnessProperty) CPart.get(0)).getKey();
            for (int i = 1; i < CPart.size(); i++)
                p = p.intersect(((WitnessProperty) CPart.get(i)).getKey());

            p = p.complement();
            this.add(new WitnessProperty(p, new WitnessBoolean(true)));

            //splitCPart
            CPart = splitClist(CPart);
            //TODO: sostituire anche nell'and?
        }

        //Combine CPRP
        HashMap<WitnessAssertion, List<WitnessPattReq>> coReqs = new HashMap<>();

        //inizializzo, è necessario??
        for(WitnessAssertion assertion : CPart)
            coReqs.put(assertion, new LinkedList<>());

        LinkedList<Map.Entry<WitnessAssertion, WitnessAssertion>> CoMatrix = new LinkedList<>();

        List<WitnessAssertion> RPart = andList.get(WitnessPattReq.class);
        List<WitnessPattReq> NewRList;

        if(RPart != null) {

            for (WitnessAssertion C_assertion : CPart) {

                NewRList = new LinkedList<>(); //Uso ORP class???

                for (WitnessAssertion R_assertion : RPart) {

                    ComplexPattern pattInt = ((WitnessProperty) C_assertion).getKey().intersect(((WitnessPattReq) R_assertion).getKey());

                    if (pattInt.domainSize() != 0) {

                        WitnessAnd newAnd = new WitnessAnd();
                        newAnd.add(((WitnessProperty) C_assertion).getValue());
                        newAnd.add(((WitnessPattReq) R_assertion).getValue());

                        if (newAnd.notObviouslyEmpty()) {
                            WitnessPattReq newReq = WitnessPattReq.build(pattInt, newAnd);
                            NewRList.add(newReq);
                            coReqs.get(C_assertion).add(newReq);
                        }
                    }
                }

                //Aggiungo un or fatto dagli elementi di NewRList o aggiungo ORP a this???
                WitnessOr ORP = new WitnessOr();
                for(WitnessPattReq req : NewRList)
                    ORP.add(req);

                this.add(ORP);
            }
        }

        //init della coMatrix
        for(WitnessAssertion assertion : CPart){
            List<WitnessPattReq> coList = coReqs.get(assertion);
            for (int i=0; i< coList.size()-1; i++) {
                for (int j = i + 1; j < coList.size(); j++) {
                    CoMatrix.add(new AbstractMap.SimpleEntry<>(coList.get(i), coList.get(j)));
                }
            }
        }

        /*
        for (Prop prop : q.CPart) {
     List<Request> coList = coReqs.get(prop);
     for (int i=0; i< coList.size()-1; i++) {
       for (int j=i+1; j< coList.size(); j++) { coMatrix.add(ri,rj);

     }}};
         */

    }

    public boolean notObviouslyEmpty(){
        return true;
    }

    private List<WitnessAssertion> splitClist(List<WitnessAssertion> list) throws WitnessException {
        return splitClist(list,0);
    }

    /**
     * Splits a CP list
     * it returns a CP list that is equivalent to list but where all different
     * patterns are disjoint
     */
    private List<WitnessAssertion> splitClist(List<WitnessAssertion> list, int index) throws WitnessException {
        if(list.size() == index) return list;
        List<WitnessAssertion> expandedTail = splitClist(list, index+1);
        Map.Entry<WitnessProperty, List<WitnessAssertion>> subHead_reducedExpandedTail = reduceElemWithList((WitnessProperty) list.get(index), expandedTail, index);

        if(subHead_reducedExpandedTail.getKey().getKey().domainSize() != null && subHead_reducedExpandedTail.getKey().getKey().domainSize() == 0)
            return subHead_reducedExpandedTail.getValue();
        else{
            subHead_reducedExpandedTail.getValue().add(subHead_reducedExpandedTail.getKey());
            return subHead_reducedExpandedTail.getValue();
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
    private Map.Entry<WitnessProperty, List<WitnessAssertion>> reduceElemWithList(WitnessProperty prop, List<WitnessAssertion> list, int index) throws WitnessException {
        if(index == list.size()) return new AbstractMap.SimpleEntry<>(prop, new LinkedList<>());

        Map.Entry<WitnessProperty, List<WitnessAssertion>> subProp_reducedTail = reduceElemWithList(prop, list, index+1);

        /* if dom(subProp) is already empty there is not reduction left to perform */
        WitnessProperty head = (WitnessProperty) list.get(index);

        if(subProp_reducedTail.getKey().getKey().domainSize() != null && subProp_reducedTail.getKey().getKey().domainSize() == 0) {
            LinkedList<WitnessAssertion> returnList = new LinkedList<>(subProp_reducedTail.getValue());
            returnList.add(head);
            return new AbstractMap.SimpleEntry<>(subProp_reducedTail.getKey(), returnList);
        }

        /* else, we verify whether the first element of the list intersects prop */
        ComplexPattern intersection = subProp_reducedTail.getKey().getKey().intersect(head.getKey());
        ComplexPattern propMinHead = subProp_reducedTail.getKey().getKey().minus(head.getKey());
        ComplexPattern headMinProp = head.getKey().minus(subProp_reducedTail.getKey().getKey());

        /* if there is not intersection, we can stop here */
        if(intersection.domainSize() != null && intersection.domainSize() == 0){
            LinkedList<WitnessAssertion> returnList = new LinkedList<>(subProp_reducedTail.getValue());
            returnList.add(head);
            return new AbstractMap.SimpleEntry<>(subProp_reducedTail.getKey(), returnList);
        }

        /* otherwise we split the element and we reduce prop */
        WitnessProperty newSubPro =new WitnessProperty(propMinHead, subProp_reducedTail.getKey().getValue()); // prop minus getValue is the reduced prop

        LinkedList<WitnessAssertion> newReducedList  = new LinkedList<>(subProp_reducedTail.getValue());
        WitnessAnd and = new WitnessAnd();
        and.add(subProp_reducedTail.getKey().getValue());
        and.add(head.getValue());
        newReducedList.add(new WitnessProperty(intersection, and)); // the intersection goes in the list

        if(headMinProp.domainSize() == null || headMinProp.domainSize() != 0)
            newReducedList.add(new WitnessProperty(headMinProp, head.getValue())); // right minus left : rightSchema goes in the list


        return new AbstractMap.SimpleEntry<>(newSubPro, newReducedList);
    }

    @Override
    public int hashCode() {
        return andList != null ? andList.hashCode() : 0;
    }

    /*
    @Override
    public int hashCode() {
        int hash = 0;

        for(Map.Entry<Object, List<WitnessAssertion>> entry : andList.entrySet())
            for(WitnessAssertion assertion : entry.getValue())
                hash += assertion.hashCode();

        return hash;
    }
     */

}