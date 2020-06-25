package patterns;



final class RETokenChar extends REToken {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
protected char[] ch;
  RETokenChar(int subIndex, char c, boolean ins) {
    super(subIndex);
    ch = new char [1];
    ch[0] = (ins) ? Character.toLowerCase(c) : c;
  }

  int getMinimumLength() {
    return ch.length;
  }
  
  // Overrides REToken.chain() to optimize for strings
  boolean chain(REToken next) {
    if (next instanceof RETokenChar) {
      RETokenChar cnext = (RETokenChar) next;
      // assume for now that next can only be one character
      int newsize = ch.length + cnext.ch.length;
      
      char[] chTemp = new char [newsize];
      
      System.arraycopy(ch,0,chTemp,0,ch.length);
      System.arraycopy(cnext.ch,0,chTemp,ch.length,cnext.ch.length);
      
      ch = chTemp;
      return false;
    } else return super.chain(next);
  }

  void dump(StringBuffer os) {
    os.append(ch);
  }

  public void accept(REVisitor v) {
    v.visit(this);
  }

  public boolean accept(REBoolVisitor v) {
    return v.visit(this);
  }

}


