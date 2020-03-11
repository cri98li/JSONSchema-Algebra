package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public class Exist_Assertion implements S{
	private Integer min, max;
	private S s;
	
	public Exist_Assertion() {
		
	}
	
	public void setMin(int min) {
		this.min = min;
	}
	
	public void setMax(int max) {
		this.max = max;
	}
	
	public void setS(S s) {
		this.s = s;
	}
	
	public Exist_Assertion intersect(Exist_Assertion exist) {
		Exist_Assertion intersectedExist = new Exist_Assertion();
		
		intersectedExist.setMin((min > exist.min)? min:exist.min);
		intersectedExist.setMax((max < exist.max)? max:exist.max);
		
		return intersectedExist;
	}
}
