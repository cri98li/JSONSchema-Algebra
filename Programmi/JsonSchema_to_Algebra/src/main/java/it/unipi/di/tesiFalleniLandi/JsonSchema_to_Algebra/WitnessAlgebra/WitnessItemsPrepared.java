package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.AlgebraStrings;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.ComplexPattern.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.*;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

import javax.swing.*;
import java.util.*;

/*
List<WitnessAssertion[]> items;
WitnessAssertion[] additionalItems;
Integer  containsLength;
WitnessAssertion[]  contains;
Double[]  min, max;

// invariant: the length of each element of Items and the length of the addditionalItems array is  2^^containsLength
// the length of contains, min, max is containsLength
// at the end of preparation every WitnessAssertion is atomic (variable, true, or false)

// combine: receives two arrays of length 2^^len1 and 2^len2 and returns the product array, that is,
// the one that associates to each bitmap XY  the witness   Arr1[X] And Arr2[Y]
//  len1 and len2 may also be 0
WitnessAssertion[]  multiply (WitnessAssertion[]  Arr1, Integer  len1, WitnessAssertion[]  Arr2, Integer  len2)
     WitnessAssertion[]  result = new WitnessAssertion [2^^(len1+len2)];
    for (i=0; i++; i < 2^^(len1+len2)] {
          bm1 = i / (2^^len) ;           //   shiftRight(i,len2);
          bm2 = i % (2^^len);          //   maskRight(i,len2);
          result(new WitnesAnd().add(Arr1[bm1]).add(Arr2[bm2]);
    }
}
 */

public class WitnessItemsPrepared implements WitnessAssertion{
    private static Logger logger = LogManager.getLogger(WitnessItemsPrepared.class);

    //items#(f1 · · · fn; f | #^M1_m1:ref(x)1, . . . , #^Mk_mk:ref(x)k)

    List<WitnessAssertion[]> items;
    WitnessAssertion[] additionalItems;
    WitnessContains[] contains;

    //private List<Function> functions;
    //private WitnessAssertion additionalItems;
    //private List<WitnessContains> contains;

    private WitnessItemsPrepared(){
        items = new LinkedList<>();
        contains = new WitnessContains[0];
        additionalItems = new WitnessAssertion[0];
    }

    public static WitnessItemsPrepared prepareArrayGroup(WitnessItems item, List<WitnessAssertion> contains, WitnessEnv env) throws REException, WitnessException {
        //Se items == null??

        List<WitnessItemsPrepared> instances = new LinkedList<>();
        WitnessContains contains_true = null;
        WitnessItemsPrepared itemsPrepared = null;
        Double maxOfMin = Double.NEGATIVE_INFINITY;

        if (contains == null) contains = new LinkedList<>();

        for (WitnessAssertion tmp : contains) {
            WitnessContains c = (WitnessContains) tmp;

            //Searching for Max of m in  #_m
            maxOfMin = (maxOfMin < c.getMin()) ? c.getMin() : maxOfMin;

            //Searching for contains_true
            if (c.getContains() instanceof WitnessBoolean && ((WitnessBoolean) c.getContains()).getValue()) {
                if (contains_true != null) //se ce ne sono di più
                    throw new UnsupportedOperationException("Multiple #:true");
                contains_true = c;
                contains.remove(c);

                break;
            }
        }

        if(contains_true == null)
            contains_true = new WitnessContains(0, Double.POSITIVE_INFINITY, new WitnessBoolean(true));

        //Normalizzazione di items
        WitnessItems normalizedItems = item.mergeElement(contains_true);

        //Normalizzazione di contains_true
        contains_true.setMin(maxOfMin);
        if(maxOfMin > contains_true.getMax())
            contains_true.setContains(new WitnessBoolean(false));

        //Normalizzazione degli altri contains (#)
        for (WitnessAssertion tmp : contains) {
            WitnessContains c = (WitnessContains) tmp;
            if(c.getMax() > contains_true.getMax())
                c.setMax(Double.POSITIVE_INFINITY);
        }

        //Trasformazione items in items#
        instances.add(WitnessItemsPrepared.transformToItemsPrepared(normalizedItems));

        //Trasformazione di ogni contains in items#
        for(WitnessAssertion tmp : contains) {
            WitnessContains c = (WitnessContains) tmp;
            instances.add(WitnessItemsPrepared.transformToItemsPrepared(c, env));
        }

        //unione di tuti gli items#
        itemsPrepared = instances.remove(0);
        for(WitnessItemsPrepared i : instances)
            itemsPrepared = merge(itemsPrepared, i);

        //separazione TODO:finire
        itemsPrepared.varNormalization_separation(env, env.variableNamingSystem);

        env.buildOBDD_notElimination();

        return itemsPrepared;
    }

    private static WitnessItemsPrepared transformToItemsPrepared(WitnessItems items){
        WitnessItemsPrepared itemsPrepared = new WitnessItemsPrepared();

        itemsPrepared.items = new LinkedList<>();

        for(WitnessAssertion ass : items.getItems()){
            WitnessAssertion[] witnessAssertionsArray = new WitnessAssertion[1];

            witnessAssertionsArray[0] = ass;

            itemsPrepared.items.add(witnessAssertionsArray);
        }

        return itemsPrepared;
    }

    private static WitnessItemsPrepared transformToItemsPrepared(WitnessContains contains, WitnessEnv env) throws REException {
        WitnessItemsPrepared itemsPrepared = new WitnessItemsPrepared();

        itemsPrepared.additionalItems = new WitnessAssertion[2];
        itemsPrepared.contains = new WitnessContains[1];

        itemsPrepared.additionalItems[0] = contains.getContains().not(env);
        itemsPrepared.additionalItems[1] = contains.getContains();
        itemsPrepared.contains[0] = contains;

        return itemsPrepared;
    }


    private static WitnessItemsPrepared merge(WitnessItemsPrepared a, WitnessItemsPrepared b){
        WitnessItemsPrepared merged = new WitnessItemsPrepared();

        //Special case

        //General case
        //a è sempre il minimo
        if(a.items.size() > b.items.size()){
            WitnessItemsPrepared c = a;
            a=b;
            b=c;
        }

        for(int i = 0; i < a.items.size(); i++)
            merged.items.add(multiply(a.items.get(i), b.items.get(i)));

        for(int i = a.items.size(); i < b.items.size(); i++)
            merged.items.add(multiply(a.additionalItems, b.items.get(i)));

        merged.additionalItems = multiply(a.additionalItems, b.additionalItems);

        //accodiamo i contains
        merged.contains = new WitnessContains[a.contains.length + b.contains.length];
        int count = 0;

        for(int i = 0; i < a.contains.length; i++)
            merged.contains[count++] = a.contains[i];

        for(int i = 0; i < b.contains.length; i++)
            merged.contains[count++] = b.contains[i];

        return merged;
    }

    private static WitnessAssertion[] multiply(WitnessAssertion[] arr1, WitnessAssertion[] arr2){

        //if(arr1==null)

        WitnessAssertion[] result = new WitnessAssertion[2^(arr1.length+arr2.length)];

        for(int i = 0; i < result.length; i++){
            int bm1 =i/(2^ arr2.length);
            int bm2 =i%(2^ arr2.length);

            WitnessAnd and = new WitnessAnd();
            and.add(arr1[bm1]);
            and.add(arr2[bm2]);

            result[i] = and;
        }

        return result;
    }


    @Override
    public String toString() {
        return "ItemsPrepared{\r\n" +
                "items#=" + Arrays.toString(items.toArray()) + "\r\n" +
                ", additionalItems=" + Arrays.toString(additionalItems) +"\r\n" +
                ", contains=" + Arrays.toString(contains) +
                "\r\n}";
    }

    @Override
    public void checkLoopRef(WitnessEnv env, Collection<WitnessVar> varList) throws RuntimeException {

    }

    @Override
    public void reachableRefs(Set<WitnessVar> collectedVar, WitnessEnv env) throws RuntimeException {

    }

    @Override
    public WitnessAssertion mergeWith(WitnessAssertion a, WitnessVarManager varManager, WitnessPattReqManager pattReqManager) throws REException {
        return null;
    }

    @Override
    public WitnessAssertion merge(WitnessVarManager varManager, WitnessPattReqManager pattReqManager) throws REException {
        return null;
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(AlgebraStrings.TYPE_ARRAY);
    }

    @Override
    public Assertion getFullAlgebra() {
        ItemsPrepared_Assertion itemsPrepared_assertion = new ItemsPrepared_Assertion();

        for(WitnessAssertion[] tmpList : items){
            Assertion[] assertionList = new Assertion[tmpList.length];
            for(int i = 0; i<tmpList.length; i++)
                assertionList[i] = tmpList[i].getFullAlgebra();

            itemsPrepared_assertion.addItems(assertionList);
        }

        Assertion[] additionalItemsArray = new Assertion[additionalItems.length];
        for(int i = 0; i<additionalItems.length; i++)
            additionalItemsArray[i] = additionalItems[i].getFullAlgebra();

        itemsPrepared_assertion.addAdditionalItems(additionalItemsArray);

        Exist_Assertion[] containsArray = new Exist_Assertion[contains.length];
        for(int i = 0; i<contains.length; i++)
            containsArray[i] = (Exist_Assertion) contains[i].getFullAlgebra();

        itemsPrepared_assertion.addContains(containsArray);

        return itemsPrepared_assertion;
    }

    @Override
    public WitnessItemsPrepared clone() {
        return null;
    }

    @Override
    public WitnessAssertion not(WitnessEnv env) throws REException {
        throw new UnsupportedOperationException("not on WitnessItemPrepared");
    }

    @Override
    public WitnessAssertion groupize() throws WitnessException, REException {
        return this;
    }

    @Override
    public Float countVarWithoutBDD(WitnessEnv env, List<WitnessVar> visitedVar) {
        return 0f;
    }

    @Override
    public int countVarToBeExp(WitnessEnv env) {
        return 0;
    }

    @Override
    public List<Map.Entry<WitnessVar, WitnessAssertion>> varNormalization_separation(WitnessEnv env, WitnessVarManager varManager) throws WitnessException, REException {
        List<Map.Entry<WitnessVar, WitnessAssertion>> newDefinitions = new LinkedList<>();

        if(items != null){
            for(int i = 0; i < items.size(); i++) {
                for (int j = 0; j < items.get(i).length; j++) {
                    if (items.get(i)[j].getClass() != WitnessBoolean.class && items.get(i)[j].getClass() != WitnessVar.class) {

                        newDefinitions.addAll(items.get(i)[j].varNormalization_separation(env, varManager));

                        WitnessVar newVar = varManager.buildVar(varManager.getName(items.get(i)[j]));
                        newDefinitions.add(new AbstractMap.SimpleEntry<>(newVar, items.get(i)[j]));

                        logger.debug("Nuova variabile "+newVar.getName()+ " sostituita a "+ items.get(i)[j]);

                        items.get(i)[j] = newVar;


                    }
                }
            }
        }

        if(additionalItems != null){
            for(int i = 0; i < additionalItems.length; i++) {
                if (additionalItems[i].getClass() != WitnessBoolean.class && additionalItems[i].getClass() != WitnessVar.class) {

                    newDefinitions.addAll(additionalItems[i].varNormalization_separation(env, varManager));

                    WitnessVar newVar = varManager.buildVar(varManager.getName(additionalItems[i]));
                    newDefinitions.add(new AbstractMap.SimpleEntry<>(newVar, additionalItems[i]));

                    logger.debug("Nuova variabile "+newVar.getName()+ " sostituita a "+ additionalItems[i]);

                    additionalItems[i] = newVar;
                }
            }
        }

        return newDefinitions;
    }

    @Override
    public WitnessAssertion varNormalization_expansion(WitnessEnv env) throws WitnessException {
        throw new UnsupportedOperationException("tbd");
    }

    @Override
    public WitnessAssertion DNF() throws WitnessException {
        throw new UnsupportedOperationException("tbd");
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
        throw new UnsupportedOperationException("tbd");
    }

    @Override
    public WitnessVar buildOBDD(WitnessEnv env, WitnessVarManager varManager) throws WitnessException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void getReport(ReportResults reportResults) {
        for(WitnessContains c : contains)
            c.getReport(reportResults);
    }
}

