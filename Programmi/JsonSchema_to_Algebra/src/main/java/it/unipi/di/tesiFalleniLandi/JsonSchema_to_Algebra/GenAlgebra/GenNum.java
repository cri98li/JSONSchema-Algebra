package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;


import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GenNum implements GenAssertion {
    private static Logger logger = LogManager.getLogger(GenNum.class);
    private JsonElement witness;


    private Double min, max;
    private boolean minExclusive, maxExclusive;
    private Double mof;
    private List<Double> notMofs; //keep sorted
    private Double _nongen = -999d;

    @Override
    public String toString() {
        return "GenNum{" +
                "min=" + min +
                ", max=" + max +
                ", minExclusive=" + minExclusive +
                ", maxExclusive=" + maxExclusive +
                ", mof=" + mof +
                ", notMofs=" + notMofs +
                '}';
    }

    @Override
    public JsonElement getWitness() {
        return witness;
    }

    public GenNum() {
        logger.debug("Creation");
//        mof = 1d; //defaul value
    }

    public void setMinMax(WitnessBet WitnessBet){
        min=WitnessBet.getMin();
        max=WitnessBet.getMax();
        logger.debug("Set min to {} max to {}", min, max);
    }

    public void setMinMax(WitnessXBet witnessXBet){
        min=witnessXBet.getMin();
        max=witnessXBet.getMax();
        minExclusive=maxExclusive=true;
        logger.debug("Set Xmin to {} Xmax to {}", min, max);

    }

    /**
     * checked at two positions: while creating Mof and notMofs constraints since no precedence is enforced
     * @throws Exception
     */
    private void invariant1() throws Exception{
        if(notMofs!=null&&notMofs.size()>0){
            Double mofTest = containsMultiple(notMofs,this.mof);
            if(mofTest>0)
            {
                logger.trace("The number {} in the NotMof List is multiple of {}", mofTest, mof);
                throw new Exception("NotMof List contains a multiple of Mof");
            }
        }
    }

    /**
     *
     * @param mof
     * @throws Exception
     */
    public void setMof(WitnessMof mof) throws Exception {
        this.mof = mof.getValue();
        //check invariant1
        invariant1();
        logger.debug("Set mof to {}", this.mof);
    }

    public void setNotMofs(List<WitnessNotMof> notMofs) throws Exception{
        this.notMofs=notMofs.stream()
                .map(e->e.getValue()).collect(Collectors.toList());
        this.notMofs.sort(Comparator.naturalOrder());
        //check invariants
        if(this.mof!=null)
            invariant1();
        //invariant2
        if(containsPairMultiple(this.notMofs))
            throw new Exception("NotMof List contains a pair of multiples");
        logger.debug("Set NotMofs to {}", this.notMofs);

    }


    /**
     * constructor with optional arguments
     * TODO Remove
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

    /** Auxiliary functions */

    /**
     *
     * @param list must be sorted
     * @param num
     * @return
     */
    private Double containsMultiple(List<Double> list, Double num){
        Double res = 0d;
        for(Double el:list)
            if(el%num==0){
                res = el;
                break;
            }

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

    /**
     * whether n is a multiple of a given element in notmof
     * @param n
     * @param notmof
     * @return
     */
    private boolean existsNotMof(Double n, List<Double> notmof){
        boolean res = false;
        for(Double nm:notmof)
            if(n%nm==0){
                res = true;
                break;
            }
        return res;
    }


    /**
     *
     * @param n
     * @param mof
     * @param notMofs
     * @return
     */
    private Double generateMofNotMof(Double n, Double mof, List<Double> notMofs){
        return fromToMofNotMof(n,mof,Double.MAX_VALUE,notMofs);
    }

    /**
     *
     * @param min
     * @param max
     * @param mof
     * @param notMofs
     * @return
     */
    private Double fromToMofNotMof(Double min, Double max, Double mof,  List<Double> notMofs){
        Double result = min * mof;
        while (existsNotMof(result,notMofs)&&result<max){
            result++;
        }
        if(result<max)
            return result;
        else
            return _nongen;
    }
//TODO check again
    @Override
    public statuses generate() {
        if(min>max)
            return statuses.Empty;
        else if(min==max)
        {
            witness = new JsonPrimitive(generateMofNotMof(min,mof,notMofs));
            return statuses.Populated;
        }
        else //min<max  I do not bother with the directions
        {
            witness = new JsonPrimitive(fromToMofNotMof(min+1,max-1,mof,notMofs)); //TODO to be checked
            return statuses.Populated;
        }
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
        return new LinkedList<>();
    }


}
