package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessBet;

public class Bet_Assertion implements Assertion{
	private Number min;
	private Number max;
	
	public Bet_Assertion() {	}
	
	public Bet_Assertion(Number min, Number max) {
		this.min = min;
		this.max = max;
	}
	
	public void setMin(Number min) {
		this.min = min;
	}
	
	public void setMax(Number max) {
		this.max = max;
	}

	@Override
	public String toString() {
		return "Bet_Assertion [" + min + ", " + max + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JsonElement toJSONSchema() {
		JsonObject obj = new JsonObject();
		
		if(min != null) obj.addProperty("minimum", min);
		if(max != null) obj.addProperty("maximum", max);
		
		return obj;
	}

	@Override
	public Assertion not() {
		AllOf_Assertion and = new AllOf_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add(FullAlgebraString.TYPE_NUMBER);
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
		String min = FullAlgebraString.NEG_INF;
		String max = FullAlgebraString.POS_INF;

		if(this.min != null) min = this.min+"";
		if(this.max != null) max = this.max+"";

		return FullAlgebraString.BETWEENNUMBER(min, max);
	}

	@Override
	public WitnessBet toWitnessAlgebra() {
		Double min = null;
		Double max = null;

		if(this.min != null)
			min = Double.parseDouble(this.min.toString());

		if(this.max != null)
			max = Double.parseDouble(this.max.toString());


		return new WitnessBet(min, max);
	}
}
