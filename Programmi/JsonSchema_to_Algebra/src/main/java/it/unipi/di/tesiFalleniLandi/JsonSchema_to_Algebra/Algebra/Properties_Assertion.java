package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.HashMap;
import java.util.Set;

import org.json.simple.JSONObject;

public class Properties_Assertion implements Assertion{
	private HashMap<String, Assertion> properties;
	private Assertion additionalProperties;
	
	public Properties_Assertion() {
		properties = new HashMap<>();
	}
	
	public void add(String key, Assertion value) {
		properties.put(key, value);
	}
	
	public void setAdditionalProperties(Assertion additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	@Override
	public String toString() {
		return "Properties_Assertion [" + properties + ";\\r\\n " + additionalProperties
				+ "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "properties";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONSchema() {
		JSONObject obj = new JSONObject();
		
		//Inserisco tutto in patternProperties perchè lavora anche come properties
		if(properties != null && !properties.isEmpty()){
			JSONObject tmp = new JSONObject();
			Set<String> keys = properties.keySet();
			
			for(String key : keys)
				tmp.put(key, properties.get(key).toJSONSchema());
				
			obj.put("patternProperties", tmp);
		}
		
		if(additionalProperties != null)
			obj.put("additionalProperties", additionalProperties.toJSONSchema());
		
		return obj;
	}

	@Override
	public Assertion not() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
