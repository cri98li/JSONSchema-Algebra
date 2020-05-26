package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common;

import java.util.Set;

/**
 * Interface to represent a pattern and them minimum set of operations we need to perform
 */
public interface PosixPattern {
    public PosixPattern intersect(PosixPattern p);

    public PosixPattern minus(PosixPattern p);

    /**
     *
     * @return the domain size of the current regex if the domain is finite, null otherwise
     */
    public Integer domainSize();

    /**
     *
     * @return a finite set of words of the pattern domain
     */
    public Set<String> generateWords();

    /**
     * Match a given string with the current
     * @param str
     * @return true if the string match the pattern, false if it doesn't
     */
    public boolean match(String str);
}
