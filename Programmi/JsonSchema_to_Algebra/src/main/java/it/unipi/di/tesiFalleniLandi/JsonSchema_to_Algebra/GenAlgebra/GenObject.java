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
        private GenVar schema;

//        public GProperty(WitnessProperty prop){
//            WitnessAssertion value = prop.getValue();
//            if (value.getClass() == WitnessBoolean.class)
//                schema = new GenBool((WitnessBoolean) value);
//            else if(value.getClass()==WitnessVar.class)
//                schema = new GenVar((WitnessVar) value);
//            else
//                new Exception("Properties must be normalized and map to WitnessVar or WitnessBool");
//            key=prop.getPattern();
//        }
    }

    public class GOrPattReq {
        private List<GPattReq> reqList;
        public GOrPattReq(WitnessOrPattReq wor){
//            reqList=wor.getReqList().stream().map(w->new GPattReq(w)).collect(Collectors.toList());
        }
    }

    public class GPattReq {
        private ComplexPattern key;
        private GenVar schema;
//        private List<GOrPattReq> orpList;
//        private boolean isSimple;
//        public GPattReq(WitnessPattReq pattReq){
//            WitnessAssertion value = pattReq.getValue();
//
//            if (value.getClass()==WitnessBoolean.class)
//                schema = new GenBool((WitnessBoolean) value);
//            else if(value.getClass()==WitnessVar.class)
//                schema = new GenVar((WitnessVar) value);
//            else
//                new Exception("Request Properties must be normalized and map to WitnessVar or WitnessBool");
//            key=pattReq.getPattern();
//            //            orpList=wpr.getOrpList().stream().map(e->new GOrPattReq(e)).collect(Collectors.toList());
//        }
    }

    /*Methods*/

    public GenObject(List<WitnessProperty> propList,
                     List<WitnessOrPattReq> orPattReqList,
                     WitnessPro minMaxPro){
        minPro=minMaxPro.getMin();
        maxPro=minMaxPro.getMax();

        //check later that propList contains only Var or true
//        CPart=propList.stream().map(p->new GProperty(p)).collect(Collectors.toList());
//        RPart=orPattReqList.stream().map(p->new GOrPattReq(p)).collect(Collectors.toList());



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
