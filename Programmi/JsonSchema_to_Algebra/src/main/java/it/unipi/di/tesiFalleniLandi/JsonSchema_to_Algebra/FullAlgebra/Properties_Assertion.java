package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import patterns.Pattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAnd;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessProperty;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import patterns.REException;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CompletionException;

public class Properties_Assertion implements Assertion{
	private HashMap<String, Assertion> properties;
	private HashMap<ComplexPattern, Assertion> patternProperties;
	private Assertion additionalProperties;
	
	public Properties_Assertion() {
		properties = new HashMap<>();
		patternProperties = new HashMap<>();
	}
	
	public void addProperties(String key, Assertion value) {
		if(properties.containsKey(key)) throw new ParseCancellationException("Detected 2 properties with the same name");
		properties.put(key, value);
	}
	
	public void addPatternProperties(ComplexPattern key, Assertion value) {
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
	public JsonObject toJSONSchema() {
		JsonObject obj = new JsonObject();

		if(properties != null && !properties.isEmpty()){
			Set<String> keys = properties.keySet();
			JsonObject tmp = new JsonObject();

			for(String key : keys)
				tmp.add(key, properties.get(key).toJSONSchema());
				
			obj.add("properties", tmp);
		}
		
		if(patternProperties != null && !patternProperties.isEmpty()){
			JsonObject tmp = new JsonObject();
			Set<ComplexPattern> keys = patternProperties.keySet();
			
			for(ComplexPattern key : keys)
				tmp.add(key.toString(), patternProperties.get(key).toJSONSchema());
				
			obj.add("patternProperties", tmp);
		}
		
		if(additionalProperties != null)
			obj.add("additionalProperties", additionalProperties.toJSONSchema());
		
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
		type.add(GrammarStringDefinitions.TYPE_OBJECT);
		and.add(type);
		and.add(or);
		
		Set<Entry<String, Assertion>> entrySet = properties.entrySet();
		
		for(Entry<String, Assertion> entry : entrySet) {
			Assertion not = entry.getValue().not();
			if(not != null) {
				or.add(new PatternRequired_Assertion(ComplexPattern.createFromName(entry.getKey()), not));
				addPattRequired.addName(ComplexPattern.createFromName(entry.getKey()));
			}
		}

		Set<Entry<ComplexPattern, Assertion>> entrySetPatt = patternProperties.entrySet();

		for(Entry<ComplexPattern, Assertion> entry : entrySetPatt) {
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

		Set<Entry<ComplexPattern, Assertion>> entrySetPatt = patternProperties.entrySet();

		for(Entry<ComplexPattern, Assertion> entry : entrySetPatt) {
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
			Set<Entry<ComplexPattern, Assertion>> entrySet = patternProperties.entrySet();
			for(Entry<ComplexPattern, Assertion> entry : entrySet) {
				String returnedValue = entry.getValue().toGrammarString();
				if(!returnedValue.isEmpty())
					str += GrammarStringDefinitions.COMMA + entry.getKey().getAlgebraString() +" : " + returnedValue;
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
	public WitnessAssertion toWitnessAlgebra() throws REException {
		WitnessAnd and = new WitnessAnd();
		ComplexPattern addPatt = ComplexPattern.createFromRegexp(".*"); //TODO: ask

		Set<Entry<String, Assertion>> entrySet = properties.entrySet();

		for(Entry<String, Assertion> entry : entrySet) {
			ComplexPattern p = ComplexPattern.createFromName(entry.getKey());
			WitnessProperty prop = new WitnessProperty(p, entry.getValue().toWitnessAlgebra());
			and.add(prop);
			addPatt = addPatt.intersect(p);
		}

		Set<Entry<ComplexPattern, Assertion>> entrySetPatt = patternProperties.entrySet();

		for(Entry<ComplexPattern, Assertion> entry : entrySetPatt) {
			ComplexPattern p = entry.getKey().clone();
			WitnessProperty pattProp = new WitnessProperty(p, entry.getValue().toWitnessAlgebra());
			and.add(pattProp);
			addPatt = addPatt.intersect(p);
		}

		if(additionalProperties != null) {
			WitnessProperty addProp = new WitnessProperty(addPatt.complement(), additionalProperties.toWitnessAlgebra());
			and.add(addProp);
		}

		return (and.isUnitaryAnd() != null) ? and.isUnitaryAnd() : and;
	}
}
