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
		Integer x00 = Integer.parseInt("00", 16);
		Integer xFF = Integer.parseInt("FF", 16);

		Automaton a = (new RegExp("[\\x00-\\xFF]")).toAutomaton();

		System.out.println(a.toString()); // TODO

		RunAutomaton ra = new RunAutomaton(a);
		assertTrue(ra.run("0"));
		assertTrue(ra.run("!"));

	}
}
