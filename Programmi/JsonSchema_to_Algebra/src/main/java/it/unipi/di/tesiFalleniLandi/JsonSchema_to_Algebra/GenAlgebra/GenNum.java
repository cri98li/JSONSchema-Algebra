package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;

import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GenNum implements GenAssertion {
    private static Logger logger = LogManager.getLogger(GenNum.class);


    private Double min, max;
    private boolean minExclusive, maxExclusive;
    private Double mof;
    private List<Double> notMofs; //sort while instantiating


    /**
     * constructor with optional arguments
     * @param minMaxOptional
     * @param XminMaxOptional
     * @param mofOptional
     * @param notMofsOptional
     */
    public GenNum(Optional<WitnessBet> minMaxOptional,
                  Optional<WitnessXBet> XminMaxOptional,
                  Optional<WitnessMof> mofOptional,
                  Optional<List<WitnessNotMof>> notMofsOptional) throws Exception {

        if(minMaxOptional.isPresent()){
            min=minMaxOptional.get().getMin();
            max=minMaxOptional.get().getMax();
        }

        if(XminMaxOptional.isPresent()){
            min=XminMaxOptional.get().getMin();
            max=XminMaxOptional.get().getMax();
            minExclusive=maxExclusive=true;
        }

        if(mofOptional.isPresent()){
            mof= mofOptional.get().getValue();
            if(notMofs!=null){
                Double mofTest = containsMultiple(notMofs,mof);
                if(mofTest>0)
                {
                    logger.trace("The number {} in the NotMof List is multiple of {}", mofTest, mof);
                    throw new Exception("NotMof List contains a multiple of Mof");
                }
            }

        }

        if(notMofsOptional.isPresent()){
            notMofs=notMofsOptional.get().stream()
                    .map(e->e.getValue()).collect(Collectors.toList());
            notMofs.sort(Comparator.naturalOrder());
            if(containsPairMultiple(notMofs))
                throw new Exception("NotMof List contains a pair of multiples");
        }

    }

    /**
     * Auxiliary functions
     * */

    /**
     *
     * @param list must be sorted
     * @param num
     * @return
     */
    private Double containsMultiple(List<Double> list, Double num){
        Double res = 0d;
        for(Double el:list)
            if(el%num==0)
                res = el;
        return res;
    }

    /**
     *
     * @param list must be sorted
     * @return
     */
    private boolean containsPairMultiple(List<Double> list) {
    //expect list size >1
        Double[] arr = new Double[list.size()];
        arr = list.toArray(arr);
        for (int i=0; i<arr.length;i++)
            for(int j=i+1; j<arr.length; j++)
                if(arr[j]%arr[i]==0)
                    return true;
        return false;
    }


    @Override
    public JsonElement generate() {
        return null;
    }

    @Override
    public JsonElement generateNext() {
        return null;
    }

    @Override
    public WitnessAssertion toWitnessAlgebra() {
        return null;
    }
}
