package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.ComplexPattern.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.AlgebraStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.Pattern;
import patterns.REException;

import java.util.List;

public class Utils_PattOfS {
    private static ComplexPattern truePattern;
    private static ComplexPattern falsePattern;

    private static Logger logger = LogManager.getLogger(Utils_PattOfS.class);


    static {
        try {
            truePattern = ComplexPattern.createFromRegexp(".*");
        } catch (REException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        falsePattern = truePattern.complement();
    }

    public static ComplexPattern pattOfS(Assertion a){
        ComplexPattern returnPattern = truePattern.clone();

        if(a.getClass() == Type_Assertion.class)
            returnPattern = pattOfS((Type_Assertion) a);

        if(a.getClass() == Const_Assertion.class)
            returnPattern = pattOfS((Const_Assertion) a);

        if(a.getClass() == AllOf_Assertion.class)
            returnPattern = pattOfS((AllOf_Assertion) a);

        if(a.getClass() == AnyOf_Assertion.class)
            returnPattern = pattOfS((AnyOf_Assertion) a);

        if(a.getClass() == Not_Assertion.class)
            returnPattern = pattOfS((Not_Assertion) a);

        if(a.getClass() == Pattern_Assertion.class)
            returnPattern = pattOfS((Pattern_Assertion) a);

        if(a.getClass() == Ref_Assertion.class)
            returnPattern = pattOfS((Ref_Assertion) a);

        logger.trace("PattOfS({}) returning {}", a, returnPattern);

        return returnPattern;
    }

    public static ComplexPattern pattOfS(Type_Assertion a){
        if(a.contains(AlgebraStrings.TYPE_STRING)) return truePattern.clone();

        return falsePattern.clone();
    }

    public static ComplexPattern pattOfS(Const_Assertion a){
        Object obj = a.getValue();
        if(obj.getClass() == String.class) Pattern.createFromName((String) obj);

        return falsePattern.clone();
    }

    public static ComplexPattern pattOfS(AllOf_Assertion a){
        List<Assertion> and = a.getAndList();
        ComplexPattern p = pattOfS(and.get(0));
        for(int i = 1; i < and.size(); i++)
            p = p.intersect(pattOfS(and.get(i)));

        return p;
    }

    public static ComplexPattern pattOfS(AnyOf_Assertion a){
        List<Assertion> or = a.getOrList();
        ComplexPattern p = pattOfS(or.get(0));
        for(int i = 1; i < or.size(); i++)
            p = p.union(pattOfS(or.get(i)));

        return p;
    }

    public static ComplexPattern pattOfS(Not_Assertion a){
        return pattOfS(a.getValue()).complement();
    }

    public static ComplexPattern pattOfS(Pattern_Assertion a){
        return a.getValue();
    }

    public static ComplexPattern pattOfS(Ref_Assertion a, Defs_Assertion env){
        return pattOfS(env.getDef(a.getRef()));
    }
}
