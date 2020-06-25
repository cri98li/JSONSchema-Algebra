package patterns;


/**
 * Represents a combination lookahead/lookbehind for POSIX [:alnum:].
 */
final class RETokenWordBoundary extends REToken {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean negated;
    private int where;
    static final int BEGIN = 1;
    static final int END = 2;

    RETokenWordBoundary(int subIndex, int where, boolean negated) {
	super(subIndex);
	this.where = where;
	this.negated = negated;
    }
    
	    
   void dump(StringBuffer os) {
    if (where == (BEGIN | END)) {
      os.append( negated ? "\\B" : "\\b" );
    } else if (where == BEGIN) {
      os.append("\\<");
    } else {
      os.append("\\>");
    }
  }

  public void accept(REVisitor v) {
    v.visit(this);
  }

  public boolean accept(REBoolVisitor v) {
    return v.visit(this);
  }
}
