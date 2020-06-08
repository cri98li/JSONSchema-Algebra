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


/*
  @Test
  public void testEscapeBackslash() {
    assertEquals("^[\\\\S]+$", PatternAdapter.rewrite("^[\\S]+$"));
  } 
*/

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
  public void testEscapeAtSymbol() {
    assertEquals("foo\\@bar", PatternAdapter.rewriteCore("foo@bar"));
    assertEquals("@foo\\@bar@", PatternAdapter.rewrite("foo@bar"));
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
  @Ignore
  public void testGroupNoncapturing() {
    assertEquals("foo(bar)?", PatternAdapter.rewriteCore("foo(?:bar)?"));
  }


}

