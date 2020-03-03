package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Items implements JSONSchemaElement{
	private List<JSONSchema> items_array;
	private JSONSchema items;
	private JSONSchema additionalItems_array;
	private JSONSchema unevaluatedItems_array;
	
	private boolean inizialized;
	
	public Items() {
		
	}
	
	public void setItems(JSONObject obj) {
		inizialized = true;
		
		items = new JSONSchema(obj);
	}
	
	
	
	
	
	
	
	public void setItems(JSONArray array) {
		inizialized = true;
		
		items_array = new LinkedList<>();
		
		Iterator<?> it = array.iterator();
		
		while(it.hasNext()) {
			JSONObject element = (JSONObject) it.next();
			items_array.add(new JSONSchema(element));
		}
	}
	
	public void setAdditionalItems(JSONObject obj) {
		inizialized = true;

		items = new JSONSchema(obj);
	}
	
	public void setUnevaluatedItems(JSONObject obj) {
		inizialized = true;

		items = new JSONSchema(obj);
	}
	
	
	public boolean isInitialized() {
		return inizialized;
	}
	
	
	
	

	@Override
	public String toString() {
		return "Items [items_array=" + items_array + ", items=" + items + ", additionalItems_array="
				+ additionalItems_array + ", unevaluatedItems_array=" + unevaluatedItems_array + "]";
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


