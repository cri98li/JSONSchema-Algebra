package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;


public class Items implements JSONSchemaElement{
	private List<JSONSchema> items_array;
	private JSONSchema items;
	private JSONSchema additionalItems_array;
	private JSONSchema unevaluatedItems_array;
	
	private boolean initialized;
	
	public Items() {	}
	
	public void setItems(Object obj) {
		JSONArray array = null;
		try{
			array = (JSONArray) obj;
		}catch(ClassCastException e) {
			items = new JSONSchema(obj);
			return;
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

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		
		if(items_array != null) {
			JSONArray array = new JSONArray();
			for(JSONSchema js : items_array)
				array.add(js.toJSON());
			obj.put("items", array);
		}
		
		if(additionalItems_array != null) obj.put("additionalItems", additionalItems_array.toJSON());
		
		if(unevaluatedItems_array != null) obj.put("unevaluatedItems", unevaluatedItems_array.toJSON());
		
		if(items != null) obj.put("items", items.toJSON());
				
		
		return obj;
	}

	@Override
	public String toGrammarString() {
		String str = "";
		if(items != null) {
			return String.format(GrammarStringDefinitions.ITEMS, "", items.toGrammarString());
		}
		
		Iterator<JSONSchema> it = items_array.iterator();
		if(it.hasNext())
			str += it.next().toGrammarString();
		
		while(it.hasNext()) {
			str += "*" + it.next().toGrammarString();
		}
		
		String str2 = "null";
		if(additionalItems_array != null)
			str2 = additionalItems_array.toGrammarString();
		
		return String.format(GrammarStringDefinitions.ITEMS, str, str2);
	}

	@Override
	public Items assertionSeparation() {
		Items obj = new Items();
		
		if(items_array != null) {
			obj.items_array = new LinkedList<>();
			for(JSONSchema s : items_array)
				obj.items_array.add(s.assertionSeparation());
		}
		
		if(items != null) obj.items = items.assertionSeparation();
		if(additionalItems_array != null) obj.additionalItems_array = additionalItems_array.assertionSeparation();
		if(unevaluatedItems_array != null) obj.unevaluatedItems_array = unevaluatedItems_array.assertionSeparation();
		
		return obj;
	}

	@Override
	public List<URI_JS> getRef() {
		List<URI_JS> returnList = new LinkedList<>();
		
		if(items_array != null) {
			for(JSONSchema s : items_array)
				returnList.addAll(s.getRef());
		}
		
		if(items != null) returnList.addAll(items.getRef());
		if(additionalItems_array != null) returnList.addAll(additionalItems_array.getRef());
		if(unevaluatedItems_array != null) returnList.addAll(unevaluatedItems_array.getRef());
		
		return returnList;
	}

	@Override
	public JSONSchema searchDef(Iterator<String> URIIterator) {
		if(URIIterator.hasNext())
			switch(URIIterator.next()) {
			case "items":
				URIIterator.remove();
				return items.searchDef(URIIterator);
			case "additionalItems":
				URIIterator.remove();
				return additionalItems_array.searchDef(URIIterator);
			case "unevaluatedItems":
				URIIterator.remove();
				return unevaluatedItems_array.searchDef(URIIterator);
			}
		
		return null;
	}

	@Override
	public List<Entry<String,Defs>> collectDef() {
		List<Entry<String,Defs>> returnList = new LinkedList<>();
		
		if(items_array != null) {
			//qui non lo posso trovare: come lo indicherei altrimenti?
		}
		
		if(items != null) returnList.addAll(Utils.addPathElement("items",items.collectDef()));
		if(additionalItems_array != null) returnList.addAll(Utils.addPathElement("additionalItems", additionalItems_array.collectDef()));
		if(unevaluatedItems_array != null) returnList.addAll(Utils.addPathElement("unevaluatedItems", unevaluatedItems_array.collectDef()));
		
		return returnList;
	}
}


