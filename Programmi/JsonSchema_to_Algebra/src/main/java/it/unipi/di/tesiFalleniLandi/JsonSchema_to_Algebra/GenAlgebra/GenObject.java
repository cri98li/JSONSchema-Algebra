package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;

import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.ComplexPattern.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenObject implements GenAssertion {
    private static Logger logger = LogManager.getLogger(GenObject.class);
    private JsonElement witness;


    private Double minPro, maxPro;
    private List<GProperty> CPart;
    private List<GOrPattReq> RPart;
    private List<GPattReq> objectReqList;


    @Override
    public String toString() {
        return "GenObject{" + _sep +
                "minPro=" + minPro +_sep +
                ", maxPro=" + maxPro +_sep +
                ", CPart=" + CPart +_sep +
                ", RPart=" + RPart +_sep +
                ", objectReqList=" + objectReqList +_sep +
                '}'+ _sep;
    }

    @Override
    public JsonElement getWitness() {
        return witness;
    }

    /*local classes*/
    /* WitnessProperty counterpart*/
    public class GProperty {
        private ComplexPattern key;
        private GenVar schema;

        public GenVar usedVar() {
            return schema;
        }

        @Override
        public String toString() {
            return "GProperty{" +
                    "key=" + key +
                    ", schema=" + schema +
                    '}';
        }

        public GProperty(WitnessProperty prop){
            WitnessAssertion value = prop.getValue();
            if (value.getClass() == WitnessBoolean.class)
                schema = ((WitnessBoolean) value).getValue()==true?new GenVarTrue("dummy"):new GenVarFalse("dummy");
            else if(value.getClass()==WitnessVar.class)
                schema = new GenVar( ((WitnessVar) value).getName());
            else
                new Exception("Properties must be normalized and map to WitnessVar or WitnessBool");
            key=prop.getPattern();
        }
    }

    /*WitnessPattReq counterpart*/
    public class GPattReq {
        private ComplexPattern key;
        private GenVar schema;
        private List<GOrPattReq> orpList;
//        private boolean isSimple;

        public GenVar usedVar() {
            return schema;
        }

        @Override
        public String toString() {
            return "GPattReq{" +
                    "key=" + key +
                    ", schema=" + schema +
                    ", orpList=" + orpList +
                    '}';
        }

        public GPattReq(WitnessPattReq pattReq){
            WitnessAssertion value = pattReq.getValue();
            if (value.getClass()==WitnessBoolean.class)
                schema = ((WitnessBoolean) value).getValue()==true?new GenVarTrue("dummy"):new GenVarFalse("dummy");
            else if(value.getClass()==WitnessVar.class)
                schema = new GenVar( ((WitnessVar) value).getName());
            else
                new Exception("Request Properties must be normalized and map to WitnessVar or WitnessBool");
            key=pattReq.getPattern();
//            orpList=pattReq.getOrpList()
//                    .stream().map(e->new GOrPattReq(e)).collect(Collectors.toList());
        }
    }


    /*WitnessOrPattReq counterpart*/
    public class GOrPattReq {
        private List<GPattReq> reqList;

        public List<GenVar> usedVars() {
            return reqList.stream().map(p->p.usedVar()).collect(Collectors.toList());
        }

        @Override
        public String toString() {
            return "GOrPattReq{" +
                    "reqList=" + reqList +
                    '}';
        }

        public GOrPattReq(WitnessOrPattReq witnessOrPattReq){
            reqList = new LinkedList<>();
            reqList = witnessOrPattReq.getReqList().stream().map(w->new GPattReq(w)).collect(Collectors.toList());
//            for(WitnessPattReq witnessPattReq: witnessOrPattReq.getReqList())
//                reqList.add(new GPattReq(witnessPattReq));
        }

        /**
         * returns true when all variables are empty or when the request list is empty
         * @return
         */
        public boolean allVarsEmpty(){
            Optional<Boolean> res = null;
            res = this.reqList.stream().map(req->req.usedVar().isEmpty())
                    .reduce((a,b)->a&&b);
            if(res.isPresent())
                return res.get();
            else
                return true; //empty list is considered to be Empty
        }
    }


    /*Methods*/

    public GenObject() {
        this.CPart = new LinkedList<>();
        this.RPart = new LinkedList<>();
        this.objectReqList = new LinkedList<>();
    }

    public void setCPart(List<WitnessProperty> propList) {
        this.CPart = propList.stream().map(p->new GProperty(p)).collect(Collectors.toList());
    }

    public void setRPart(List<WitnessOrPattReq> orPattReqList) {
        this.RPart = orPattReqList.stream().map(p->new GOrPattReq(p)).collect(Collectors.toList());
    }

    public void setObjectReqList() {
        if(this.RPart.size()>0){
            //flatten RPart
            List<GPattReq> gPattReqList = new LinkedList<>();
            for(GOrPattReq gOrPattReq: RPart)
                for(GPattReq gPattReq: gOrPattReq.reqList)
                    this.objectReqList.add(gPattReq);
        }
    }

//    public void setObjectReqList(List<GPattReq> objectReqList) {
//        this.objectReqList = objectReqList;
//    }

    public void setMinMaxPro( WitnessPro minMaxPro){
        minPro=minMaxPro.getMin();
        maxPro=minMaxPro.getMax();
    }

    //TODO invariants

    //aux methods
    //TODO implement
    private List<List<GPattReq>> optimizedHittingSet(){
        return new LinkedList<>();
    }

    //TODO implement
    private boolean noPatternIsRepeatedTooOften(List<GPattReq> set){
        return false;
    }

    private boolean allVarsPop(List<GPattReq> list){
        Optional<Boolean> tv = list.stream().map(p->p.usedVar().isPop()).reduce((a,b)->a&&b);
        if(tv.isPresent())
            return tv.get();
        else
            return false;
    }

    @Override
    public statuses generate() {
        for(GOrPattReq orp: RPart)
            if(orp.allVarsEmpty())
                return statuses.Empty;

        int s = CPart.stream().filter(p->p.usedVar().isOpen()||p.usedVar().isPop())
                .collect(Collectors.toList()).size();
        if(s<minPro)
            return statuses.Empty;

//        List<List<GPattReq>> solutionSet = optimizedHittingSet();
        List<List<GPattReq>> reducedSolutionSet = optimizedHittingSet().stream()
                .filter(set->set.size()<maxPro&&noPatternIsRepeatedTooOften(set))
                .collect(Collectors.toList());
        if(reducedSolutionSet.isEmpty())
            return statuses.Empty;

        List<List<GPattReq>> popSolutionSet = reducedSolutionSet.stream().filter(l->allVarsPop(l))
                .collect(Collectors.toList());
        if(popSolutionSet.isEmpty())
            return statuses.Open;

        //pick solution randomly
        Random r = new Random();
        int position = r.nextInt(popSolutionSet.size()-1);
        List<GPattReq> solution = popSolutionSet.get(position);


        return statuses.Populated;
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
        List<GenVar> cpart_vars = CPart.stream().map(gp->gp.usedVar()).collect(Collectors.toList());
        List<GenVar> rpart_vars = RPart.stream().flatMap(op->op.usedVars().stream()).collect(Collectors.toList());
        return Stream.concat(cpart_vars.stream(), rpart_vars.stream()).distinct().collect(Collectors.toList());
    }


}
