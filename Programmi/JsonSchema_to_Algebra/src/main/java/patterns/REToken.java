package patterns;

/**
  Adapted from gnu.regexp
*/

import java.io.Serializable;

abstract class REToken implements Serializable, RETokenI {

	private static final long serialVersionUID = 1L;
	protected REToken next = null;
	protected REToken uncle = null;

	protected boolean hasStart = false;

	protected int subIndex;

	protected REToken(int subIndex) {
		this.subIndex = subIndex;
	}

	int getMinimumLength() {
		return 0;
	}

	void setUncle(REToken anUncle) {
		uncle = anUncle;
	}

	boolean chain(REToken token) {
		next = token;
		return true; // Token was accepted
	}

	abstract void dump(StringBuffer os);

	void dumpAll(StringBuffer os) {
		dump(os);
		if (next != null)
			next.dumpAll(os);
	}

	protected boolean hasNext() {
		return next != null;
	}

	protected REToken getNext() {
		return next;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		dump(sb);
		return sb.toString();
	}

}
