package patterns;

final class RETokenPOSIX extends REToken {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int type;
	boolean insens;
	boolean negated;

	static final int ALNUM = 0;
	static final int ALPHA = 1;
	static final int BLANK = 2;
	static final int CNTRL = 3;
	static final int DIGIT = 4;
	static final int GRAPH = 5;
	static final int LOWER = 6;
	static final int PRINT = 7;
	static final int PUNCT = 8;
	static final int SPACE = 9;
	static final int UPPER = 10;
	static final int XDIGIT = 11;
	static final int INDEX0 = 12; // \0, added by Steffi

	// Array indices correspond to constants defined above.
	static final String[] s_nameTable = { "alnum", "alpha", "blank", "cntrl", "digit", "graph", "lower", "print",
			"punct", "space", "upper", "xdigit", "index0" };

	// The RE constructor uses this to look up the constant for a string
	static int intValue(String key) {
		for (int i = 0; i < s_nameTable.length; i++) {
			if (s_nameTable[i].equals(key))
				return i;
		}
		return -1;
	}

	RETokenPOSIX(int subIndex, int type, boolean insens, boolean negated) {
		super(subIndex);
		this.type = type;
		this.insens = insens;
		this.negated = negated;
	}

	int getMinimumLength() {
		return 1;
	}

	void dump(StringBuffer os) {
		if (negated)
			os.append('^');
		os.append("[:" + s_nameTable[type] + ":]");
	}

	public void accept(REVisitor v) {
		v.visit(this);
	}

}
