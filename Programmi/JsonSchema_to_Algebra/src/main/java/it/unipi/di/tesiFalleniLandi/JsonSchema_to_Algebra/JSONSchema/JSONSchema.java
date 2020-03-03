package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONSchema implements JSONSchemaElement{
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

	public JSONSchema(JSONObject object) {
		if_then_else = new If_Then_Else();
		items = new Items();
		length = new Length();
		betweenItems = new BetweenItems();
		betweenNumber = new BetweenNumber();
		
		
		//inizio parsing
		Iterator<Object> it = object.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String) it.next();
			switch(key) { 
			case "properties":
				properties = new Properties((JSONObject)object.get(key));
				break;
			
			case "type":
				try {
					type = new Type((JSONArray)object.get(key));
				}catch (ClassCastException e) {
					type = new Type((String)object.get(key));
				}
				break;
			
			case "if":
				if_then_else.setIf((JSONObject)object.get(key));
				break;
				
			case "then":
				if_then_else.setThen((JSONObject)object.get(key));
				break;
				
			case "else":
				if_then_else.setElse((JSONObject)object.get(key));
				break;
			
			case "not":
				not = new Not((JSONObject)object.get(key));
				break;
			
			case "oneOf":
				oneOf = new OneOf((JSONArray)object.get(key));
				break;
			
			case "allOf":
				allOf = new AllOf((JSONArray)object.get(key));
				break;
			
			case "anyOf":
				anyOf = new AnyOf((JSONArray)object.get(key));
				break;
				
			case "items":
				try {
					items.setItems((JSONArray) object.get(key));
				} catch (ClassCastException e) {
					items.setItems((JSONObject) object.get(key));
				}
				break;
			
			case "minItems":
				betweenItems.setMinItems((Long)object.get(key));
				break;
				
			case "maxItems":
				betweenItems.setMaxItems((Long)object.get(key));
				break;
				
			case "multipleOf":
				multipleOf = new MultipleOf((Long)object.get(key));
				break;
				
			case "minLength":
				length.setMinLength((Long)object.get(key));
				break;
				
			case "maxLength":
				length.setMaxLength((Long)object.get(key));
				break;
				
			case "contains":
				contains.setContains((JSONObject) object.get(key));
				break;
			
			case "minContains":
				contains.setMinContains((Long)object.get(key));
				break;
			
			case "maxContains":
				contains.setMaxContains((Long)object.get(key));
				break;
				
			case "minimum":
				betweenNumber.setMin((Long)object.get(key));
				break;
				
			case "maximum":
				betweenNumber.setMax((Long)object.get(key));
				break;
				
			case "exclusiveMinimum":
				try {
					betweenNumber.setExclusiveMin((Boolean) object.get(key));
				}catch(ClassCastException e) {
					betweenNumber.setExclusiveMin((Long) object.get(key));
				}
				
				break;
				
			case "exclusiveMaximum":
				try {
					betweenNumber.setExclusiveMax((Boolean) object.get(key));
				}catch(ClassCastException e) {
					betweenNumber.setExclusiveMax((Long) object.get(key));
				}
				
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
	public String toString() {
		return "JSONSchema [properties=" + properties + "\r\n type=" + type + "\r\n if_then_else=" + if_then_else + "\r\n not="
				+ not + "\r\n oneOf=" + oneOf + "\r\n allOf=" + allOf + "\r\n anyOf=" + anyOf + "\r\n items=" + items
				+ "\r\n multipleOf=" + multipleOf + "\r\n length=" + length + "\r\n betweenItems=" + betweenItems + "]";
	}
	
	
	@Override
	public String toJSONString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toGrammarString() {
		// TODO Auto-generated method stub
		return null;
	}

}
