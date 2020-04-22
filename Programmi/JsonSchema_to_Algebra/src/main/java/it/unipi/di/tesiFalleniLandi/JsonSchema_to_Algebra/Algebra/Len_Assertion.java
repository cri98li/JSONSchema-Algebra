package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

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

	@Override
	public String getJSONSchemaKeyword() {
		return "length";
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
		if(min != null && max != null) {
			And_Assertion and = new And_Assertion();
			and.add(new Len_Assertion(null, min-1));
			and.add(new Len_Assertion(max+1, null));
			return and;
		}
		
		if(min != null)
			return new Len_Assertion(null, min-1);
		
		
		return new Len_Assertion(max+1, null);
	}

	@Override
	public Assertion notElimination() {
		// TODO Auto-generated method stub
		return new Len_Assertion(min, max);
	}

	@Override
	public String toGrammarString() {
		return String.format(GrammarStringDefinitions.LENGTH, min, max);
	}
}
