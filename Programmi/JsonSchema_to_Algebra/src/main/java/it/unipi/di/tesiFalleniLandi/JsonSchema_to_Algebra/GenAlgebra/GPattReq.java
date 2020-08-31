package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.ComplexPattern.ComplexPattern;

import java.util.List;

public class GPattReq {
    private ComplexPattern key;
    private GenVar schema;
    private List<GOrPattReq> orpList;
    private boolean isSimple;
}
