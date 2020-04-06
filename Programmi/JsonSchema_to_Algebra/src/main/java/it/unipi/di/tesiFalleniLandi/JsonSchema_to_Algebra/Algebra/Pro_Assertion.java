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
	public JSONObject toJSONSchema() {
		JSONObject obj = new JSONObject();
		
		obj.put("minProperties", minProperties);
		obj.put("maxProperties", maxProperties);
		
		return obj;
	}

	@Override
	public Assertion not() {
		if(minProperties != null && maxProperties != null) {
			And_Assertion and = new And_Assertion();
			and.add(new Pro_Assertion(null, minProperties-1));
			and.add(new Pro_Assertion(maxProperties+1, null));
			return and;
		}
		
		if(minProperties != null)
			return new Len_Assertion(null, minProperties-1);
		
		
		return new Len_Assertion(maxProperties+1, null);
	}
}
