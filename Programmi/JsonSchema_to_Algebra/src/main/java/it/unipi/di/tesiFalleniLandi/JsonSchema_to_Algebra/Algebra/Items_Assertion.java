package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.Arrays;
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

	@Override
	public Assertion not() { //TODO: caso == null
		
		//only additionaItems
		if(additionalItems != null && itemsArray == null) {
			add(additionalItems);
			additionalItems = null;
		}
		
		
		
		And_Assertion rootAnd = new And_Assertion();
		Or_Assertion rootOr = new Or_Assertion();
		Type_Assertion typeArray = new Type_Assertion();
		typeArray.add("arr");
		rootAnd.add(typeArray);
		rootAnd.add(rootOr);
		
		//ITEMS
		for(int i = 0; i < itemsArray.size(); i++) {
			And_Assertion itemAndAssertion = new And_Assertion();
			Items_Assertion itemAssertion = new Items_Assertion();
			rootOr.add(itemAndAssertion);
			itemAndAssertion.add(itemAssertion);
			
			itemAndAssertion.add(new Exist_Assertion((long) (i+1), null, new And_Assertion(true)));
			itemAssertion.setAdditionalItems(new And_Assertion(true));
			
			for(int j = 0; j < itemsArray.size(); j++)
				itemAssertion.add((i == j) ? itemsArray.get(i).not() : new And_Assertion(true));
		}
		
		
		if(additionalItems == null) return rootAnd;
		
		//ADDITIONAL ITEMS
		Boolean[] bm = new Boolean[itemsArray.size()];
		Arrays.fill(bm, false);
		//Or_Assertion orAdditionalItems = new Or_Assertion(); non è rootOr????
		Assertion notAdditionalItems = additionalItems.not();
		
		do {
			And_Assertion andAdditionalItems = new And_Assertion();
			rootOr.add(andAdditionalItems);
			andAdditionalItems.add(new Exist_Assertion((long) sumbit(bm), null, new And_Assertion(true)));
			Items_Assertion itemsAdditionalItems = new Items_Assertion();
			andAdditionalItems.add(itemsAdditionalItems);
			
			for(int i = 0; i < bm.length; i++)
				itemsAdditionalItems.add(bm[i] ? additionalItems : notAdditionalItems);
				
			
		}while(addbit(bm, 0));
		
		
		return rootAnd;
	}
	
	private Boolean addbit(Boolean[] bm, int position) {
	    if(bm.length == position) return false;
		
		if(!bm[position]) {
	    	bm[position] = true;
	    	return true;
	    }
	    
		bm[position] = false;
	    return addbit(bm, position+1);
	}
	
	private int sumbit(Boolean[] bm) {
	    int count = 0;
	    
		for(int i = 0; i < bm.length; i++)
	    	if(bm[i]) count++;
		
		return count;
	}
}
