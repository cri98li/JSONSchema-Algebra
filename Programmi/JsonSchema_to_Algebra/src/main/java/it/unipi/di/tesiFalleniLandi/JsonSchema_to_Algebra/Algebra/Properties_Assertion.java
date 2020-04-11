package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;

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
			
			for(String key : keys) {
				JSONObject tmp2 = new JSONObject();
				Utils.putContent(tmp2, properties.get(key).getJSONSchemaKeyword(), properties.get(key).toJSONSchema());
				tmp.put(key, tmp2);
			}
				
			obj.put("patternProperties", tmp);
		}
		
		if(additionalProperties != null)
			obj.put("additionalProperties", additionalProperties.toJSONSchema());
		
		return obj;
	}

	@Override
	public Assertion not() {
		And_Assertion and = new And_Assertion();
		Type_Assertion type = new Type_Assertion();
		AddPatternRequired_Assertion addPattRequired = new AddPatternRequired_Assertion();
		Or_Assertion or = new Or_Assertion();
		type.add("obj");
		and.add(type);
		and.add(or);
		
		Set<Entry<String, Assertion>> entrySet = properties.entrySet();
		
		for(Entry<String, Assertion> entry : entrySet) {
			or.add(new PatternRequired_Assertion(entry.getKey(), entry.getValue().not()));
			addPattRequired.addName(entry.getKey());
		}
		
		if(additionalProperties != null) {
			addPattRequired.setAdditionalProperties(additionalProperties.not());
			or.add(addPattRequired);
		}
		
		return and;
	}

	@Override
	public Assertion notElimination() {
		Properties_Assertion prop = new Properties_Assertion();
		
		Set<Entry<String, Assertion>> entrySet = properties.entrySet();
		
		for(Entry<String, Assertion> entry : entrySet)
			prop.add(entry.getKey(), entry.getValue().notElimination());
		
		if(additionalProperties != null)
			prop.setAdditionalProperties(additionalProperties.notElimination());
		
		return prop;
	}
	
	@Override
	public String toGrammarString() {
		String str = "";
		
		if(properties != null) {
			Set<Entry<String, Assertion>> entrySet = properties.entrySet();
			for(Entry<String, Assertion> entry : entrySet) {
				String returnedValue = entry.getValue().toGrammarString();
				if(!returnedValue.isEmpty())
					str += GrammarStringDefinitions.COMMA + String.format(GrammarStringDefinitions.SINGLEPROPERTIES, entry.getKey(), returnedValue);
			}
		}
		
		if(additionalProperties != null) 
			return String.format(GrammarStringDefinitions.PROPERTIES, str.substring(GrammarStringDefinitions.COMMA.length()), additionalProperties.toGrammarString());
		
		if(str.isEmpty() && additionalProperties == null) return "";
		return String.format(GrammarStringDefinitions.PROPERTIES, str.substring(GrammarStringDefinitions.COMMA.length()), "");
	}
}
