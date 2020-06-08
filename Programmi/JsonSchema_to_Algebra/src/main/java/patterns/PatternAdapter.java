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
   protected static String translate(String ecmaRegex) {
     String bricksHead = "";
     String bricksTail = "";

     if (ecmaRegex.startsWith("^"))
       ecmaRegex = ecmaRegex.substring(1);
     else
       bricksHead = "@";

     if (ecmaRegex.endsWith("$") && ! ecmaRegex.endsWith("\\$"))
       ecmaRegex = ecmaRegex.substring(0, ecmaRegex.length() -1);
     else
       bricksTail = "@";

     return bricksHead + rewriteCore(ecmaRegex) + bricksTail;
   }

  /**
    Mimicks a poor man's recursive descent parser to rewrite the regex from left to right.
  */
  protected static String rewriteCore(String ecmaRegex) {
    //System.out.println("\nrewriteCore: " + ecmaRegex);
   
    if (ecmaRegex.isEmpty())
      return "";

    //System.out.println("\n->" + ecmaRegex);

    // Preserve single characters to be matched.
    if (ecmaRegex.matches("^[a-zA-Z].*$"))
      return ecmaRegex.substring(0,1) + rewriteCore(ecmaRegex.substring(1));

    // Bricks automaton does not know "\d" for digits.
    if (ecmaRegex.matches("^\\\\d.*$"))
      return "[0-9]" + rewriteCore(ecmaRegex.substring(2));

    // Preserve what is already escaped.
    if (ecmaRegex.matches("^\\\\\\\\[.*+?!@d].*$"))
      return ecmaRegex.substring(0,3) + rewriteCore(ecmaRegex.substring(3));

    if (ecmaRegex.startsWith("@"))
      return "\\@" + rewriteCore(ecmaRegex.substring(1));

    if (ecmaRegex.startsWith("[")) {
      // find position of closing parenthesis.
      int escapedPar = ecmaRegex.indexOf("\\]");
      int endPar = ecmaRegex.indexOf("]");

      // TODO - do not fall for \]
      if (escapedPar + 1 == endPar) 
        endPar = endPar + 1 + ecmaRegex.substring(endPar + 1).indexOf("]");

      String charclass = rewriteCharClass(ecmaRegex.substring(1, endPar));

      return "[" + charclass + "]" + rewriteCore(ecmaRegex.substring(endPar+1));
    }

    return ecmaRegex;
  }


  /**
    Rewriting within a character class "[abc...]".
  */
  protected static String rewriteCharClass(String ecmaRegex) {
    //System.out.println("\nrewriteCharClass: " + ecmaRegex);

    if (ecmaRegex.matches("^[a-zA-Z0-9].*$"))
      return ecmaRegex.substring(0, 1) + rewriteCharClass(ecmaRegex.substring(1));

    // Bricks automaton does not know "\d" for digits.
    if (ecmaRegex.matches("^\\\\d.*$"))
      return "0-9" + rewriteCharClass(ecmaRegex.substring(2));

    if (ecmaRegex.matches("^\\\\[\\]\\[].*$"))
      return ecmaRegex.substring(0,2) + rewriteCharClass(ecmaRegex.substring(2));

    return ecmaRegex; // TODO
  }


  /**
    @param regex String in ECMAScript syntax
    @return regex String in Bricks syntax
  */
  public static String rewrite(String ecmaRegex) {
    // Make sure you first escape, and then bind to ^ and $.
    // Otherwise, trailing "@" symbols get escaped, too.
    /*
    String escaped = escape(ecmaRegex);

    String resolved = resolve(escaped);
   
    String bounded = bound(resolved);
    */
    return translate(ecmaRegex); 
  }

}
