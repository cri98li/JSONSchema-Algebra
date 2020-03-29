package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import org.json.simple.JSONObject;

public class BetItems_Assertion implements Assertion{
	
	private Long min, max;
	
	public BetItems_Assertion() {	}
	
	public BetItems_Assertion(Long min, Long max) {
		this.min = min;
		this.max = max;
	}
	
	public void setMin(Long min) {
		this.min = min;
	}
	
	public void setMax(Long max) {
		this.max = max;
	}
	
	public BetItems_Assertion intersect(BetItems_Assertion bet) {
		BetItems_Assertion intersectedBet = new BetItems_Assertion();
		
		intersectedBet.setMin((min > bet.min)? min:bet.min);
		intersectedBet.setMax((max < bet.max)? max:bet.max);
		
		return intersectedBet;
	}
	

	@Override
	public String toString() {
		return "BetItems_Assertion [min=" + min + ", max=" + max + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "betweenItems";
	}

	@Override
	public Object toJSONSchema() {
		JSONObject obj = new JSONObject();
		
		if(max != null) obj.put("maxItems", max);
		if(min != null) obj.put("minItems", min);
		
		return obj;
	}

}
