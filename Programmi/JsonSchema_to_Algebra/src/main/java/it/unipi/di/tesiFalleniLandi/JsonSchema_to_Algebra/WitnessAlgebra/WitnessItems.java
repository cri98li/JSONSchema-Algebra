package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.AlgebraStrings;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Items_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

import java.util.*;

public class WitnessItems implements WitnessAssertion{
    private static Logger logger = LogManager.getLogger(WitnessItems.class);

    private List<WitnessAssertion> items;
    private WitnessAssertion additionalItems;
    private boolean blocked;

    public WitnessItems() {
        blocked = false;
        items = new LinkedList<>();
        logger.trace("Creating an empty WitnessItems");
    }

    public void setAdditionalItems(WitnessAssertion addItem){
        logger.trace("Setting {} as AdditionalItems in {}", addItem, this);
        if(!blocked) {
            if(addItem.getClass() == WitnessAnd.class && ((WitnessAnd) addItem).getIfUnitaryAnd() != null)
                addItem = ((WitnessAnd) addItem).getIfUnitaryAnd();
            additionalItems = addItem;
        }
        else
            logger.warn("Items is blocked --> no action ");
    }

    public void addItems(WitnessAssertion item){
        logger.trace("Adding {} into Items in {}", item, this);
        try{
            if(!((WitnessBoolean)item).getValue()) {
                blocked = true;
                additionalItems = item;
                logger.warn("Setting blocked=true because toAdd={}", item);
                return;
            }
        }catch (ClassCastException e) { }

        if (!blocked) {
            if (item.getClass() == WitnessAnd.class && ((WitnessAnd) item).getIfUnitaryAnd() != null)
                item = ((WitnessAnd) item).getIfUnitaryAnd();
            items.add(item);
        }else
            logger.warn("Items is blocked --> no action ");
    }

    @Override
    public String toString() {
        return "WitnessItems{" +
                "items=" + items +
                ", additionalItems=" + additionalItems +
                '}';
    }

    @Override
    public void checkLoopRef(WitnessEnv env, Collection<WitnessVar> varList) throws RuntimeException {
        return;
    }

    @Override
    public void reachableRefs(Set<WitnessVar> collectedVar, WitnessEnv env) throws RuntimeException {
        for(WitnessAssertion item : items)
            item.reachableRefs(collectedVar, env);

        if(additionalItems != null)
            additionalItems.reachableRefs(collectedVar, env);
    }

    @Override
    public WitnessAssertion mergeWith(WitnessAssertion a) throws REException {
        WitnessAssertion result = null;

        if(items.size() == 0 && additionalItems.getClass() == WitnessBoolean.class)
            if(((WitnessBoolean)additionalItems).getValue())
                result = additionalItems;
            else {
                WitnessAnd and = new WitnessAnd();
                and.add(a);
                and.add(new WitnessContains(0l, 0l, new WitnessBoolean(true)));
                result = and;
            }
        else {
            for (int i = items.size() - 1; i >= 0; i--)
                if (additionalItems != null && additionalItems.equals(items.get(i)))
                    items.remove(i);
                else
                    break;

            if (a.getClass() == this.getClass()) result = this.mergeElement((WitnessItems) a);

        }

        logger.warn("Merge result: {}", result);
        return result;
    }

    @Override
    public WitnessAssertion merge() throws REException {
        WitnessItems newWitnessItems = new WitnessItems();
        for(WitnessAssertion item : items)
            newWitnessItems.addItems(item.merge());

        if(additionalItems != null)
            newWitnessItems.setAdditionalItems(additionalItems.merge());

        newWitnessItems.blocked = blocked;

        return newWitnessItems;
    }

    public WitnessAssertion mergeElement(WitnessItems a) throws REException{
        logger.trace("Merging {} with {}", a, this);

        //items[;bool]
        if (a.items.size() == 0 && a.additionalItems.getClass() == WitnessBoolean.class)
            if (((WitnessBoolean) a.additionalItems).getValue() == true) {
                logger.trace("Merge result: {}", a.additionalItems);
                return a.additionalItems; //return true: this items is always true
            }else {
                WitnessAnd and = new WitnessAnd();

                and.add(a);
                and.add(new WitnessContains(0l, 0l, new WitnessBoolean(true)));


                logger.trace("Merge result: {}", and);
                return and; //it is true iff array size is empty
            }

        for (int i = items.size() - 1; i >= 0; i--)
            if (additionalItems != null && additionalItems.equals(items.get(i)))
                items.remove(i);
            else if (items.get(i).getClass() == WitnessBoolean.class && ((WitnessBoolean) items.get(i)).getValue() == false) {
                for (int j = items.size() - 1; j >= i; j--)
                    items.remove(j);

                additionalItems = new WitnessBoolean(false);
            }


        WitnessItems ite = new WitnessItems();

        ite.additionalItems = a.additionalItems;
        if (a.additionalItems == null) ite.additionalItems = additionalItems;
        if (a.additionalItems != null && this.additionalItems != null)
            ite.additionalItems = a.additionalItems.clone().mergeWith(this.additionalItems);

        int i;
        int min = (a.items.size() < items.size()) ? a.items.size() : items.size();
        for (i = 0; i < min; i++) {
            WitnessAssertion tmp = items.get(i).mergeWith(a.items.get(i));
            if(tmp != null)
                ite.addItems(tmp);
            else{
                WitnessAnd and = new WitnessAnd();
                and.add(items.get(i));
                and.add(a.items.get(i));
                ite.addItems(and);
            }
        }



        for(; i < a.items.size(); i++) {
            WitnessAssertion tmp = a.items.get(i).mergeWith(this.additionalItems);
            if(tmp != null)
                ite.addItems(tmp);
            else
                ite.addItems(a.items.get(i));
        }


        for(; i < items.size(); i++) {
            WitnessAssertion tmp = items.get(i).mergeWith(a.additionalItems);
            if(tmp != null)
                ite.addItems(tmp);
            else
                ite.addItems(items.get(i));
        }


        logger.trace("Merge result: {}", ite);
        return ite;
    }

    @Override
    public WitnessType getGroupType() {
        return new WitnessType(AlgebraStrings.TYPE_ARRAY);
    }

    @Override
    public Assertion getFullAlgebra() {
        Items_Assertion items = new Items_Assertion();

        for(WitnessAssertion a : this.items)
            items.add(a.getFullAlgebra());

        if(additionalItems != null)
            items.setAdditionalItems(additionalItems.getFullAlgebra());

        return items;
    }

    @Override
    public WitnessAssertion clone() {
        logger.trace("Cloning WitnessItems: {}", this);
        WitnessItems clone = new WitnessItems();
        for(WitnessAssertion assertion : items)
            clone.items.add(assertion.clone());

        clone.blocked = blocked;

        if(additionalItems != null)
            clone.additionalItems = additionalItems.clone();

        return clone;
    }

    @Override
    public WitnessAssertion not(WitnessEnv env) throws REException {
        //only additionaItems
        if(additionalItems != null && items == null) {
            addItems(additionalItems);
            additionalItems = null;
        }

        WitnessAnd rootAnd = new WitnessAnd();
        WitnessOr rootOr = new WitnessOr();
        WitnessType typeArray = new WitnessType();
        typeArray.add("arr");
        rootAnd.add(typeArray);
        rootAnd.add(rootOr);

        //ITEMS
        for(int i = 0; i < items.size(); i++) {
            WitnessAnd itemAndAssertion = new WitnessAnd();
            WitnessItems itemAssertion = new WitnessItems();
            itemAndAssertion.add(itemAssertion);

            itemAndAssertion.add(new WitnessContains(i+1., Double.POSITIVE_INFINITY, new WitnessBoolean(true)));
            rootOr.add(itemAndAssertion);

            for(int j = 0; j < items.size(); j++)
                itemAssertion.addItems((i == j && items.get(i).not(env) != null) ? items.get(i).not(env): new WitnessBoolean(true));
        }

        if(additionalItems == null) return rootAnd;

        //ADDITIONAL ITEMS
        Boolean[] bm = new Boolean[items.size()];
        Arrays.fill(bm, false);
        WitnessAssertion notAdditionalItems = additionalItems.not(env);

        do {
            WitnessAnd andAdditionalItems = new WitnessAnd();
            rootOr.add(andAdditionalItems);
            andAdditionalItems.add(new WitnessContains((long) sumbit(bm) + 1, null, notAdditionalItems));
            WitnessItems itemsAdditionalItems = new WitnessItems();
            andAdditionalItems.add(itemsAdditionalItems);

            for(int i = 0; i < bm.length; i++)
                itemsAdditionalItems.addItems( (bm[i] == false) ? additionalItems : notAdditionalItems );

        }while(addbit(bm, 0));


        return rootAnd;
    }

    private Boolean addbit(Boolean[] bm, int position) {
        if(bm.length == position) return false;

        if(!bm[position]) {
            bm[position] = true;
            return true;
        }

        bm[position] = false;
        return addbit(bm, position+1);
    }

    private int sumbit(Boolean[] bm) {
        int count = 0;

        for(int i = 0; i < bm.length; i++)
            if(bm[i]) count++;

        return count;
    }

    @Override
    public WitnessAssertion groupize() throws WitnessException, REException {
        WitnessItems items = new WitnessItems();

        if(additionalItems != null)
            if(additionalItems.getClass() != WitnessAnd.class){
                WitnessAnd and = new WitnessAnd();
                and.add(additionalItems);
                items.additionalItems = and.groupize();
            }else
                items.additionalItems = additionalItems.groupize();

        for(WitnessAssertion assertion : this.items)
                if(assertion.getClass() != WitnessAnd.class){
                    WitnessAnd and = new WitnessAnd();
                    and.add(assertion);
                    items.addItems(and.groupize());
                }else
                    items.addItems(assertion.groupize());

        return items;
    }

    @Override
    public Float countVarWithoutBDD(WitnessEnv env, List<WitnessVar> visitedVar) {
        Float count = 0f;
        if(items != null){
            for(int i = 0; i < items.size(); i++){
                count += items.get(i).countVarWithoutBDD(env, visitedVar);
            }
        }

        if(additionalItems != null)
            count += additionalItems.countVarWithoutBDD(env, visitedVar);

        return count;
    }

    @Override
    public int countVarToBeExp(WitnessEnv env) {
        return 0;
    }

    @Override
    public List<Map.Entry<WitnessVar, WitnessAssertion>> varNormalization_separation(WitnessEnv env) throws WitnessException, REException {
        List<Map.Entry<WitnessVar, WitnessAssertion>> newDefinitions = new LinkedList<>();

        if(items != null){
            for(int i = 0; i < items.size(); i++){
                if(items.get(i).getClass() != WitnessBoolean.class && items.get(i).getClass() != WitnessVar.class) {
                    newDefinitions.addAll(items.get(i).varNormalization_separation(env));

                    WitnessVar newVar = new WitnessVar(Utils_WitnessAlgebra.getName(items.get(i)));
                    newDefinitions.add(new AbstractMap.SimpleEntry<>(newVar, items.get(i)));
                    items.set(i, newVar);
                }
            }
        }

        if(additionalItems != null){
            if(additionalItems.getClass() != WitnessBoolean.class && additionalItems.getClass() != WitnessVar.class) {
                newDefinitions.addAll(additionalItems.varNormalization_separation(env));

                WitnessVar newVar = new WitnessVar(Utils_WitnessAlgebra.getName(additionalItems));
                newDefinitions.add(new AbstractMap.SimpleEntry<>(newVar, additionalItems));
                additionalItems = newVar;
            }
        }

        return newDefinitions;
    }

    @Override
    public WitnessAssertion varNormalization_expansion(WitnessEnv env) {
        return this;
    }

    @Override
    public WitnessAssertion DNF() throws WitnessException {
        WitnessItems newItems = new WitnessItems();

        if(items != null){
            for(WitnessAssertion assertion : items)
                newItems.addItems(assertion.DNF());
        }

        if(additionalItems != null)
            newItems.setAdditionalItems(additionalItems.DNF());

        return newItems;
    }

    @Override
    public WitnessAssertion toOrPattReq() {
        for(int i = 0; i < items.size(); i++)
            items.set(i, items.get(i).toOrPattReq());

        return this;
    }

    @Override
    public boolean isBooleanExp() {
        return false;
    }

    @Override
    public boolean isRecursive(WitnessEnv env, LinkedList<WitnessVar> visitedVar) {
        if(items != null){
            for(int i = 0; i < items.size(); i++){
                if(items.get(i).isRecursive(env, visitedVar))
                    return true;
            }
        }

        if(additionalItems != null && additionalItems.isRecursive(env, visitedVar))
            return true;

        return false;
    }

    @Override
    public WitnessVar buildOBDD(WitnessEnv env) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessItems that = (WitnessItems) o;

        if (blocked != that.blocked) return false;

        if(!Objects.equals(that.additionalItems, additionalItems)) return false;

        if(that.items.size() != items.size()) return false;

        List<WitnessAssertion> check = new LinkedList<>();
        if(this.items.size() >= that.items.size()) {
            check.addAll(this.items);
            check.removeAll(that.items);
        }else{
            check.addAll(that.items);
            check.removeAll(this.items);
        }

        return check.size() == 0;
    }

    @Override
    public int hashCode() {
        int result = items != null ? items.hashCode() : 0;
        result = 31 * result + (blocked ? 1 : 0);
        result = 31 * result + (additionalItems != null ? additionalItems.hashCode() : 0);
        return result;
    }
}