package patterns;

class RETokenStart extends REToken {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	RETokenStart(int subIndex, String newline) {
		super(subIndex);
	}

	void dump(StringBuffer os) {
		os.append('^');
	}

	public void accept(REVisitor v) {
		v.visit(this);
	}

}
