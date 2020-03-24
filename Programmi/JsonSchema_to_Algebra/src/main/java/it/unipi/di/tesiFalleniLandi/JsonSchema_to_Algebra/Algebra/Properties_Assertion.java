package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.HashMap;

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
	
	
	
}
