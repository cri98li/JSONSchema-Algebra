
package patterns;

import java.util.Vector;


final class RETokenRepeated extends REToken {
    protected REToken token;
    protected int min,max;
    protected boolean stingy;
    
    RETokenRepeated(int subIndex, REToken token, int min, int max) {
	super(subIndex);
	this.token = token;
	this.min = min;
	this.max = max;
    }

    /** Sets the minimal matching mode to true. */
    void makeStingy() {
	stingy = true;
    }
    
    /** Queries if this token has minimal matching enabled. */
    boolean isStingy() {
	return stingy;
    }
    
    /**
     * The minimum length of a repeated token is the minimum length
     * of the token multiplied by the minimum number of times it must
     * match.
     */
    int getMinimumLength() {
	return (min * token.getMinimumLength());
    }

    void dump(StringBuffer os) {
	os.append("(?:");
	token.dumpAll(os);
	os.append(')');
	if ((max == Integer.MAX_VALUE) && (min <= 1))
	    os.append( (min == 0) ? '*' : '+' );
	else if ((min == 0) && (max == 1))
	    os.append('?');
	else {
	    os.append('{').append(min);
	    if (max > min) {
		os.append(',');
		if (max != Integer.MAX_VALUE) os.append(max);
	    }
	    os.append('}');
	}
	if (stingy) os.append('?');
    }

  public void accept(REVisitor v) {
    v.visit(this);
  }
}
