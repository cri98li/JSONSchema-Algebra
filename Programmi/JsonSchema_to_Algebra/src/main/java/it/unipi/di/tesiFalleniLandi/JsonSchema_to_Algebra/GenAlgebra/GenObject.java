package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;

import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.ComplexPattern.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;

import java.util.List;

public class GenObject implements GenAssertion {
    private Double minPro, maxPro;
    private List<GProperty> CPart;
    private List<GOrPattReq> RPart;
    private List<GPattReq> objectReqList;


    /*local classes*/
    public class GProperty {
        private ComplexPattern key;
        private GenVar schema;
    }

    public class GOrPattReq {
        private List<GPattReq> reqList;
    }

    public class GPattReq {
        private ComplexPattern key;
        private GenVar schema;
        private List<GOrPattReq> orpList;
        private boolean isSimple;
    }

    /*Methods*/



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
