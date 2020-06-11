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

  
  @Test
  public void testSwaggerHostFixed() {
    // js_10009.json, but with fixes (!)
    // This regex contains lookaheads, which Bricks cannot handle properly.
    Pattern p = Pattern.createFromRegexp("^[^{}\\/ :\\]+(?::\\d+)?$");

    assertFalse(p.toString().contains("?:")); // Make sure "?:" is ignored.

    assertTrue(p.match("swagger.io"));
    assertTrue(p.match("petstore.swagger.wordnik.com"));
    assertTrue(p.match("example.com:8089"));
  }


  @Ignore
  @Test(expected = IllegalArgumentException.class)
  public void testSwaggerHostBroken() {
    // js_10009.json, as found in that file, with syntax errors.
    Pattern p = Pattern.createFromRegexp("^[^{}/ :\\\\]+(?::\\d+)?$");
  }


}
