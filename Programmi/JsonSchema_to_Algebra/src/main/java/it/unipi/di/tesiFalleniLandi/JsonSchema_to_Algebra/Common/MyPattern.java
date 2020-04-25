package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common;

//Da https://stackoverflow.com/questions/8610743/how-to-negate-any-regular-expression-in-java
public class MyPattern {
    private String pattern;

    public MyPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    public String negate(){
        return "^(?!"+(pattern.replace("^", "")) + ").*$";
    }
}
