package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.AlgebraStrings;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Defs_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

import java.util.*;

public class WitnessEnv implements WitnessAssertion {
    private static Logger logger = LogManager.getLogger(WitnessEnv.class);

    private HashMap<WitnessVar, WitnessAssertion> varList; //map of definitions <variable, value> (included root and not_root)
    private BiMap<WitnessVar, WitnessVar> coVar; //maintain the relation between the variables and the complement variables
    private WitnessVar rootVar; //indicate the root variable
    protected WitnessBDD bdd;
    protected WitnessVarManager variableNamingSystem;

    private LinkedList<WitnessVar> varToBeElaborated; // notElimination + buildOBDD

    private WitnessEnv(){
        varList = new HashMap<>();
        coVar = HashBiMap.create();
        varToBeElaborated = new LinkedList<>();
    }

    public WitnessEnv(WitnessVarManager manager){
        varList = new HashMap<>();
        coVar = HashBiMap.create(); //init BiMap
        varToBeElaborated = new LinkedList<>();
        variableNamingSystem = manager;
        bdd = new WitnessBDD(variableNamingSystem);
        varList.put(bdd.getFalseVar(), new WitnessBoolean(false));
        varList.put(bdd.getTrueVar(), new WitnessBoolean(true));
        coVar.put(bdd.getFalseVar(), bdd.getTrueVar());

        logger.trace("Creating an empty WitnessEnv");
    }

    public Map.Entry<WitnessVar, WitnessVar> addWithComplement(WitnessAssertion value) {
        if(value.getClass() == WitnessAnd.class && ((WitnessAnd) value).getIfUnitaryAnd() != null)
            value = ((WitnessAnd) value).getIfUnitaryAnd();

        logger.trace("Adding with complement {}", value);

        WitnessVar var = variableNamingSystem.buildVar(variableNamingSystem.getName(value));
        add(var, value);

        buildOBDD_notElimination(); //complete the variable and build obdd

        logger.trace("Added {} and its complement {}", var, getCoVar(var));

        return new AbstractMap.SimpleEntry<>(var, getCoVar(var));
    }

    /**
     *
     * @param key
     * @param value
     * @return
     */
    public void add(WitnessVar key, WitnessAssertion value){
        logger.trace("Adding to env <{}, {}>", key, value);
        if(WitnessAnd.class == value.getClass() && ((WitnessAnd)value).getIfUnitaryAnd() != null)
            add(key, ((WitnessAnd) value).getIfUnitaryAnd());
        else {
            varList.put(key, value);
            varToBeElaborated.add(key); //TODO: variabili da elaborare è qui!! attenzione
        }
    }

    public void buildOBDD_notElimination(){
        /*buildOBDD();
        notElimination();*/

        try {
            notElimination_dumb();
        } catch (WitnessException | REException e) {
            e.printStackTrace();
        }
        buildOBDD();
    }

    /**
     * Associate to each variable one obdd (also trivial).
     * There are two cases of variable:
     *      - variables that are not boolean expression of variables.
     *          In this case we create a trivial obbd
     *
     *      - variables that are boolean espression
     *          in this case we put all those variables in a list.
     *          Then we sort the list (asc, order by the number of variable in the body without obdd) and then create the associated obbd.
     *          if the obbd cannot be created due to a variable that has not yet been associated to an obbd,
     *          then we move the element in the tail of the list and proceed with the rest.
     */

    //TODO: to remove iterazioniSenzaDiminuzione we can count the var without repetitions and run checkForloopRef()???
    private void buildOBDD() {
        logger.trace("Building obdd");

        LinkedList<Map.Entry<WitnessVar, Float>> booleanExpressions = new LinkedList<>(); // list of pair <variable, number of variable without BDD associated>

        //dumb
        for (Map.Entry<WitnessVar, WitnessAssertion> var : varList.entrySet()) {
            //the variable has already a BDD associated
            if (bdd.contains(var.getKey())){
                continue;
            }

            //the variable is not a boolean expression --> we build a trivial bdd
            if (!varList.get(var.getKey()).isBooleanExp()) {
                if(!bdd.contains(var.getKey()))
                    bdd.createVar(var.getKey());
            } else {
                booleanExpressions.add(new AbstractMap.SimpleEntry(var.getKey(), varList.get(var.getKey()).countVarWithoutBDD(this, new LinkedList<>())));
            }
        }


        /*for (WitnessVar var : varToBeElaborated) {
            //the variable has already a BDD associated
            if (bdd.contains(var)){
                logger.warn("buildOBDD: that is strange...");
                continue;
            }

            //the variable is not a boolean expression --> we build a trivial bdd
            if (!varList.get(var).isBooleanExp()) {
                if(!bdd.contains(var))
                    bdd.createVar(var);
            } else {
                booleanExpressions.add(new AbstractMap.SimpleEntry(var, varList.get(var).countVarWithoutBDD(this, new LinkedList<>())));
            }
        }*/

        //booleanExpressions list sorted by number of variable without BDD (asc)
        Collections.sort(booleanExpressions, (witnessVarIntegerEntry, t1) -> (int) (witnessVarIntegerEntry.getValue() - t1.getValue()));

        //to remove easily the element from booleanExpressions list we copy only the variable names in a tmp list
        LinkedList<WitnessVar> booleanExpressionsTemp = new LinkedList<>();
        for(Map.Entry<WitnessVar, Float> entry : booleanExpressions)
            booleanExpressionsTemp.add(entry.getKey());

        int iterazioniSenzaDiminuzione = 0;

        while (booleanExpressionsTemp.size() != 0) {
            logger.trace("booleanExpression.size = {}; itWithoutDec= {}", booleanExpressions.size(), iterazioniSenzaDiminuzione);

            //no BDD can be created if the current variable is not forced
            //it should happen when we have an un-guarded loop between variable
            // def "x" = ref("y"),
            // def "y" = ref("x")
            if (iterazioniSenzaDiminuzione == booleanExpressions.size()) {
                // (forcing the current??)
                Map.Entry<WitnessVar, Float> first = booleanExpressions.removeFirst();
                first.getKey().forceOBDD(this, variableNamingSystem);
                booleanExpressions.addLast(first);
                iterazioniSenzaDiminuzione = 0;
                logger.warn("Forcing new obdd {}, that should not have happened", first.getKey());
                continue;
            }

            WitnessVar first = booleanExpressionsTemp.removeFirst();

            try {
                WitnessVar obddName = varList.get(first).buildOBDD(this, variableNamingSystem);

                //the variable already exists -- > it can be renamed
                if(varList.containsKey(obddName)){
                    //varToBeElaborated.remove(first.getKey());
                    logger.trace("Renaming variable (already exists with the same semantics) from {} to {}", first, obddName);

                    varList.remove(first);

                    WitnessVar complName = null;
                    if(coVar.containsKey(first) )
                        complName = coVar.remove(first); //QUI
                    else
                        complName = coVar.inverse().remove(first); //QUI

                    bdd.rename(first, obddName);
                    variableNamingSystem.rename(first, obddName);

                    //same for the complement
                    varList.remove(complName);
                    bdd.rename(complName, getCoVar(obddName));
                    booleanExpressionsTemp.remove(complName);
                    booleanExpressionsTemp.remove(getCoVar(obddName));
                    variableNamingSystem.rename(complName, getCoVar(obddName));


                }else {
                    bdd.rename(obddName, first);
                    logger.trace("Renaming the string associated to obdd with the variable name. from {} to {}", obddName, first);
                }

                iterazioniSenzaDiminuzione = 0;
            } catch (WitnessException ex) {
                //trying to create obbd of a boolean expression that contains variable with no obbd associated
                logger.catching(ex);
                booleanExpressionsTemp.addLast(first);
                logger.trace("ref to a var without obdd in {}", first);
                iterazioniSenzaDiminuzione++;
            }
        }
    }


    public WitnessAssertion getDefinition(WitnessVar var){
        if(!varList.containsKey(var))
            throw new RuntimeException("Schema with definition-reference not completed: "+var.getName()+". that should not have happened");
        return varList.get(var);
    }

    public WitnessVar getCoVar(WitnessVar name){
        if(!varToBeElaborated.isEmpty()){
            buildOBDD_notElimination();
        }

        if(coVar.containsKey(name)){
            return coVar.get(name);
        }else if(coVar.inverse().containsKey(name)){
            return coVar.inverse().get(name);
        }else {
            throw new RuntimeException("var not found in coVar: " + name);
        }
    }

    public void setRootVar(String key, WitnessAssertion value) {
        WitnessVar var = variableNamingSystem.buildVar(key);
        add(var, value);
        rootVar = var;
    }

    public boolean containsVar(WitnessVar var){
        return varList.containsKey(var);
    }

    @Override
    public String toString() {
        return "WitnessEnv{" +
                "var=" + varList +
                ", rootVar=" + rootVar +
                '}';
    }

    @Override
    public void checkLoopRef(WitnessEnv env, Collection<WitnessVar> varList) throws RuntimeException {
        for(Map.Entry<WitnessVar, WitnessAssertion> entry : this.varList.entrySet()) {
            varList = new HashSet<>();
            varList.add(entry.getKey());
            entry.getValue().checkLoopRef(this, varList);
        }
    }

    @Override
    public void reachableRefs(Set<WitnessVar> collectedVar, WitnessEnv env) throws RuntimeException {
        Set<WitnessVar> visitedVar = new HashSet<>();
        for(Map.Entry<WitnessVar, WitnessAssertion> var : varList.entrySet())
            if(!visitedVar.contains(var.getKey())) //We skip all the variable that we already visit
                var.getValue().reachableRefs(visitedVar, this);
    }

    private void notElimination_dumb() throws WitnessException, REException {
        if(varToBeElaborated.isEmpty()) return; //Nothing to do

        List<WitnessVar> tmp = new LinkedList<>();

        for(WitnessVar var : varToBeElaborated){
            String complementName = AlgebraStrings.NOT_DEFS + var.getName();

            if (var.getName().startsWith(AlgebraStrings.NOT_DEFS))
                complementName = var.getName().substring(AlgebraStrings.NOT_DEFS.length());

            WitnessVar complementVarName = variableNamingSystem.buildVar(complementName);

            coVar.put(var, complementVarName);
            tmp.add(complementVarName);
        }

        varToBeElaborated = new LinkedList<>();

        for(WitnessVar complVar : tmp){
            WitnessAssertion value = varList.get(coVar.inverse().get(complVar)).not(this);
            varList.put(complVar, value);
        }
    }

    /**
     * Complete each variable with his negation and update the coVar map
     * @throws WitnessException
     * @throws REException
     */
    private void notElimination() {
        if(varToBeElaborated.isEmpty()) return; //Nothing to do

        logger.trace("Performing notElimination");

        HashSet<WitnessVar> completed = new HashSet<>(); //set of completed variable
        LinkedList<Map.Entry<WitnessVar, WitnessAssertion>> newVar = new LinkedList<>(); //to avoid concurrentModificationException
        LinkedList<WitnessVar> tempVariables = new LinkedList<>();

        int iterazioniSenzaDiminuzione = 0;
        while (varToBeElaborated.size() != 0){
            logger.trace("notElimination: list.size = {}; itWithoutDec= {}", varToBeElaborated.size(), iterazioniSenzaDiminuzione);
            /**
             * example:    ****
             * def "x" = props["a": ref("y");],
             * def "y" = ref("x")
             *
             * create a "temp" var x <--> not_x without value.
             * when this loop ends, we compute the body for those var (listed in tempVariables)
             */
            if(iterazioniSenzaDiminuzione == varToBeElaborated.size()){
                WitnessVar first = varToBeElaborated.removeFirst();

                assert(first.isRecursive(this, new LinkedList<>()));

                String complementName = AlgebraStrings.NOT_DEFS + first.getName();

                if (first.getName().startsWith(AlgebraStrings.NOT_DEFS))
                    complementName = first.getName().substring(AlgebraStrings.NOT_DEFS.length());

                WitnessVar complementVarName = variableNamingSystem.buildVar(complementName);

                if(coVar.containsValue(complementVarName)){ //type[all].not() == false --> so type[all] == true, i can remove it
                    varList.remove(first);
                    variableNamingSystem.rename(first, coVar.inverse().get(complementVarName));
                    continue;
                }
                coVar.put(first, complementVarName);
                tempVariables.add(complementVarName);

                logger.debug("notElimination: Forcing new var {}", complementVarName);

                varToBeElaborated.addLast(first);
                iterazioniSenzaDiminuzione = 0;
                continue;
            }

            WitnessVar key = varToBeElaborated.removeFirst();
            WitnessAssertion value = varList.get(key);

            try {

                if (completed.contains(key) || coVar.containsKey(key) || coVar.containsValue(key)){ // if name is already completed --> skip
                    iterazioniSenzaDiminuzione = 0;
                    continue;
                }

                completed.add(key);

                //compute the name of the complement
                String complementName = AlgebraStrings.NOT_DEFS + key.getName();

                if (key.getName().startsWith(AlgebraStrings.NOT_DEFS))
                    complementName = key.getName().substring(AlgebraStrings.NOT_DEFS.length());
                WitnessVar complementVarName = variableNamingSystem.buildVar(complementName);

                //does the complement already exist?
                WitnessAssertion not = value.not(this);
                WitnessVar obddName = null;

                if(not.isBooleanExp())
                    obddName = not.buildOBDD(this, variableNamingSystem);
                else
                    obddName = bdd.createVar(); //create a variable that represents it

                if (varList.containsKey(obddName)) {
                    if(coVar.containsValue(obddName)) {
                        varList.remove(key);
                        variableNamingSystem.rename(key, coVar.inverse().get(obddName));
                    }else
                        coVar.put(key, obddName);
                    completed.add(obddName);
                    variableNamingSystem.rename(complementVarName.getName(), obddName.getName());
                    iterazioniSenzaDiminuzione = 0;
                    continue;
                }

                if (varList.containsKey(complementVarName)) {
                    completed.add(complementVarName);
                    coVar.put(key, complementVarName);
                } else {
                    coVar.put(key, complementVarName);
                    newVar.add(new AbstractMap.SimpleEntry(complementVarName, not));
                }

            }catch (WitnessException | REException ex){
                logger.catching(ex);
                completed.remove(key);
                varToBeElaborated.addLast(key);
                iterazioniSenzaDiminuzione++;
                continue;
            }
        }

        //add the new var
        for(Map.Entry<WitnessVar, WitnessAssertion> entry : newVar)
            varList.put(entry.getKey(), entry.getValue());

        //compute a body for the "temp" var created by ****
        for(WitnessVar var : tempVariables) {
            try {
                varList.put(var, varList.get(getCoVar(var)).not(this));
            } catch (REException e) {
                logger.catching(e);
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }



    @Override
    public WitnessEnv mergeWith(WitnessAssertion a, WitnessVarManager varManager) throws REException {
        throw new UnsupportedOperationException();
    }

    @Override
    public WitnessAssertion merge(WitnessVarManager varManager) throws REException {
        WitnessEnv newEnv = cloneNoWitnessVar();

        for(Map.Entry<WitnessVar, WitnessAssertion> entry : varList.entrySet())
            if(entry.getKey().equals(rootVar))
                newEnv.setRootVar(entry.getKey().getName(), entry.getValue().merge(varManager));
            else
                newEnv.add(entry.getKey(), entry.getValue().merge(varManager));

        newEnv.varToBeElaborated = new LinkedList<>(varToBeElaborated);

        return newEnv;
    }

    @Override
    public WitnessType getGroupType() {
        return null;
    }

    @Override
    public Assertion getFullAlgebra() {
        Defs_Assertion defs = new Defs_Assertion();

        if(rootVar != null) defs.setRootDef(rootVar.getName(), varList.get(rootVar).getFullAlgebra());

        Set<Map.Entry<WitnessVar, WitnessAssertion>> entrySet = varList.entrySet();
        for(Map.Entry<WitnessVar, WitnessAssertion> entry : entrySet) {
            if(!entry.getKey().equals(rootVar))
                defs.add(entry.getKey().getName(), entry.getValue().getFullAlgebra());
        }

        return defs;
    }

    @Override
    public WitnessEnv clone() {
        logger.debug("Cloning WitnessEnv");

        WitnessEnv clone = cloneNoWitnessVar();

        for(Map.Entry<WitnessVar, WitnessAssertion> entry : varList.entrySet())
            clone.varList.put(entry.getKey(), entry.getValue().clone());

        return clone;
    }

    private WitnessEnv cloneNoWitnessVar(){
        WitnessEnv clone = new WitnessEnv();

        clone.rootVar = rootVar;
        clone.coVar = coVar;
        clone.bdd = bdd;
        clone.varToBeElaborated = new LinkedList<>(varToBeElaborated);
        clone.variableNamingSystem = variableNamingSystem;

        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessEnv that = (WitnessEnv) o;

        if(! Objects.equals(that.rootVar, this.rootVar))
            return false;

        if(that.varList.size() != this.varList.size())
            return false;

        for(Map.Entry<WitnessVar, WitnessAssertion> entry : that.varList.entrySet()) {
            if (!this.varList.containsKey(entry.getKey())) return false;
            if(!this.varList.get(entry.getKey()).equals(entry.getValue()))
                return false;
        }

        return true;
    }

    //auto generated code
    @Override
    public int hashCode() {
        int result = varList != null ? varList.hashCode() : 0;
        result = 31 * result + (rootVar != null ? rootVar.hashCode() : 0);
        return result;
    }

    @Override
    public WitnessAssertion not(WitnessEnv env) throws REException {
        this.notElimination();

        WitnessEnv clone = this.clone();
        clone.rootVar = coVar.get(rootVar);

        return clone;
    }

    @Override
    public WitnessEnv groupize() throws WitnessException, REException {
        WitnessEnv env = cloneNoWitnessVar();

        for(Map.Entry<WitnessVar, WitnessAssertion> entry : this.varList.entrySet()) {
            if(entry.getValue().getClass() == WitnessAnd.class || entry.getValue().getClass() == WitnessBoolean.class)
                env.varList.put(entry.getKey(), entry.getValue().groupize());
            else {   //TODO: chiedere al prof se a noi va bene che dopo la groupize ci possano essere variabili del tipo a = type(obj) mon racchiuse nell'and (gruppo)
                WitnessAnd and = new WitnessAnd();
                and.add(entry.getValue());
                env.varList.put(entry.getKey(), and.groupize());
            }
        }

        return env;
    }

    @Override
    public Float countVarWithoutBDD(WitnessEnv env, List<WitnessVar> visitedVar) {
        Float count = 0f;

        for(Map.Entry<WitnessVar, WitnessAssertion> entry : this.varList.entrySet())
            count += entry.getValue().countVarWithoutBDD(this, new LinkedList<>());

        return count;
    }

    @Override
    public int countVarToBeExp(WitnessEnv env) {
        return 0;
    }

    @Override
    public List<Map.Entry<WitnessVar, WitnessAssertion>> varNormalization_separation(WitnessEnv env, WitnessVarManager varManager) throws WitnessException, REException {
        List<Map.Entry<WitnessVar, WitnessAssertion>> newDefinitions = new LinkedList<>();

        for(Map.Entry<WitnessVar, WitnessAssertion> entry : this.varList.entrySet())
            newDefinitions.addAll(entry.getValue().varNormalization_separation(this, this.variableNamingSystem));

        //update the environment with the new definitions
        for(Map.Entry<WitnessVar, WitnessAssertion> entry : newDefinitions){
            this.add(entry.getKey(), entry.getValue());
        }

        buildOBDD_notElimination();

        for(Map.Entry<WitnessVar, WitnessAssertion> entry : newDefinitions){
            WitnessVar covar = getCoVar(entry.getKey());
            varList.put(covar, getDefinition(covar).groupize()); //We do not use the add method because the variables are already elaborated
        }

        return newDefinitions;
    }

    @Override
    public WitnessEnv varNormalization_expansion(WitnessEnv env) throws WitnessException {
        WitnessEnv newEnv = cloneNoWitnessVar();
        LinkedList<Map.Entry<WitnessVar, Integer>> orderedList = new LinkedList(); //orderList: pair list <varName, #varToBeExpanded>

        for(Map.Entry<WitnessVar, WitnessAssertion> entry : varList.entrySet()) {
            int nVarToBeExp = entry.getKey().countVarToBeExp(this);
            orderedList.add(new AbstractMap.SimpleEntry(entry.getKey(), nVarToBeExp));
        }

        Collections.sort(orderedList, Comparator.comparingInt(Map.Entry::getValue));

        //due to sorting, each variable is only expanded with the previous
        for(Map.Entry<WitnessVar, Integer> entry : orderedList) {
            if(entry.getValue() == 0)
                newEnv.varList.put(entry.getKey(), varList.get(entry.getKey()));
            else
                newEnv.varList.put(entry.getKey(), varList.get(entry.getKey()).varNormalization_expansion(newEnv));
        }

        newEnv.varToBeElaborated = new LinkedList<>(varToBeElaborated); //TODO: da fare meglio

        return newEnv;
    }

    public WitnessEnv DNF() throws WitnessException {
        WitnessEnv newEnv = cloneNoWitnessVar();

        for(Map.Entry<WitnessVar, WitnessAssertion> entry : varList.entrySet())
            newEnv.varList.put(entry.getKey(), (entry.getValue()).DNF());

        return newEnv;
    }

    @Override
    public WitnessAssertion toOrPattReq() {
        for (WitnessVar key : varList.keySet())
            varList.put(key, varList.get(key).toOrPattReq());

        return this;
    }

    @Override
    public boolean isBooleanExp() {
        throw new UnsupportedOperationException("isBooleanExp on WitnessEnv");
    }

    @Override
    public boolean isRecursive(WitnessEnv env, LinkedList<WitnessVar> visitedVar) {
        throw new UnsupportedOperationException("isRecursive on WitnessEnv");
    }

    @Override
    public WitnessVar buildOBDD(WitnessEnv env, WitnessVarManager varManager) {
        throw new UnsupportedOperationException("buildOBDD on WitnessEnv");
    }


    public void objectPrepare() throws REException, WitnessException {
        toOrPattReq();

        Collection<Map.Entry<WitnessVar, WitnessAssertion>> entrySet = new HashMap<>(varList).entrySet();

        while (!entrySet.isEmpty()) {
            List<Map.Entry<WitnessVar, WitnessAssertion>> newDefinitions = new LinkedList<>();


            for (Map.Entry<WitnessVar, WitnessAssertion> entry : entrySet) {

                if (entry.getValue().getClass() == WitnessAnd.class)
                    newDefinitions.addAll(((WitnessAnd) entry.getValue()).objectPrepare(this, variableNamingSystem));
                else if (entry.getValue().getClass() == WitnessOr.class)
                    newDefinitions.addAll(((WitnessOr) entry.getValue()).objectPrepare(this, variableNamingSystem));
                    //else if(entry.getValue().getClass() == WitnessType.class && entry.getValue().equals(new WitnessType("obj"))){ //only type object
                else {
                    // in case of definitions likes:
                    // def "x" = type[obj]
                    // we have to prepare that definition but the assertion type[obj] is not contained in boolean operator (AND, OR)
                    WitnessAnd tmp = new WitnessAnd();
                    tmp.add(entry.getValue());
                    newDefinitions.addAll(tmp.objectPrepare(this, variableNamingSystem)); // if the element is not a type[obj], the method call tmp.objectPrepare(this) have no effect
                    /*if (tmp.getIfUnitaryAnd() != null)
                        varList.put(entry.getKey(), tmp.getIfUnitaryAnd());
                    else
                        varList.put(entry.getKey(), tmp);*/
                }
            }
            List<Map.Entry<String, Integer>> tmp = new LinkedList<>();

            for(Map.Entry<WitnessVar, WitnessAssertion> newDef : newDefinitions) {
                add(newDef.getKey(), newDef.getValue());
                tmp.add(new AbstractMap.SimpleEntry<>(new String(newDef.getKey().getName()), newDef.getValue().countVarToBeExp(this)));
            }

            Collections.sort(tmp, Comparator.comparingInt(Map.Entry::getValue));

            buildOBDD_notElimination();

            //mi ricosctruisco il set su cui scorrere, ci aggiungo anche le variabili negate????
            newDefinitions = new LinkedList<>();
            for(Map.Entry<String, Integer> newDef : tmp) {
                String name = newDef.getKey();
                WitnessVar varName = variableNamingSystem.buildVar(name);
                if (name.equals(varName.getName())) {//se NON è stata rinominata è una variabile nuova
                    newDefinitions.add(new AbstractMap.SimpleEntry<>(varName, getDefinition(varName)));

                    // il suo complemento
                    WitnessVar coName = getCoVar(varName);
                    newDefinitions.add(new AbstractMap.SimpleEntry<>(coName, varList.get(coName)));
                }
            }

            logger.debug("Expanding {} variables", newDefinitions.size());

            for(Map.Entry<WitnessVar, WitnessAssertion> newDef : newDefinitions){
                WitnessAssertion normalizedValue = newDef.getValue();

                normalizedValue = normalizedValue.varNormalization_expansion(this);
                normalizedValue = normalizedValue.merge(variableNamingSystem);
                normalizedValue = normalizedValue.groupize();
                normalizedValue = normalizedValue.DNF();

                varList.put(newDef.getKey(), normalizedValue);
            }

            entrySet = newDefinitions;
        }
    }
}
