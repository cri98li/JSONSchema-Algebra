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

		System.out.println(a);

		RunAutomaton ra = new RunAutomaton(a);
		assertTrue(ra.run("0"));
	}
}
