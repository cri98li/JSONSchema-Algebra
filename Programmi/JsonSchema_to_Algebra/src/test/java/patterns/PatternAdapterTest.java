package patterns;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Ignore;

public class PatternAdapterTest {



  @Test
  public void testBound() {
    assertEquals("@foo@", PatternAdapter.rewrite("foo"));
    assertEquals("@foo", PatternAdapter.rewrite("foo$"));
    assertEquals("foo@", PatternAdapter.rewrite("^foo"));
    assertEquals("foo",PatternAdapter.rewrite("^foo$"));

    // Make sure that a n escaped dollar sign is not mistaken
    // for the end o the string in binding the regex.
    assertEquals("@foo\\$@", PatternAdapter.rewrite("foo\\$"));
  }


  @Test
  public void testNonWhitespace() {
    assertEquals("[^\\r\\n\\t\\f\\v ]+", PatternAdapter.rewriteCore("[\\S]+"));
  } 


  @Test
  public void testPreserveEscapings() {
    // Must preserve "\.".
    assertEquals("\\.", PatternAdapter.rewriteCore("\\."));

    // Must preserve "\+".
    assertEquals("\\+", PatternAdapter.rewriteCore("\\+"));
 
    // Must preserve "\*".
    assertEquals("\\*", PatternAdapter.rewriteCore("\\*"));
  }

  
  @Test
  public void testEscapeTabs() {
    assertEquals("\\t", PatternAdapter.rewriteCore("\\t"));
    assertEquals("\\v", PatternAdapter.rewriteCore("\\v"));
  }


  @Test
  public void testEscapeAtSymbol() {
    assertEquals("foo\\@bar", PatternAdapter.rewriteCore("foo@bar"));
    assertEquals("@foo\\@bar@", PatternAdapter.rewrite("foo@bar"));
    assertEquals("[a-z]\\@[0-9]", PatternAdapter.rewriteCore("[a-z]@[0-9]"));
  }


  @Test
  public void testDigits() {
    assertEquals("[0-9]", PatternAdapter.rewriteCore("\\d"));
    assertEquals("\\\\d", PatternAdapter.rewriteCore("\\\\d"));
    assertEquals("[a0-9]", PatternAdapter.rewriteCore("[a\\d]"));
    assertEquals("foo[0-9]bar", PatternAdapter.rewriteCore("foo[\\d]bar"));
    assertEquals("foo[abc\\]def0-9]bar", PatternAdapter.rewriteCore("foo[abc\\]def\\d]bar"));
  }


  @Test
  public void testCharClasses() {
    assertEquals("[0-9]", PatternAdapter.rewriteCore("[0-9]"));
    assertEquals("[0-9]+", PatternAdapter.rewriteCore("[0-9]+"));
    assertEquals("[0-9]+\\+[0-9]", PatternAdapter.rewriteCore("[0-9]+\\+[0-9]"));
    assertEquals("[.+-]", PatternAdapter.rewriteCore("[.+-]"));
    assertEquals("[\\.+-]", PatternAdapter.rewriteCore("[\\.+-]"));
  }

  

  @Test
  public void testGroupNoncapturing() {
    assertEquals("foo(bar)?", PatternAdapter.rewriteCore("foo(?:bar)?"));
  }

 
  @Test
  public void testParentheses() {
    assertEquals("abc(def)ghi", PatternAdapter.rewriteCore("abc(def)ghi"));
    assertEquals("abc(def)?ghi", PatternAdapter.rewriteCore("abc(def)?ghi"));
    assertEquals("abc([0-9])?ghi", PatternAdapter.rewriteCore("abc(\\d)?ghi"));
    assertEquals("abc\\(def\\)ghi", PatternAdapter.rewriteCore("abc\\(def\\)ghi"));
    assertEquals("abc[()]def", PatternAdapter.rewriteCore("abc[()]def"));
  }


  @Test
  public void testMultiplicity() {
    assertEquals("a{3}", PatternAdapter.rewriteCore("a{3}"));
    assertEquals("a{2,5}", PatternAdapter.rewriteCore("a{2,5}"));
  }  


}
