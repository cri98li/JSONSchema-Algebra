package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.ComplexPattern;

import com.google.gson.JsonElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.Pattern;
import patterns.REException;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Estensione della classe Pattern di stefanie. Ci consente di fare ottimizzazioni e aggiungere funzionalità
 * senza ostacolare la collaborazione con Stefanie
 */
public class ComplexPattern implements ComplexPatternElement {
    /**
     * Offrono funzionalità di caching per azioni già performate. Vedi file f10188.json negazione
     */
    private static HashMap<String, ComplexPattern> patternCache = new HashMap<>();
    private static HashMap<String, ComplexPattern> complementCache = new HashMap<>(); //file 10188

    private ComplexPatternElement assertion;
    private String originalPattern;
    private Pattern stefaniePattern;

    private static Logger logger = LogManager.getLogger(ComplexPattern.class);

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

        logger.trace("Intersection between {} and {} = {}", this, p, cp);

        return cp;
    }

    public ComplexPattern minus(ComplexPattern p) {
        ComplexPattern cp = new ComplexPattern(this.stefaniePattern.minus(p.stefaniePattern));

        pAllOf and = new pAllOf();
        and.add(this.assertion == null ? this : this.assertion);
        and.add(p.assertion == null ? pNot.createPNot(p) : pNot.createPNot(p.assertion));

        cp.assertion = and;

        logger.trace("Minus between {} and {} = {}", this, p, cp);

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
            return complementCache.get(this.toString());
        }

        ComplexPattern cp = new ComplexPattern(this.stefaniePattern.complement());
        cp.assertion = pNot.createPNot(this);

        complementCache.put(this.toString(), cp);

        logger.trace("Complement {} = {}", this, cp);

        return cp;
    }

    public static ComplexPattern createFromName(String patternName) {
        if(patternCache.containsKey("name: "+patternName)) return patternCache.get("name: "+patternName);

        ComplexPattern tmp = new ComplexPattern(Pattern.createFromName(patternName), "^"+patternName+"$");
        patternCache.put("name: "+patternName, tmp);

        logger.trace("Creating a ComplexPattern from Name: {}", tmp);

        return tmp;
    }

    public static ComplexPattern createFromRegexp(String ecmaRegex) throws REException {
        if(patternCache.containsKey("regexp: "+ecmaRegex))
            return patternCache.get("regexp: "+ecmaRegex);
        ComplexPattern tmp = new ComplexPattern(Pattern.createFromRegexp(ecmaRegex), ecmaRegex);
        patternCache.put("regexp: "+ecmaRegex, tmp);

        logger.trace("Creating a ComplexPattern from RegExp: {}", tmp);

        return tmp;
    }

    public boolean isSubsetOf(ComplexPattern p) {
        boolean result = this.stefaniePattern.isSubsetOf(p.stefaniePattern);

        logger.trace("isSubsetOf={}, between {} and {}", result, this, p);

        return result;
    }

    public boolean isEquivalent(ComplexPattern p) {
        boolean result = this.stefaniePattern.isEquivalent(p.stefaniePattern);

        logger.trace("isEquivalent={}, between {} and {}", result, this, p);

        return result;
    }


    public Float domainSize() {
        Float result = Float.POSITIVE_INFINITY;
        if(this.stefaniePattern.domainSize() != null)
             result = Float.valueOf(this.stefaniePattern.domainSize());

        logger.trace("domainSize of {} = {}", this, result);

        return result;
    }

    public Collection<String> generateWords() {
        Collection<String> collection = this.stefaniePattern.generateWords();
        logger.trace("generateWords = {}", collection);
        return collection;
    }

    public ComplexPattern clone() {
        logger.trace("cloning ComplexPattern = {}", this);
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
        boolean result = Pattern.overlaps(left.stefaniePattern, right.stefaniePattern);

        logger.trace("overlaps={} between {} and {}", result, left, right);

        return result;
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
        return (originalPattern == null) ? assertion.toString() : "\""+originalPattern+"\"";
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
