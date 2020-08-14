package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessOrPattReq;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessPattReq;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

import java.util.*;

public class OrPattReq_Assertion implements Assertion{
    List<Map.Entry<ComplexPattern, Assertion>> reqList;

    private static Logger logger = LogManager.getLogger(OrPattReq_Assertion.class);

    public OrPattReq_Assertion() {
        reqList = new LinkedList<>();
        logger.trace("Creating an empty OrPAttReq_Assertion");
    }

    public void add(ComplexPattern key, Assertion value) {
        logger.trace("Adding <{}, {}> to {}", key, value, this);

        /*if(ORP.containsKey(key)) {
            logger.trace("Same ORP contains {} --> generating value as AnyOf", key);
            AnyOf_Assertion or = new AnyOf_Assertion(); //TODO: se hanno la stessa chiave in ORP cosa faccio?
            or.add(ORP.get(key));
            or.add(value);
            ORP.put(key, or);
            return;
        }*/

        reqList.add(new AbstractMap.SimpleEntry(key, value));
    }

    @Override
    public JsonElement toJSONSchema() {
        throw new UnsupportedOperationException("OrPattReq.toJSONSchema");
    }

    @Override
    public Assertion not() {
        throw new UnsupportedOperationException("OrPattReq.not");
    }

    @Override
    public Assertion notElimination() {
        return this; //TODO: da chiedere notElimination in ORP
    }

    @Override
    public String toGrammarString() {
        StringBuilder str = new StringBuilder();

        if(reqList != null) {
            for(Map.Entry<ComplexPattern, Assertion> entry : reqList) {
                String returnedValue = entry.getValue().toGrammarString();
                if(!returnedValue.isEmpty())
                    str.append(FullAlgebraString.COMMA)
                            .append(entry.getKey().getAlgebraString())
                            .append(":")
                            .append(returnedValue);
            }
        }

        if(str.length() == 0) return "";
        return FullAlgebraString.ORPATTREQ(str.substring(FullAlgebraString.COMMA.length()));
    }

    @Override
    public WitnessAssertion toWitnessAlgebra() throws REException {
        WitnessOrPattReq witnessOrPattReq = new WitnessOrPattReq();
        for (Map.Entry<ComplexPattern, Assertion> entry : this.reqList)
            witnessOrPattReq.add(WitnessPattReq.build(entry.getKey(), entry.getValue().toWitnessAlgebra()));

        return witnessOrPattReq;
    }
}
