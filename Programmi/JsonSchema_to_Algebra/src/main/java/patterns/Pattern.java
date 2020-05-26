package patterns;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.PosixPattern;

import java.util.Set;
import java.util.HashSet;

/**
 This class uses foma and fomaJNI,
 from https://github.com/dcavar/fomaJNI.
 */

public class Pattern implements PosixPattern {

    static {
        System.loadLibrary("fomaJNI");
    }

    private long ptr_; // class pointer to net

    private native long getFST(String fname); // returns a net pointer

    // TODO - refactor so that pointer is not passed as an argument.
    private native void destroyFST(long ptr); // calls fsm_destroy on net pointer

    private native long intersectFST(long otherPtr);

    private native long minusFST(long otherPtr);

    private native boolean isEmptyFST();

    private native boolean matchFST(String str);

    // TODO - check out main memory management with JNI,
    // make sure we don't get memory leaks.
    // https://thebreakfastpost.com/2012/02/09/wrapping-a-c-library-with-jni-part-3/
    protected void finalize() throws Throwable {
        destroy();
    }

    public void destroy() {
        System.out.println("Destroying " + ptr_);
        destroyFST(ptr_);
    }

    public static Pattern createFromName(String name) {
        return new Pattern("^" + name + "$");
    }

    public static Pattern createFromRegexp(String regex) {
        return new Pattern(regex);
    }

    private Pattern(long ptr) {
        this.ptr_ = ptr;
    }

    public Pattern(String regex) {
        // TODO - translate to FOMA syntax


        String fomaRegex = regex.replace("?", "^{0,1}");

        // TODO - find library that translates from ECMA to XEROX
        ptr_ = getFST(fomaRegex);
    }

    public boolean isEmpty(){
        return isEmptyFST();
    }

    public boolean match(String str) {
        return matchFST(str);
    }

    public PosixPattern intersect(PosixPattern p) {
        return new Pattern(this.intersectFST(((Pattern) p).ptr_));
    }

    public PosixPattern minus(PosixPattern p) {
        return new Pattern(this.minusFST(((Pattern) p).ptr_));
    }

    /*
      if domain(a) is finite, return |a|.
      Otherwise return null;
    */
    @Override
    public Integer domainSize() {
        return 42;
    }

    @Override
    public Set<String> generateWords() {
        Set<String> set = new HashSet<String>();
        set.add("a");
        return set;
    }

    public static void main(String[] args) {
        System.out.println("This is Pattern.java");

        Pattern pattern = Pattern.createFromRegexp("a?");
        if (pattern.isEmpty())
            System.out.println("Language is empty");
        else
            System.out.println("Language NOT empty");
    }
}