package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import com.google.gson.JsonObject;

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
	
	public Exist_Assertion intersect(Exist_Assertion exist) {
		Exist_Assertion intersectedExist = new Exist_Assertion();
		
		intersectedExist.setMin((min > exist.min)? min:exist.min);
		intersectedExist.setMax((max < exist.max)? max:exist.max);
		
		return intersectedExist;
	}

	@Override
	public String toString() {
		return "Exist_Assertion [min=" + min + ", max=" + max + ", contains=" + contains + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "contains";
	}

	@Override
	public JsonObject toJSONSchema() {
		JsonObject obj = new JsonObject();
		
		if(contains != null) obj.put("contains", contains.toJSONSchema());
		if(min != null) obj.put("minContains", min);
		if(max != null) obj.put("maxContains", max);
		
		return obj;
	}
	
	
}
