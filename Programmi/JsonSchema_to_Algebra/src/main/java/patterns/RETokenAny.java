package patterns;


final class RETokenAny extends REToken {
  /** True if '.' can match a newline (RE_DOT_NEWLINE) */
  private boolean newline; 

  /** True if '.' can't match a null (RE_DOT_NOT_NULL) */
  private boolean matchNull;    
  
  RETokenAny(int subIndex, boolean newline, boolean matchNull) { 
    super(subIndex);
    this.newline = newline;
    this.matchNull = matchNull;
  }

  int getMinimumLength() {
    return 1;
  }

  void dump(StringBuffer os) {
    os.append('.');
  }

  public void accept(REVisitor v) {
    v.visit(this);
  }
}

