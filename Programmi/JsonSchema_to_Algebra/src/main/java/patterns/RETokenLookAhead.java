package patterns;

/**
 * @since gnu.regexp 1.1.3
 * @author Shashank Bapat
 */
final class RETokenLookAhead extends REToken {
  REToken re;
  boolean negative;

  RETokenLookAhead(REToken re, boolean negative) throws REException {
    super(0);
    this.re = re;
    this.negative = negative;
  }

  void dump(StringBuffer os) {
    os.append("(?");
    os.append(negative ? '!' : '=');
    re.dumpAll(os);
    os.append(')');
  }

  public void accept(REVisitor v) {
    v.visit(this);
  }
}

