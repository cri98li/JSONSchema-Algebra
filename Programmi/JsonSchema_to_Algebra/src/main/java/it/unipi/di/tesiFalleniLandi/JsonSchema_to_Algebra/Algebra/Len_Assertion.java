package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public class Len_Assertion implements Assertion{
	private Integer min, max;
	
	public Len_Assertion() {
	}
	
	public Len_Assertion(Integer min, Integer max) {
		this.min = min;
		this.max = max;
	}

	public void setMin(Integer min) {
		this.min = min;
	}
	
	public void setMax(Integer max) {
		this.max = max;
	}
	
	public Len_Assertion intersect(Len_Assertion len) {
		Len_Assertion intersectedLen = new Len_Assertion();
		
		intersectedLen.setMin((min > len.min)? min:len.min);
		intersectedLen.setMax((max < len.max)? max:len.max);
		
		return intersectedLen;
	}

	@Override
	public String toString() {
		return "Len_Assertion [min=" + min + ", max=" + max + "]";
	}
	
	
}
