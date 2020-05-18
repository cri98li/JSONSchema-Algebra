package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common;

import java.util.Set;

/**
 * Interface to represent a pattern and them minimum set of operations we need to perform
 */
public interface PosixPattern {

    /**
     * @return the complement of the current PosixPattern
     */
    public PosixPattern complement();

    /**
     * @param p
     * @return the union of the current PosixPattern with a given one (OR)
     */
    public PosixPattern or(PosixPattern p);

    /**
     *
     * @param p
     * @return a new pattern that match only the string matched by this and p
     */
    public PosixPattern and(PosixPattern p);

    /**
     *
     * @return the current pattern if representable with regex, null otherwise
     */
    public String getPattern();

    /**
     *
     * @return true if the current pattern satisfies the given one
     */
    public boolean contains(PosixPattern p);

    /**
     *
     * @return the domain size of the current regex if the domain is finite, null otherwise
     */
    public Integer domainSize();

    /**
     *
     * @return a finite set of words of the pattern domain
     */
    public Set<String> generateWord();

    /**
     * Match a given string with the current
     * @param str
     * @return true if the string match the pattern, false if it doesn't
     */
    public boolean match(String str);
}
