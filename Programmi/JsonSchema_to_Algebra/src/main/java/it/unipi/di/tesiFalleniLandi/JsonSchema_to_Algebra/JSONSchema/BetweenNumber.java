package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import org.json.simple.JSONObject;

public class BetweenNumber implements JSONSchemaElement{
	private Long maximum;
	private Long minimum;
	
	private Long exclusiveMaximum;
	private Long exclusiveMinimum;
	
	private Boolean booleanExclusiveMaximum;
	private Boolean booleanExclusiveMinimum;
	
	public BetweenNumber() {
	}
	
	public void setMax(Object obj) {
		this.maximum = (Long) obj;
	}
	
	public void setMin(Object obj) {
		this.minimum = (Long) obj;
	}
	
	
	public void setExclusiveMax(Object obj) {
		try {
			booleanExclusiveMaximum = (boolean) obj;
		}catch(ClassCastException e) {
			this.exclusiveMaximum = (Long) obj;
		}
	}
	
	public void setExclusiveMin(Object obj) {
		try {
			booleanExclusiveMinimum = (boolean) obj;
		}catch(ClassCastException e) {
			this.exclusiveMinimum = (Long) obj;
		}
	}
	
	
	

	@Override
	public String toString() {
		return "BetweenNumber [maximum=" + maximum + "\\r\\n minimum=" + minimum + "\\r\\n exclusiveMaximum="
				+ exclusiveMaximum + "\\r\\n exclusiveMinimum=" + exclusiveMinimum + "\\r\\n booleanExclusiveMaximum="
				+ booleanExclusiveMaximum + "\\r\\n booleanExclusiveMinimum=" + booleanExclusiveMinimum + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		
		if(maximum != null) obj.put("maximum", maximum);
		if(minimum != null) obj.put("minimum", minimum);
		
		if(exclusiveMaximum != null) obj.put("exclusiveMaximum", exclusiveMaximum);
		if(exclusiveMinimum != null) obj.put("exclusiveMinimum", exclusiveMinimum);
		
		if(booleanExclusiveMaximum != null) obj.put("exclusiveMaximum", booleanExclusiveMaximum);
		if(booleanExclusiveMinimum != null) obj.put("exclusiveMinimum", booleanExclusiveMinimum);
		
		return obj;
	}

	@Override
	public String toGrammarString() {
		// TODO Auto-generated method stub
		return null;
	}

}