package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.ComplexPattern.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

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

        public String generateName(){
            return key.generateWords().iterator().next();
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

        public List<GOrPattReq> getOrpList() {
            return orpList;
        }

        public float patternDomainSize(){
            return key.domainSize();
        }
        public String generateName(){
            return key.generateWords().iterator().next();
        }

        public JsonElement getWitness(){
            return schema.getWitness();
        }
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

    //TODO implement invariants

    private boolean inVariant1(){
        Optional<Boolean> opt = this.RPart.stream().map(orp->orp.reqList.isEmpty()).reduce((a,b)->a&&b);
        if(opt.isPresent())
            return opt.get();
        else
            return true; //RPart is true
    }
    //aux methods

    private List<List<GPattReq>> hittingSet(List<GPattReq> requests, Integer from, List<GOrPattReq> orpList){
        if(orpList.isEmpty())
            return new LinkedList<>();
        GPattReq firstReq = requests.get(from);
        List<GOrPattReq> tailOrp = orpList.stream().filter(orp->!firstReq.getOrpList().contains(orp)).collect(Collectors.toList());
        List<List<GPattReq>> solWithFirst = new LinkedList<>();
        for(List<GPattReq> solutionOfRest: hittingSet(requests, from+1, tailOrp))
            solWithFirst.add(solutionOfRest);
        List<List<GPattReq>> solWithoutFirst = hittingSet(requests, from +1, orpList);
        solWithFirst.addAll(solWithoutFirst);
        return solWithFirst;
    }

    private boolean isMinimal(List<GPattReq> sol, List<GOrPattReq> orpList){return true;}

    private List<List<GPattReq>> optimizedHittingSet() throws Exception {
        List<List<GPattReq>> result = new LinkedList<>();
        Set<GPattReq> simpleResult;
        //ensure variant 1
        if(!inVariant1())
            throw new Exception("invariant1 non verified!");
        //collect all requests belonging to orp with 1 request only
        simpleResult = RPart.stream().filter(orp->orp.reqList.size()==1)
                .map(orp->orp.reqList.get(0)).collect(Collectors.toSet());
        if(simpleResult.size()==RPart.size()){
            result.add(List.copyOf(simpleResult));
            return result;
        }
        //proceed with the remaining requests
        List<GOrPattReq> remainingOrpsList = RPart.stream().collect(Collectors.toList());
        remainingOrpsList.removeAll(simpleResult.stream().flatMap(e->e.getOrpList().stream()).collect(Collectors.toList()));
        List<GPattReq> remainingRequests = objectReqList.stream().collect(Collectors.toList());
        remainingRequests.removeAll(simpleResult);
        result = hittingSet(remainingRequests,0,remainingOrpsList)
                .stream().filter(l->isMinimal(l,remainingOrpsList))
                .collect(Collectors.toList());
        return  result;
    }

    /**
     * checks whether no pattern can generate
      * @param set
     * @return
     */
    private boolean noPatternIsRepeatedTooOften(List<GPattReq> set){
        Map<GPattReq,Long> grouped = set.stream().collect(groupingBy(Function.identity(), Collectors.counting()));
        Optional<Boolean> opt = grouped.keySet().stream().map(p->grouped.get(p)<p.patternDomainSize()).reduce((a,b)->a&&b);
        if(opt.isPresent())
            return opt.get();
        else
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
    public statuses generate()  {
        for(GOrPattReq orp: RPart)
            if(orp.allVarsEmpty())
                return statuses.Empty;

        int s = CPart.stream().filter(p->p.usedVar().isOpen()||p.usedVar().isPop())
                .collect(Collectors.toList()).size();
        if(s<minPro)
            return statuses.Empty;

//        List<List<GPattReq>> solutionSet = optimizedHittingSet();
        List<List<GPattReq>> reducedSolutionSet = null;
        try {
            reducedSolutionSet = optimizedHittingSet().stream()
                    .filter(set->set.size()<maxPro&&noPatternIsRepeatedTooOften(set))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        //generate partial properties set
        HashMap<String,JsonElement> properties = new HashMap<>();
        solution.stream().forEach(req->properties.put(req.generateName(),req.getWitness()));
        if(properties.size()>=minPro){
            //no need to generate more, may have generate more the maxPro, though
            witness = new JsonObject();
            properties.forEach((p,v)->((JsonObject) witness).add(p,v));
            return statuses.Populated;
        }
        //pick up some properties from CPart
        Set<String> usedNames = properties.keySet();
        List<GProperty> populatedCpart = CPart.stream().filter(p->p.usedVar().isPop()).collect(Collectors.toList());
        if(populatedCpart.size()<minPro)
            return statuses.Open; //var remains open until more usedVars become pop
        while(properties.size()<minPro){
            populatedCpart = populatedCpart.stream().filter(p->!usedNames.contains(p.generateName()))
                    .collect(Collectors.toList());
            GProperty newp = populatedCpart.remove(0);
            String name = newp.generateName();
            properties.put(name,newp.usedVar().getWitness());
            usedNames.add(name);
        }
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
