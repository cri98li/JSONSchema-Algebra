package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common;

//Da https://stackoverflow.com/questions/8610743/how-to-negate-any-regular-expression-in-java
public class MyPattern implements PosixPattern{
    private String pattern;

    public MyPattern(String pattern) {
        if(pattern.isEmpty()) {
            this.pattern = "^" + pattern + "$";
        }else{
            if(pattern.charAt(0) != '^' && pattern.charAt(pattern.length() -1) != '$')
                this.pattern = "^"+pattern+"$";
            else
                this.pattern = pattern;
        }
    }

    @Override
    public String getPattern() {
        return pattern;
    }

    @Override
    public boolean contains() {
        return false;
    }

    public String toString(){
        return pattern;
    }

    public String negate(){
        return "^(?!"+(pattern.replace("^", "")) + ").*$";
    }

    @Override
    public PosixPattern complement() {
        return null;
    }

    @Override
    public PosixPattern join(PosixPattern p) {
        return null;
    }

    @Override
    public PosixPattern[] intersect(PosixPattern p) {
        return new PosixPattern[0];
    }

    @Override
    public PosixPattern and(PosixPattern p) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyPattern myPattern = (MyPattern) o;

        return pattern != null ? pattern.equals(myPattern.pattern) : myPattern.pattern == null;
    }

    @Override
    public int hashCode() {
        return pattern != null ? pattern.hashCode() : 0;
    }
}
