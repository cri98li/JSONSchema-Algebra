package patterns;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit tests devoted to group constructs.
 */
public class GroupConstructTests {

	@Test
	public void testGroupMatch() throws REException {
		Pattern p = Pattern.createFromRegexp("^(?:abc){3}$");

		assertTrue(p.match("abcabcabc"));
	}

	// TODO - this is a rare case, but should be supported.
	@Test(expected = REException.class)
	public void testNamedCapturingGroup() throws REException {
		Pattern p = Pattern.createFromRegexp("(?<foopattern>)^foo.*$");

		assertTrue(p.match("foobar"));
	}

	@Test(expected = REException.class)
	public void testPosLookahead() throws REException {
		Pattern p = Pattern.createFromRegexp("X(?=Y)");

		System.out.println(p.toAutomatonString());

		assertTrue(p.match("XYZ"));
		assertFalse(p.match("XX"));
	}

	@Test(expected = REException.class)
	public void testNegLookahead() throws REException {
		Pattern p = Pattern.createFromRegexp("X(?!Y)");

		System.out.println(p.toAutomatonString());

		assertFalse(p.match("XYZ"));
		assertTrue(p.match("XX"));
	}

}
