package patterns;


class RETokenStart extends REToken {
  private String newline; // matches after a newline
    
  RETokenStart(int subIndex, String newline) {
    super(subIndex);
    this.newline = newline;
  }
    
  void dump(StringBuffer os) {
    os.append('^');
  }

  public void accept(REVisitor v) {
    v.visit(this);
  }
}
