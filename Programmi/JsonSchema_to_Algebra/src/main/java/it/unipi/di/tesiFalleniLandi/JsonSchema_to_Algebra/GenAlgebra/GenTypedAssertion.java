package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;

import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class GenTypedAssertion implements GenAssertion{
//    private List<GenAssertion> typedAssertion;
    private HashMap<String,List<GenAssertion>> typedAssertionMap;
    private Class[] orderedTypes = {GenNum.class, GenString.class, GenArray.class, GenObject.class, GenNull.class, GenNull.class};
    private String[] orderedTypeNames = Arrays.stream(orderedTypes).map(t->t.getSimpleName()).toArray(String[]::new);


    public List<GenAssertion> getTypedAssertion() {
//        return new ArrayList<>(typedAssertionMap.values());
        return typedAssertionMap.values().stream()
                .flatMap(l->l.stream()).collect(Collectors.toList());
    }

    public GenTypedAssertion(List<GenAssertion> typedAssertion) {
        typedAssertionMap = new HashMap<>();
//        typedAssertion.stream().collect(Collectors.groupingBy(t->t.getClass().getSimpleName()));
        typedAssertionMap.putAll(typedAssertion.stream().collect(Collectors.groupingBy(t->t.getClass().getSimpleName())));
    }

    public boolean containsBaseType() {
        return typedAssertionMap.containsKey(GenNull.class.getSimpleName())||
                typedAssertionMap.containsKey(GenNum.class.getSimpleName())||
                typedAssertionMap.containsKey(GenString.class.getSimpleName());
    }


    @Override
    public JsonElement generate() {
        JsonElement result = null;
        for(String t :orderedTypeNames){
            GenAssertion g = typedAssertionMap.get(t).get(0); //default generate one randomly
            result =  g.generate();
            break; //generate the first witness according to the order chosen in @orderedTypes
        }
        return result;
    }

    @Override
    public JsonElement generateNext() {
        return null;
    }

    @Override
    public WitnessAssertion toWitnessAlgebra() {
        return null;
    }

    @Override
    public List<GenVar> usedVars() {
        return null;
    }
}
