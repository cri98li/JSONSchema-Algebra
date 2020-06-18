package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import patterns.Pattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessPattern;
import org.json.simple.JSONObject;
import patterns.REException;

public class Len_Assertion implements Assertion{
	private Long min, max;
	
	public Len_Assertion() {
	}
	
	public Len_Assertion(Long min, Long max) {
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
		return "Len_Assertion [min=" + min + ", max=" + max + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONSchema() {
		JSONObject obj = new JSONObject();
		
		if(min != null)	obj.put("minLength", min);
		if(max != null)	obj.put("maxLength", max);
		
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
		type.add(GrammarStringDefinitions.TYPE_STRING);
		and.add(type);

		if(min != null && max != null) {
			AnyOf_Assertion or = new AnyOf_Assertion();
			if(min != 0)
				or.add(new Len_Assertion(null, min-1));

			or.add(new Len_Assertion(max+1, null));
			and.add(or);
			return and;
		}
		
		if(min != null) {
			and.add(new Len_Assertion(null, min-1));
			return and;
		}
		
		and.add(new Len_Assertion(max+1, null));
		return and;
	}

	@Override
	public Assertion notElimination() {
		return new Len_Assertion(min, max);
	}

	@Override
	public String toGrammarString() {
		String min = "0", max = GrammarStringDefinitions.POS_INF;

		if(this.min != null) min = this.min+"";
		if(this.max != null) max = this.max+"";

		return String.format(GrammarStringDefinitions.LENGTH, min, max);
	}

	@Override
	public WitnessAssertion toWitnessAlgebra() throws REException {
		String minStr = "0", maxStr = "";
		if(min != null) minStr = min.toString();
		if(max != null) maxStr = max.toString();

		return new WitnessPattern(Pattern.createFromRegexp("^.{"+ minStr +"," + maxStr + "}$"));
	}
}
