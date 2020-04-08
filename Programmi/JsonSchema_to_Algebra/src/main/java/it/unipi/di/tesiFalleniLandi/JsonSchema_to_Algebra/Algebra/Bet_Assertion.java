package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

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

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONSchema() {
		JSONObject obj = new JSONObject();
		
		if(max != null) obj.put("maximum", max);
		if(min != null) obj.put("minimum", min);
		
		return obj;
	}

	@Override
	public Assertion not() {
		And_Assertion and = new And_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add("num");
		and.add(type);
		
		if(min != null && max != null) {
			Or_Assertion or = new Or_Assertion();
			or.add(new XBet_Assertion(null, min));
			or.add(new XBet_Assertion(max, null));
			and.add(or);
			return and;
		}
		
		if(min != null) {
			and.add(new XBet_Assertion(null, min));
			return and;
		}
		
		
		and.add(new XBet_Assertion(max, null));
		return and;
	}

	@Override
	public Assertion notElimination() {
		return new Bet_Assertion(min, max);
	}
	
	public String toGrammarString() {
		return String.format(GrammarStringDefinitions.BETWEENNUMBER, min, max);
	}
}
