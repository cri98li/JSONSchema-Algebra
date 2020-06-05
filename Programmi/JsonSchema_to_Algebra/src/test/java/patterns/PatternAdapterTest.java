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

  // TODO - add all the tests from JSON Schema Test Suite
  // https://github.com/json-schema-org/JSON-Schema-Test-Suite/blob/master/tests/draft6/pattern.json
  
}
