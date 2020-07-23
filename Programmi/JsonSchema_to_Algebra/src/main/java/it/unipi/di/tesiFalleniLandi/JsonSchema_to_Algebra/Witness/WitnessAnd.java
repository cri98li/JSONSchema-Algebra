package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.UnsenseAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.AllOf_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Boolean_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Not_Assertion;
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

        //caso aggiunta di un tipo diverso
        if(el.getClass() == WitnessType.class){
            if(andList.get(WitnessType.class) == null){
                List<WitnessAssertion> list = new LinkedList<>();
                list.add(el);
                andList.put(el.getClass(), list);
                return true;
            }
            List<WitnessAssertion> typeList = andList.remove(WitnessType.class);
            WitnessAssertion merged = ((WitnessType) el).mergeElement((WitnessType) typeList.get(0));
            if(merged.getClass() == WitnessBoolean.class) throw new UnsenseAssertion("add di 2 and incompatibili");
            else{
                List<WitnessAssertion> list = new LinkedList<>();
                list.add(merged);
                andList.put(el.getClass(), list);
                return true;
            }
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

    /**
     * INPUT: lista di asserzioni in and
     * OUPUT: lista in and contenente solo istanze di WitnessGroup, WitnessOr e WitnessVar
     */
    @Override
    public WitnessAssertion groupize() throws WitnessException, REException {
        //caso base: se and vuoto --> and[true] --> ritorno true
        if(andList.size() == 0) return new WitnessBoolean(true);

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

            and = new WitnessAnd();
            type = new WitnessType(GrammarStringDefinitions.TYPE_OBJECT);
            and.add(type);
            groups.put(type, and);

            and = new WitnessAnd();
            type = new WitnessType(GrammarStringDefinitions.TYPE_ARRAY);
            and.add(type);
            groups.put(type, and);

            and = new WitnessAnd();
            type = new WitnessType(GrammarStringDefinitions.TYPE_STRING);
            and.add(type);
            groups.put(type, and);

            and = new WitnessAnd();
            type = new WitnessType(GrammarStringDefinitions.TYPE_BOOLEAN);
            and.add(type);
            groups.put(type, and);

            and = new WitnessAnd();
            type = new WitnessType(GrammarStringDefinitions.TYPE_NULL);
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
    public void objectPrepare(WitnessEnv env) throws REException, WitnessException {
        List<WitnessAssertion> CPart = andList.get(WitnessProperty.class); //List<WitnessProperty>
        WitnessPro minMax = new WitnessPro();

        if(andList.get(WitnessPro.class) != null)
            minMax = (WitnessPro) andList.get(WitnessPro.class).get(0);

        if(andList.get(WitnessType.class) == null)
            return; //TODO: //throw new ParseCancellationException("and senza tipo");

        if(andList.get(WitnessType.class) != null && !andList.get(WitnessType.class).contains(new WitnessType(GrammarStringDefinitions.TYPE_OBJECT))){ //se non è un gruppo object
            return;
        }

        if(andList.get(WitnessType.class).size() > 1)
            throw new ParseCancellationException("and non gruppizzato");


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
            //CPart = splitClist(CPart);
            //TODO: sostituire anche nell'and?
        }

        //Combine CPRP
        HashMap<WitnessAssertion, List<WitnessPattReq>> coReqs = new HashMap<>();

        //inizializzo, è necessario??
        for(WitnessAssertion assertion : CPart)
            coReqs.put(assertion, new LinkedList<>());

        LinkedList<Map.Entry<WitnessPattReq, WitnessPattReq>> CoMatrix = new LinkedList<>();

        List<WitnessAssertion> RPart = andList.remove(WitnessPattReq.class);
        List<WitnessPattReq> NewRList;

        if(RPart != null) {

            for (WitnessAssertion C_assertion : CPart) {

                NewRList = new LinkedList<>(); //Uso ORP class???

                for (WitnessAssertion R_assertion : RPart) {

                    ComplexPattern pattInt = ((WitnessProperty) C_assertion).getKey().intersect(((WitnessPattReq) R_assertion).getKey());

                    if (pattInt.domainSize() == null || pattInt.domainSize() != 0) { //isNotEmpty

                        WitnessAnd newAnd = new WitnessAnd();
                        newAnd.add(((WitnessProperty) C_assertion).getValue());
                        newAnd.add(((WitnessPattReq) R_assertion).getValue());

                        if (newAnd.notObviouslyEmpty()) {
                            WitnessPattReq newReq = WitnessPattReq.build(pattInt, newAnd.isUnitaryAnd() != null ? newAnd.isUnitaryAnd() : newAnd );
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
            for (int i=0; i < coList.size()-1; i++) {
                for (int j = i + 1; j < coList.size(); j++) {
                    CoMatrix.add(new AbstractMap.SimpleEntry<>(coList.get(i), coList.get(j)));
                }
            }
        }

        //variable saparation
        this.variableNormalization_separation(env);

        //Reduce compatible pairs
        reduceCompatiblePairs(CoMatrix, null);
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
        newReducedList.add(new WitnessProperty(intersection, and.isUnitaryAnd() != null ? and.isUnitaryAnd() : and)); // the intersection goes in the list

        if(headMinProp.domainSize() == null || headMinProp.domainSize() != 0)
            newReducedList.add(new WitnessProperty(headMinProp, head.getValue())); // right minus left : rightSchema goes in the list


        return new AbstractMap.SimpleEntry<>(newSubPro, newReducedList);
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

    private void reduceCompatiblePairs(LinkedList<Map.Entry<WitnessPattReq, WitnessPattReq>> coMatrix, Assertion q) throws REException {
        while(!coMatrix.isEmpty()){
            WitnessPattReq left = coMatrix.getFirst().getKey();
            WitnessPattReq right = coMatrix.getFirst().getValue();
            coMatrix.removeFirst();

            ComplexPattern intersection = left.getKey().intersect(right.getKey());
            ComplexPattern leftMinRight = left.getKey().minus(right.getKey());
            ComplexPattern rightMinLeft = right.getKey().minus(left.getKey());

            if(intersection.domainSize() != null && intersection.domainSize() == 0) continue;

            WitnessAnd leftMinRightSchema = new WitnessAnd();
            leftMinRightSchema.add(left.getValue());
            leftMinRightSchema.add(new Not_Assertion(right.getValue().getFullAlgebra()).toWitnessAlgebra());

            WitnessAnd rightMinLeftSchema = new WitnessAnd();
            leftMinRightSchema.add(right.getValue());
            leftMinRightSchema.add(new Not_Assertion(left.getValue().getFullAlgebra()).toWitnessAlgebra());

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
                leftFragments.add(WitnessPattReq.build(intersection, leftMinRightSchema.isUnitaryAnd() != null ? leftMinRightSchema.isUnitaryAnd() : leftMinRightSchema));
            }
            if(leftMinRight.domainSize() != null && leftMinRight.domainSize() != 0){
                leftFragments.add(WitnessPattReq.build(leftMinRight, left.getValue()));
            }
            if(rightMinLeftSchema.notObviouslyEmpty()){
                rightFragments.add(WitnessPattReq.build(intersection, rightMinLeftSchema.isUnitaryAnd() != null ? rightMinLeftSchema.isUnitaryAnd() : rightMinLeftSchema));
            }
            if(rightMinLeft.domainSize() != null && rightMinLeft.domainSize() != 0){
                rightFragments.add(WitnessPattReq.build(rightMinLeft, right.getValue()));
            }

           //substitute
            for(int i=0; i < coMatrix.size(); i++){
                if(coMatrix.get(i).getKey().equals(left) || coMatrix.get(i).getValue().equals(left)) {
                    coMatrix.remove(i);
                    for(WitnessPattReq tmp : leftFragments)
                        coMatrix.add(new AbstractMap.SimpleEntry<>(left, tmp));
                }
                if(coMatrix.get(i).getKey().equals(right) || coMatrix.get(i).getValue().equals(right)) {
                    coMatrix.remove(i);
                    for(WitnessPattReq tmp : rightFragments)
                        coMatrix.add(new AbstractMap.SimpleEntry<>(right, tmp));
                }
            }

            List<WitnessAssertion> RList = andList.get(WitnessOr.class);
            for(WitnessAssertion RListEl : RList) {
                ((WitnessOr)RListEl).remove(left);
                ((WitnessOr)RListEl).remove(right);
            }

            leftFragments.addAll(rightFragments);

            WitnessOr orTmp = new WitnessOr();
            for(WitnessPattReq tmp : leftFragments)
                orTmp.add(tmp);

            this.add(orTmp);

        }
    }


    /*
     * void reduceCompatiblePairs(CoMatrix coMatrix, QuadObject q) {
     *
     *while(coMatrix.isNotempty())

    {
     *Pair left = coMatrix.getValue().left;
     *Pair right = coMatrix.getValue().right;
     *coMatrix.removePair(coMatrix.getValue());
     *Triple( int,leftMinRight, rightMinLeft) =decompose(left.pattern,
            * right.pattern);
     *if (int.isEmpty) {
        continue;
    }
     *Assertion leftMinRightSchema = new And(left.schema, new Not(right.schema));
     *Assertion rightMinLeftSchema = new And(right.schema, new Not(left.schema));
     *Assertion intSchema = new And(left.schema, right.schema);
     *List<Request> leftFragments = new List<>();
     *List<Request> rightFragments = new List<>();
     *if (intSchema.notObviouslyEmpty()) {
     *Request Req = new Request( int,intSchema);
     *leftFragments.add(Req);
     *rightFragments.add(Req);
     *}
     *if (leftMinRightSchema.isNotObviouslyEmpty()) {
     *leftFragments.add(new Request( int,leftMinRightSchema));
     *}
     *if (leftMinRight.isNotEmpty()) {
     *leftFragments.add(new Request(leftMinRight, left.schema));
     *}
     *if (rightMinLeftSchema.isNotObviouslyEmpty()) {
     *rightFragments.add(new Request( int,rightMinLeftSchema));
     *}
     *if (rightMinLeft.isNotEmpty()) {
     *rightFragments.add(new Request(rightMinLeft, right.schema));
     *}
     *for (lOrp:
           left.orpList) {
     *for (lF:
           leftFragments) {
            lOrp.connect(lF);
        }
     *lOrp.deConnect(left);  // beware: we are looping on lft.orpList
     *                              // and modifying the list at the same time
     *}
     *for (rOrp:
           right.orpList) {
     *for (rF:
           rightFragments) {
            rOrp.connect(rF);
        }
     *rOrp.deConnect(right);
     *}
     *coMatrix.substitute(left, leftFragments);
     *coMatrix.substitute(right, rightFragments);
     *q.RList.remove(left).remove(right);
     *leftFragments.addAll(rightFragments); // we use set union since the intersection
     *q.RList.add(leftFragments);           // fragment appears in both leftF and rightF
     *}
     *
}
     * @return
     */






    @Override
    public int hashCode() {
        return andList != null ? andList.hashCode() : 0;
    }

}