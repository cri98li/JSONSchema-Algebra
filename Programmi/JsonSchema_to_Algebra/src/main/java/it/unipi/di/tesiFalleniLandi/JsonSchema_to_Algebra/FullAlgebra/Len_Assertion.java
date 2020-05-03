package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.MyPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessPattReq;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessPattern;
import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class Len_Assertion implements Assertion{
	private Long min, max;
	
	public Len_Assertion() { }
	
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
	
	public Len_Assertion intersect(Len_Assertion len) {
		Len_Assertion intersectedLen = new Len_Assertion();
		
		intersectedLen.setMin((min > len.min)? min:len.min);
		intersectedLen.setMax((max < len.max)? max:len.max);
		
		return intersectedLen;
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
		Type_Assertion type = new Type_Assertion();
		type.add(GrammarStringDefinitions.TYPE_STRING);
		and.add(type);

		if(min != null && max != null) {
			and.add(new Len_Assertion(null, min-1));
			and.add(new Len_Assertion(max+1, null));
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
	public WitnessAssertion toWitnessAlgebra() {
		return new WitnessPattern(new MyPattern("^.{"+ min +"," + max + "}$"));
	}
}
