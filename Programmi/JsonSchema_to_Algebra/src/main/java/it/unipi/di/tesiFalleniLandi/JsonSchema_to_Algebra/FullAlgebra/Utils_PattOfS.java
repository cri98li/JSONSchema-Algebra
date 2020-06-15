package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import patterns.Pattern;

import java.util.List;

public class Utils_PattOfS {
    //Vedi definizione sul documento

    private static Pattern truePattern = Pattern.createFromRegexp(".*");
    //private static String falsePattern ="(?!x)x"; //TODO: https://stackoverflow.com/questions/1723182/a-regex-that-will-never-be-matched-by-anything
    private static Pattern falsePattern = truePattern.complement();

    public static Pattern pattOfS(Assertion a){
        //return new Pattern(truePattern);
        return truePattern.clone(); //TODO: PattOfS
    }

    public static Pattern pattOfS(Type_Assertion a){
        if(a.contains(GrammarStringDefinitions.TYPE_STRING)) return truePattern.clone();

        return falsePattern.clone();
    }

    public static Pattern pattOfS(Const_Assertion a){
        Object obj = a.getValue();
        if(obj.getClass() == String.class) Pattern.createFromName((String) obj);

        return falsePattern.clone();
    }

    public static Pattern pattOfS(AllOf_Assertion a){
        List<Assertion> and = a.getAndList();
        Pattern p = pattOfS(and.get(0));
        for(int i = 1; i < and.size(); i++)
            p = p.intersect(pattOfS(and.get(i)));

        return p;
    }

    public static Pattern pattOfS(AnyOf_Assertion a){
        List<Assertion> or = a.getOrList();
        Pattern p = pattOfS(or.get(0));
        for(int i = 1; i < or.size(); i++)
            p = p.union(pattOfS(or.get(i)));

        return p;
    }

    public static Pattern pattOfS(Not_Assertion a){
        return pattOfS(a.getValue()).complement();
    }

    public static Pattern pattOfS(Pattern_Assertion a){
        return a.getValue();
    }

    public static Pattern pattOfS(Ref_Assertion a){
        return pattOfS(Defs_Assertion.env.getDef(a.getRef()));
    }
}
