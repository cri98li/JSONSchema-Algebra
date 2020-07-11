package patterns;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

public class RealWorldTest {

	@Test
	public void testOr() throws REException {
		// js_0.json
		Pattern p = Pattern.createFromRegexp("^dev|alpha|beta|rc|RC|stable$");

		assertTrue(p.match("dev"));
		assertTrue(p.match("developer")); // Note that only the first word is bounded.
		assertTrue(p.match("alpha"));
		assertTrue(p.match("foo-alpha-bar"));
		assertFalse(p.match("stables and horses"));
	}

	@Test
	public void testBackslash() throws REException { // TODO
		Pattern p = Pattern.createFromRegexp("^[\\S]+$");

		// matches any non-whitespace characters
		assertTrue("foo", p.match("foo"));
		assertFalse("a b", p.match("a b"));
		assertFalse("a\nb", p.match("a\nb"));
		assertFalse("\t", p.match("\t"));
	}

	@Test
	public void testAlphaNumRange() throws REException {
		Pattern p = Pattern.createFromRegexp("^[a-z0-9-\\.]+$");

		assertTrue("<alpha>", p.match("a"));
		assertTrue("...", p.match("..."));
		assertTrue("a1-79", p.match("a1-z9"));
		assertFalse("a\\b\\c", p.match("a\\b\\c"));
	}

	@Test
	public void testItext() throws REException {
		Pattern p = Pattern.createFromRegexp("^I[a-z0-f]{4}$");

		assertTrue(p.match("Iabcd"));
		assertTrue(p.match("I1234"));
		assertTrue(p.match("I[AQ7"));
	}

	@Test
	public void testEmail() throws REException {
		// js_1.json, property author_email
		Pattern p = Pattern.createFromRegexp("^[a-z\\d_\\.\\+-]+@([a-z\\d\\.-]+\\.)+[a-z]+$");

		assertTrue("correct email", p.match("bill.gates@microsoft.com"));
		assertFalse("incorrect email", p.match("bill.gates.microsoft.com"));
	}

	@Test
	public void testBackslashD() throws REException {
		// js_100.json, policy
		Pattern p = Pattern.createFromRegexp("^((\\d+\\.)?(\\d+\\.)?(\\*|\\d+))|builtin$");

		// System.out.println("\n" + p);
		// System.out.println("\n" + p.toAutomatonString());

		assertTrue("builtin", p.match("builtin"));
		assertFalse("d", p.match("d"));
		assertTrue("1.3.4", p.match("1.3.4")); // From the JSON Schema document itself.
		assertTrue("0.1", p.match("0.1")); // From the JSON Schema document itself.

		assertTrue("*", p.match("*"));
	}

	@Test // (expected = IllegalArgumentException.class)
	public void testSwaggerHost() throws REException {
		// js_10009.json.
		Pattern p = Pattern.createFromRegexp("^[^{}/ :\\\\]+(?::\\d+)?$");

		assertTrue(p.match("swagger.io"));
		assertTrue(p.match("petstore.swagger.wordnik.com"));
		assertTrue(p.match("example.com:8089"));
	}

	@Test
	public void testSwaggerBasePathFixed() throws REException {
		// js_10009.json, with fix.
		assertTrue(Pattern.createFromRegexp("^/").match("/api"));
	}

	@Test
	public void testUniqueIdentifier() throws REException {
		// js_10015.json
		Pattern p = Pattern.createFromRegexp("^[-_.A-Za-z0-9]+$");

		assertTrue(p.match("foo-bar_baz.09"));
	}

	@Test
	public void testCountryCode() throws REException {
		// js_10015.json
		// ISO 3166-1 alpha-2 country code in upper case.
		Pattern p = Pattern.createFromRegexp("^[A-Z]{2}$");

		assertTrue(p.match("DE"));
	}

	@Test
	public void testAge() throws REException {
		// js_10015.json
		// RFC3339 date (or prefix).
		Pattern p = Pattern.createFromRegexp("^\\d\\d\\d\\d(-\\d\\d(-\\d\\d)?)?$");

		assertTrue(p.match("1985-04-12"));
		assertFalse(p.match("1985-04-12T23:20:50.52Z"));
	}

	@Test
	public void testNameOfDirectory() throws REException {
		// js_10019.json
		assertTrue(Pattern.createFromRegexp("^[a-zA-Z0-9_.]*$").match("shot01"));
	}

	@Test
	public void testDateTime() throws REException {
		// js_1002.json
		Pattern p = Pattern.createFromRegexp(
				"^(\\d{4})(-)?(\\d\\d)(-)?(\\d\\d)(T)?(\\d\\d)(:)?(\\d\\d)(:)?(\\d\\d)(\\.\\d+)?(Z|([+-])(\\d\\d)(:)?(\\d\\d))");

		assertTrue(p.match("1985-04-12T23:20:50.52Z"));
		assertTrue(p.match("2009-04-16T12:07:25.123+01:00"));
	}

	@Test
	public void testNameOf() throws REException {
		// js_10021.json
		Pattern p = Pattern.createFromRegexp("^\\w*$");

		assertTrue(p.match("Hulk"));
		assertTrue(p.match("maya2016"));
	}

	@Test
	public void testAddressToMongoDB() throws REException {
		// js_10021.json
		Pattern p = Pattern.createFromRegexp("^mongodb://[\\w/@:.]*$");

		assertTrue(p.match("mongodb://localhost:27017"));
	}

	@Test
	public void testAddressToSentryEtc() throws REException {
		// js_10021.json
		Pattern p = Pattern.createFromRegexp("^http[\\w/@:.]*$");

		assertTrue(p.match(
				"https://5b872b280de742919b115bdc8da076a5:8d278266fe764361b8fa6024af004a9c@logs.mindbender.com/2"));
		assertTrue(p.match("http://192.168.99.101"));
	}

	@Test
	public void testContainerID() throws REException {
		// js_10021.json
		Pattern p = Pattern.createFromRegexp("^[\\w.]*$");

		assertTrue(p.match("avalon.container"));
	}

	@Test
	public void testFilter() throws REException {
		// js_10036.json
		Pattern p = Pattern.createFromRegexp("^[\\w\\-@]+\\.[\\w\\-\\.#\\*\\[\\]\\?]+$");

		assertTrue(p.match("ab@.ab-c#?*[]")); // I made this string up.
	}

	@Test
	public void testFilter2() throws REException {
		// js_10036.json
		Pattern p = Pattern.createFromRegexp("^[\\w\\-\\*\\[\\]\\?]+@[\\w\\-]+\\.[\\w\\-\\*\\[\\]\\?]+$");

		assertTrue(p.match("ab*?@abc.com?")); // I made this string up.
	}

	@Test
	public void testExpires() throws REException {
		// js_1004.json
		Pattern p = Pattern.createFromRegexp(
				"^(\\d{4})(-)?(\\d\\d)(-)?(\\d\\d)(T)?(\\d\\d)(:)?(\\d\\d)(:)?(\\d\\d)(\\.\\d+)?(Z|([+-])(\\d\\d)(:)?(\\d\\d))?");

		assertTrue(p.match("1985-04-12T23:20:50.52Z"));
		assertTrue(p.match("2009-04-16T12:07:25.123+01:00"));
	}

	@Test
	public void testResourceID() throws REException {
		// js_10040.json
		Pattern p = Pattern.createFromRegexp("^[\\w\\-]+(\\.[\\w\\-^#]+)+$");

		assertTrue(p.match("foo-.bar#.baz^")); // I made this up.
	}

	@Test
	public void testResource() throws REException {
		// js_10040.json
		Pattern p = Pattern.createFromRegexp("^[\\w\\-]+\\..+$");

		assertTrue(p.match("foo-.bar#.baz^")); // I made this up.
	}

	@Test
	public void testProto() throws REException {
		// js_10041.json
		Pattern p = Pattern.createFromRegexp("^tcp$|^udp$|^\\\\*$");

		assertTrue(p.match("tcp"));
		assertTrue(p.match("udp"));
		assertTrue(p.match("\\\\"));
		assertFalse(p.match("tcudp"));
	}

	@Test
	public void testEndpoint() throws REException {
		// js_10041.json
		Pattern p = Pattern.createFromRegexp("^[\\w\\-\\*\\[\\]\\?]+$");

		assertTrue(p.match("-*[]?"));
	}

	@Test
	@Ignore // Does not work yet, requires *proper* rewriting of ^$ into @
	public void testPathType() throws REException {
		// js_10376.json
		Pattern p = Pattern.createFromRegexp("^(\\/?((\\.{2})|([a-z0-9\\-]*))($|\\/))*$");

		assertTrue(p.match("/foo/bar/../baz/"));
	}

	@Test
	@Ignore // Does not work yet, please fix next
	public void testRegistrationType() throws REException {
		// js_13403.json
		Pattern p = Pattern.createFromRegexp("^$|^List$");

		assertTrue(p.match(""));
		assertTrue(p.match("List"));
		assertFalse(p.match("Listing"));
	}

	@Test
	public void testClientSideExtension() throws REException {
		// js_13403.json
		Pattern p = Pattern.createFromRegexp("^ClientSideExtension\\\\..*$");

		assertTrue(p.match("ClientSideExtension\\1"));
		assertTrue(p.match("ClientSideExtension\\\\abc"));
	}

	@Test
	public void testPublicationIdentifier() throws REException {
		// js_116.jso
		Pattern p = Pattern.createFromRegexp("^PMID:[0-9]+$|^doi:10\\.[0-9]{4}[\\d\\S\\:\\.]+");

		assertTrue(p.match("PMID:12345678"));
		assertTrue(p.match("doi:10.1234/abc123"));
	}

	@Test
	public void testObjectName() throws REException {
		// js_10042.json
		Pattern p = Pattern.createFromRegexp("^[\\w\\-\\.]+$");

		assertTrue(p.match("a-b-c...-d"));
	}

	@Test
	public void testImage() throws REException {
		// js_10042.json
		Pattern p = Pattern.createFromRegexp("^native:|docker://.+|http://.+|file://.+$");

		assertTrue(p.match("native:"));
		assertTrue(p.match("docker://foo"));
		assertTrue(p.match("http://bar"));
		assertTrue(p.match("file://baz"));
	}

	/*
	 * js_10042.json: "pattern": "^[\\w\\-]+$" js_10043.json: "pattern":
	 * "^[\\w\\s\\-\\*\\/,]+$" js_10046.json: "pattern":
	 * "^[\\w\\-\\.\\*\\[\\]\\?]+$" js_10047.json: "pattern":
	 * "^[a-zA-Z0-9\\-_]+(\\.[a-zA-Z0-9\\-_^#]+)+$" js_10047.json: "pattern":
	 * "^[a-zA-Z0-9\\-_]+$" js_10051.json: "pattern":
	 * "^[a-zA-Z0-9_]+(\\.[a-zA-Z0-9\\-_^#]+)+$" js_10051.json: "pattern":
	 * "^cell$|^campus$|^region$|^global$" js_10051.json: "required": ["scope",
	 * "pattern", "alias"] js_10053.json: "pattern":
	 * "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}" js_10053.json:
	 * "pattern": "^/[a-z\\-]+$" js_10053.json: "pattern":
	 * "^[\\w\\-\\.#@\\*\\[\\]\\?]+$" js_10053.json: "pattern":
	 * "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}" js_10053.json:
	 * "pattern": "^/[a-z\\-]+$" js_10053.json: "pattern":
	 * "^[\\w\\-\\.#@\\*\\[\\]\\?]+$" js_10055.json: "pattern":
	 * "^[\\w\\-\\.\\*\\[\\]\\?]+$" js_10056.json: "pattern": "^(\\w+)(:\\w+)*$"
	 * js_10059.json: "pattern": "^[a-zA-Z0-9_]+(\\.[a-zA-Z0-9\\-_^#]+)+$"
	 * js_10059.json: "pattern": {"type": "string"}, js_10059.json: "required":
	 * ["cells", "pattern", "types", "identity-group", js_10092.json: "pattern":
	 * "^(application/|audio/|example/|image/|multipart/|text/|video/)"
	 * js_10092.json: {"pattern": "^([a-z]){2,3}$"}, js_10092.json: {"pattern":
	 * "^([a-z]){2,3}-"} js_1010.json: "pattern":
	 * "^(debug|info|warn|crit|fatal|unknown)$" js_1010.json: "pattern": "^0$"
	 * js_1011.json: "pattern": "^2e$" js_1011.json: "pattern": "^aint.metrics$"
	 * js_1011.json: "pattern": "^info$" js_1012.json: "pattern": "^2c$"
	 * js_1013.json: "pattern": "^2d$" js_10250.json: "pattern": "^(schema:)"},
	 * js_10250.json: "pattern": "^(schema:)" js_10250.json: "pattern": "^(mailto)",
	 * js_10250.json: "pattern": "^(mailto)", js_10327.json: "pattern":
	 * "^(\\d+(\\.\\d+)*)$" js_10327.json: "pattern":
	 * "^[0-9A-Za-z]([0-9A-Za-z_.-]*[0-9A-Za-z])( \\(.*\\))?$" js_10327.json:
	 * "pattern":
	 * "^(\\d+(\\.\\d+)*)((a|b|c|rc)(\\d+))?(\\.(post)(\\d+))?(\\.(dev)(\\d+))?$"
	 * js_10327.json: "pattern": "^[0-9a-z_.-+]+$" js_10327.json: "pattern":
	 * "^[0-9A-Za-z]([0-9A-Za-z_.-]*[0-9A-Za-z])?$" js_10327.json: "pattern":
	 * "^[0-9A-Za-z]([0-9A-Za-z_.-]*[0-9A-Za-z])?$" js_10327.json: "pattern":
	 * "^([A-Za-z_][A-Za-z_0-9]*([.][A-Za-z_][A-Za-z_0-9]*)*)(:[A-Za-z_][A-Za-z_0-9]*([.][A-Za-z_][A-Za-z_0-9]*)*)?(\\[[0-9A-Za-z]([0-9A-Za-z_.-]*[0-9A-Za-z])?\\])?$"
	 * js_10327.json: "pattern":
	 * "^[A-Za-z_][A-Za-z_0-9]*([.][A-Za-z_][A-Za-z_0-9]*)*$" js_10327.json:
	 * "pattern": "^[A-Za-z_][A-Za-z_0-9]*([.][A-Za-z_0-9]*)*$" js_1033.json:
	 * "pattern": "^[^{}/ :\\\\]+(?::\\d+)?$", js_1033.json: "pattern": "^/",
	 * js_10338.json: "pattern": "^[^{}/ :\\\\]+(?::\\d+)?$", js_10338.json:
	 * "pattern": "^/", js_10351.json: "pattern": "^http://" js_10351.json:
	 * "pattern": "^/" js_10351.json: "pattern": "^/" js_10355.json: "pattern":
	 * "^[a-zA-Z0-9_]+$" js_10358.json: "pattern": "^http://" js_10358.json:
	 * "pattern": "^/" js_10358.json: "pattern": "^/" js_10360.json: "pattern":
	 * "^[^{}/ :\\\\]+(?::\\d+)?$",
	 * 
	 */

}
