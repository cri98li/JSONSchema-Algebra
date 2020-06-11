package patterns;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Ignore;

public class RealWorldTest {


  @Test
  public void testOr() {
    Pattern p = Pattern.createFromRegexp("^dev|alpha|beta|rc|RC|stable$");

    assertTrue(p.match("dev"));
    assertTrue(p.match("alpha"));
    assertFalse(p.match("dev|alpha"));
  }


  @Test
  public void testBackslash() { // TODO
    Pattern p = Pattern.createFromRegexp("^[\\S]+$");

    // matches any non-whitespace characters
    assertTrue("foo", p.match("foo"));
    assertFalse("a b", p.match("a b"));
    assertFalse("a\nb", p.match("a\nb"));
    assertFalse("\t", p.match("\t"));
  }


  @Test
  public void testAlphaNumRange() {
    Pattern p = Pattern.createFromRegexp("^[a-z0-9-\\.]+$");

    assertTrue(p.match("a"));
    assertTrue(p.match("..."));
    assertTrue(p.match("a1-z9"));
    assertFalse(p.match("a\\b\\c"));
  }


  @Test
  public void testItext() {
    Pattern p = Pattern.createFromRegexp("^I[a-z0-f]{4}$");

    assertTrue(p.match("Iabcd"));
    assertTrue(p.match("I1234"));
    assertTrue(p.match("I[AQ7"));
  }

  @Test
  public void testFoo() {
    Pattern p = Pattern.createFromRegexp("^a+@(b+\\.)+c+$");
    assertTrue(p.match("aaa@bbb.ccc"));
  }


  @Test
  public void testEmail() {
    // js_1.json, property author_email
    Pattern p = Pattern.createFromRegexp("^[a-z\\d_\\.\\+-]+@([a-z\\d\\.-]+\\.)+[a-z]+$");

    assertTrue("correct email", p.match("bill.gates@microsoft.com"));
    assertFalse("incorrect email", p.match("bill.gates.microsoft.com"));
  }


  @Test
  public void testBackslashD() {
    // js_100.json, policy
    Pattern p = Pattern.createFromRegexp("^((\\d+\\.)?(\\d+\\.)?(\\*|\\d+))|builtin$");

    //System.out.println("\n" + p);
    //System.out.println("\n" + p.toAutomatonString());

    assertTrue("builtin", p.match("builtin"));
    assertFalse("d", p.match("d"));
    assertTrue("1.3.4", p.match("1.3.4")); // From the JSON Schema document itself.
    assertTrue("0.1", p.match("0.1"));   // From the JSON Schema document itself.

    assertTrue("*", p.match("*"));
  }  

  


  @Test //(expected = IllegalArgumentException.class)
  public void testSwaggerHost() {
    // js_10009.json.
   Pattern p = Pattern.createFromRegexp("^[^{}/ :\\\\]+(?::\\d+)?$");

   assertTrue(p.match("swagger.io"));
   assertTrue(p.match("petstore.swagger.wordnik.com"));
   assertTrue(p.match("example.com:8089"));
  }


  @Test
  public void testSwaggerBasePathFixed() {
    // js_10009.json, with fix.
    assertTrue(Pattern.createFromRegexp("^/").match("/api"));
  }

  
  @Test
  public void testUniqueIdentifier() {
    // js_10015.json
    Pattern p = Pattern.createFromRegexp("^[-_.A-Za-z0-9]+$");

    assertTrue(p.match("foo-bar_baz.09"));
  }


  @Test
  public void testCountryCode() {
    // js_10015.json
    // ISO 3166-1 alpha-2 country code in upper case.
    Pattern p = Pattern.createFromRegexp("^[A-Z]{2}$");

    assertTrue(p.match("DE"));
  }

  
  @Test
  public void testAge() {
    // js_10015.json
    // RFC3339 date (or prefix).
    Pattern p = Pattern.createFromRegexp("^\\d\\d\\d\\d(-\\d\\d(-\\d\\d)?)?$");

    assertTrue(p.match("1985-04-12"));
    assertFalse(p.match("1985-04-12T23:20:50.52Z"));
  }


  @Test
  public void testNameOfDirectory() {
    // js_10019.json
    assertTrue(Pattern.createFromRegexp("^[a-zA-Z0-9_.]*$").match("shot01"));
  } 

  
  @Test
  public void testDateTime() {
    // js_1002.json
    Pattern p = Pattern.createFromRegexp("^(\\d{4})(-)?(\\d\\d)(-)?(\\d\\d)(T)?(\\d\\d)(:)?(\\d\\d)(:)?(\\d\\d)(\\.\\d+)?(Z|([+-])(\\d\\d)(:)?(\\d\\d))");
   
    assertTrue(p.match("1985-04-12T23:20:50.52Z"));
    assertTrue(p.match("2009-04-16T12:07:25.123+01:00"));
  }


  @Test
  public void testNameOf() {
    // js_10021.json
    Pattern p = Pattern.createFromRegexp("^\\w*$");

    assertTrue(p.match("Hulk"));
    assertTrue(p.match("maya2016"));
  }

/*


js_10021.json:            "pattern": "^mongodb://[\\w/@:.]*$",
js_10021.json:            "pattern": "^\\w*$",
js_10021.json:            "pattern": "^http[\\w/@:.]*$",
js_10021.json:            "pattern": "^http[\\w/@:.]*$",
js_10021.json:            "pattern": "^[0-9]*$",
js_10021.json:            "pattern": "^\\w*$",
js_10021.json:            "pattern": "^\\w*$",
js_10021.json:            "pattern": "^[\\w.]*$",
js_10021.json:            "pattern": "^[\\w.]*$",
js_10025.json:            "pattern": "^[a-zA-Z0-9_.]*$",
js_10031.json:            "pattern": "^[a-zA-Z0-9_.]*$",
js_10033.json:            "pattern": "^[a-zA-Z0-9_.]*$",
js_10035.json:      "pattern": "^[^{}/ :\\\\]+(?::\\d+)?$",
js_10035.json:      "pattern": "^/",
js_10036.json:                        "pattern": "^[\\w\\-@]+\\.[\\w\\-\\.#\\*\\[\\]\\?]+$"
js_10036.json:                        "pattern": "^[\\w\\-\\*\\[\\]\\?]+@[\\w\\-]+\\.[\\w\\-\\*\\[\\]\\?]+$"
js_1004.json:      "pattern": "^(\\d{4})(-)?(\\d\\d)(-)?(\\d\\d)(T)?(\\d\\d)(:)?(\\d\\d)(:)?(\\d\\d)(\\.\\d+)?(Z|([+-])(\\d\\d)(:)?(\\d\\d))?"
js_1004.json:      "pattern": "^(\\d{4})(-)?(\\d\\d)(-)?(\\d\\d)(T)?(\\d\\d)(:)?(\\d\\d)(:)?(\\d\\d)(\\.\\d+)?(Z|([+-])(\\d\\d)(:)?(\\d\\d))?"
js_10040.json:                "pattern": "^[\\w\\-]+(\\.[\\w\\-^#]+)+$"
js_10040.json:                "pattern": "^[\\w\\-]+@[\\w\\-]+(\\.[\\w\\-^#]+)+$"
js_10040.json:                        "pattern": "^[\\w\\-]+\\..+$"
js_10040.json:                        "pattern": "^[\\w\\-]+\\@[\\w\\-]+\\..+$"


*/

}
