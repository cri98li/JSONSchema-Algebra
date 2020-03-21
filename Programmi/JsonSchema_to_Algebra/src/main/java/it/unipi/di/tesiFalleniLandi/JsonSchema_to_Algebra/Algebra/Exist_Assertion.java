package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public class Exist_Assertion implements Assertion{
	private Integer min, max;
	private Assertion schema;
	
	public Exist_Assertion() {
		
	}
	
	public Exist_Assertion(Integer min, Integer max, Assertion schema) {
		this.min = min;
		this.max = max;
		this.schema = schema;
	}

	public void setMin(int min) {
		this.min = min;
	}
	
	public void setMax(int max) {
		this.max = max;
	}
	
	public void setS(Assertion schema) {
		this.schema = schema;
	}
	
	public Exist_Assertion intersect(Exist_Assertion exist) {
		Exist_Assertion intersectedExist = new Exist_Assertion();
		
		intersectedExist.setMin((min > exist.min)? min:exist.min);
		intersectedExist.setMax((max < exist.max)? max:exist.max);
		
		return intersectedExist;
	}

	@Override
	public String toString() {
		return "Exist_Assertion [min=" + min + ", max=" + max + ", schema=" + schema + "]";
	}
	
	
}
