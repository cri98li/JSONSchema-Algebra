package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import org.json.simple.JSONObject;

public class BetweenItems implements JSONSchemaElement{
	private Long minItems;
	private Long maxItems;
	
	private boolean initialized;
	
	public BetweenItems() { }
	
	public void setMinItems(Object obj) {
		Long value = (Long) obj;
		
		initialized = true;
		minItems = value;
	}
	
	public void setMaxItems(Object obj) {
		Long value = (Long) obj;
		
		initialized = true;
		maxItems = value;
	}
	
	public boolean isInitialized() {
		return initialized;
	}
	
	@Override
	public String toString() {
		return "BetweenItems [minItems=" + minItems + ", maxItems=" + maxItems + ", initialized=" + initialized + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		
		if(minItems != null) obj.put("minItems", minItems);
		if(maxItems != null) obj.put("maxItems", maxItems);
		
		return obj;
	}

	@Override
	public String toGrammarString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BetweenItems assertionSeparation() {
		BetweenItems obj = new BetweenItems();
		
		if(minItems != null) obj.minItems = minItems;
		if(maxItems != null) obj.maxItems = maxItems;
		
		return obj;
	}

}
