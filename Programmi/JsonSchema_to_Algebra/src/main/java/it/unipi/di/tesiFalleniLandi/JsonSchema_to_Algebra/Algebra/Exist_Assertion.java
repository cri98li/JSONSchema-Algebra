package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.json.simple.JSONObject;

public class Exist_Assertion implements Assertion{
	private Long min, max;
	private Assertion contains;
	
	public Exist_Assertion() {	}
	
	public Exist_Assertion(Long min, Long max, Assertion schema) {
		this.min = min;
		this.max = max;
		this.contains = schema;
	}

	public void setMin(Long min) {
		this.min = min;
	}
	
	public void setMax(Long max) {
		this.max = max;
	}
	
	public void setContains(Assertion schema) {
		this.contains = schema;
	}

	@Override
	public String toString() {
		return "Exist_Assertion [min=" + min + ", max=" + max + ", contains=" + contains + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONSchema() {
		JSONObject obj = new JSONObject();
		if(contains.getClass() == Boolean_Assertion.class && ((Boolean_Assertion) contains).getValue() == true){

			if(min != null) obj.put("minItems", min);
			if(max != null) obj.put("maxItems", max);

			return obj;
		}

		if(contains != null)
			obj.put("contains", contains.toJSONSchema());
		
		if(min != null) obj.put("minContains", min);
		if(max != null) obj.put("maxContains", max);
		
		return obj;
	}
	
	@Override
	public Assertion not() {
		And_Assertion and = new And_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add("arr");
		and.add(type);
		
		if(min != null && max != null) {
			Or_Assertion or = new Or_Assertion();
			if(min > 0)
				or.add(new Exist_Assertion(0L, min - 1, contains));
			or.add(new Exist_Assertion(max + 1, null, contains));
			and.add(or);
			return and;
		}
		
		if(min != null && min > 0) {
			and.add(new Exist_Assertion(0L, min - 1, contains));
			return and;
		}

		if(max != null) {
			and.add(new Exist_Assertion(max + 1, null, contains));
		}

		return and;
	}

	@Override
	public Assertion notElimination() {
		Assertion tmp = contains.notElimination();
		return new Exist_Assertion(min, max, tmp);
	}

	@Override
	public String toGrammarString() {
		String min = "0", max = GrammarStringDefinitions.POS_INF;
		if (this.min != null) min = this.min + "";
		if (this.max != null) max = this.max + "";

		if(contains.getClass() == Boolean_Assertion.class && ((Boolean_Assertion) contains).getValue() == true)
			return String.format(GrammarStringDefinitions.BETWEENITEMS, min, max);
		else
			return String.format(GrammarStringDefinitions.CONTAINS, min, max, contains.toGrammarString());

	}
}
