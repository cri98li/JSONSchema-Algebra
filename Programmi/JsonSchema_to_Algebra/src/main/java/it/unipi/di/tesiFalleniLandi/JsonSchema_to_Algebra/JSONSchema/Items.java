package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;


public class Items implements JSONSchemaElement{
	private List<JSONSchema> items_array;
	private JSONSchema items;
	private JSONSchema additionalItems_array;
	private JSONSchema unevaluatedItems_array;
	
	public Items() {}
	
	public void setItems(JsonElement obj) {
		JsonArray array;
		
		try{
			array = obj.getAsJsonArray();
		}catch(ClassCastException | IllegalStateException e) {
			items = new JSONSchema(obj);
			return;
		}
		
		items_array = new LinkedList<>();
		
		Iterator<JsonElement> it = array.iterator();
		
		while(it.hasNext()) {
			items_array.add(new JSONSchema(it.next()));
		}
	}
	
	public void setAdditionalItems(JsonElement obj) {
		additionalItems_array = new JSONSchema(obj);
	}
	
	public void setUnevaluatedItems(JsonElement obj) {
		unevaluatedItems_array = new JSONSchema(obj);
	}
	
	@Override
	public String toString() {
		return "Items [items_array=" + items_array + ", items=" + items + ", additionalItems_array="
				+ additionalItems_array + ", unevaluatedItems_array=" + unevaluatedItems_array + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JsonElement toJSON() {
		JsonObject obj = new JsonObject();
		
		if(items_array != null) {
			JsonArray array = new JsonArray();
			for(JSONSchema js : items_array)
				array.add(js.toJSON());
			obj.add("items", array);
		}
		
		if(additionalItems_array != null) obj.add("additionalItems", additionalItems_array.toJSON());
		
		if(unevaluatedItems_array != null) obj.add("unevaluatedItems", unevaluatedItems_array.toJSON());
		
		if(items != null) obj.add("items", items.toJSON());
				
		
		return obj;
	}

	@Override
	public String toGrammarString() {
		String str = "";
		
		if(items != null && items.numberOfAssertions() != 0) {
			return String.format(GrammarStringDefinitions.ITEMS, "", items.toGrammarString());
		}

		if(items_array != null) {
			Iterator<JSONSchema> it = items_array.iterator();

			while (it.hasNext()) {
				JSONSchemaElement jse = it.next();
				if(jse.numberOfAssertions() != 0)
					str += jse.toGrammarString() + ",";
			}

			str = str.substring(0, str.length()-1);
		}
		
		String str2 = "";
		if(additionalItems_array != null && additionalItems_array.numberOfAssertions() != 0) {
			str2 = additionalItems_array.toGrammarString();
			if(str.isEmpty())
				return String.format(GrammarStringDefinitions.ITEMS, str2, "");
		}
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
		
		if(items_array != null)
			for(JSONSchema s : items_array)
				returnList.addAll(s.getRef());
		
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
		
		if(items != null) returnList.addAll(Utils_JSONSchema.addPathElement("items",items.collectDef()));
		if(additionalItems_array != null) returnList.addAll(Utils_JSONSchema.addPathElement("additionalItems", additionalItems_array.collectDef()));
		if(unevaluatedItems_array != null) returnList.addAll(Utils_JSONSchema.addPathElement("unevaluatedItems", unevaluatedItems_array.collectDef()));
		
		return returnList;
	}

	@Override
	public int numberOfAssertions() {
		if(items != null) return items.numberOfAssertions();

		if(items_array != null)
			for(JSONSchema s : items_array)
				if(s.numberOfAssertions() > 0) return 1;

		if(additionalItems_array != null) return additionalItems_array.numberOfAssertions();

		if(unevaluatedItems_array != null) return unevaluatedItems_array.numberOfAssertions();

		return 0;
	} 
	
	@Override
	public Items clone(){
		Items newItems = new Items();
		
		if(items != null)
			newItems.items = items.clone();

		if(items_array != null) {
			newItems.items_array = new LinkedList<>();
			for (JSONSchema item : items_array) {
				newItems.items_array.add(item.clone());
			}
		}

		if(additionalItems_array != null) newItems.additionalItems_array = additionalItems_array.clone();
		if(unevaluatedItems_array != null) newItems.unevaluatedItems_array = unevaluatedItems_array.clone();
		
		return newItems;
	}
}


