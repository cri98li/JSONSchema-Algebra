package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessPro;
import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class Pro_Assertion implements Assertion{
	
	private Long min, max;
	
	public Pro_Assertion(Long min, Long max) {
		this.min = min;
		this.max = max;
	}
	
	public Pro_Assertion() {
	}

	public Pro_Assertion intersect(Pro_Assertion pro) {
		Pro_Assertion intersectedPro = new Pro_Assertion();
		
		intersectedPro.min = (min > pro.min)? min:pro.min;
		intersectedPro.max = (max < pro.max)? max:pro.max;
		
		return intersectedPro;
	}

	@Override
	public String toString() {
		return "Pro_Assertion [minProperties=" + min + ", maxProperties=" + max + "]";
	}


	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONSchema() {
		JSONObject obj = new JSONObject();
		
		if(min != null)	obj.put("minProperties", min);
		if(max != null)	obj.put("maxProperties", max);
		
		return obj;
	}

	@Override
	public Assertion not() {
		if(min != null && max != null) {
			AnyOf_Assertion or = new AnyOf_Assertion();
			or.add(new Pro_Assertion(null, min-1));
			or.add(new Pro_Assertion(max+1, null));
			return or;
		}
		
		if(min != null)
			return new Len_Assertion(null, min-1);
		
		
		return new Len_Assertion(max+1, null);
	}

	@Override
	public Assertion notElimination() {
		return new Pro_Assertion(min, max);
	}

	@Override
	public String toGrammarString() {
		String min = "0", max = GrammarStringDefinitions.POS_INF;

		if(this.min != null) min = this.min+"";
		if(this.max != null) max = this.max+"";

		return String.format(GrammarStringDefinitions.BETWEENPROPERTIES, min, max);
	}

	@Override
	public WitnessAssertion toWitnessAlgebra() {
		return new WitnessPro(Double.parseDouble(min.toString()), max == null ? null : Double.parseDouble(max.toString()));
	}
}
