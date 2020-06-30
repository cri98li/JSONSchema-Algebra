package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.UnsenseAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.*;
import java.util.Map.Entry;

public class JSONSchema implements JSONSchemaElement{
	
	private Boolean booleanAsJSONSchema; //To handle boolean value
	
	private HashMap<String, JSONSchemaElement> jsonSchema; //Dictionary of keywords in the schema

	public JSONSchema(Object obj) {
		jsonSchema = new HashMap<>();
		JSONObject object = null;

		try {
			booleanAsJSONSchema = (Boolean) obj;
			return;
		}catch(ClassCastException e) {
			object = (JSONObject) obj;
		}
		
		Iterator<?> it = object.keySet().iterator();
		
		if(object.keySet().size() == 0) { // empty schema
			booleanAsJSONSchema = true;
			return;			
		}
		
		while(it.hasNext()) {
			String key = (String) it.next();
			switch(key) { 
			
			//properties, patternProperties and additionalProperties grouped in Properties.
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
				
			//dependencies, dependentSchemas and dependentRequired grouped in Dependencies.
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
				
			//items and additionalItems grouped in Items.
			case "items":
				jsonSchema.putIfAbsent("items", new Items());
				((Items) jsonSchema.get("items")).setItems(object.get(key));
				break;
				
			case "additionalItems":
				jsonSchema.putIfAbsent("items", new Items());
				((Items) jsonSchema.get("items")).setAdditionalItems(object.get(key));
				break;
			
			//if, then and else grouped IfThenElse.
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
			
			//minItems and maxItems grouped in BetweenItems.
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
				
			//minLength and maxLength grouped in Length.
			case "minLength":
				jsonSchema.putIfAbsent("length", new Length());
				((Length) jsonSchema.get("length")).setMinLength(object.get(key));
				break;
				
			case "maxLength":
				jsonSchema.putIfAbsent("length", new Length());
				((Length) jsonSchema.get("length")).setMaxLength(object.get(key));
				break;
				
			//contains, minContains and maxContains grouped in Contains.
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
				
			//Iminimum, maximum, exclusiveMinimum and exclusiveMaximum grouped in BetweenNumber.
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
				try {
					jsonSchema.put("required", new Required(object.get(key)));
				}catch(UnsenseAssertion e){}
				break;
				
			case "pattern":
				jsonSchema.put("pattern", new Pattern(JSONValue.escape((String) object.get(key))));
				break;
				
			case "uniqueItems":
				jsonSchema.put("uniqueItems", new UniqueItems(object.get(key)));
				break;
				
			//minProperties and maxProperties grouped in BetweenProperties.
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
				((UnknowElement) jsonSchema.get("unknow")).add(key, object.get(key));
				break;
			}
		}
	}
	
	
	public JSONSchema() { }

	public void addJSONSchemaElement(String key, JSONSchemaElement value) {
		if(jsonSchema == null)	jsonSchema = new HashMap<>();
		jsonSchema.put(key, value);
	}
	
	@Override
	public Object toJSON() {
		JSONObject schema = new JSONObject();

		//boolean as a schema
		if(booleanAsJSONSchema != null) return booleanAsJSONSchema;
		
		Set<Entry<String, JSONSchemaElement>> entries = jsonSchema.entrySet();
		for(Entry<String, JSONSchemaElement> entry : entries){
			JSONObject toAdd = (JSONObject)entry.getValue().toJSON();
			Set<String> subSchemaKeySet = toAdd.keySet();
			for (String key : subSchemaKeySet){
				if(key.equals(Utils.ROOTDEF_FOR_JSONSCHEMA))
					schema.putAll((JSONObject) toAdd.get(key));
				else
					schema.put(key, toAdd.get(key));
			}
		}
		
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
		return "JSONSchema [booleanAsJSONSchema=" + booleanAsJSONSchema + "\r\n jsonSchema=" + jsonSchema + "]";
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

		//cerca i ref all'interno dello schema
		for(Entry<String, JSONSchemaElement> entry : entrySet)
			returnList.addAll(entry.getValue().getRef());
		
		return returnList;
	}


	@Override
	public JSONSchema searchDef(Iterator<String> URIIterator) {
		if(!URIIterator.hasNext())
			return this;
		
		String nextElement = URIIterator.next();

		//dato un nome di definizione cerca di risolverlo
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
