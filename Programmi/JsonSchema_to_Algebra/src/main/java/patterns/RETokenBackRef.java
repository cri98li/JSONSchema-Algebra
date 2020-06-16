package patterns;


final class RETokenBackRef extends REToken {
  private int num;
  private boolean insens;
  
  RETokenBackRef(int subIndex, int num, boolean insens) {
    super(subIndex);
    this.num = num;
    this.insens = insens;
  }

  void dump(StringBuffer os) {
    os.append('\\').append(num);
  }

  public void accept(REVisitor v) {
    v.visit(this);
  }

}


