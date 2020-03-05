package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONObject;

public class JSONSchema implements JSONSchemaElement{
	private Boolean booleanAsJSONSchema;
	
	private Properties properties;
	private Type type;
	private IfThenElse ifThenElse;
	private Not not;
	private OneOf oneOf;
	private AllOf allOf;
	private AnyOf anyOf;
	private Items items;
	private MultipleOf multipleOf;
	private Length length;
	private BetweenItems betweenItems;
	private Contains contains;
	private BetweenNumber betweenNumber;
	private Required required;
	private Pattern pattern;
	private UniqueItems uniqueItems;
	private BetweenProperties betweenProperties;
	private Enum _enum;
	private Const _const;

	public JSONSchema(Object obj) {
		JSONObject object;
		try {
			booleanAsJSONSchema = (Boolean) obj;
			return;
		}catch(ClassCastException e) {
			object = (JSONObject) obj;
		}
		
		//inizio parsing
		Iterator<?> it = object.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String) it.next();
			switch(key) { 
			case "properties":
				if(properties == null) properties = new Properties();
				properties.setProperties(object.get(key));
				break;
			
			case "patternProperties":
				if(properties == null) properties = new Properties();
				properties.setPatternProperties(object.get(key));
				break;
				
			case "additionalProperties":
				if(properties == null) properties = new Properties();
				properties.setAdditionalProperties(object.get(key));
				break;
			
			case "additionalItems":
				if(items == null) items = new Items();
				items.setAdditionalItems(object);
				break;

			case "type":
				type = new Type(object.get(key));
				break;
			
			case "if":
				if(ifThenElse == null) ifThenElse = new IfThenElse();
				ifThenElse.setIf(object.get(key));
				break;
				
			case "then":
				if(ifThenElse == null) ifThenElse = new IfThenElse();
				ifThenElse.setThen(object.get(key));
				break;
				
			case "else":
				if(ifThenElse == null) ifThenElse = new IfThenElse();
				ifThenElse.setElse(object.get(key));
				break;
			
			case "not":
				not = new Not(object.get(key));
				break;
			
			case "oneOf":
				oneOf = new OneOf(object.get(key));
				break;
			
			case "allOf":
				allOf = new AllOf(object.get(key));
				break;
			
			case "anyOf":
				anyOf = new AnyOf(object.get(key));
				break;
				
			case "items":
				if(items == null) items = new Items();
				items.setItems(object.get(key));
				break;
			
			case "minItems":
				if(betweenItems == null) betweenItems = new BetweenItems();
				betweenItems.setMinItems(object.get(key));
				break;
				
			case "maxItems":
				if(betweenItems == null) betweenItems = new BetweenItems();
				betweenItems.setMaxItems(object.get(key));
				break;
				
			case "multipleOf":
				multipleOf = new MultipleOf(object.get(key));
				break;
				
			case "minLength":
				if(length == null) length = new Length();
				length.setMinLength(object.get(key));
				break;
				
			case "maxLength":
				if(length == null) length = new Length();
				length.setMaxLength(object.get(key));
				break;
				
			case "contains":
				if(contains == null) contains = new Contains();
				contains.setContains(object.get(key));
				break;
			
			case "minContains":
				if(contains == null) contains = new Contains();
				contains.setMinContains(object.get(key));
				break;
			
			case "maxContains":
				if(contains == null) contains = new Contains();
				contains.setMaxContains(object.get(key));
				break;
				
			case "minimum":
				if(betweenNumber == null) betweenNumber = new BetweenNumber();
				betweenNumber.setMin(object.get(key));
				break;
				
			case "maximum":
				if(betweenNumber == null) betweenNumber = new BetweenNumber();
				betweenNumber.setMax(object.get(key));
				break;
				
			case "exclusiveMinimum":
				if(betweenNumber == null) betweenNumber = new BetweenNumber();
				betweenNumber.setExclusiveMin(object.get(key));
				break;
				
			case "exclusiveMaximum":
				if(betweenNumber == null) betweenNumber = new BetweenNumber();
				betweenNumber.setExclusiveMax(object.get(key));
				break;
				
			case "required":
				required = new Required(object.get(key));
				break;
				
			case "pattern":
				pattern = new Pattern((String) object.get(key));
				break;
				
			case "uniqueItems":
				uniqueItems = new UniqueItems(object.get(key));
				break;
				
			case "minProperties":
				if(betweenProperties == null) betweenProperties = new BetweenProperties();
				betweenProperties.setMinProperties(object.get(key));
				break;
				
			case "maxProperties":
				if(betweenProperties == null) betweenProperties = new BetweenProperties();
				betweenProperties.setMaxProperties(object.get(key));
				break;
				
			case "enum":
				_enum = new Enum(object.get(key));
				break;
				
			case "const":
				_const = new Const(object.get(key));
				break;
			
			default:
				break;
			}
		}
	}

	
	
	
	public JSONSchema() {
	}




	@SuppressWarnings("unchecked")
	@Override
	public Object toJSON() {
		JSONObject schema = new JSONObject();

		//caso boolean as a Schema
		if(booleanAsJSONSchema != null) return booleanAsJSONSchema;
		
		if(_const != null) schema.put("const", _const.toJSON());
		
		if(_enum != null) schema.put("enum", _enum.toJSON());
		
		if(properties != null) putContent(schema, properties.toJSON());
		
		if(type != null) schema.put("type", type.toJSON());
		
		if(ifThenElse != null) putContent(schema, ifThenElse.toJSON());
		
		if(not != null) schema.put("not", not.toJSON());
		
		if(oneOf != null) schema.put("oneOf", oneOf.toJSON());
		
		if(anyOf != null) schema.put("anyOf", anyOf.toJSON());
		
		if(allOf != null) schema.put("allOf", allOf.toJSON());
		
		if(multipleOf != null) schema.put("multipleOf", multipleOf.toJSON());
		
		if(uniqueItems != null) schema.put("uniqueItems", uniqueItems.toJSON());
		
		if(required != null) schema.put("required", required.toJSON());
		
		if(pattern != null) schema.put("pattern", pattern.toJSON());
		
		if(betweenProperties != null) putContent(schema, betweenProperties.toJSON());
		
		if(betweenNumber != null) putContent(schema, betweenNumber.toJSON());
		
		if(contains != null) putContent(schema, contains.toJSON());
		
		if(length != null) putContent(schema, length.toJSON());
		
		if(betweenItems != null) putContent(schema, betweenItems.toJSON());
		
		if(items != null) putContent(schema, items.toJSON());
		
		return schema;
	}
	
	//inserisce il contenuto di toPut in schema
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
		
		schema.allOf = new AllOf();
		
		
		if(_const != null) {
			JSONSchema tmp = new JSONSchema();
			tmp._const = _const.assertionSeparation();
			schema.allOf.addElement(tmp);
		}
		if(_enum != null) {
			JSONSchema tmp = new JSONSchema();
			tmp._enum = _enum.assertionSeparation();
			schema.allOf.addElement(tmp);
		}
		
		if(properties != null) {
			JSONSchema tmp = new JSONSchema();
			tmp.properties = properties.assertionSeparation();
			schema.allOf.addElement(tmp);
		}
		
		if(type != null) {
			JSONSchema tmp = new JSONSchema();
			tmp.type = type.assertionSeparation();
			schema.allOf.addElement(tmp);
		}
		
		if(ifThenElse != null) {
			JSONSchema tmp = new JSONSchema();
			tmp.ifThenElse = ifThenElse.assertionSeparation();
			schema.allOf.addElement(tmp);
		}
		
		if(not != null) {
			JSONSchema tmp = new JSONSchema();
			tmp.not = not.assertionSeparation();
			schema.allOf.addElement(tmp);
		}
		
		if(items != null) {
			JSONSchema tmp = new JSONSchema();
			tmp.items = items.assertionSeparation();
			schema.allOf.addElement(tmp);
		}
		
		if(multipleOf != null) {
			JSONSchema tmp = new JSONSchema();
			tmp.multipleOf = multipleOf.assertionSeparation();
			schema.allOf.addElement(tmp);
		}
		
		if(length != null) {
			JSONSchema tmp = new JSONSchema();
			tmp.length = length.assertionSeparation();
			schema.allOf.addElement(tmp);
		}
		
		if(betweenNumber != null) {
			JSONSchema tmp = new JSONSchema();
			tmp.betweenNumber = betweenNumber.assertionSeparation();
			schema.allOf.addElement(tmp);
		}
		
		if(betweenItems != null) {
			JSONSchema tmp = new JSONSchema();
			tmp.betweenItems = betweenItems.assertionSeparation();
			schema.allOf.addElement(tmp);
		}
		
		if(contains != null) {
			JSONSchema tmp = new JSONSchema();
			tmp.contains = contains.assertionSeparation();
			schema.allOf.addElement(tmp);
		}
		
		if(required != null) {
			JSONSchema tmp = new JSONSchema();
			tmp.required = required.assertionSeparation();
			schema.allOf.addElement(tmp);
		}
		
		if(pattern != null) {
			JSONSchema tmp = new JSONSchema();
			tmp.pattern = pattern.assertionSeparation();
			schema.allOf.addElement(tmp);
		}
		
		if(uniqueItems != null) {
			JSONSchema tmp = new JSONSchema();
			tmp.uniqueItems = uniqueItems.assertionSeparation();
			schema.allOf.addElement(tmp);
		}
		
		if(betweenProperties != null) {
			JSONSchema tmp = new JSONSchema();
			tmp.betweenProperties = betweenProperties.assertionSeparation();
			schema.allOf.addElement(tmp);
		}
		
		if(oneOf != null) {
			JSONSchema tmp = new JSONSchema();
			tmp.oneOf = oneOf.assertionSeparation();
			schema.allOf.addElement(tmp);
		}
		
		if(allOf != null) {
			JSONSchema tmp = new JSONSchema();
			tmp.allOf = allOf.assertionSeparation();
			schema.allOf.addElement(tmp);
		}
		
		if(anyOf != null) {
			JSONSchema tmp = new JSONSchema();
			tmp.anyOf = anyOf.assertionSeparation();
			schema.allOf.addElement(tmp);
		}
		
		return schema;
	}

	@Override
	public String toString() {
		return "JSONSchema [booleanAsJSONSchema=" + booleanAsJSONSchema + "\r\n properties=" + properties
				+ "\r\n type=" + type + "\r\n ifThenElse=" + ifThenElse + "\r\n not=" + not + "\r\n oneOf="
				+ oneOf + "\r\n allOf=" + allOf + "\r\n anyOf=" + anyOf + "\r\n items=" + items
				+ "\r\n multipleOf=" + multipleOf + "\r\n length=" + length + "\r\n betweenItems=" + betweenItems
				+ "\r\n contains=" + contains + "\r\n betweenNumber=" + betweenNumber + "\r\n required="
				+ required + "\r\n pattern=" + pattern + "\r\n uniqueItems=" + uniqueItems
				+ "\r\n betweenProperties=" + betweenProperties + "\r\n _enum=" + _enum + "\r\n _const=" + _const
				+ "]";
	}

	@Override
	public String toGrammarString() {
		// TODO Auto-generated method stub
		return null;
	}

}
