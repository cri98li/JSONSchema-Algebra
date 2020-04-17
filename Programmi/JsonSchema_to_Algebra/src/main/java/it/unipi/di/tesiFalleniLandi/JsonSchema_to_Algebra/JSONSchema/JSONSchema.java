package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;

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
			object = (JSONObject) obj;
		}
		
		//inizio parsing
		Iterator<?> it = object.keySet().iterator();
		
		if(object.keySet().size() == 0) { //caso schema vuoto
			booleanAsJSONSchema = true;
			return;			
		}
		
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
				
			//I valori di dependencies, dependentSchemas e dependentRequired vengono memorizzati nella stessa classe (Dependencies).
			case "dependencies":
				jsonSchema.putIfAbsent("dependencies", new Dependencies());
				((Dependencies) jsonSchema.get("dependencies")).setDependencies(object.get(key));
				break;
			
			case "dependentSchemas":
				jsonSchema.putIfAbsent("dependencies", new Dependencies());
				((Dependencies) jsonSchema.get("dependencies")).setDependentSchemas(object.get(key));
				break;
				
			case "dependentRequired":
				jsonSchema.putIfAbsent("dependencies", new Dependencies());
				((Dependencies) jsonSchema.get("dependencies")).setDependentRequired(object.get(key));
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
				
			case "$ref":
				jsonSchema.put("$ref", new Ref(object.get(key)));
				break;
				
			case "ref":
				jsonSchema.put("$ref", new Ref(object.get(key)));
				break;
				
			case "$defs":
				jsonSchema.put("$defs", new Defs(object.get(key)));
				break;
				
			case "definitions": 
				jsonSchema.put("$defs", new Defs(object.get(key)));
				break;
				
			case "format":
				jsonSchema.put("format", new Format(object.get(key)));
				break;
				
			case "propertyNames":
				jsonSchema.put("propertyNames", new PropertyNames(object.get(key)));
				break;
			
			default:
				jsonSchema.putIfAbsent("unknow", new UnknowElement());
				((UnknowElement) jsonSchema.get("unknow")).add(key, object.get(key));;
				break;
			}
		}
	}
	
	
	public JSONSchema() {
	}

	public void addJSONSchemaElement(String key, JSONSchemaElement value) {
		if(jsonSchema == null)	jsonSchema = new HashMap<>();
		jsonSchema.put(key, value);
	}
	
	@Override
	public Object toJSON() {
		JSONObject schema = new JSONObject();
		
		//caso boolean as a Schema
		if(booleanAsJSONSchema != null) return booleanAsJSONSchema;
		
		Set<Entry<String, JSONSchemaElement>> entries = jsonSchema.entrySet();
		for(Entry<String, JSONSchemaElement> entry : entries)
			Utils.putContent(schema, entry.getKey(), entry.getValue().toJSON());
		
		return schema;
	}
	
	
	public JSONSchema assertionSeparation() {
		JSONSchema schema = new JSONSchema();
		schema.jsonSchema = new HashMap<>();
		if(booleanAsJSONSchema != null) {
			schema.booleanAsJSONSchema = booleanAsJSONSchema;
			return schema;
		}
		
		schema.jsonSchema.putIfAbsent("allOf", new AllOf());
		
		Set<Entry<String, JSONSchemaElement>> entries = jsonSchema.entrySet();
		
		for(Entry<String, JSONSchemaElement> entry : entries) {
			
			
			//TYPE SEPARATION
			if(entry.getKey().equals("type")) {
				AnyOf anyOf = new AnyOf();
				Type type = ((Type)entry.getValue());
				
				if(type.type_array != null)
					for(String str : type.type_array) {
						JSONSchema tmp = new JSONSchema();
						tmp.jsonSchema = new HashMap<>();
						Type t = new Type();
						t.type = str;
						tmp.jsonSchema.put("type", t);
						anyOf.addElement(tmp);
					}
				else {
					JSONSchema tmp = new JSONSchema();
					tmp.jsonSchema = new HashMap<>();
					tmp.jsonSchema.put("type", type);
					anyOf.addElement(tmp);
				}
				
				JSONSchema tmp = new JSONSchema();
				tmp.jsonSchema = new HashMap<>();
				tmp.jsonSchema.put("anyOf", anyOf);
				((AllOf) schema.jsonSchema.get("allOf")).addElement(tmp);
				continue;
			}
			
			//$schema, id e %defs non vanno dentro allOf
			if(entry.getKey().equals("$schema") 
					|| entry.getKey().equals("id")
					|| entry.getKey().equals("$id")
					|| entry.getKey().equals("unknow")) {
				schema.jsonSchema.put(entry.getKey(), entry.getValue());
				continue;
			}
			
			
			JSONSchema tmp = new JSONSchema();
			tmp.jsonSchema = new HashMap<>();
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
		int nElement = 0;
		
		if(booleanAsJSONSchema != null) return booleanAsJSONSchema + "";
		
		Set<Entry<String, JSONSchemaElement>> entries = jsonSchema.entrySet();
		
		for(Entry<String, JSONSchemaElement> entry : entries) {			
			String returnedValue = entry.getValue().toGrammarString();
			if(returnedValue.isEmpty())
				continue;
			str += GrammarStringDefinitions.COMMA + returnedValue;
			nElement += entry.getValue().numberOfAssertions();
		}
		
		/*
		 * if(entries.isEmpty()) return "true";
		 * if(str.isEmpty()) return "";
		 */
		if(nElement == 0) return "";
		if(nElement == 1) return str.substring(GrammarStringDefinitions.COMMA.length());
		return String.format(GrammarStringDefinitions.JSONSCHEMA, str.substring(GrammarStringDefinitions.COMMA.length()));
	}
	
	public int numberOfAssertions() {
		int count = 0;
		
		Set<Entry<String, JSONSchemaElement>> entries = jsonSchema.entrySet();
		for(Entry<String, JSONSchemaElement> entry : entries)
			count += entry.getValue().numberOfAssertions();

		if(booleanAsJSONSchema != null)
			count++;

		return count;
	}


	@Override
	public List<URI_JS> getRef() {
		List<URI_JS> returnList = new LinkedList<>();
		
		if(booleanAsJSONSchema != null) return returnList;
		
		Set<Entry<String, JSONSchemaElement>> entrySet = jsonSchema.entrySet();
		
		for(Entry<String, JSONSchemaElement> entry : entrySet)
			returnList.addAll(entry.getValue().getRef());
		
		return returnList;
	}


	@Override
	public JSONSchema searchDef(Iterator<String> URIIterator) {
		if(!URIIterator.hasNext())
			return this;
		
		String nextElement = URIIterator.next();
		
		if(jsonSchema.containsKey(nextElement))
			return jsonSchema.get(nextElement).searchDef(URIIterator);
		
		return null;
	}


	@Override
	public List<Entry<String, Defs>> collectDef() {
		List<Entry<String, Defs>> returnList = new LinkedList<>();
		
		Set<Entry<String, JSONSchemaElement>> entrySet = jsonSchema.entrySet();
		
		for(Entry<String, JSONSchemaElement> entry : entrySet)
			returnList.addAll(Utils_JSONSchema.addPathElement(entry.getKey(), entry.getValue().collectDef()));
		
		jsonSchema.remove("$defs");
		
		return returnList;
	}

	
	@Override
	public JSONSchema clone(){
		JSONSchema clone = new JSONSchema();
		
		if(booleanAsJSONSchema != null)
			clone.booleanAsJSONSchema = booleanAsJSONSchema;
		
		if(jsonSchema != null) {
			Set<Entry<String, JSONSchemaElement>> entrySet = jsonSchema.entrySet();
			clone.jsonSchema = new HashMap<>();
			
			for(Entry<String, JSONSchemaElement> entry : entrySet)
				clone.jsonSchema.put(entry.getKey(), entry.getValue().clone());
		}
		
		return clone;
	}
}
