package patterns;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.RunAutomaton;

/**
 * Functionality tests directly using the Bricks automaton library.
 */
public class AutomatonTest {

	@Test
	public void testNull() {
		Automaton a = (new RegExp("[^\0]")).toAutomaton();

		RunAutomaton ra = new RunAutomaton(a);
		assertTrue(ra.run("0"));
	}

	@Test
	public void testHex() {
		Automaton a = (new RegExp("[\\x00-\\xFF]")).toAutomaton();

		// System.out.println(a.toString()); // TODO

		RunAutomaton ra = new RunAutomaton(a);
		assertTrue(ra.run("0"));
		// assertTrue(ra.run("!"));
	}

	@Test
	public void testPattern() {
		// From schema 48427
		String pattern = "Normal|Fighting|Flying|Poison|Ground|Rock|Bug|Ghost|Steel|Fire|Water|Grass|Electric|Psychic|Ice|Dragon|Dark|Fairy";

		Automaton a = (new RegExp(pattern)).toAutomaton();
		RunAutomaton ra = new RunAutomaton(a);
		assertTrue(ra.run("Ice"));
	}
}
