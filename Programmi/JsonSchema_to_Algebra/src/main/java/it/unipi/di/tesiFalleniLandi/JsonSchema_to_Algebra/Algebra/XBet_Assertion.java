package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import com.google.gson.JsonObject;

public class XBet_Assertion implements Assertion{
	private Object min, max;
	
	public void setMin(Object min) {
		this.min = min;
	}
	
	public void setMax(Object max) {
		this.max = max;
	}

	@Override
	public String toString() {
		return "XBet_Assertion [min=" + min + ", max=" + max + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "betweenNumber";
	}

	@Override
	public JsonObject toJSONSchema() {
		JsonObject obj = new JsonObject();
		
		if(max != null) obj.put("exclusiveMaximum", max);
		if(min != null) obj.put("exclusiveMinimum", min);
		
		/*
		if(booleanExclusiveMaximum != null) obj.put("exclusiveMaximum", booleanExclusiveMaximum);
		if(booleanExclusiveMinimum != null) obj.put("exclusiveMinimum", booleanExclusiveMinimum);
		*/
		
		return obj;
	}
	
	
}
