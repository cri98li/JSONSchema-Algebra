package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;


public class Items implements JSONSchemaElement{
	private List<JSONSchema> items_array;
	private JSONSchema items;
	private JSONSchema additionalItems_array;
	private JSONSchema unevaluatedItems_array;
	
	private boolean initialized;
	
	public Items() {
		
	}
	
	public void setItems(Object obj) {
		JSONArray array = null;
		try{
			array = (JSONArray) obj;
		}catch(ClassCastException e) {
			items = new JSONSchema(obj);
		}
		
		initialized = true;
		
		items_array = new LinkedList<>();
		
		Iterator<?> it = array.iterator();
		
		while(it.hasNext()) {
			items_array.add(new JSONSchema(it.next()));
		}
	}
	
	public void setAdditionalItems(Object obj) {
		initialized = true;

		items = new JSONSchema(obj);
	}
	
	public void setUnevaluatedItems(Object obj) {
		initialized = true;

		items = new JSONSchema(obj);
	}
	
	public boolean isInitialized() {
		return initialized;
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


