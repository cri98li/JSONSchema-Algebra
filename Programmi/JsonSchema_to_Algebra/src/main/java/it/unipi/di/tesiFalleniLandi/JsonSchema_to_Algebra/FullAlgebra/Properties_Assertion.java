package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.MyPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.PosixPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAnd;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessProperty;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Properties_Assertion implements Assertion{
	private HashMap<String, Assertion> properties;
	private HashMap<PosixPattern, Assertion> patternProperties;
	private Assertion additionalProperties;
	
	public Properties_Assertion() {
		properties = new HashMap<>();
		patternProperties = new HashMap<>();
	}
	
	public void addProperties(String key, Assertion value) {
		if(properties.containsKey(key)) throw new ParseCancellationException("Detected 2 properties with the same name");
		properties.put(key, value);
	}
	
	public void addPatternProperties(PosixPattern key, Assertion value) {
		if(patternProperties.containsKey(key)) throw new ParseCancellationException("Detected 2 patternProperties with the same pattern");
		patternProperties.put(key, value);
	}
	
	public void setAdditionalProperties(Assertion additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	@Override
	public String toString() {
		return "Properties_Assertion [properties=" + properties + ", patternProperties=" + patternProperties
				+ ", additionalProperties=" + additionalProperties + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONSchema() {
		JSONObject obj = new JSONObject();

		if(properties != null && !properties.isEmpty()){
			Set<String> keys = properties.keySet();
			JSONObject tmp = new JSONObject();

			for(String key : keys)
				tmp.put(key, properties.get(key).toJSONSchema());
				
			obj.put("properties", tmp);
		}
		
		if(patternProperties != null && !patternProperties.isEmpty()){
			JSONObject tmp = new JSONObject();
			Set<PosixPattern> keys = patternProperties.keySet();
			
			for(PosixPattern key : keys)
				tmp.put(key, patternProperties.get(key).toJSONSchema());
				
			obj.put("patternProperties", tmp);
		}
		
		if(additionalProperties != null)
			obj.put("additionalProperties", additionalProperties.toJSONSchema());
		
		return obj;
	}

	@Override
	public Assertion not() {
		AllOf_Assertion and = new AllOf_Assertion();

		if(properties.isEmpty() && patternProperties.isEmpty() && additionalProperties == null) {
			and.add(new Boolean_Assertion(false));
			return and;
		}

		Type_Assertion type = new Type_Assertion();
		AddPatternRequired_Assertion addPattRequired = new AddPatternRequired_Assertion();
		AnyOf_Assertion or = new AnyOf_Assertion();
		type.add("obj");
		and.add(type);
		and.add(or);
		
		Set<Entry<String, Assertion>> entrySet = properties.entrySet();
		
		for(Entry<String, Assertion> entry : entrySet) {
			Assertion not = entry.getValue().not();
			if(not != null) {
				or.add(new PatternRequired_Assertion(new MyPattern(entry.getKey()), not));
				addPattRequired.addName(new MyPattern(entry.getKey()));
			}
		}

		Set<Entry<PosixPattern, Assertion>> entrySetPatt = patternProperties.entrySet();

		for(Entry<PosixPattern, Assertion> entry : entrySetPatt) {
			Assertion not = entry.getValue().not();
			if(not != null) {
				or.add(new PatternRequired_Assertion(entry.getKey(), not));
				addPattRequired.addName(entry.getKey());
			}
		}
		
		if(additionalProperties != null) {
			Assertion not = additionalProperties.not();
			if(not != null) {
				addPattRequired.setAdditionalProperties(not);
				or.add(addPattRequired);
			}
		}
		
		return and;
	}

	@Override
	public Assertion notElimination() {
		Properties_Assertion prop = new Properties_Assertion();
		
		Set<Entry<String, Assertion>> entrySet = properties.entrySet();
		
		for(Entry<String, Assertion> entry : entrySet) {
			Assertion not = entry.getValue().notElimination();
			if(not != null)
				prop.addProperties(entry.getKey(), not);
		}

		Set<Entry<PosixPattern, Assertion>> entrySetPatt = patternProperties.entrySet();

		for(Entry<PosixPattern, Assertion> entry : entrySetPatt) {
			Assertion not = entry.getValue().notElimination();
			if (not != null)
				prop.addPatternProperties(entry.getKey(), not);
		}
		
		if(additionalProperties != null) {
			Assertion not = additionalProperties.notElimination();
			if (not != null)
				prop.setAdditionalProperties(additionalProperties.notElimination());
		}
		
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

		if(patternProperties != null) {
			Set<Entry<PosixPattern, Assertion>> entrySet = patternProperties.entrySet();
			for(Entry<PosixPattern, Assertion> entry : entrySet) {
				String returnedValue = entry.getValue().toGrammarString();
				if(!returnedValue.isEmpty())
					str += GrammarStringDefinitions.COMMA + String.format(GrammarStringDefinitions.SINGLEPROPERTIES, entry.getKey(), returnedValue);
			}
		}

		if(additionalProperties != null)
			if(str.isEmpty())
				return String.format(GrammarStringDefinitions.PROPERTIES, "", additionalProperties.toGrammarString());
			else
				return String.format(GrammarStringDefinitions.PROPERTIES, str.substring(GrammarStringDefinitions.COMMA.length()), additionalProperties.toGrammarString());

		if(str.isEmpty() && additionalProperties == null) return "";
		return String.format(GrammarStringDefinitions.PROPERTIES, str.substring(GrammarStringDefinitions.COMMA.length()), "");
	}

	@Override
	public WitnessAssertion toWitnessAlgebra() {
		WitnessAnd and = new WitnessAnd();
		PosixPattern addPatt = new MyPattern("");

		Set<Entry<String, Assertion>> entrySet = properties.entrySet();

		for(Entry<String, Assertion> entry : entrySet) {
			PosixPattern p = new MyPattern(entry.getKey());
			WitnessProperty prop = new WitnessProperty(p, entry.getValue().toWitnessAlgebra());
			and.add(prop);
			addPatt.or(p);
		}

		Set<Entry<PosixPattern, Assertion>> entrySetPatt = patternProperties.entrySet();

		for(Entry<PosixPattern, Assertion> entry : entrySetPatt) {
			PosixPattern p = new MyPattern(entry.getKey().getPattern());
			WitnessProperty pattProp = new WitnessProperty(p, entry.getValue().toWitnessAlgebra());
			and.add(pattProp);
			addPatt.or(p);
		}

		if(additionalProperties != null) {
			addPatt.complement();
			WitnessProperty addProp = new WitnessProperty(addPatt, additionalProperties.toWitnessAlgebra());
			and.add(addProp);
		}

		return and;
	}
}
