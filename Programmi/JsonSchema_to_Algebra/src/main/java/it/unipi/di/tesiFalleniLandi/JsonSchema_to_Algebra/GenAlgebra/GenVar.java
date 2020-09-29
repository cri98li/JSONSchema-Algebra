package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;

import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessVar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GenVar implements GenAssertion{
    private static Logger logger = LogManager.getLogger(GenVar.class);
//    enum statuses {Open, Sleeping, Empty, Populated };

    private String name ;
    private List<GenVar> uses;
    private List<GenVar> isUsedBy;
    private int evalOrder;
    private statuses status;

    public statuses getStatus() {
        return status;
    }

    public void setStatus(statuses status) {
        this.status = status;
    }

    public void setEvalOrder(boolean containsBaseType) {
        this.evalOrder = this.inDegree();
        if(containsBaseType) //lower the score in case baseType
            this.evalOrder --;
    }

    public boolean allVarsPopOrEmp() {
        Optional<Boolean> obj = uses.stream().map(v -> v.isOpen() || v.isEmpty()).reduce((b1, b2) -> b1 && b2);
        return obj.orElse(true); //if uses is empty
    }

        public int getEvalOrder() {
        return evalOrder;
    }

    public List<GenVar> getUses() {
        return this.uses;
    }

    public void addIsUsedBy(GenVar using) {
        this.isUsedBy.add(using);
    }

    public void setIsUsedBy(List<GenVar> isUsedBy) {
        this.isUsedBy = isUsedBy;
    }

    private int inDegree(){
        return isUsedBy.size();
    }

    private int outDegree(){
        return uses.size();
    }

    public void setUses(List<GenAssertion> genAssertionList) {
        this.uses = genAssertionList.stream()
                .flatMap(a->a.usedVars().stream())
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "GenVar{" +
                "name='" + name + '\'' +
                ", uses=" + uses.stream().map(v->v.getName()).collect(Collectors.toList())  +
//                ", isUsedBy=" + isUsedBy +
                ", isUsedBy=" + isUsedBy.stream().map(v->v.getName()).collect(Collectors.toList()) +
                '}'+_sep ;
    }

    public String getName() {
        return name;
    }

    public GenVar(String varname) {
        name=varname;
        this.isUsedBy = new LinkedList<>();
        this.uses = new LinkedList<>();
        logger.debug("Created a var named {} ",this.name);

    }


    public boolean isOpen() {
        return status==statuses.Open;
    }

    public boolean isEmpty() {return status==statuses.Empty;}

    public boolean isPop() {
        return status==statuses.Populated;
    }

    public boolean isSleeping(){
        return status==statuses.Sleeping;
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
    public List<GenVar> usedVars() {
        return new LinkedList<>();
    }


}
