package patterns;

import java.io.Serializable;


/**
 * permet de retouner le char (a la pos de anchor)
 * de deplacer anchor de (index)
 * de tester si anchor < s.length
 */
class CharIndexedCharArray implements CharIndexed, Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char[] s;
    private int anchor;
    
    CharIndexedCharArray(char[] str, int index) {
	s = str;
	anchor = index;
    }
    
    public char charAt(int index) {
	int pos = anchor + index;
	return ((pos < s.length) && (pos >= 0)) ? s[pos] : OUT_OF_BOUNDS;
    }
    
    public boolean isValid() {
	return (anchor < s.length);
    }
    
    public boolean move(int index) {
	return ((anchor += index) < s.length);
    }
}
