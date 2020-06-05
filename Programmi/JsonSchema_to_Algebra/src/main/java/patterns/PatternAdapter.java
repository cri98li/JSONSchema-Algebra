package patterns;

/**
  Translates JSON Schema pattern syntax (a subset of ECMAScript) such that 
  the Bricks automaton package can handle them.
*/

public class PatternAdapter {
 
  /**
    JSON Schema patterns are not anchored 
    unless they start with a ^ and end with a $.
  */
  protected static String bound(String regex) {
    String bricksRegex;
    if (regex.startsWith("^"))
      bricksRegex = regex.substring(1);
    else
      bricksRegex = "@" + regex;

     if (regex.endsWith("$") && !regex.endsWith("\\$"))
       bricksRegex = bricksRegex.substring(0, bricksRegex.length() - 1);
     else
       bricksRegex = bricksRegex + "@";

     return bricksRegex;
  }

  public static String rewrite(String regex) {
    return bound(regex); // TODO - more to be done
  }

}
