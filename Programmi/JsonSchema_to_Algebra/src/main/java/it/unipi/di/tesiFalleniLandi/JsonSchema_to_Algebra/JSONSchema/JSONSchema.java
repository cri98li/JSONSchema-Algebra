package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class JSONSchema implements JSONSchemaElement{
	
	private Boolean booleanAsJSONSchema; //Per gestire il caso di schema booleano
	
	private HashMap<String, JSONSchemaElement> jsonSchema; //Dizionario di keywords presenti nello schema

	/**
	 * Costruttore, prova a parsare un Object in boolean o in JSONObject.
	 * Nel caso di JSONObject, si procede con la creazione delle parole chiave di JSONSchema presenti nel JSONObject.
	 * @param obj
	 */
	public JSONSchema(Object obj) {
		jsonSchema = new HashMap<>();
		
		JSONObject object = null;
		try {
			booleanAsJSONSchema = (Boolean) obj;
			return;
		}catch(ClassCastException e) {
			try {
			object = (JSONObject) obj;
			}catch(ClassCastException ex) {
				System.out.println("Error: schema must be boolean or object!");
			}
		}
		
		//inizio parsing
		Iterator<?> it = object.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String) it.next();
			switch(key) { 
			
			//I valori di properties, patternProperties e additionalProperties vengono memorizzati nella stessa classe (Properties).
			case "properties":
				jsonSchema.putIfAbsent("properties", new Properties());
				((Properties) jsonSchema.get("properties")).setProperties(object.get(key));
				break;
			
			case "patternProperties":
				jsonSchema.putIfAbsent("properties", new Properties());
				((Properties) jsonSchema.get("properties")).setPatternProperties(object.get(key));
				break;
				
			case "additionalProperties":
				jsonSchema.putIfAbsent("properties", new Properties());
				((Properties) jsonSchema.get("properties")).setAdditionalProperties(object.get(key));
				break;
				
			//I valori di items, additionalItems vengono memorizzati nella stessa classe (Items).	
			case "items":
				jsonSchema.putIfAbsent("items", new Items());
				((Items) jsonSchema.get("items")).setItems(object.get(key));
				break;
				
			case "additionalItems":
				jsonSchema.putIfAbsent("items", new Items());
				((Items) jsonSchema.get("items")).setAdditionalItems(object.get(key));
				break;
			
			//I valori di if, then e else vengono memorizzati nella stessa classe (IfThenElse).
			case "if":
				jsonSchema.putIfAbsent("ifThenElse", new IfThenElse());
				((IfThenElse) jsonSchema.get("ifThenElse")).setIf(object.get(key));
				break;
				
			case "then":
				jsonSchema.putIfAbsent("ifThenElse", new IfThenElse());
				((IfThenElse) jsonSchema.get("ifThenElse")).setThen(object.get(key));
				break;
				
			case "else":
				jsonSchema.putIfAbsent("ifThenElse", new IfThenElse());
				((IfThenElse) jsonSchema.get("ifThenElse")).setElse(object.get(key));
				break;
			
			case "not":
				jsonSchema.put("not", new Not(object.get(key)));
				break;
				
			case "type":
				jsonSchema.put("type", new Type(object.get(key)));
				break;
			
			case "oneOf":
				jsonSchema.put("oneOf", new OneOf(object.get(key)));
				break;
			
			case "allOf":
				jsonSchema.put("allOf", new AllOf(object.get(key)));
				break;
			
			case "anyOf":
				jsonSchema.put("anyOf", new AnyOf(object.get(key)));
				break;
			
			//I valori di minItems e maxItems vengono memorizzati nella stessa classe (BetweenItems).
			case "minItems":
				jsonSchema.putIfAbsent("betweenItems", new BetweenItems());
				((BetweenItems) jsonSchema.get("betweenItems")).setMinItems(object.get(key));
				break;
				
			case "maxItems":
				jsonSchema.putIfAbsent("betweenItems", new BetweenItems());
				((BetweenItems) jsonSchema.get("betweenItems")).setMaxItems(object.get(key));
				break;
				
			case "multipleOf":
				jsonSchema.put("multipleOf", new MultipleOf(object.get(key)));
				break;
				
			//I valori di minLength e maxLength vengono memorizzati nella stessa classe (Length).
			case "minLength":
				jsonSchema.putIfAbsent("length", new Length());
				((Length) jsonSchema.get("length")).setMinLength(object.get(key));
				break;
				
			case "maxLength":
				jsonSchema.putIfAbsent("length", new Length());
				((Length) jsonSchema.get("length")).setMaxLength(object.get(key));
				break;
				
			//I valori di contains, minContains e maxContains vengono memorizzati nella stessa classe (Contains).
			case "contains":
				jsonSchema.putIfAbsent("contains", new Contains());
				((Contains) jsonSchema.get("contains")).setContains(object.get(key));
				break;
			
			case "minContains":
				jsonSchema.putIfAbsent("contains", new Contains());
				((Contains) jsonSchema.get("contains")).setMinContains(object.get(key));
				break;
			
			case "maxContains":
				jsonSchema.putIfAbsent("contains", new Contains());
				((Contains) jsonSchema.get("contains")).setMaxContains(object.get(key));
				break;
				
			//I valori di minimum, maximum, exclusiveMinimum e exclusiveMaximum vengono memorizzati nella stessa classe (BetweenNumber).
			case "minimum":
				jsonSchema.putIfAbsent("betweenNumber", new BetweenNumber());
				((BetweenNumber) jsonSchema.get("betweenNumber")).setMin(object.get(key));
				break;
				
			case "maximum":
				jsonSchema.putIfAbsent("betweenNumber", new BetweenNumber());
				((BetweenNumber) jsonSchema.get("betweenNumber")).setMax(object.get(key));
				break;
				
			case "exclusiveMinimum":
				jsonSchema.putIfAbsent("betweenNumber", new BetweenNumber());
				((BetweenNumber) jsonSchema.get("betweenNumber")).setExclusiveMin(object.get(key));
				break;
				
			case "exclusiveMaximum":
				jsonSchema.putIfAbsent("betweenNumber", new BetweenNumber());
				((BetweenNumber) jsonSchema.get("betweenNumber")).setExclusiveMax(object.get(key));
				break;
				
			case "required":
				jsonSchema.put("required", new Required(object.get(key)));
				break;
				
			case "pattern":
				jsonSchema.put("pattern", new Pattern(object.get(key)));
				break;
				
			case "uniqueItems":
				jsonSchema.put("uniqueItems", new UniqueItems(object.get(key)));
				break;
				
			//I valori di minProperties e maxProperties vengono memorizzati nella stessa classe (BetweenProperties).
			case "minProperties":
				jsonSchema.putIfAbsent("betweenProperties", new BetweenProperties());
				((BetweenProperties) jsonSchema.get("betweenProperties")).setMinProperties(object.get(key));
				break;
				
			case "maxProperties":
				jsonSchema.putIfAbsent("betweenProperties", new BetweenProperties());
				((BetweenProperties) jsonSchema.get("betweenProperties")).setMaxProperties(object.get(key));
				break;
				
			case "enum":
				jsonSchema.put("enum", new Enum(object.get(key)));
				break;
				
			case "const":
				jsonSchema.put("const", new Const(object.get(key)));
				break;
			
			default:
				jsonSchema.put(key, new UnknowElement(object.get(key)));
				break;
			}
		}
	}
	
	
	public JSONSchema() {
		jsonSchema = new HashMap<>();
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public Object toJSON() {
		JSONObject schema = new JSONObject();
		
		List<String> putContentKeywords = Arrays.asList( new String[]{
				"properties",
				"ifThenElse",
				"items",
				"betweenItems",
				"length",
				"contains",
				"betweenNumber",
				"betweenProperties"
		});

		//caso boolean as a Schema
		if(booleanAsJSONSchema != null) return booleanAsJSONSchema;
		
		Set<Entry<String, JSONSchemaElement>> entries = jsonSchema.entrySet();
		for(Entry<String, JSONSchemaElement> entry : entries)
			if(putContentKeywords.contains(entry.getKey()))
				putContent(schema, (JSONObject) entry.getValue().toJSON());
			else schema.put(entry.getKey(), entry.getValue().toJSON());
		
		return schema;
	}
	
	
	/**
	 * Inserisce il contenuto di toPut in schema
	 * @param schema
	 * @param toPut
	 */
	private void putContent(JSONObject schema, JSONObject toPut) {
		Set<?> keys = toPut.keySet();
		for(Object key : keys) {
			schema.put(key, toPut.get(key));
		}
	}
	
	
	public JSONSchema assertionSeparation() {
		JSONSchema schema = new JSONSchema();
		if(booleanAsJSONSchema != null) {
			schema.booleanAsJSONSchema = booleanAsJSONSchema;
			return schema;
		}
		
		schema.jsonSchema.putIfAbsent("allOf", new AllOf());
		
		Set<Entry<String, JSONSchemaElement>> entries = jsonSchema.entrySet();
		
		for(Entry<String, JSONSchemaElement> entry : entries) {
			JSONSchema tmp = new JSONSchema();
			tmp.jsonSchema.put(entry.getKey(), entry.getValue().assertionSeparation());
			((AllOf) schema.jsonSchema.get("allOf")).addElement(tmp);
		}
		
		return schema;
	}

	

	@Override
	public String toString() {
		return "JSONSchema [booleanAsJSONSchema=" + booleanAsJSONSchema + "\\r\\n jsonSchema=" + jsonSchema + "]";
	}

	@Override
	public String toGrammarString() {
		String str = "";
		
		if(booleanAsJSONSchema != null) return booleanAsJSONSchema+"";
		
		Set<Entry<String, JSONSchemaElement>> entries = jsonSchema.entrySet();
		
		for(Entry<String, JSONSchemaElement> entry : entries)
			str += GrammarStringDefinitions.AND + entry.getValue().toGrammarString();
		
		/*
		private Properties properties; -------------------- aspettiamo
		 */
		
		
		if(str.length() == 0 || str.contains("null")) return "TODO";
		return String.format(GrammarStringDefinitions.JSONSCHEMA, str.subSequence(GrammarStringDefinitions.AND.length(), str.length()));
	}

}
