package patterns;

final class RETokenRange extends REToken {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private char lo, hi;
  RETokenRange(int subIndex, char lo, char hi, boolean ins) {
    super(subIndex);
    this.lo = (ins) ? Character.toLowerCase(lo) : lo;
    this.hi = ins ? Character.toLowerCase(hi) : hi;
  }

  int getMinimumLength() {
    return 1;
  }
    
  void dump(StringBuffer os) {
    os.append(lo).append('-').append(hi);
  }

  public void accept(REVisitor v) {
    v.visit(this);
  }

  public boolean accept(REBoolVisitor v) {
    return v.visit(this);
  }
}

