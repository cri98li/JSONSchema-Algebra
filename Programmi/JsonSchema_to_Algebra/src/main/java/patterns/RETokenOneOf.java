package patterns;

import java.util.Vector;


final class RETokenOneOf extends REToken {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
protected Vector<REToken> options;
  protected boolean negative;

  // This constructor is used for convenience when we know the set beforehand,
  // e.g. \d --> new RETokenOneOf("0123456789",false, ..)
  //      \D --> new RETokenOneOf("0123456789",true, ..)

  RETokenOneOf(int subIndex, String optionsStr, boolean negative, boolean insens) {
    super(subIndex);
    options = new Vector<REToken>();
    this.negative = negative;
    for (int i = 0; i < optionsStr.length(); i++)
      options.addElement(new RETokenChar(subIndex,optionsStr.charAt(i),insens));
  }

  RETokenOneOf(int subIndex, Vector<REToken> options, boolean negative) {
    super(subIndex);
    this.options = options;
    this.negative = negative;
  }

  int getMinimumLength() {
    int min = Integer.MAX_VALUE;
    int x;
    for (int i=0; i < options.size(); i++) {
      if ((x = ((REToken) options.elementAt(i)).getMinimumLength()) < min)
	min = x;
    }
    return min;
  }

  void dump(StringBuffer os) {
    os.append(negative ? "[^" : "(?:");
    for (int i = 0; i < options.size(); i++) {
      if (!negative && (i > 0)) os.append('|');
      ((REToken) options.elementAt(i)).dumpAll(os);
    }
    os.append(negative ? ']' : ')');
  }  

  public void accept(REVisitor v) {
    v.visit(this);
  }

  public boolean accept(REBoolVisitor v) {
    return v.visit(this);
  }

}
