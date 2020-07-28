package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common;

import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.AlgebraParserElement;
import org.apache.http.io.HttpMessageParserFactory;
import patterns.Pattern;
import patterns.REException;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ComplexPattern implements ComplexPatternElement {

    private static HashMap<String, ComplexPattern> patternCache = new HashMap<>();
    private static HashMap<String, ComplexPattern> complementCache = new HashMap<>();

    private ComplexPatternElement assertion;
    private String originalPattern;
    private Pattern stefaniePattern;

    protected ComplexPattern(Pattern stefaniePattern, String originalPattern) {
        this.stefaniePattern = stefaniePattern;
        this.originalPattern = originalPattern;
    }

    protected ComplexPattern(Pattern p){
        stefaniePattern = p;
    }

    public ComplexPattern intersect(ComplexPattern p) {
        ComplexPattern cp = new ComplexPattern(this.stefaniePattern.intersect(p.stefaniePattern));

        pAllOf and = new pAllOf();
        and.add(this.assertion == null ? this : this.assertion);
        and.add(p.assertion == null ? p : p.assertion);
        cp.assertion = and;

        return cp;
    }

    public ComplexPattern minus(ComplexPattern p) {
        ComplexPattern cp = new ComplexPattern(this.stefaniePattern.minus(p.stefaniePattern));

        pAllOf and = new pAllOf();
        and.add(this.assertion == null ? this : this.assertion);
        and.add(p.assertion == null ? new pNot(p) : new pNot(p.assertion));

        cp.assertion = and;

        return cp;
    }

    public ComplexPattern union(ComplexPattern p) {
        ComplexPattern cp = new ComplexPattern(this.stefaniePattern.union(p.stefaniePattern));

        pAnyOf or = new pAnyOf();
        or.add(this.assertion == null ? this : this.assertion);
        or.add(p.assertion == null ? p : p.assertion);
        cp.assertion = or;

        return cp;
    }

    public ComplexPattern complement() {
        if(complementCache.containsKey(this.toString())) {
            System.out.println("lmao-");
            return complementCache.get(this.toString());
        }

        ComplexPattern cp = new ComplexPattern(this.stefaniePattern.complement());
        cp.assertion = new pNot(this);

        //complementCache.put(this.toString(), cp);

        return cp;
    }

    public static ComplexPattern createFromName(String patternName) {
        if(patternCache.containsKey(patternName)) return patternCache.get(patternName);

        ComplexPattern tmp = new ComplexPattern(Pattern.createFromName(patternName), "^"+patternName+"$");
        patternCache.put(patternName, tmp);

        return tmp;
    }

    public static ComplexPattern createFromRegexp(String ecmaRegex) throws REException {
        if(patternCache.containsKey(ecmaRegex))
            return patternCache.get(ecmaRegex);
        ComplexPattern tmp = new ComplexPattern(Pattern.createFromRegexp(ecmaRegex), ecmaRegex);
        patternCache.put(ecmaRegex, tmp);

        return tmp;
    }

    public boolean isSubsetOf(ComplexPattern p) {
        return this.stefaniePattern.isSubsetOf(p.stefaniePattern);
    }

    public boolean isEquivalent(ComplexPattern p) {
        return this.stefaniePattern.isEquivalent(p.stefaniePattern);
    }


    public Float domainSize() {
        if(this.stefaniePattern.domainSize() == null)
            return Float.POSITIVE_INFINITY;
        return Float.valueOf(this.stefaniePattern.domainSize());
    }

    public Collection<String> generateWords() {
        return this.stefaniePattern.generateWords();
    }

    public ComplexPattern clone() {
        ComplexPattern clone = new ComplexPattern(stefaniePattern.clone());
        if(assertion != null)
            clone.assertion = assertion.clone(); //TODO: check
        if(originalPattern != null)
            clone.originalPattern = originalPattern.toString();
        return clone;
    }


    public static ComplexPattern listComplement(Collection<ComplexPattern> patterns) {
        ComplexPattern u = null;

        for (ComplexPattern p : patterns) {
            if (u == null)
                u = p;
            else
                u = u.union(p);
        }

        return u.complement();
    }

    public static boolean overlaps(ComplexPattern left, ComplexPattern right) {
        return Pattern.overlaps(left.stefaniePattern, right.stefaniePattern);
    }

    public static boolean overlaps(Collection<ComplexPattern> patterns) {
        List<Pattern> patternList = new LinkedList<>();
        for(ComplexPattern p : patterns){
            patternList.add(p.stefaniePattern);
        }

        return Pattern.overlaps(patternList);
    }

    @Override
    public String toString() {
        return (originalPattern == null) ? assertion.toString() : originalPattern; //"\"" + ... + "\""
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ComplexPattern pattern = (ComplexPattern) o;
        return this.isEquivalent(pattern);
    }


    public String getAlgebraString(){
        return this.toString();
    }


    /**
     * //TODO: andrebbe scritto come if(complexPattern) then Assertion??
     * @return
     */
    public JsonElement toJSONSchema(){
        throw new UnsupportedOperationException("ComplexPattern.toJSON non è supportata");
        //return assertion.toJSONSchema(); ??
    }

    public boolean isComplex(){
        return originalPattern == null;
    }

    public String getOriginalPattern() {
        return originalPattern;
    }

    public boolean isEmptyDomain(){
        return this.domainSize() == 0;
    }
}
