package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;

import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessVar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GenVar implements GenAssertion{
    private static Logger logger = LogManager.getLogger(GenVar.class);

    private String name ;
    private List<GenVar> uses;
    private List<GenVar> isUsedBy;

    @Override
    public String toString() {
        return "GenVar{" +
                "name='" + name + '\'' +
                ", uses=" + uses +
                ", isUsedBy=" + isUsedBy +
                '}'+_sep ;
    }

    public String getName() {
        return name;
    }

    public GenVar(String varname) {
        name=varname;
        logger.debug("Created a var named {} ",this.name);

    }

    public boolean isOpen() {
        return false; //TODO implement
    }

    public boolean isEmpty() {
        return false; //TODO implement
    }

    public boolean isPop() {
        return false; //TODO implement
    }

    public boolean isTrue(){
        return false; //TODO implment
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
