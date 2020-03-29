package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import org.json.simple.JSONObject;

public class XBet_Assertion implements Assertion{
	private Integer min, max;
	
	public void setMin(int min) {
		this.min = min;
	}
	
	public void setMax(int max) {
		this.max = max;
	}
	
	public XBet_Assertion intersect(XBet_Assertion xbet) {
		XBet_Assertion intersectedXBet = new XBet_Assertion();
		
		intersectedXBet.setMin((min > xbet.min)? min:xbet.min);
		intersectedXBet.setMax((max < xbet.max)? max:xbet.max);
		
		return intersectedXBet;
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
	public Object toJSONSchema() {
		JSONObject obj = new JSONObject();
		
		if(max != null) obj.put("exclusiveMaximum", max);
		if(min != null) obj.put("exclusiveMinimum", min);
		
		/*
		if(booleanExclusiveMaximum != null) obj.put("exclusiveMaximum", booleanExclusiveMaximum);
		if(booleanExclusiveMinimum != null) obj.put("exclusiveMinimum", booleanExclusiveMinimum);
		*/
		
		return obj;
	}
	
	
}
