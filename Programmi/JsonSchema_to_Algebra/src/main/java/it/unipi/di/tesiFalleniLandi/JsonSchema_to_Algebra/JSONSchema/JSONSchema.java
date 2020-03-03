package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONSchema implements JSONSchemaElement{
	private Properties properties;
	private Type type;
	private If_Then_Else if_then_else;
	private Not not;
	private OneOf oneOf;
	private AllOf allOf;
	private AnyOf anyOf;
	private Items items;

	public JSONSchema(JSONObject object) {
		if_then_else = new If_Then_Else();
		items = new Items();
		
		
		
		
		//inizio parsing
		Iterator<Object> it = object.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String) it.next();
			switch(key) { 
			case "properties":
				properties = new Properties((JSONObject)object.get(key));
				break;
			
			case "type":
				type = new Type((String) object.get(key));
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
					items.setItems((JSONArray)object.get(key));
				} catch (ClassCastException e) {
					items.setItems((JSONObject)object.get(key));
				}
				break;
			}
		}
	}

	@Override
	public String toString() {
		String s =  "JSONSchema [";
		
		s += (properties == null) ? "" : ", properties="+properties;
		
		s += (type == null) ? "" : ", type="+type;
		
		s += (!if_then_else.isInitialized()) ? "" : ", if_then_else="+if_then_else;
		
		s += (oneOf == null) ? "" : ", oneOf="+oneOf;
		
		s += (allOf == null) ? "" : ", allOf="+allOf;
		
		s += (anyOf == null) ? "" : ", anyOf="+anyOf;
		
		
		//RICORDA: nell'ultimo non ci va la virgola
		
		return s+"]";
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
