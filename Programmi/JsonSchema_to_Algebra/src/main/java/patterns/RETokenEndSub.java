package patterns;


final class RETokenEndSub extends REToken {
  RETokenEndSub(int subIndex) {
    super(subIndex);
  }
    
  void dump(StringBuffer os) {
  // handled by RE
  }

  public void accept(REVisitor v) {
    v.visit(this);
  }
}
