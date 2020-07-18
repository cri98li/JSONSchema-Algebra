package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessPro;

public class Pro_Assertion implements Assertion{
	private Long min, max;
	
	public Pro_Assertion(Long min, Long max) {
		this.min = min;
		this.max = max;
	}
	
	public Pro_Assertion() {
	}

	@Override
	public String toString() {
		return "Pro_Assertion [minProperties=" + min + ", maxProperties=" + max + "]";
	}


	@SuppressWarnings("unchecked")
	@Override
	public JsonObject toJSONSchema() {
		JsonObject obj = new JsonObject();
		
		if(min != null)	obj.addProperty("minProperties", min);
		if(max != null)	obj.addProperty("maxProperties", max);
		
		return obj;
	}

	@Override
	public Assertion not() {
		AllOf_Assertion and = new AllOf_Assertion();
		if((min == null || min == 0) && max == null) {
			and.add(new Boolean_Assertion(false));
			return and;
		}

		Type_Assertion type = new Type_Assertion();
		type.add(GrammarStringDefinitions.TYPE_OBJECT);
		and.add(type);

		if(min != null && max != null) {
			AnyOf_Assertion or = new AnyOf_Assertion();
			if(min != 0)
				or.add(new Pro_Assertion(null, min-1));

			or.add(new Pro_Assertion(max+1, null));
			and.add(or);
			return and;
		}
		
		if(min != null)
			return new Pro_Assertion(null, min-1);
		
		
		return new Pro_Assertion(max+1, null);
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
		return new WitnessPro(min == null ? null : Double.parseDouble(min.toString()), max == null ? null : Double.parseDouble(max.toString()));
	}
}
