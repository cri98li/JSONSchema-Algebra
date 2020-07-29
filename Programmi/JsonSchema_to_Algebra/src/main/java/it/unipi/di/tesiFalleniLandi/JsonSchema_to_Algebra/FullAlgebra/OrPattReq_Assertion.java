package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessOrPattReq;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessPattReq;
import patterns.REException;

import java.util.*;

public class OrPattReq_Assertion implements Assertion{
    private HashMap<ComplexPattern, Assertion> ORP;

    public OrPattReq_Assertion() {
        ORP = new HashMap<>();
    }

    public OrPattReq_Assertion(ComplexPattern name, Assertion assertion) {
        ORP = new HashMap<>();
        ORP.put(name, assertion);
    }

    public void add(ComplexPattern key, Assertion value) {
        if(ORP.containsKey(key)) {
            AnyOf_Assertion or = new AnyOf_Assertion(); //TODO: se hanno la stessa chiave in ORP cosa faccio?
            or.add(ORP.get(key));
            or.add(value);
            ORP.put(key, or);
            return;
        }

        ORP.put(key, value);
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

        if(ORP != null) {
            Set<Map.Entry<ComplexPattern, Assertion>> entrySet = ORP.entrySet();
            for(Map.Entry<ComplexPattern, Assertion> entry : entrySet) {
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
        for (Map.Entry<ComplexPattern, Assertion> entry : this.ORP.entrySet())
            witnessOrPattReq.add(WitnessPattReq.build(entry.getKey(), entry.getValue().toWitnessAlgebra()));

        return witnessOrPattReq;
    }
}
