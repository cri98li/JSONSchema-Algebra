package patterns;

import java.text.MessageFormat;

//import jsynedit.search.*;


public class REException extends Exception {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int type;
  private int pos;

  // Error conditions from GNU regcomp(3) manual

  /**
   * Error flag.
   * Invalid use of repetition operators such  as  using
   * `*' as the first character.
   */
  public static final int REG_BADRPT  =  1;

  /**
   * Error flag.
   * Invalid use of back reference operator.
   */
  public static final int REG_BADBR   =  2;

  /**
   * Error flag.
   * Un-matched brace interval operators.
   */
  public static final int REG_EBRACE  =  3;

  /**
   * Error flag.
   * Un-matched bracket list operators.
   */
  public static final int REG_EBRACK  =  4;

  /**
   * Error flag.
   * Invalid  use  of the range operator, eg. the ending
   * point of the range occurs  prior  to  the  starting
   * point.
   */
  public static final int REG_ERANGE  =  5;

  /**
   * Error flag.
   * Unknown character class name. <B>Not implemented</B>.
   */
  public static final int REG_ECTYPE  =  6;

  /**
   * Error flag.
   * Un-matched parenthesis group operators.
   */
  public static final int REG_EPAREN  =  7;

  /**
   * Error flag.
   * Invalid back reference to a subexpression.
   */
  public static final int REG_ESUBREG =  8;

  /**
   * Error flag.
   * Non specific error. <B>Not implemented</B>.
   */
  public static final int REG_EEND    =  9;

  /**
   * Error flag.
   * Invalid escape sequence. <B>Not implemented</B>.
   */
  public static final int REG_ESCAPE  = 10;

  /**
   * Error flag.
   * Invalid  use  of pattern operators such as group or list.
   */
  public static final int REG_BADPAT  = 11;

  /**
   * Error flag.
   * Compiled  regular  expression  requires  a  pattern
   * buffer larger than 64Kb. <B>Not implemented</B>.
   */
  public static final int REG_ESIZE   = 12;

  /**
   * Error flag.
   * The regex routines ran out of memory. <B>Not implemented</B>.
   */
  public static final int REG_ESPACE  = 13;

  REException(String msg, int type, int position) { 
    super(msg); 
    this.type = type;
    this.pos = position;
  }

  /**
   * Returns the type of the exception, one of the constants listed above.
   */

  public int getType() {
    return type;
  }

  /**
   * Returns the position, relative to the string or character array being
   * compiled, where the error occurred.  This position is generally the point
   * where the error was detected, not necessarily the starting index of
   * a bad subexpression.
   */
  public int getPosition() {
    return pos;
  }

  /**
   * Reports the descriptive message associated with this exception
   * as well as its index position in the string or character array
   * being compiled.
   */
  public String getMessage() {
    @SuppressWarnings("deprecation")
	Object[] args = {new Integer(pos)};
    StringBuffer sb = new StringBuffer();
    String prefix = RE.getLocalizedMessage("error.prefix");
    sb.append(MessageFormat.format(prefix, args));
    sb.append('\n');
    sb.append(super.getMessage());
    return sb.toString();
  }
}
