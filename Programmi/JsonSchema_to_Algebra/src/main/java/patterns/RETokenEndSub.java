package patterns;


final class RETokenEndSub extends REToken {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

RETokenEndSub(int subIndex) {
    super(subIndex);
  }
    
  void dump(StringBuffer os) {
  // handled by RE
  }

  public void accept(REVisitor v) {
    v.visit(this);
  }

  public boolean accept(REBoolVisitor v) {
    return v.visit(this);
  }
}
