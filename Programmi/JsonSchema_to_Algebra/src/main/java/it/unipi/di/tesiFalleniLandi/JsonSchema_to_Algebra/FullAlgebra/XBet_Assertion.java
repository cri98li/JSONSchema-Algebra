package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessXBet;
import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class XBet_Assertion implements Assertion{
	private Object min, max;
	
	public XBet_Assertion(Object min, Object max) {
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
		return "XBet_Assertion [min=" + min + ", max=" + max + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONSchema() {
		JSONObject obj = new JSONObject();

		if(min != null) obj.put("exclusiveMinimum", min);
		if(max != null) obj.put("exclusiveMaximum", max);

		return obj;
	}
	
	@Override
	public Assertion not() {
		AllOf_Assertion and = new AllOf_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add("num");
		and.add(type);
		
		if(min != null && max != null) {
			AnyOf_Assertion or = new AnyOf_Assertion();
			or.add(new Bet_Assertion(null, min));
			or.add(new Bet_Assertion(max, null));
			and.add(or);
			return and;
		}
		
		if(min != null) {
			and.add(new Bet_Assertion(null, min));
			return and;
		}
		
		
		and.add(new Bet_Assertion(max, null));
		return and;
	}

	@Override
	public Assertion notElimination() {
		return new XBet_Assertion(min, max);
	}

	@Override
	public String toGrammarString() {
		String min = GrammarStringDefinitions.NEG_INF, max = GrammarStringDefinitions.POS_INF;

		if(this.min != null) min = this.min+"";
		if(this.max != null) max = this.max+"";

		return String.format(GrammarStringDefinitions.BETWEENNUMBER_EXCL, min, max);
	}

	@Override
	public WitnessAssertion toWitnessAlgebra() {
		Double min = null, max = null;

		if(this.min != null)
			min = Double.parseDouble(this.min.toString());

		if(this.max != null)
			max = Double.parseDouble(this.max.toString());

		return new WitnessXBet(min, max);
	}

}