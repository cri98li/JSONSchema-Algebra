package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public class Len_Assertion {
	private Integer min, max;
	
	public void setMin(int min) {
		this.min = min;
	}
	
	public void setMax(int max) {
		this.max = max;
	}
	
	public Len_Assertion intersect(Len_Assertion len) {
		Len_Assertion intersectedLen = new Len_Assertion();
		
		intersectedLen.setMin((min > len.min)? min:len.min);
		intersectedLen.setMax((max < len.max)? max:len.max);
		
		return intersectedLen;
	}
}
