package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessFalseAssertionException;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAnd;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessBoolean;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessProperty;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Properties_Assertion implements Assertion{
	private HashMap<String, Assertion> properties;
	private HashMap<ComplexPattern, Assertion> patternProperties;
	private Assertion additionalProperties;

	private static Logger logger = LogManager.getLogger(Properties_Assertion.class);
	
	public Properties_Assertion() {
		properties = new HashMap<>();
		patternProperties = new HashMap<>();
		logger.trace("Created a new Properties_Assertion: {}", this);
	}
	
	public void addProperties(String key, Assertion value) {
		if(properties.containsKey(key)) throw new ParseCancellationException("Detected 2 properties with the same name");
		logger.trace("Adding as Properties <{}, {}> to {}", key, value, this);
		properties.put(key, value);
	}
	
	public void addPatternProperties(ComplexPattern key, Assertion value) {
		if(patternProperties.containsKey(key)) throw new ParseCancellationException("Detected 2 patternProperties with the same pattern");
		logger.trace("Adding as PatternProperties <{}, {}> to {}", key, value, this);
		patternProperties.put(key, value);
	}
	
	public void setAdditionalProperties(Assertion additionalProperties) {
		logger.trace("Adding as AdditionalProperties {} to {}", additionalProperties, this);
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
		type.add(FullAlgebraString.TYPE_OBJECT);
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
		StringBuilder str = new StringBuilder();

		if(properties != null) {
			Set<Entry<String, Assertion>> entrySet = properties.entrySet();
			for(Entry<String, Assertion> entry : entrySet) {
				String returnedValue = entry.getValue().toGrammarString();
				if(!returnedValue.isEmpty())
					str.append(FullAlgebraString.COMMA)
							.append(FullAlgebraString.SINGLEPROPERTIES(entry.getKey(), returnedValue));
			}
		}

		if(patternProperties != null) {
			Set<Entry<ComplexPattern, Assertion>> entrySet = patternProperties.entrySet();
			for(Entry<ComplexPattern, Assertion> entry : entrySet) {
				String returnedValue = entry.getValue().toGrammarString();
				if(!returnedValue.isEmpty())
					str.append(FullAlgebraString.COMMA)
							.append(FullAlgebraString.SINGLEPATTERNPROPERTIES(entry.getKey().getAlgebraString(), returnedValue));

			}
		}

		if(additionalProperties != null)
			if(str.length() == 0)
				return FullAlgebraString.PROPERTIES("", additionalProperties.toGrammarString());
			else
				return FullAlgebraString.PROPERTIES(str.substring(FullAlgebraString.COMMA.length()), additionalProperties.toGrammarString());

		if(str.length() == 0 && additionalProperties == null) return "";
		return FullAlgebraString.PROPERTIES(str.substring(FullAlgebraString.COMMA.length()), "");
	}

	@Override
	public WitnessAssertion toWitnessAlgebra() throws REException {
		WitnessAnd and = new WitnessAnd();
		ComplexPattern usedPatt = null;//= ComplexPattern.createFromRegexp(".*").complement();//TODO: correggere

		Set<Entry<String, Assertion>> entrySet = properties.entrySet();

		try {
			for (Entry<String, Assertion> entry : entrySet) {
				ComplexPattern p = ComplexPattern.createFromName(entry.getKey());
				WitnessProperty prop = new WitnessProperty(p, entry.getValue().toWitnessAlgebra());
				and.add(prop);
				if(usedPatt == null) usedPatt = p;
				else usedPatt = usedPatt.union(p);
			}

			Set<Entry<ComplexPattern, Assertion>> entrySetPatt = patternProperties.entrySet();

			for (Entry<ComplexPattern, Assertion> entry : entrySetPatt) {
				ComplexPattern p = entry.getKey().clone();
				WitnessProperty pattProp = new WitnessProperty(p, entry.getValue().toWitnessAlgebra());
				and.add(pattProp);
				if(usedPatt == null) usedPatt = p;
				else usedPatt = usedPatt.union(p);
			}

			if (additionalProperties != null) {
				WitnessProperty addProp;
				if(usedPatt == null){
					addProp = new WitnessProperty(ComplexPattern.createFromRegexp(".*"), additionalProperties.toWitnessAlgebra());
				}
				else {
					addProp = new WitnessProperty(usedPatt.complement(), additionalProperties.toWitnessAlgebra());
				}
				and.add(addProp);
			}
		}catch (WitnessFalseAssertionException e){
			return new WitnessBoolean(false);
		}

		return and;
	}
}
