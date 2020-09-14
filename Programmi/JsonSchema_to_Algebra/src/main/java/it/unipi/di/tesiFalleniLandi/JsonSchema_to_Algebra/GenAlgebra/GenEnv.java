package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

import static it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.AlgebraStrings.*;

public class GenEnv {

    private static Logger logger = LogManager.getLogger(GenEnv.class);

    private HashMap<GenVar, List<GenAssertion>> varList;
    private BiMap<GenVar,GenVar> coVar;
    private GenVar rootVar;
    private List<GenVar> openVars, sleepingVars, emptyVars, popVars;
    private String _sep = "\r\n";

    @Override
    public String toString() {
        return "GenEnv{" + _sep +
                "varList=" + varList + _sep +
                ", coVar=" + coVar +  _sep +
                ", rootVar=" + rootVar + _sep +
                ", openVars=" + openVars + _sep +
                ", sleepingVars=" + sleepingVars + _sep +
                ", emptyVars=" + emptyVars + _sep +
                ", popVars=" + popVars + _sep +
                '}'+ _sep;
    }

    /**
     * Default constructor
     */
    public GenEnv() {
        varList = new HashMap<>();
        coVar = HashBiMap.create();
        openVars = new LinkedList<>();
        sleepingVars = new LinkedList<>();
        emptyVars = new LinkedList<>();
        popVars = new LinkedList<>();
    }

    /**
     * Maps a DNF to a list of GenAssertion objects
     * @param witAssert
     * @return
     */
    private List<GenAssertion> fromWitnessDNF(WitnessAssertion witAssert) throws Exception {
        logger.debug("Extracting assertions from {} ...",witAssert.toString());
        List<GenAssertion> result = new LinkedList<GenAssertion>();

        if(witAssert instanceof WitnessBoolean){
            result.add(((WitnessBoolean) witAssert).getValue()==true ?
                    new GenVarTrue(""):new GenVarFalse(""));
        }
        else
            if(witAssert instanceof WitnessOr){
                for(List<WitnessAssertion> listWA: ((WitnessOr) witAssert).getOrList().values())
                    for(WitnessAssertion WA: listWA) {
                        try {
                            result.add(fromWitness(WA));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            }
            else if(witAssert instanceof WitnessAnd){ //singleton
                try {
                    result.add(fromWitness(witAssert));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
                else
                {
                    throw new Exception("Expected WitnessOr found " +  witAssert.getClass());
                }

        logger.debug("... extracted {}",result);
        return result;
    }

    /**
     * maps a type to its corresponding genType
     * @param witnessType
     * @return
     */
    private GenAssertion fromSingletonTypeOnly(WitnessType witnessType) throws Exception {
        return fromSingletonTypeWithConstraints(witnessType, new LinkedList<>());
    }

    /**
     * attaches numeric constraints, if any
     * side-effect on genNum
     * Remark: does not verify the presence of non-applicable constraints
     * @param genNum
     * @param constraints
     */
    private void attachNumConstraints(GenNum genNum, List<WitnessAssertion> constraints) throws Exception {
        //Between
        List<WitnessBet> witnessBetList = constraints.stream()
                .filter(o->o instanceof WitnessBet)
                .map(e->(WitnessBet)e)
                .collect(Collectors.toList());
        if(witnessBetList.size()>1)
            new Exception("More than one Witness Bet constraints");
        else
        if(witnessBetList.size()==1)
            genNum.setMinMax(witnessBetList.get(0));

        //XBetween
        List<WitnessXBet> witnessXBetList = constraints.stream()
                .filter(o->o instanceof WitnessXBet)
                .map(e->(WitnessXBet)e)
                .collect(Collectors.toList());
        if(witnessXBetList.size()>1)
            new Exception("More than one Witness XBet constraints");
        else
        if(witnessXBetList.size()==1)
            genNum.setMinMax(witnessXBetList.get(0));

        //WitnessMof
        List<WitnessMof> witnessMofList = constraints.stream()
                .filter(o->o instanceof WitnessMof)
                .map(e->(WitnessMof)e)
                .collect(Collectors.toList());
        if(witnessMofList.size()>1)
            new Exception("More than one mof constraints");
        else
            if(witnessMofList.size()==1)
                genNum.setMof(witnessMofList.get(0));

        //WitnessNotMof
        List<WitnessNotMof> witnessNotMofList = constraints.stream()
                .filter(o->o instanceof WitnessNotMof)
                .map(e->(WitnessNotMof)e)
                .collect(Collectors.toList());
        if(witnessNotMofList.size()>=1)
            genNum.setNotMofs(witnessNotMofList);
    }

    /**
     * attaches string constraints, if any
     * side-effect on genString
     * Remark: does not verify the presence of non-applicable constraints
     * @param genString
     * @param constraints
     * @throws Exception
     */
    private void attachStringConstraints(GenString genString, List<WitnessAssertion> constraints) throws Exception {
        List<WitnessPattern> witnessPatternList = constraints.stream()
                .filter(o->o instanceof WitnessPattern)
                .map(e->(WitnessPattern)e)
                .collect(Collectors.toList());
        if(witnessPatternList.size()>1)
            new Exception("More than one pattern constraints");
        else
        if(witnessPatternList.size()==1)
            genString.setPattern(witnessPatternList.get(0).getPattern());
    }


    /**
     * attaches object constraints, if any
     * side-effect on genString
     * Remark: does not verify the presence of non-applicable constraints
     * @param genObject
     * @param constraints
     */
    private void attachObjectConstraints(GenObject genObject, List<WitnessAssertion> constraints){
        //WitnessPro
        List<WitnessPro> witnessProList =  constraints.stream()
                .filter(o->o instanceof WitnessPro)
                .map(e->(WitnessPro)e)
                .collect(Collectors.toList());
        if(witnessProList.size()>1)
            new Exception("More than one Witness XBet constraints");
        else
        if(witnessProList.size()==1)
            genObject.setMinMaxPro(witnessProList.get(0));

        //WitnessProperty
        List<WitnessProperty> witnessPropertyList = constraints.stream()
                .filter(o->o instanceof WitnessProperty)
                .map(e->(WitnessProperty)e)
                .collect(Collectors.toList());
        genObject.setCPart(witnessPropertyList);

        //WitnessOrPattReq
        List<WitnessOrPattReq> witnessOrPattReqList = constraints.stream()
                .filter(o->o instanceof WitnessOrPattReq)
                .map(e->(WitnessOrPattReq)e)
                .collect(Collectors.toList());
        genObject.setRPart(witnessOrPattReqList);

    }

        /**
         *
         * @param witnessType
         * @param constraints
         * @return
         */
    private GenAssertion fromSingletonTypeWithConstraints(WitnessType witnessType,
                                                          List<WitnessAssertion> constraints ) throws Exception {
        GenAssertion result = null;
        String[] typeList = witnessType.getType().toArray(String[]::new);
        if(typeList.length !=1)
            throw new Exception("Type construct must contain ONE type. Current constructs contains  " + typeList.length + " types");
        //get the first (and unique) element of the list
        String typeName = typeList[0];
        //check to which case the group corresponds
        switch (typeName){
            case TYPE_BOOLEAN:
                result = new GenBool();
                break;
            case TYPE_NULL:
                result = new GenNull();
                break;
            case TYPE_NUMBER:
                result = new GenNum();
                try {
                    attachNumConstraints((GenNum) result, constraints);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case TYPE_STRING:
                result = new GenString();
                try {
                    attachStringConstraints((GenString)result, constraints);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case TYPE_OBJECT:
                result = new GenObject();
                attachObjectConstraints((GenObject)result, constraints);
                break;
            case TYPE_ARRAY:
                result = new GenArray();
                break;
            default:throw new Exception("Undefined Type "+typeName);
        }
        return result;
    }
    /**
     * Generates a GenAssertion for a typed object
     * @param typedGroup
     * @return
     */
    private GenAssertion fromWitness(WitnessAssertion typedGroup) throws Exception {
        logger.debug("Converting assertion {} to GenAssertion", typedGroup);
        GenAssertion result = null;
        if(typedGroup instanceof WitnessType){
            result= fromSingletonTypeOnly((WitnessType) typedGroup);
            return result;
        }
        else
            if(typedGroup instanceof WitnessAnd){
                //retrieve the type construct
//                List<WitnessAssertion> wAssertList = ((WitnessAnd) typedGroup).getAndList()
//                        .values().stream().flatMap(List::stream)
//                        .collect(Collectors.toList());
                List<WitnessAssertion> wAssertList = new LinkedList<>();
                for(List<WitnessAssertion> witnessAssertionList:
                        ((WitnessAnd) typedGroup).getAndList().values()){
                    for(WitnessAssertion witnessAssertion: witnessAssertionList)
                        wAssertList.add(witnessAssertion);
                }
                List<WitnessAssertion> wTypeList =  wAssertList.stream()
                        .filter(o->o instanceof WitnessType)
                        .collect(Collectors.toList());

                if(wTypeList.size()<1)
                    throw new Exception("No type construct in a typed group");
                else if(wTypeList.size()>1)
                    throw new Exception("More than one type construct in a typed group");

                WitnessType wType = (WitnessType) wTypeList.get(0); //by default get the first element
                //check again is type is not a singleton
                result = fromSingletonTypeWithConstraints(wType,wAssertList);
                return result;

        }
            else
                if(typedGroup instanceof WitnessVar){
                    return getByNameElseCreate(((WitnessVar)typedGroup).getName());
                }
                    else
                    {
                        throw new Exception("Typed group must be either And or Type or ref, but found "+typedGroup);
                    }
    }


    /**
     *
     * @param env
     * @throws Exception
     */
    public GenEnv(WitnessEnv env) throws Exception {
        logger.debug("Creating a GenEnv from a witnees Env");
        logger.debug("Creating varList");
        //varlist
        varList = new HashMap<>();
        for (Map.Entry<WitnessVar, WitnessAssertion> e : env.getVarList().entrySet())
            varList.put(new GenVar(e.getKey().getName()), fromWitnessDNF(e.getValue()));
        logger.debug("Setting rootvar");
        //rootvar
        rootVar = getByNameElseCreate(env.getRootVar().getName());
        logger.debug("Creating covar");
        //covar
        coVar = HashBiMap.create();
        for (Map.Entry<WitnessVar, WitnessVar> e:env.getCoVar().entrySet())
            coVar.put(new GenVar(e.getKey().getName()),new GenVar(e.getValue().getName()));
        //instantiate  var structures
        openVars = new LinkedList<>();
        sleepingVars = new LinkedList<>();
        emptyVars = new LinkedList<>();
        popVars = new LinkedList<>();
        //initially set all vars to open
        //TODO


    }

    public GenVar getByNameElseCreate(String name){
        GenVar res = null;
        for(GenVar v: varList.keySet())
            if(v.getName().compareTo(name)==0){
                res = v;
                break;
                }
        if(res==null)
            res = new GenVar(name);
        return res;
    }


    public JsonElement generate() {
        return null;
    }





}
