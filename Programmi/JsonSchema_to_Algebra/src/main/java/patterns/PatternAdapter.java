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

    //if (ecmaRegex.matches("^[a-zA-Z0-9)][*?+].*$"))
    //  return ecmaRegex.substring(0,2) + rewriteCore(ecmaRegex.substring(2));

    // Ignore non capturing goups "?:".
    if (ecmaRegex.startsWith("?:"))
      return rewriteCore(ecmaRegex.substring(2));

    // Repetition, e.g. a{3}.
    if (ecmaRegex.matches("^\\{\\d\\}.*$")) {
      int endPos = ecmaRegex.indexOf("}");
      return ecmaRegex.substring(0, endPos + 1) + rewriteCore(ecmaRegex.substring(endPos + 1));
    }

    // Repetition, e.g. a{3,5}.
    if (ecmaRegex.matches("^\\{\\d,\\d\\}.*$")) {
      int endPos = ecmaRegex.indexOf("}");
      return ecmaRegex.substring(0, endPos + 1) + rewriteCore(ecmaRegex.substring(endPos + 1));
    }

    // Preserve single characters to be matched.
    if (ecmaRegex.matches("^[a-zA-Z|0-9()?*+: ].*$"))
      return ecmaRegex.substring(0,1) + rewriteCore(ecmaRegex.substring(1));

    // Bricks automaton does not know "\d" for digits.
    if (ecmaRegex.matches("^\\\\d.*$"))
      return "[0-9]" + rewriteCore(ecmaRegex.substring(2));

    // Preserve what is already escaped.
    if (ecmaRegex.matches("^\\\\[.*+?!$dntrfv()].*$")) 
      return ecmaRegex.substring(0,2) + rewriteCore(ecmaRegex.substring(2));

    // Vertical tab "\v" is not recognized by Bricks automata.
    //if (ecmaRegex.matches("^\\\\v.*$"))
    //  return "\\u000b" + rewriteCore(ecmaRegex.substring(2));

    // Supposed to match "\\"
    if (ecmaRegex.matches("^\\\\\\\\.*$"))
      return ecmaRegex.substring(0,2) + rewriteCore(ecmaRegex.substring(2));

    if (ecmaRegex.startsWith("@"))
      return "\\@" + rewriteCore(ecmaRegex.substring(1));

    // Recognize a charclass [... someting ...]
    if (ecmaRegex.startsWith("[")) {
      // find position of closing parenthesis.
      int escapedPar = ecmaRegex.indexOf("\\]");
      int endPar = ecmaRegex.indexOf("]");

      // TODO - do not fall for \]
      if (escapedPar + 1 == endPar) 
        endPar = endPar + 1 + ecmaRegex.substring(endPar + 1).indexOf("]");

      String charclass = rewriteCharClass(ecmaRegex.substring(1, endPar));

      // handle [...]+, [...]?, [..]*
      if (endPar < ecmaRegex.length() - 1) {
        char lookahead = ecmaRegex.charAt(endPar + 1);
        if (lookahead == '+' || lookahead == '?' || lookahead == '*' )
          return "[" + charclass + "]" + Character.toString(lookahead) + rewriteCore(ecmaRegex.substring(endPar + 2)); 
        else
          return "[" + charclass + "]" + rewriteCore(ecmaRegex.substring(endPar + 1));
     } else 
       return "[" + charclass + "]";
    }


    // If part of the string is unprocessed, this is usually a bug.
    assert ecmaRegex.isEmpty() : ("Left over ecmaRegex: " + ecmaRegex);

    return ecmaRegex;
  }


  /**
    Rewriting within a character class "[abc...]".
  */
  protected static String rewriteCharClass(String ecmaRegex) {
    //System.out.println("\nrewriteCharClass: " + ecmaRegex);

    if (ecmaRegex.isEmpty())
      return "";

    if (ecmaRegex.charAt(0) == '^')
      return "^" + rewriteCharClass(ecmaRegex.substring(1));

    if (ecmaRegex.matches("^[a-zA-Z0-9]-[a-zA-Z0-9].*$"))
      return ecmaRegex.substring(0,3) + rewriteCharClass(ecmaRegex.substring(3));

    // Characters that are simply preserved.
    if (ecmaRegex.matches("^[-a-zA-Z0-9_ ().+{}:/].*$"))
      return ecmaRegex.substring(0, 1) + rewriteCharClass(ecmaRegex.substring(1));

    // Bricks automaton does not know "\d" for digits.
    if (ecmaRegex.matches("^\\\\d.*$"))
      return "0-9" + rewriteCharClass(ecmaRegex.substring(2));

    if (ecmaRegex.matches("^\\\\S.*$")) // TODO - this is probably too crude
      return "^\\r\\n\\t\\f\\v " + rewriteCharClass(ecmaRegex.substring(2)); 

    // Preserve escaped brackets \[ and \].
    if (ecmaRegex.matches("^\\\\[\\]\\[].*$"))
      return ecmaRegex.substring(0,2) + rewriteCharClass(ecmaRegex.substring(2));

    // Preserve what is already escaped.
    if (ecmaRegex.matches("^\\\\[.*+?!dntrfv].*$"))
      return ecmaRegex.substring(0,2) + rewriteCharClass(ecmaRegex.substring(2));

    // "\"
    if (ecmaRegex.matches("^\\\\.*$"))
      return "\\\\" + rewriteCharClass(ecmaRegex.substring(1));

    // Vertical tab "\v" is not recognized by Bricks automata.
    //if (ecmaRegex.matches("^\\\\v.*$"))
    //  return "\\u000b" + rewriteCharClass(ecmaRegex.substring(2));

    // If part of the string is unprocessed, this is usually a bug.
    assert ecmaRegex.isEmpty() : ("Left over ecmaRegex: '" + ecmaRegex + "'");

    return ecmaRegex; // TODO
  }


  /**
    @param regex String in ECMAScript syntax
    @return regex String in Bricks syntax
  */
  public static String rewrite(String ecmaRegex) {
    // TODO - inline

    return translate(ecmaRegex); 
  }

}
