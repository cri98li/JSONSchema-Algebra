package patterns;

final class RETokenAny extends REToken {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	RETokenAny(int subIndex, boolean newline, boolean matchNull) {
		super(subIndex);
	}

	int getMinimumLength() {
		return 1;
	}

	void dump(StringBuffer os) {
		os.append('.');
	}

	public void accept(REVisitor v) {
		v.visit(this);
	}

}
