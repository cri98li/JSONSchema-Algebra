package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;

import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.ComplexPattern.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.*;

import java.util.List;
import java.util.stream.Collectors;

public class GenObject implements GenAssertion {
    private Double minPro, maxPro;
    private List<GProperty> CPart;
    private List<GOrPattReq> RPart;
    private List<GPattReq> objectReqList;


    /*local classes*/
    public class GProperty {
        private ComplexPattern key;
        private GenAssertion schema;

        public GProperty(WitnessProperty prop){
            key=prop.getPattern();
            schema=fromWitness(prop.getValue());
        }
    }

    public class GOrPattReq {
        private List<GPattReq> reqList;
        public GOrPattReq(WitnessOrPattReq wor){
            reqList=wor.getReqList().stream().map(w->new GPattReq(w)).collect(Collectors.toList());
        }
    }

    public class GPattReq {
        private ComplexPattern key;
        private GenAssertion schema;
        private List<GOrPattReq> orpList;
//        private boolean isSimple;
        public GPattReq(WitnessPattReq wpr){
            key=wpr.getPattern();
            schema=fromWitness(wpr.getValue());
            orpList=wpr.getOrpList().stream().map(e->new GOrPattReq(e)).collect(Collectors.toList());
        }
    }

    /*Methods*/

    public GenObject(List<WitnessProperty> propList,
                     WitnessPattReq pattReq,
                     WitnessPro minMaxPro){
        minPro=minMaxPro.getMin();
        maxPro=minMaxPro.getMax();
        //verify invariants
        CPart=propList.stream().map(p->new GProperty(p)).collect(Collectors.toList());
//        RPart=new GPattReq(pattReq);
//        objectReqList=;


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

    @Override
    public GenAssertion fromWitness(WitnessAssertion w) {
        return null;
    }
}
