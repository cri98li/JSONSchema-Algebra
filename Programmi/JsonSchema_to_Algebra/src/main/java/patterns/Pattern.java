package patterns;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.BasicAutomata;
import dk.brics.automaton.BasicOperations;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.RunAutomaton;
import dk.brics.automaton.SpecialOperations;

public class Pattern {

	/**
	 * The internal Bricks automaton.
	 */
	private Automaton automaton;

	/**
	 * Records the underlying regular expression in Bricks (!) syntax.
	 **/
	private String originalBricksPattern = null;

	/**
	 * A string representation of the underlying pattern.
	 */
	private String printablePattern;

	/**
	 * @param String constant that is not a regular expression
	 */
	public static Pattern createFromName(String patternName) {
		return new Pattern(BasicAutomata.makeString(patternName));
	}

	/**
	 * @param ecmaRegex String in ECMAScript syntax (not anchored by default)
	 * @exception IllegalArgumentException if regex is invalid
	 * @exception REException              if ecmaRegex contains syntax error
	 */
	public static Pattern createFromRegexp(String ecmaRegex) throws REException {
		return new Pattern(PatternAdapter.rewrite(ecmaRegex));
	}

	private Pattern(Automaton automaton) {
		this.automaton = automaton;
	}

	/**
	 * Assumes that regex is already in the Bricks syntax!
	 */
	protected Pattern(String bricksRegex) {
		this.originalBricksPattern = bricksRegex;

		// In Bricks, '#' is the empty language. Disable this.
		int flags = RegExp.ALL & ~RegExp.EMPTY;
		this.automaton = (new RegExp(bricksRegex, flags)).toAutomaton();
	}

	public boolean isEmpty() {
		return BasicOperations.isEmpty(this.automaton);
	}

	public boolean match(String str) {
		// The less performant way to match a string.
		// return BasicOperations.run(this.automaton, str);

		// For full performance, we use the RunAutomaton class.
		RunAutomaton ra = new RunAutomaton(this.automaton);
		return ra.run(str);
	}

	public Pattern intersect(Pattern p) {
		Automaton a = BasicOperations.intersection(this.automaton, p.automaton);
		Pattern pi = new Pattern(a);

		pi.printablePattern = "allOf[" + this.toString() + ", " + p.toString() + "]";
		return pi;
	}

	public Pattern minus(Pattern p) {
		Pattern m = new Pattern(BasicOperations.minus(this.automaton, p.automaton));

		m.printablePattern = "allOf[" + this.toString() + ", not(" + p.toString() + ")]";
		return m;
	}

	public Pattern union(Pattern p) {
		Pattern u = new Pattern(BasicOperations.union(this.automaton, p.automaton));

		u.printablePattern = "anyOf[" + this.toString() + ", " + p.toString() + "]";
		return u;
	}

	public Pattern complement() {
		Pattern complement = new Pattern(BasicOperations.complement(this.automaton));
		complement.printablePattern = "not(" + this.toString() + ")";

		return complement;
	}

	/**
	 * Returns true if this pattern declares a language that is a subset of the
	 * language declared by pattern p.
	 */
	public boolean isSubsetOf(Pattern p) {
		return BasicOperations.subsetOf(this.automaton, p.automaton);
	}

	public boolean isEquivalent(Pattern p) {
		return this.automaton.equals(p.automaton);
	}

	/**
	 * If domain(a) is finite, return |a|. Otherwise, return null.
	 */
	public Integer domainSize() {
		Set<String> domain = SpecialOperations.getFiniteStrings(this.automaton);
		return domain == null ? null : Integer.valueOf(domain.size());
	}

	/**
	 * Returns the set of accepted strings, assuming this automaton has a finite
	 * language. If the language is not finite, this returns one word that matches.
	 * If the language is empty, this returns null.
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
		clone.originalBricksPattern = this.originalBricksPattern;
		clone.printablePattern = this.printablePattern;
		return clone;
	}

	/**
	 * Given {A1, .., An}, returns pattern for not(A1|...|An)
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
	 * Returns true iff the language declared by the two patterns overlaps, i.e.
	 * their intersection is not empty.
	 */
	public static boolean overlaps(Pattern left, Pattern right) {
		return !left.automaton.overlap(right.automaton).isEmpty();
	}

	/**
	 * Returns true iff there are two patterns that overlap.
	 */
	public static boolean overlaps(Collection<Pattern> patterns) {
		Pattern[] array = patterns.toArray(new Pattern[0]);

		for (int i = 0; i < array.length; i++) {
			Pattern first = array[i];

			if (i == array.length - 1)
				break;

			for (int j = i + 1; j < array.length; j++) {
				Pattern second = array[j];

				if (Pattern.overlaps(first, second))
					return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {

		if (this.originalBricksPattern != null)
			return "base(" + this.originalBricksPattern + ")";

		assert (this.printablePattern != null) : "should not have happened";
		return this.printablePattern;
	}

	/**
	 * For debugging purposes.
	 * 
	 * @return Serialization of the automaton states and transitions.
	 */
	public String toAutomatonString() {
		return this.automaton.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Pattern pattern = (Pattern) o;
		return this.isEquivalent(pattern);
	}

}
