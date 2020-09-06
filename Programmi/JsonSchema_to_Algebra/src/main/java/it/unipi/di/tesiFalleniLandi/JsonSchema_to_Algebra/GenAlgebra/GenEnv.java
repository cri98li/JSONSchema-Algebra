package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.*;

import java.util.*;
import java.util.stream.Collectors;

import static it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.AlgebraStrings.*;

public class GenEnv {

    private HashMap<GenVar, List<GenAssertion>> varList;
    private BiMap<GenVar,GenVar> coVar;
    private GenVar rootVar;
    private List<GenVar> openVars, sleepingVars, emptyVars, popVars;

    public GenEnv() {
        varList = new HashMap<>();
        coVar = HashBiMap.create();
        openVars = new LinkedList<>();
        sleepingVars = new LinkedList<>();
        emptyVars = new LinkedList<>();
        popVars = new LinkedList<>();
    }

    /**
     *
     * @param witAssert
     * @return
     */
    public List<GenAssertion> fromWitnessDNF(WitnessAssertion witAssert){
        if(witAssert instanceof WitnessOr){
            //collect the typed groups and convert each one
            return(((WitnessOr) witAssert).getOrList().values()
                    .stream().flatMap(List::stream)
                    .map(group -> fromWitness(group))
                    .collect(Collectors.toList()));
        }
        else{
            new Exception("Expected WitnessOr found " +  witAssert.toString());
            return new ArrayList<>();
        }


    }


    public GenAssertion fromWitness(WitnessAssertion typedGroup){
        if(typedGroup instanceof WitnessType){
            //singleton
            new GenVarTrue("");
        }
        else
            if(typedGroup instanceof WitnessAnd){
                //retrieve type
                List<WitnessAssertion> wAssertList = ((WitnessAnd) typedGroup).getAndList()
                        .values().stream().flatMap(List::stream)
                        .collect(Collectors.toList());
                List<WitnessAssertion> wTypeList =  wAssertList.stream()
                        .filter(o->o instanceof WitnessType)
                        .collect(Collectors.toList());

                if(wTypeList.size()<1)
                    new Exception("No type construct");
                else if(wTypeList.size()>1)
                    new Exception("More than one type construct");
                WitnessType wType = (WitnessType) wTypeList.get(0); //by default get the first element
                //check again is type is not a singleton
                if(wType.getType().size()>1)
                    new Exception("More than one type in the type construct");
                //get the first (and unique) element of the list
                String typeName = wType.getType().toArray(String[]::new)[0];
                //check to which case the group corresponds
                switch (typeName){
//                    case TYPE_OBJECT://obj
//                        ;
//                    case TYPE_ARRAY:
//                        ;
//                    case TYPE_NUMBER:
//                        ;
                    case TYPE_NULL:
                        return new GenNull();
//                    case TYPE_STRING:
//                        ;
//                    case TYPE_BOOLEAN:
//                        ;
                }

        }
            return new GenNull();
    }

    public GenEnv(WitnessEnv env){
//        varList = env.getVarList().entrySet().stream()
//                .map(e->(new GenVar(e.getKey().getName()),fromWitness(e.getValue())));
        varList = new HashMap<>();
        for (Map.Entry<WitnessVar, WitnessAssertion> e : env.getVarList().entrySet())
            varList.put(new GenVar(e.getKey().getName()), fromWitnessDNF(e.getValue()));

        coVar = HashBiMap.create();
        for (Map.Entry<WitnessVar, WitnessVar> e:env.getCoVar().entrySet())
            coVar.put(new GenVar(e.getKey().getName()),new GenVar(e.getValue().getName()));
        //instantiate  var structures
        openVars = new LinkedList<>();
        sleepingVars = new LinkedList<>();
        emptyVars = new LinkedList<>();
        popVars = new LinkedList<>();
        //initially all vars are open


    }


    public JsonElement generate() {
        return null;
    }




}
