package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;

import org.json.simple.JSONObject;

public class JSONSchema implements JSONSchemaElement{
	private Boolean booleanAsJSONSchema;
	
	
	private Properties properties;
	private Type type;
	private If_Then_Else if_then_else;
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

	public JSONSchema(Object obj) {
		JSONObject object;
		try {
			booleanAsJSONSchema = (Boolean) obj;
			return;
		}catch(ClassCastException e) {
			object = (JSONObject) obj;
		}
		
		if_then_else = new If_Then_Else();
		items = new Items();
		length = new Length();
		betweenItems = new BetweenItems();
		betweenNumber = new BetweenNumber();
		contains = new Contains();
		betweenProperties = new BetweenProperties();
		properties = new Properties();
		
		//inizio parsing
		Iterator<?> it = object.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String) it.next();
			switch(key) { 
			case "properties":
				properties.setProperties(object.get(key));
				break;
			
			case "type":
				type = new Type(object.get(key));
				break;
			
			case "if":
				if_then_else.setIf(object.get(key));
				break;
				
			case "then":
				if_then_else.setThen(object.get(key));
				break;
				
			case "else":
				if_then_else.setElse(object.get(key));
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
				items.setItems(object.get(key));
				break;
			
			case "minItems":
				betweenItems.setMinItems(object.get(key));
				break;
				
			case "maxItems":
				betweenItems.setMaxItems(object.get(key));
				break;
				
			case "multipleOf":
				multipleOf = new MultipleOf(object.get(key));
				break;
				
			case "minLength":
				length.setMinLength(object.get(key));
				break;
				
			case "maxLength":
				length.setMaxLength(object.get(key));
				break;
				
			case "contains":
				contains.setContains(object.get(key));
				break;
			
			case "minContains":
				contains.setMinContains(object.get(key));
				break;
			
			case "maxContains":
				contains.setMaxContains(object.get(key));
				break;
				
			case "minimum":
				betweenNumber.setMin(object.get(key));
				break;
				
			case "maximum":
				betweenNumber.setMax(object.get(key));
				break;
				
			case "exclusiveMinimum":
				betweenNumber.setExclusiveMin(object.get(key));
				break;
				
			case "exclusiveMaximum":
				try {
					betweenNumber.setExclusiveMax(object.get(key));
				}catch(ClassCastException e) {
					//e.printStackTrace();
					betweenNumber.setExclusiveMax(object.get(key));
				}
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
				betweenProperties.setMinProperties(object.get(key));
				break;
				
			case "maxProperties":
				betweenProperties.setMaxProperties(object.get(key));
				break;
			
			default:
				break;
			}
		}
	}

	/*@Override
	public String toString() {
		String s =  "JSONSchema [";
		
		s += (properties == null) ? "" : ", properties="+properties;
		
		s += (type == null) ? "" : ", type="+type;
		
		s += (!if_then_else.isInitialized()) ? "" : ", if_then_else="+if_then_else;
		
		s += (oneOf == null) ? "" : ", oneOf="+oneOf;
		
		s += (allOf == null) ? "" : ", allOf="+allOf;
		
		s += (anyOf == null) ? "" : ", anyOf="+anyOf;
		
		s += (!items.isInitialized()) ? "" : ", items="+items;
		
		s += (multipleOf == null) ? "" : ", multipleOf="+multipleOf;
		
		s += (!length.isInitialized()) ? "" : ", length="+length;
		
		s += (!betweenItems.isInitialized()) ? "" : ", betweenItems="+betweenItems;
		
		s += (!contains.isInitialized()) ? "" : ", contains="+contains;
		
		
		//RICORDA: nell'ultimo non ci va la virgola
		
		return s+"]";
	}*/

	
	
	
	@Override
	public String toJSONString() {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public String toString() {
		return "JSONSchema [booleanAsJSONSchema=" + booleanAsJSONSchema + "\r\n  properties=" + properties
				+ "\r\n  type=" + type + "\r\n  if_then_else=" + if_then_else + "\r\n  not=" + not
				+ "\r\n  oneOf=" + oneOf + "\r\n  allOf=" + allOf + "\r\n  anyOf=" + anyOf + "\r\n  items="
				+ items + "\r\n  multipleOf=" + multipleOf + "\r\n  length=" + length + "\r\n  betweenItems="
				+ betweenItems + "\r\n  contains=" + contains + "\r\n  betweenNumber=" + betweenNumber
				+ "\r\n  required=" + required + "\r\n  pattern=" + pattern + "\r\n  uniqueItems=" + uniqueItems
				+ "\r\n  betweenProperties=" + betweenProperties + "]";
	}

	@Override
	public String toGrammarString() {
		// TODO Auto-generated method stub
		return null;
	}

}
