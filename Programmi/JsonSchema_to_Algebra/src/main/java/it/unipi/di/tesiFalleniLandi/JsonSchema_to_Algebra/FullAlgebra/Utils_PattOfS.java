package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import patterns.Pattern;

import java.util.List;

public class Utils_PattOfS {
    //Vedi definizione sul documento

    private static String falsePattern ="$^"; //TODO: check
    private static String truePattern =".*"; //TODO: check

    public static Pattern pattOfS(Assertion a){
        //return new Pattern(truePattern);
        return Pattern.createFromName(truePattern); //TODO: PattOfS
    }

    public static Pattern pattOfS(Type_Assertion a){
        if(a.contains(GrammarStringDefinitions.TYPE_STRING))Pattern.createFromName(truePattern);/*return new Pattern(truePattern);*/ //TODO: ??

        /*return new Pattern(truePattern);*/
        return Pattern.createFromName(truePattern); //TODO : ??
    }

    public static Pattern pattOfS(Const_Assertion a){
        Object obj = a.getValue();
        if(obj.getClass() == String.class) Pattern.createFromRegexp((String) obj);/*return new Pattern((String) obj);*/ //TODO : PattOfS

        /*return new Pattern(falsePattern);*/
        return Pattern.createFromName(falsePattern); //TODO: PattOfS
    }

    public static Pattern pattOfS(AllOf_Assertion a){
        List<Assertion> and = a.getAndList();
        Pattern p = pattOfS(and.get(0));
        for(int i = 0; i < and.size(); i++)
            p = p.intersect(pattOfS(and.get(i))); //TODO: pattOfS intersect

        return p;
    }

    public static Pattern pattOfS(AnyOf_Assertion a){
        List<Assertion> or = a.getOrList();
        Pattern p = pattOfS(or.get(0));
        for(int i = 0; i < or.size(); i++)
            p = p.intersect(pattOfS(or.get(i))); //TODO: pattOfS union

        return p;
    }

    public static Pattern pattOfS(Not_Assertion a){
        //return pattOfS(a.getValue()).complement(); TODO: pattOfS
        return null;
    }

    public static Pattern pattOfS(Pattern_Assertion a){
        return a.getValue();
    }

    public static Pattern pattOfS(Ref_Assertion a){
        //TODO: da chidere (pag 16 PattOfS(x, e) --> PattOfS(e(x), e)
        return pattOfS(Defs_Assertion.env.getDef(a.getRef()));
    }
}
