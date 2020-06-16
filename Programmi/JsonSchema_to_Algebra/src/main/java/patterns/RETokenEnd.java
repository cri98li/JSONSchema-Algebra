package patterns;

final class RETokenEnd extends REToken {
  /**
   * Indicates whether this token should match on a line break.
   */
  private String newline;

  RETokenEnd(int subIndex,String newline) { 
    super(subIndex);
    this.newline = newline;
  }

  void dump(StringBuffer os) {
    os.append('$');
  }

  public void accept(REVisitor v) {
    v.visit(this);
  }
}
