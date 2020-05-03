package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common;

public interface PosixPattern {

    public PosixPattern complement();

    public PosixPattern join(PosixPattern p);

    public PosixPattern[] intersect(PosixPattern p);

    public PosixPattern and(PosixPattern p);

    public String getPattern();

    public boolean contains();
}
