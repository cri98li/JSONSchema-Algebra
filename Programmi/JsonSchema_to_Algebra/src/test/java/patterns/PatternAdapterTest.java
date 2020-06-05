package patterns;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Ignore;

public class PatternAdapterTest {

  @Test
  public void testRewriteBound() {
    assertEquals("@foo@", PatternAdapter.bound("foo"));
    assertEquals("@foo", PatternAdapter.bound("foo$"));
    assertEquals("foo@", PatternAdapter.bound("^foo"));
    assertEquals("foo",PatternAdapter.bound("^foo$"));

    // TODO -this one is still problematic
    //assertEquals("@foo$@", PatternAdapter.bound("foo\\$"));
  }

   
}
