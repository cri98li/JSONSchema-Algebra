package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.MyPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.PosixPattern;

import java.util.List;

public class Utils_PattOfS {

    private static String falsePattern ="$^";
    private static String truePattern =".*";

    public static PosixPattern pattOfS(Assertion a){
        return new MyPattern(truePattern);
    }

    public static PosixPattern pattOfS(Type_Assertion a){
        if(a.contains(GrammarStringDefinitions.TYPE_STRING)) return new MyPattern(truePattern);

        return new MyPattern(truePattern);
    }

    public static PosixPattern pattOfS(Const_Assertion a){
        Object obj = a.getValue();
        if(obj.getClass() == String.class) return new MyPattern((String) obj);

        return new MyPattern(falsePattern);
    }

    public static PosixPattern pattOfS(AllOf_Assertion a){
        List<Assertion> and = a.getAndList();
        PosixPattern p = pattOfS(and.get(0));
        for(int i = 0; i < and.size(); i++)
            p = p.and(pattOfS(and.get(i)));

        return p;
    }

    public static PosixPattern pattOfS(AnyOf_Assertion a){
        List<Assertion> or = a.getOrList();
        PosixPattern p = pattOfS(or.get(0));
        for(int i = 0; i < or.size(); i++)
            p = p.or(pattOfS(or.get(i)));

        return p;
    }

    public static PosixPattern pattOfS(Not_Assertion a){
        return pattOfS(a.getValue()).complement();
    }

    public static PosixPattern pattOfS(Pattern_Assertion a){
        return a.getValue();
    }

    public static PosixPattern pattOfS(Ref_Assertion a){
        //TODO: da chidere (pag 16 PattOfS(x, e) --> PattOfS(e(x), e)
        return pattOfS(Defs_Assertion.env.getDef(a.getRef()));
    }
}
