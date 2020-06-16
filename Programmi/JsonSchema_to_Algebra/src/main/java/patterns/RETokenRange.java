package patterns;

final class RETokenRange extends REToken {
  private char lo, hi;
  private boolean insens;

  RETokenRange(int subIndex, char lo, char hi, boolean ins) {
    super(subIndex);
    this.lo = (insens = ins) ? Character.toLowerCase(lo) : lo;
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
}

