package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

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

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONSchema() {
		JSONObject obj = new JSONObject();
		
		if(max != null) obj.put("maxItems", max);
		if(min != null) obj.put("minItems", min);
		
		return obj;
	}
	
	@Override
	public Assertion not() {
		And_Assertion and = new And_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add("obj");
		and.add(type);
		
		if(min != null && max != null) {
			Or_Assertion or = new Or_Assertion();
			or.add(new BetItems_Assertion(0L, min - 1));
			or.add(new BetItems_Assertion(max + 1, null));
			and.add(or);
			return and;
		}
		
		if(min != null) {
			and.add(new BetItems_Assertion(0L, min - 1));
			return and;
		}
		
		and.add(new BetItems_Assertion(max + 1, null));
		return and;
	}

	@Override
	public Assertion notElimination() {
		return new BetItems_Assertion(min, max);
	}

	@Override
	public String toGrammarString() {
		String str = "";
		
		if(min != null && max != null) {
			str = String.format(GrammarStringDefinitions.BETWEENITEMS, min, max);
		} else if (min != null && max == null) {
			str = String.format(GrammarStringDefinitions.BETWEENITEMS, min, GrammarStringDefinitions.NULLVALUE);
		}else if(min == null && max != null) {
			str = String.format(GrammarStringDefinitions.BETWEENITEMS, GrammarStringDefinitions.NULLVALUE, max);
		}
			
		return str;
	}

}
