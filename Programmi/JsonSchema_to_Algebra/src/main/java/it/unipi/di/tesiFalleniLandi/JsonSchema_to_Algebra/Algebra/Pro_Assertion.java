package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

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

	@Override
	public String getJSONSchemaKeyword() {
		return "betweenProperties";
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
			And_Assertion and = new And_Assertion();
			and.add(new Pro_Assertion(null, min-1));
			and.add(new Pro_Assertion(max+1, null));
			return and;
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
		// TODO Auto-generated method stub
		return String.format(GrammarStringDefinitions.BETWEENPROPERTIES, min, max);
	}
}
