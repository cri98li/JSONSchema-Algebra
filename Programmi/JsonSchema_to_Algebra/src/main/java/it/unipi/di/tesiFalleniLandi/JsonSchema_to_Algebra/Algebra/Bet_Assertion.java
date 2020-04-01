package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import com.google.gson.JsonObject;

public class Bet_Assertion implements Assertion{
	
	private Object min, max;
	
	public Bet_Assertion() {	}
	
	public Bet_Assertion(Object min, Object max) {
		this.min = min;
		this.max = max;
	}
	
	public void setMin(Object min) {
		this.min = min;
	}
	
	public void setMax(Object max) {
		this.max = max;
	}

	@Override
	public String toString() {
		return "Bet_Assertion [" + min + ", " + max + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "betweenNumber";
	}

	@Override
	public JsonObject toJSONSchema() {
		JsonObject obj = new JsonObject();
		
		if(max != null) obj.put("maximum", max);
		if(min != null) obj.put("minimum", min);
		
		return obj;
	}
	
	
}
