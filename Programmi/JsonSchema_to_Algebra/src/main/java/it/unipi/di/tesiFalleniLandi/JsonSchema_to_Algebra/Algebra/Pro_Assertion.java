package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import com.google.gson.JsonObject;

public class Pro_Assertion implements Assertion{
	
	private Long minProperties, maxProperties;
	
	public Pro_Assertion(Long min, Long max) {
		minProperties = min;
		maxProperties = max;
	}
	
	private Pro_Assertion() {
	}

	public Pro_Assertion intersect(Pro_Assertion pro) {
		Pro_Assertion intersectedPro = new Pro_Assertion();
		
		intersectedPro.minProperties = (minProperties > pro.minProperties)? minProperties:pro.minProperties;
		intersectedPro.maxProperties = (maxProperties < pro.maxProperties)? maxProperties:pro.maxProperties;
		
		return intersectedPro;
	}

	@Override
	public String toString() {
		return "Pro_Assertion [minProperties=" + minProperties + ", maxProperties=" + maxProperties + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "betweenProperties";
	}

	@Override
	public JsonObject toJSONSchema() {
		JsonObject obj = new JsonObject();
		
		obj.put("minProperties", minProperties);
		obj.put("maxProperties", maxProperties);
		
		return obj;
	}

	
}
