package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class _BetItems_Assertion implements Assertion{
	private Long min, max;
	
	public _BetItems_Assertion() {	}
	
	public _BetItems_Assertion(Long min, Long max) {
		this.min = min;
		this.max = max;
	}
	
	public void setMin(Long min) {
		this.min = min;
	}
	
	public void setMax(Long max) {
		this.max = max;
	}

	@Override
	public String toString() {
		return "BetItems_Assertion [min=" + min + ", max=" + max + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONSchema() {

		JSONObject obj = new JSONObject();

		if(min != null) obj.put("minItems", min);
		if(max != null) obj.put("maxItems", max);

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
			or.add(new _BetItems_Assertion(0L, min - 1));
			or.add(new _BetItems_Assertion(max + 1, null));
			and.add(or);
			return and;
		}
		
		if(min != null) {
			and.add(new _BetItems_Assertion(0L, min - 1));
			return and;
		}
		
		and.add(new _BetItems_Assertion(max + 1, null));
		return and;
	}

	@Override
	public Assertion notElimination() {
		return new _BetItems_Assertion(min, max);
	}

	@Override
	public String toGrammarString() {
		String min = GrammarStringDefinitions.NEG_INF, max = GrammarStringDefinitions.POS_INF;

		if (this.min != null) min = this.min + "";
		if (this.max != null) max = this.max + "";

		return String.format(GrammarStringDefinitions.BETWEENITEMS, min, max);
	}

}
