package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public class Pro_Assertion implements Assertion{
	
	private Integer minProperties, maxProperties;
	
	public Pro_Assertion() {
		
	}
	
	public void setMinProperties(int min) {
		minProperties = min;
	}
	
	public void setMaxProperties(int max) {
		maxProperties = max;
	}
	
	public Pro_Assertion intersect(Pro_Assertion pro) {
		Pro_Assertion intersectedPro = new Pro_Assertion();
		
		intersectedPro.setMinProperties((minProperties > pro.minProperties)? minProperties:pro.minProperties);
		intersectedPro.setMaxProperties((maxProperties < pro.maxProperties)? maxProperties:pro.maxProperties);
		
		return intersectedPro;
	}

	@Override
	public String toString() {
		return "Pro_Assertion [minProperties=" + minProperties + ", maxProperties=" + maxProperties + "]";
	}

	
}
