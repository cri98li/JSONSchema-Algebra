package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

//https://stackoverflow.com/questions/8610743/how-to-negate-any-regular-expression-in-java
//https://www.brics.dk/automaton/

public class MyPattern implements PosixPattern{

    public static final String NONPRINTABLEREGEX = "no printable regex";

    public static void main(String[] args){
        //From the example at page 37

        MyPattern p1 = new MyPattern(".d@");
        MyPattern p2 = new MyPattern("a.");

        MyPattern intersezione = p1.and(p2);

        System.out.println("INPUT \"a\": \t p1: " + p1.match("a") + "\tp2: " + p2.match("a") + "\tintersect: " + intersezione.match("a"));

        System.out.println("INPUT \"ad\": \t p1: " + p1.match("ad") + "\tp2: " + p2.match("ad") + "\tintersect: " + intersezione.match("ad"));

        System.out.println(p2.domainSize());

        System.out.println(Arrays.toString(p2.generateWord().toArray()));

    }


    private Automaton automaton;
    private final RegExp regex;

    public MyPattern(MyPattern pattern) {
        regex = pattern.regex;
        automaton = pattern.automaton.clone();
    }

    public MyPattern(String pattern) {
        regex = new RegExp(pattern);
        automaton = regex.toAutomaton();
    }

    @Override
    public String getPattern() {
        if(regex != null)
            return regex.toString();
        return NONPRINTABLEREGEX;
    }

    @Override
    public boolean contains(PosixPattern p) { //TODO verificare
        MyPattern pattern = (MyPattern) p;
        return this.and(p).equals(pattern);
    }

    @Override
    public Integer domainSize() {
        if(automaton.isFinite())
            return automaton.getFiniteStrings().size();
        else
            return null;
    }

    @Override
    public Set<String> generateWord() {
        if(domainSize() == null)
            return automaton.getStrings(50);

        return  automaton.getFiniteStrings();
    }

    @Override
    public boolean match(String str) {
        return automaton.run(str);
    }

    public String toString(){
        if(regex != null)
            return regex.toString();
        else
            return automaton.toDot();
    }

    @Override
    public PosixPattern complement() {
        MyPattern p = new MyPattern(this);
        p.automaton = automaton.complement();
        return p;
    }

    @Override
    public PosixPattern or(PosixPattern p) {
        MyPattern newPattern = new MyPattern(this);

        newPattern.automaton = automaton.union(((MyPattern) p).automaton);

        return newPattern;
    }

    @Override
    public MyPattern and(PosixPattern p) {
        MyPattern newPattern = new MyPattern(this);

        newPattern.automaton = automaton.intersection(((MyPattern) p).automaton);

        return newPattern;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyPattern myPattern = (MyPattern) o;

        return Objects.equals(regex, myPattern.regex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(automaton, regex);
    }
}
