package patterns;

//import org.apache.commons.lang3.StringEscapeUtils;

/**
  Translates JSON Schema pattern syntax (a subset of ECMAScript) such that 
  the Bricks automaton package can handle them.
*/

public class PatternAdapter {
 
  /**
    JSON Schema patterns are not anchored 
    unless they start with a ^ and end with a $.
  
    @param regex String in ECMAScript  syntax
    @return  regex String in Bricks syntax
  */
  protected static String bound(String ecmaRegex) {
    String bricksRegex;
    if (ecmaRegex.startsWith("^"))
      bricksRegex = ecmaRegex.substring(1);
    else
      bricksRegex = "@" + ecmaRegex;

     if (ecmaRegex.endsWith("$") && !ecmaRegex.endsWith("\\$"))
       bricksRegex = bricksRegex.substring(0, bricksRegex.length() - 1);
     else
       bricksRegex = bricksRegex + "@";

     return bricksRegex;
  }

  /*
    @param regex String in ECMAScript  syntax
    @return  regex String in Bricks syntax
  */
  public static String rewrite(String ecmaRegex) {
    return bound(ecmaRegex); // TODO - more to be done
  }

}
