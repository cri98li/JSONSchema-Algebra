package patterns;


final class RETokenBackRef extends REToken {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int num;
  RETokenBackRef(int subIndex, int num, boolean insens) {
    super(subIndex);
    this.num = num;
  }

  void dump(StringBuffer os) {
    os.append('\\').append(num);
  }

  public void accept(REVisitor v) {
    v.visit(this);
  }

  public boolean accept(REBoolVisitor v) {
    return v.visit(this);
  }
}


