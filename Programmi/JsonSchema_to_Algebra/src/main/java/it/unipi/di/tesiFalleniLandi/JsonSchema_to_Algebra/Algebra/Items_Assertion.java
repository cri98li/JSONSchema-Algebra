package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;

public class Items_Assertion implements Assertion{

	private List<Assertion> itemsArray;
	private Assertion additionalItems;
	
	public Items_Assertion() {
	}

	public Items_Assertion(List<Assertion> itemsArray, Assertion additionalItems) {
		this.itemsArray = itemsArray;
		this.additionalItems = additionalItems;
	}

	public void add(Assertion assertion) {
		if(itemsArray == null) itemsArray = new LinkedList<>();
		itemsArray.add(assertion);
	}

	public void setAdditionalItems(Assertion additionalItems) {
		this.additionalItems = additionalItems;
	}

	@Override
	public String toString() {
		return "Items_Assertion [itemsArray=" + itemsArray + ", additionalItems=" + additionalItems + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "items";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONSchema() {
		JSONObject obj = new JSONObject();
		if(itemsArray != null)
			if(itemsArray.size() == 1)
				Utils.putContent(obj, itemsArray.get(0).getJSONSchemaKeyword(), itemsArray.get(0).toJSONSchema());
			else {
				JSONArray array = new JSONArray();
				for(Assertion assertion : itemsArray) {
					JSONObject tmp = new JSONObject();
					Utils.putContent(tmp, assertion.getJSONSchemaKeyword(), assertion.toJSONSchema());
					array.add(tmp);
				}
				obj.put("items", array);
			}
		
		if(additionalItems != null)
			Utils.putContent(obj, "additionalItems", additionalItems.toJSONSchema());
	
		return obj;
	}
	
}
