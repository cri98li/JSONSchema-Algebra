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
	
	public void setMax(Object obj) {
		Long max = (Long) obj;
		this.max_incl = max;
		
		if(excludedMax) max_incl-=1;
	}
	
	public void setMin(Object obj) {
		Long min = (Long) obj;
		this.min_incl = min;
		
		if(excludedMin) min_incl+=1;
	}
	
	
	public void setExclusiveMax(Object obj) {
		try {
			excludedMax = (boolean) obj;
		}catch(ClassCastException e) {
			this.max_incl = ((Long) obj) -1;
			excludedMax = true;
		}
	}
	
	public void setExclusiveMin(Object obj) {
		try {
			excludedMin = (boolean) obj;
		}catch(ClassCastException e) {
			this.min_incl = ((Long) obj) +1;
			excludedMin = true;
		}
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