package patterns;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Ignore;
import java.util.Collection;
import java.util.HashSet;

public class PatternTest {

  @Test
  public void testCreateFromName() {
    Pattern pattern = Pattern.createFromName("foo");
    assertTrue(pattern.domainSize() == 1);
    assertTrue(pattern.generateWords().contains("foo"));
  }

  @Test
  public void testCreateFromRegexp() {
    // Regexps are not anchored by default.
    assertTrue(Pattern.createFromRegexp("foo").match("some-foo-thing"));
    assertTrue(Pattern.createFromRegexp("foo").match("foo"));

    assertFalse(Pattern.createFromRegexp("^foo$").match("some-foo-thing"));
    assertTrue(Pattern.createFromRegexp("^foo$").match("foo"));
  }

  @Test
  public void testIsEmptyFalse() {
    Pattern pattern = Pattern.createFromRegexp("^a?$"); 
    assertFalse(pattern.isEmpty());
  }

  @Test
  public void testIsEmptyTrue() {
    Pattern a = Pattern.createFromRegexp("^a$");
    Pattern b = Pattern.createFromRegexp("^b$");
    Pattern c = a.intersect(b);
    assertTrue(c != null);
    assertTrue(c.isEmpty());
  }

  @Test
  public void testIsIntersectEmptyFalse() {
    Pattern a = Pattern.createFromRegexp("^a*$");
    Pattern b = Pattern.createFromRegexp("^b*$");
    // (a*) & (b*) includes the empty word
    assertFalse(a.intersect(b).isEmpty());
  }

  @Test
  public void testGenerateWords() {
    assertTrue(Pattern.createFromRegexp("^a?$").generateWords().contains("a"));
  }

  @Test
  public void testGenerateWordsInf() {
    Collection<String> words = Pattern.createFromRegexp("^a+$").generateWords();
    assertTrue(words.size() == 1);
    assertTrue(words.contains("a"));
  }

  @Test
  public void testDomainSize() {
    assertTrue(Pattern.createFromRegexp("^a|b|c$").domainSize() == Integer.valueOf(3));
  }

  @Test
  public void testDomainSizeInf() {
    assertTrue(Pattern.createFromRegexp("^a*$").domainSize() == null);
  }
 
  @Test
  public void testMinus() {
    Pattern p1 = Pattern.createFromRegexp("^aa?$");
    Pattern p2 = Pattern.createFromRegexp("^a$");

    assertFalse(p1.minus(p2).isEmpty());
    assertTrue(p2.minus(p1).isEmpty());
  }

  @Test
  public void testMatchTrue() {
    assertTrue(Pattern.createFromRegexp("aa*").match("aa"));
  }

  @Test
  public void testMatchFalse() {
    assertFalse(Pattern.createFromRegexp("^aa*$").match("abc"));
  }

  @Test
  public void testClone() {
    Pattern p1 = Pattern.createFromRegexp("a*");
    Pattern p2 = p1.clone();

    assertTrue(p1 != p2);
    assertTrue(p1.isEquivalent(p2));
  }

  @Test
  public void testSubsetOf() {
    Pattern p1 = Pattern.createFromRegexp("^a*$");
    Pattern p2 = Pattern.createFromRegexp("^a+$");

    assertFalse(p1.isSubsetOf(p2));
    assertTrue(p2.isSubsetOf(p1));
  }

  @Test
  public void testIsEquivalent() {
    Pattern p1 = Pattern.createFromRegexp("^aa*$");
    Pattern p2 = Pattern.createFromRegexp("^a+$");

    assertTrue(p1.isEquivalent(p1));
    assertTrue(p1.isEquivalent(p2));
    assertTrue(p2.isEquivalent(p1));
  }

  @Test
  public void testComplement() {
    Pattern p = Pattern.createFromRegexp("^a$");
    assertTrue(p.isEquivalent(p.complement().complement()));
  }

  @Test
  public void testUnion() {
    Pattern p1 = Pattern.createFromRegexp("^a?$");
    Pattern p2 = Pattern.createFromRegexp("^b?$");
    Pattern u = p1.union(p2);

    assertTrue(u.match(""));
    assertTrue(u.match("a"));
    assertTrue(u.match("b"));
  }

  @Test
  public void testListComplement() {
    Pattern p1 = Pattern.createFromRegexp("^a$");
    Pattern p2 = Pattern.createFromRegexp("^b$");
  
    Collection<Pattern> patterns =  new HashSet<Pattern>();
    patterns.add(p1);
    patterns.add(p2);

    Pattern c = Pattern.listComplement(patterns);
    assertFalse(c.match("a"));
    assertFalse(c.match("b"));
    assertTrue(c.match("c"));
  }

  @Test
  public void testOverlaps() {
    Pattern p1 = Pattern.createFromRegexp("^a?b?$");
    Pattern p2 = Pattern.createFromRegexp("^b?c?$");
    Pattern p3 = Pattern.createFromName("xyz");

    assertTrue(Pattern.overlaps(p1, p2));
    assertFalse(Pattern.overlaps(p1, p3));
    assertFalse(Pattern.overlaps(p2,p3));
  }

  @Test
  public void testOverlapsCollection() {
    Pattern p1 = Pattern.createFromRegexp("^a?b?$");
    Pattern p2 = Pattern.createFromRegexp("^b?c?$");
    Pattern p3 = Pattern.createFromName("xyz");

    Collection<Pattern> collection = new HashSet<Pattern>();
    collection.add(p1);
    collection.add(p2);
    collection.add(p3);

    assertTrue(Pattern.overlaps(collection));
  }

  @Test
  public void testOverlapsCollectionNoMatch() {
    Pattern p1 = Pattern.createFromRegexp("^foo$");
    Pattern p2 = Pattern.createFromRegexp("^bar$");
    Pattern p3 = Pattern.createFromRegexp("^baz$");

    Collection<Pattern> collection = new HashSet<Pattern>();
    collection.add(p1);
    collection.add(p2);
    collection.add(p3);

    assertFalse(Pattern.overlaps(collection));
  }

  @Test(expected = IllegalArgumentException.class)
  public void  testInvalidPattern() {
    Pattern p = Pattern.createFromRegexp("{1,");
    System.out.println(p.toAutomatonString());
  }

}
