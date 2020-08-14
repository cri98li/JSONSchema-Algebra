package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Defs_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessException;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessFalseAssertionException;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessTrueAssertionException;
import jdd.bdd.BDD;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class WitnessEnv implements WitnessAssertion {
    private static Logger logger = LogManager.getLogger(WitnessEnv.class);

    private ConcurrentHashMap<WitnessVar, WitnessAssertion> varList;
    private WitnessVar rootVar;

    protected BiMap<WitnessVar, WitnessVar> coVar; // <x,not_x> < y,not_y>

    public WitnessEnv(){
        varList = new ConcurrentHashMap<>();
        coVar = HashBiMap.create(); //init BiMap
        logger.trace("Creating an empty WitnessEnv");
    }

    public Map.Entry<WitnessVar, WitnessVar> addWithComplement(WitnessAssertion value) throws WitnessException, REException {
        logger.trace("Adding with complement {}", value);
        WitnessVar var = new WitnessVar(Utils_WitnessAlgebra.getName(value));
        add(var, value);
        notElimination();//lo completo subito

        logger.trace("Added {} and its complement {}", var, getCoVarName(var));
        return new AbstractMap.SimpleEntry<>(var, getCoVarName(var));
    }

    //public Map.Entry<WitnessVar, WitnessVar> addWithComplement(WitnessAssertion value) throws REException, WitnessException {

        /*WitnessVar var = getOBDDName(value);
        WitnessVar newVarName = new WitnessVar(Utils_WitnessAlgebra.getName(value));
        WitnessBDD.rename(var, newVarName);
        WitnessVar.rename(var.getName(), newVarName.getName());   //non dovrebbe fare nulla
        varList.put(newVarName, value);

        WitnessAssertion not = value.not(this);
        WitnessVar cVar = getOBDDName(not);
        WitnessVar newCVarName = new WitnessVar(FullAlgebraString.NOT_DEFS + var.getName());
        WitnessBDD.rename(cVar, newCVarName);
        WitnessVar.rename(cVar.getName(), newCVarName.getName());   //non dovrebbe fare nulla
        varList.put(newCVarName, not);

        coVar.put(newVarName, newCVarName);

        return new AbstractMap.SimpleEntry<>(newVarName, newCVarName);*/
    //}

    /**
     *
     * @param key
     * @param value
     * @return
     */
    public void add(WitnessVar key, WitnessAssertion value){
        logger.trace("Adding to env <{}, {}>", key, value);
        if(WitnessAnd.class == value.getClass()){
            add(key, (WitnessAnd) value);
            return;
        }
        varList.put(key, value);
    }

    public void add(WitnessVar key, WitnessAnd value){
        varList.put(key, value);
    }

    /**
     * Associa ad ogni variabile 1 obdd (anche banale).
     * Suddivido le variabili per:
     *      - variabili che non sono espressioni booleane di variabili
     *      - variabili che sono espressioni booleane
     * per la prima tipologia creo un obdd banale (un nodo collegato a true e false)
     * per la seconda invece ordino la lista in maniera crescente per numero di riferimenti da risolvere e poi creo i relativi obdd rimuovendo in ordine.
     *      se mi accorgo di non poter creare un obdd per colpa di una definizione che non è associata ancora ad un obdd (eccezione??),
     *      sposto quell'elemento in fondo alla lista e proseguo con i restanti. Quando mi accorgo di essere in ciclo (ho scorso tutta la lista senza mai
     *      diminuire la dimensione), forzo la creazione di una variabile
     */
    public void buildOBDD() {
        logger.trace("Building obdd");
        LinkedList<Map.Entry<WitnessVar, Float>> booleanExpression = new LinkedList<>();

        //Caso base, creo le 2 asserzioni vere e false
        /*if(!WitnessBDD.contains(WitnessBDD.getFalseVar()) || !WitnessBDD.contains(WitnessBDD.getTrueVar())){
            WitnessAssertion trueAssertion = new WitnessBoolean(true);
            WitnessAssertion falseAssertion = new WitnessBoolean(false);
            WitnessBDD.createVar(WitnessBDD.getTrueVar());
            WitnessBDD.createVar(WitnessBDD.getFalseVar());
            this.add(WitnessBDD.getFalseVar(), falseAssertion);
            this.add(WitnessBDD.getTrueVar(), trueAssertion);
            coVar.forcePut(WitnessBDD.getTrueVar(), WitnessBDD.getFalseVar());
        }*/

        for (Map.Entry<WitnessVar, WitnessAssertion> entry : varList.entrySet()) {
            //caso base: esiste già un obdd associato
            if (WitnessBDD.contains(entry.getKey())) continue;

            if (!entry.getValue().isBooleanExp()) {
                //creo un obdd banale
                WitnessBDD.createVar(entry.getKey());
            } else {
                booleanExpression.add(new AbstractMap.SimpleEntry(entry.getKey(), entry.getValue().countVarWithoutBDD(this, new LinkedList<>())));
            }
        }

        //Riordino la lista
        Collections.sort(booleanExpression, new Comparator<Map.Entry<WitnessVar, Float>>() {
            @Override
            public int compare(Map.Entry<WitnessVar, Float> witnessVarIntegerEntry, Map.Entry<WitnessVar, Float> t1) {
                return (int) (witnessVarIntegerEntry.getValue() - t1.getValue());
            }
        });


        int iterazioniSenzaDiminuzione = 0;
        while (booleanExpression.size() != 0) {
            logger.trace("booleanExpression.size = {}; itWithoutDec= {}", booleanExpression.size(), iterazioniSenzaDiminuzione);
            if (iterazioniSenzaDiminuzione == booleanExpression.size()) {
                //se sono qui vuol dire che non posso creare piú obdd se non "forzo" la creazione di una variabile
                // (forzo quella corrente?)
                Map.Entry<WitnessVar, Float> first = booleanExpression.removeFirst();
                first.getKey().forceOBDD();
                booleanExpression.addLast(first);
                iterazioniSenzaDiminuzione = 0;
                logger.debug("Forcing new obdd {}", first.getKey());
                continue;
            }

            Map.Entry<WitnessVar, Float> first = booleanExpression.removeFirst();

            try {
                WitnessVar obddName = varList.get(first.getKey()).buildOBDD(this);


                if(varList.containsKey(obddName)){ //è una variabile che posso rinominare
                    logger.trace("Rinomino variabile (esiste già con stessa semantica) da {} a {}", first.getKey(), obddName);
                    varList.remove(first.getKey());
                    coVar.replace(first.getKey(), obddName);
                    WitnessVar.rename(first.getKey(), obddName);
                }else {
                    WitnessBDD.rename(obddName, first.getKey());
                    logger.trace("Rinomino la stringa associata all'obdd con il nome della variabile. Da {} a {}", obddName, first.getKey());
                }

                iterazioniSenzaDiminuzione = 0;
            } catch (WitnessException ex) {
                logger.catching(ex);
                //se sono qui vuol dire che ho cercato di creare un obdd di un'espressione che contiene variabili che non hanno ancora un obdd associato
                booleanExpression.addLast(first);
                logger.trace("ref to a var without obdd in {}", first.getKey());
                iterazioniSenzaDiminuzione++;
            }
        }
    }


    public WitnessAssertion getDefinition(WitnessVar var){
        if(!varList.containsKey(var))
            throw new RuntimeException("Schema con definizioni-riferimenti non completi");
        return varList.get(var);
    }

    public WitnessVar getCoVarName(WitnessVar name){
        if(coVar.containsKey(name)){
            return coVar.get(name);
        }else if(coVar.inverse().containsKey(name)){
            return coVar.inverse().get(name);
        }else
            throw new RuntimeException("var not found in coVar: "+name);
    }

    public void setRootVar(WitnessVar key, WitnessAssertion value) throws WitnessException {
        /*WitnessVar bddVar = getOBDDName(value);

        if(varList.contains(bddVar)){
            rootVar = bddVar;
            WitnessVar.rename(key.getName(), bddVar.getName());
            return;
        }

        varList.put(key, value);
        rootVar = key;
        WitnessBDD.rename(bddVar, key);*/
        varList.put(key, value);
        rootVar = key;
    }

    @Override
    public String toString() {
        return "WitnessEnv{" +
                "var=" + varList +
                ", rootVar=" + rootVar +
                '}';
    }

    @Override
    public void checkLoopRef(WitnessEnv env, Collection<WitnessVar> varList) throws WitnessException {
        for(Map.Entry<WitnessVar, WitnessAssertion> entry : this.varList.entrySet()) {
            varList = new HashSet<>();
            varList.add(entry.getKey());

            entry.getValue().checkLoopRef(this, varList);
        }
    }

    /**
     * Completa tutte le variabili con le negate e aggiorna la tabella coVar
     * @throws WitnessException
     * @throws REException
     */
    public void notElimination() throws WitnessException, REException {
        logger.trace("Performing notElimination");
        buildOBDD();

        HashSet<WitnessVar> completed = new HashSet<>();
        LinkedList<Map.Entry<WitnessVar, WitnessAssertion>> newVar = new LinkedList<>(); //to avoid concurrentModificationException

        LinkedList<WitnessVar> tmp = new LinkedList<>();
        for(Map.Entry<WitnessVar, WitnessAssertion> entry : varList.entrySet())
            tmp.add(entry.getKey());

        LinkedList<WitnessVar> daSistemare = new LinkedList<>();

        int iterazioniSenzaDiminuzione = 0;
        while (tmp.size() != 0){
            logger.trace("notElimination: list.size = {}; itWithoutDec= {}", tmp.size(), iterazioniSenzaDiminuzione);
            /**
             * Caso: ****
             * def "x" = props["a": ref("y");],
             * def "y" = ref("x")
             *
             * creo una corrispondenza "finta" x <--> not_x, che poi sistemerò in un secondo momento
             */
            if(iterazioniSenzaDiminuzione == tmp.size()){
                WitnessVar first = tmp.removeFirst();
                if(first.isRecursive(this, new LinkedList<>())) {//questo controllo è scontato...
                    String nomeComplemento = FullAlgebraString.NOT_DEFS + first.getName();
                    if (first.getName().startsWith(FullAlgebraString.NOT_DEFS))
                        nomeComplemento = first.getName().substring(FullAlgebraString.NOT_DEFS.length());
                    WitnessVar complementVarName = new WitnessVar(nomeComplemento);

                    coVar.put(first, complementVarName);
                    daSistemare.add(complementVarName);
                    logger.debug("notElimination: Forcing new var {}", complementVarName);
                }
                tmp.addLast(first);
                iterazioniSenzaDiminuzione = 0;
                continue;
            }

            //PER STAMPARE LISTA
            //System.out.println("ToBeCompl: "+tmp.size()+"\r\n\t");
            //tmp.forEach(System.out::println);

            WitnessVar nome = tmp.removeFirst();
            WitnessAssertion corpo = varList.get(nome);
            try {

                if (completed.contains(nome) || coVar.containsKey(nome) || coVar.containsValue(nome)){
                    iterazioniSenzaDiminuzione = 0;
                    continue;
                }

                completed.add(nome);

                //calcolo nome complemento
                String nomeComplemento = FullAlgebraString.NOT_DEFS + nome.getName();
                if (nome.getName().startsWith(FullAlgebraString.NOT_DEFS))
                    nomeComplemento = nome.getName().substring(FullAlgebraString.NOT_DEFS.length());
                WitnessVar complementVarName = new WitnessVar(nomeComplemento);

                //esiste già il complemento ?
                WitnessAssertion not = corpo.not(this);
                WitnessVar obddName = null;
                if(not.isBooleanExp()) {
                    obddName = not.buildOBDD(this);
                }
                else
                    obddName = WitnessBDD.createVar();//creo una variabile che lo rappresenta

                if (varList.containsKey(obddName)) {
                    coVar.put(nome, obddName);
                    completed.add(obddName);
                    WitnessVar.rename(complementVarName.getName(), obddName.getName());
                    iterazioniSenzaDiminuzione = 0;
                    continue;
                }

                if (varList.containsKey(complementVarName)) {//TODO: mi fido del nome o confronto i corpi?
                    completed.add(complementVarName);
                    coVar.forcePut(nome, complementVarName);
                } else {
                    coVar.forcePut(nome, complementVarName);
                    newVar.add(new AbstractMap.SimpleEntry(complementVarName, not));
                }
            }catch (Exception ex){
                logger.catching(ex);
                completed.remove(nome);
                tmp.addLast(nome);
                iterazioniSenzaDiminuzione++;
                continue;
            }
        }

        //aggiungo le nuove variabili negate
        for(Map.Entry<WitnessVar, WitnessAssertion> entry : newVar)
            this.add(entry.getKey(), entry.getValue());

        //do un corpo alle variabili fittizzie che ho creato ****
        for(WitnessVar var : daSistemare)
            varList.put(var, varList.get(getCoVarName(var)).not(this));
    }



    @Override
    public WitnessEnv mergeWith(WitnessAssertion a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public WitnessAssertion merge() throws REException {
        Set<Map.Entry<WitnessVar, WitnessAssertion>> entrySet = varList.entrySet();
        WitnessEnv newEnv = new WitnessEnv();

        for(Map.Entry<WitnessVar, WitnessAssertion> entry : entrySet) {
            WitnessVar name = entry.getKey();

            if(name.equals(rootVar)) {
                try {
                    newEnv.setRootVar(new WitnessVar(name.getName()), varList.get(name).merge());
                } catch (WitnessException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                //try {
                    newEnv.add(new WitnessVar(name.getName()), varList.get(name).merge());
                //} catch (WitnessException e) {
                 //   throw new RuntimeException(e);
                //}
            }
        }

        newEnv.coVar = coVar; //todo: clone?
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
        WitnessEnv clone = new WitnessEnv();

        clone.rootVar = rootVar;
        clone.coVar = coVar;
        for(Map.Entry<WitnessVar, WitnessAssertion> entry : varList.entrySet())
            clone.varList.put(entry.getKey(), entry.getValue().clone());

        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WitnessEnv that = (WitnessEnv) o;

        if(!that.rootVar.equals(this.rootVar))
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

    @Override
    public int hashCode() {
        int result = varList != null ? varList.hashCode() : 0;
        result = 31 * result + (rootVar != null ? rootVar.hashCode() : 0);
        return result;
    }

    @Override
    public WitnessAssertion not(WitnessEnv env) throws REException, WitnessException {
        this.notElimination();

        return this; //l'ambiente è non completato --> il negato dell'ambiente sarebbe l'ambiente stesso
    }

    @Override
    public WitnessEnv groupize() throws WitnessException, REException {
        WitnessEnv env = new WitnessEnv();

        if(rootVar != null)
            env.rootVar = (WitnessVar) rootVar.groupize();

        for(Map.Entry<WitnessVar, WitnessAssertion> entry : this.varList.entrySet()) {
            env.add(entry.getKey(), entry.getValue().groupize());
        }

        env.coVar = coVar;
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
    public void varNormalization_separation(WitnessEnv env) throws WitnessException, REException {

        for(Map.Entry<WitnessVar, WitnessAssertion> entry : this.varList.entrySet())
            entry.getValue().varNormalization_separation(this);
    }

    @Override
    public WitnessEnv varNormalization_expansion(WitnessEnv env) throws WitnessException {
        WitnessEnv newEnv = new WitnessEnv();
        LinkedList<Map.Entry<WitnessVar, Integer>> orderList = new LinkedList(); //orderList: pair list <varName, #varToBeExpanded>

        for(Map.Entry<WitnessVar, WitnessAssertion> entry : varList.entrySet()) {
            int nVarToBeExp = entry.getKey().countVarToBeExp(this);
            orderList.add(new AbstractMap.SimpleEntry(entry.getKey(), nVarToBeExp));
        }

        Collections.sort(orderList, Comparator.comparingInt(Map.Entry::getValue));

        if(rootVar != null)
            newEnv.rootVar = rootVar;

        //thanks to sorting, each variable is only expanded with the previous
        for(Map.Entry<WitnessVar, Integer> entry : orderList) {
            if(entry.getValue() == 0)
                newEnv.add(entry.getKey(), varList.get(entry.getKey()));
            else
                newEnv.add(entry.getKey(), varList.get(entry.getKey()).varNormalization_expansion(newEnv));
        }

        /*if(!newEnv.equals(this))
            return newEnv.variableNormalization_expansion(null);*/

        newEnv.coVar = coVar;
        newEnv.buildOBDD();

        return newEnv;
    }

    public WitnessEnv DNF() throws WitnessException {
        WitnessEnv dnf = new WitnessEnv();

        dnf.rootVar = rootVar;

        for(Map.Entry<WitnessVar, WitnessAssertion> entry : varList.entrySet())
            dnf.varList.put(entry.getKey(), (entry.getValue()).DNF());

        dnf.coVar=coVar;
        return dnf;
    }

    @Override
    public WitnessAssertion toOrPattReq() throws WitnessFalseAssertionException, WitnessTrueAssertionException {
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
    public WitnessVar buildOBDD(WitnessEnv env) {
        throw new UnsupportedOperationException();
    }


    public void objectPrepare() throws REException, WitnessException {
        //call object prepare only for and assertion, or assertion
        for(Map.Entry<WitnessVar, WitnessAssertion> entry : varList.entrySet()) {
            if (entry.getValue().getClass() == WitnessAnd.class)
                ((WitnessAnd) entry.getValue()).objectPrepare(this);
            else if (entry.getValue().getClass() == WitnessOr.class)
                ((WitnessOr) entry.getValue()).objectPrepare(this);
        }
    }


    public WitnessEnv prepareTypes(){
        //deflist --> usiamo quella di env
        HashMap<BDD, WitnessVar> varhash = new HashMap<>();

        //for(Map.Entry<WitnessVar, WitnessAssertion> pair : varList){
            //Per ogni gruppo G fai...
        //}

        return null;
    }

    private WitnessVar newVar(HashMap<BDD, WitnessVar> varhash, BDD boolExp){
        if(varhash.containsKey(boolExp)) return varhash.get(boolExp);
        else{


        }

        return null;
    }

    private void addVar(HashMap<BDD, WitnessVar> varhash, BDD BoolExp){

    }

    private WitnessAssertion BDDtoWitness(BDD boolExp){
        // dato l'obdd con i nomi delle variabili, restituire una rappresentazione in WintessAssertion
        // per aggiungere la nuova variabile

        return null;
    }

    private WitnessAssertion existsVar(HashMap<BDD, WitnessVar> varhash, WitnessAssertion boolExp){
        //BDD n = boolExp.getBDD();

        return null;
    }
}
