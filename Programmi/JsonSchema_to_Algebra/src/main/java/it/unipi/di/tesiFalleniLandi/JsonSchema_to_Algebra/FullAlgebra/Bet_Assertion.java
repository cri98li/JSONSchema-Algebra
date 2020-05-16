package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessBet;
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

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONSchema() {
		JSONObject obj = new JSONObject();
		
		if(min != null) obj.put("minimum", min);
		if(max != null) obj.put("maximum", max);
		
		return obj;
	}

	@Override
	public Assertion not() {
		AllOf_Assertion and = new AllOf_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add(GrammarStringDefinitions.TYPE_NUMBER);
		and.add(type);
		
		if(min != null && max != null) {
			AnyOf_Assertion or = new AnyOf_Assertion();
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
		String min = GrammarStringDefinitions.NEG_INF, max = GrammarStringDefinitions.POS_INF;

		if(this.min != null) min = this.min+"";
		if(this.max != null) max = this.max+"";

		return String.format(GrammarStringDefinitions.BETWEENNUMBER, min, max);
	}

	@Override
	public WitnessBet toWitnessAlgebra() {
		Double min = null, max = null;

		if(this.min != null)
			min = Double.parseDouble(this.min.toString());

		if(this.max != null)
			max = Double.parseDouble(this.max.toString());


		return new WitnessBet(min, max);
	}
}
