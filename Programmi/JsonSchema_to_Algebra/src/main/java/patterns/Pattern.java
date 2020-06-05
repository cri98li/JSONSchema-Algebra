package patterns;

import java.util.Collection;
import java.util.Set;
import java.util.Collections;

import dk.brics.automaton.*;
public class Pattern {
  private String initialPattern;
  private boolean printable;

  // TODO - explore runautomaton

  private Automaton automaton;

  public static Pattern createFromName(String patternName) {
    return new Pattern(BasicAutomata.makeString(patternName));
  }

  public static Pattern createFromRegexp(String regex) {
    return new Pattern(PatternAdapter.rewrite(regex));
  }

  private Pattern(Automaton automaton) {
    this.automaton = automaton;

    printable = false;
  }

  private Pattern(String regex) {
    // TODO - adapt regexp syntax
  
    this((new RegExp(regex)).toAutomaton());

    initialPattern = regex;
    this.printable = true;

  } 

  public boolean isEmpty(){
    return BasicOperations.isEmpty(this.automaton);
  }

  public boolean match(String str) {
    // TODO - for full performance, use the RunAutomaton class.
    //return BasicOperations.run(this.automaton, str);

    // For full performance, we use the RunAutomaton class.
    RunAutomaton ra = new RunAutomaton(this.automaton);
    return ra.run(str);
  }

  public Pattern intersect(Pattern p) {
    Automaton a = BasicOperations.intersection(this.automaton, p.automaton);
    return new Pattern(a);
  }

  public Pattern minus(Pattern p) {
    return new Pattern(BasicOperations.minus(this.automaton, p.automaton));
  }

  public Pattern union(Pattern p) {
    return new Pattern(BasicOperations.union(this.automaton, p.automaton));
  }

  public Pattern complement() {
    return new Pattern(BasicOperations.complement(this.automaton));
  }

  /**
    Returns true if this pattern declares a language that is a subset
    of the language declared by pattern p.
  */
  public boolean isSubsetOf(Pattern p) {
    return BasicOperations.subsetOf(this.automaton, p.automaton);
  }

  public boolean isEquivalent(Pattern p) {
    return this.automaton.equals(p.automaton);
  }

  /**
    If domain(a) is finite, return |a|. Otherwise, return null.
  */
  public Integer domainSize() {
    Set<String> domain = SpecialOperations.getFiniteStrings(this.automaton);
    return domain == null? null : Integer.valueOf(domain.size());
  }

 
  /**
    Returns the set of accepted strings, assuming this automaton has a finite language. 
    If the language is not finite, this returns one word that matches. 
    If the language is empty, this returns null.
   * @return
   */
  public Collection<String> generateWords() {
    Set<String> words = SpecialOperations.getFiniteStrings(this.automaton);

    if (words != null)
      return words;

    if (words == null && !this.automaton.isEmpty())
      return Collections.singleton(BasicOperations.getShortestExample(this.automaton, true));

    return null;
  }

  public Pattern clone() {
    Pattern clone = new Pattern(this.automaton.clone());

    clone.printable = printable;
    clone.initialPattern = initialPattern;

    return clone;
  }

  
  /**
    Given {A1, .., An}, returns pattern for not(A1|...|An)
  */
  public static Pattern listComplement(Collection<Pattern> patterns) {
    Pattern u = null;

    for (Pattern p : patterns) {
      if (u == null)
        u = p;
      else
        u = u.union(p);
    }

    return u.complement();
  }


  /**
    Returns true iff the language declared by the two patterns overlaps,
    i.e. their intersection is not empty.
  */
  public static boolean overlaps(Pattern left, Pattern right) {
    return ! left.automaton.overlap(right.automaton).isEmpty();
  }


  /**
    Returns true iff there are two patterns that overlap.
  */ 
  public static boolean overlaps(Collection<Pattern> patterns) {
     boolean answer = false;
      Pattern[] array = patterns.toArray(new Pattern[0]);
    
     for (int i = 0; i < array.length; i++) {
       Pattern first = array[i];

       if (i  == array.length - 1) 
         break;

       for (int j = i + 1; j < array.length; j++) {
         Pattern second = array[j];

         if (Pattern.overlaps(first, second)) 
           return true;
       }
     }
 
     return false; 
  }


  /**
    Returns a string representation of the automaton (states and transitions).
  */
  @Override
  public String toString() {
    return printable ? initialPattern : this.automaton.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Pattern pattern = (Pattern) o;

    return this.isEquivalent(pattern);
  }
}
