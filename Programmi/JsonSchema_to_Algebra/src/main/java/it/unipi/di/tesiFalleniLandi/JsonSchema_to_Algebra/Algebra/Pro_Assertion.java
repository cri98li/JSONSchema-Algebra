package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import org.json.simple.JSONObject;

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

	@SuppressWarnings("unchecked")
	@Override
	public Object toJSONSchema() {
		JSONObject obj = new JSONObject();
		
		obj.put("minProperties", minProperties);
		obj.put("maxProperties", maxProperties);
		
		return obj;
	}

	
}
