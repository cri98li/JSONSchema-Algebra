package patterns;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Ignore;

public class PatternAdapterTest {

  // TODO - test patterns with positive or negative lookahead
  // Possibly throw an exception.

  @Test
  public void testRepetitions() throws REException {
    assertEquals("(abc)*", PatternAdapter.rewrite("^(abc)*$"));
    assertEquals("(a)+", PatternAdapter.rewrite("^a+$"));
    assertEquals("foo(bar)?", PatternAdapter.rewrite("^foo(bar)?$"));
  }

  @Test
  @Ignore //  Not implemented yet.
  public void testBound() throws REException {
    assertEquals("@foo@", PatternAdapter.rewrite("foo"));

    assertEquals("@foo", PatternAdapter.rewrite("foo$"));
    assertEquals("foo@", PatternAdapter.rewrite("^foo"));
    assertEquals("foo",PatternAdapter.rewrite("^foo$"));

    // Make sure that a n escaped dollar sign is not mistaken
    // for the end o the string in binding the regex.
    assertEquals("@foo\\$@", PatternAdapter.rewrite("foo\\$")); // Waaaah
  }


  @Test 
  @Ignore // This is not implemented yet.
  public void testMultiBound() throws REException {
    assertEquals("foo|bar", PatternAdapter.rewrite("^foo$|^bar$"));
    assertEquals("foo@|bar@", PatternAdapter.rewrite("^foo|^bar"));
    assertEquals("@foo|bar@", PatternAdapter.rewrite("foo$|^bar"));
    assertEquals("foo|@bar", PatternAdapter.rewrite("^foo$|bar"));
  }



  @Test
  public void testPreserveEscapings() throws REException {
    // Must preserve "\.".
    assertEquals("\\.", PatternAdapter.rewrite("^\\.$"));

    // Must preserve "\+".
    assertEquals("\\+", PatternAdapter.rewrite("^\\+$"));
 
    // Must preserve "\*".
    assertEquals("\\*", PatternAdapter.rewrite("^\\*$"));

    // Must preserve "\\".
    assertEquals("\\\\", PatternAdapter.rewrite("^\\\\$"));
  }

  
  @Test
  public void testEscapeTabs() throws REException {
    assertEquals("\t", PatternAdapter.rewrite("^\\t$"));
    assertEquals("\n", PatternAdapter.rewrite("^\\n$"));
  }
	

  @Test
  public void testEscapeAtSymbol() throws REException {
    assertEquals("foo\\@bar", PatternAdapter.rewrite("^foo@bar$"));
    //assertEquals("@foo\\@bar@", PatternAdapter.rewrite("^foo@bar$")); // TODO
    assertEquals("([a-z])\\@([0-9])", PatternAdapter.rewrite("^[a-z]@[0-9]$"));
  }


  @Test
  public void testDigits() throws REException {
    assertEquals("[0-9]", PatternAdapter.rewrite("^\\d$"));
    assertEquals("\\\\d", PatternAdapter.rewrite("^\\\\d$"));
    assertEquals("(a|[0-9]|a)", PatternAdapter.rewrite("^[a\\d]$")); // TODO - why 'a' twice?
    assertEquals("foo([0-9])bar", PatternAdapter.rewrite("^foo[\\d]bar$"));
    assertEquals("foo(a|b|c|]|d|e|f|[0-9]|f)bar", PatternAdapter.rewrite("^foo[abc\\]def\\d]bar$"));
  }


  @Test
  public void testCharClasses() throws REException {
    assertEquals("([0-9])", PatternAdapter.rewrite("^[0-9]$"));
    assertEquals("(([0-9]))+", PatternAdapter.rewrite("^[0-9]+$"));
    assertEquals("(([0-9]))+\\+([0-9])", PatternAdapter.rewrite("^[0-9]+\\+[0-9]$"));
    assertEquals("(\\.|\\+|-)", PatternAdapter.rewrite("^[.+-]$"));

    assertEquals("([\\.]|\\+|-)", PatternAdapter.rewrite("^[\\.+-]$"));
    assertEquals("(\\\\|\\.)", PatternAdapter.rewrite("^[\\\\.]$"));
  }


  @Test 
  public void testGroupNoncapturing() throws REException {
    assertEquals("foo(bar)?", PatternAdapter.rewrite("^foo(?:bar)?$"));
  }



  @Test 
  public void testParentheses() throws REException {
    assertEquals("abcdefghi", PatternAdapter.rewrite("^abc(def)ghi$"));
    assertEquals("abc(def)?ghi", PatternAdapter.rewrite("^abc(def)?ghi$"));
    assertEquals("abc([0-9])?ghi", PatternAdapter.rewrite("^abc(\\d)?ghi$"));
    assertEquals("abc\\(def\\)ghi", PatternAdapter.rewrite("^abc\\(def\\)ghi$"));
    assertEquals("abc(\\(|\\))def", PatternAdapter.rewrite("^abc[()]def$"));
  }

  
  @Test 
  public void testMultiplicity() throws REException {
    assertEquals("(a){3}", PatternAdapter.rewrite("^a{3}$"));
    assertEquals("(a){2,5}", PatternAdapter.rewrite("^a{2,5}$"));
  }


  @Test 
  public void testAnyWordCharacter() throws REException {
    assertEquals("[a-zA-Z0-9_]", PatternAdapter.rewrite("^\\w$"));
    assertEquals("([a-zA-Z0-9_])", PatternAdapter.rewrite("^[\\w]$"));
  }

 
  @Test
  public void testBackslashInRange() throws REException {
   assertEquals("(a|\\\\)", PatternAdapter.rewrite("^[a\\\\]$"));
   assertEquals("(\\\\|a)", PatternAdapter.rewrite("^[\\\\a]$"));
  }


  // TODO - escape |
}
