package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

public class BetweenNumber implements JSONSchemaElement{
	private Long max_incl;
	private Long min_incl;
	
	private boolean excludedMax;
	private boolean excludedMin;
	
	public BetweenNumber() {
		min_incl = 0L;
		
		excludedMin = false;
		excludedMin = false;
	}
	
	public void setMax(Long max) {
		this.max_incl = max;
		
		if(excludedMax) max_incl-=1;
	}
	
	public void setMin(Long min) {
		this.min_incl = min;
		
		if(excludedMin) min_incl+=1;
	}
	
	
	public void setExclusiveMax(Long max) {
		this.max_incl = max-1;
		excludedMax = true;
		
	}
	
	public void setExclusiveMin(Long min) {
		this.min_incl = min +1;
		excludedMin = true;
	}
	
	public void setExclusiveMax(boolean value) {
		excludedMax = value;
		
	}
	
	public void setExclusiveMin(boolean value) {
		excludedMin = value;
	}
	
	
	@Override
	public String toString() {
		return "BetweenNumber [max_incl=" + max_incl + ", min_incl=" + min_incl + "]";
	}

	@Override
	public String toJSONString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toGrammarString() {
		// TODO Auto-generated method stub
		return null;
	}

}