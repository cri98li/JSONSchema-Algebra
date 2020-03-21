package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

public class Items_Assertion implements Assertion{

	private List<Assertion> itemsArray;
	private Assertion additionalItems;
	
	public Items_Assertion() {
		itemsArray = new LinkedList<>();
	}

	public Items_Assertion(List<Assertion> itemsArray, Assertion additionalItems) {
		this.itemsArray = itemsArray;
		this.additionalItems = additionalItems;
	}

	public void add(Assertion assertion) {
		itemsArray.add(assertion);
	}

	public void setAdditionalItems(Assertion additionalItems) {
		this.additionalItems = additionalItems;
	}

	@Override
	public String toString() {
		return "Items_Assertion [itemsArray=" + itemsArray + ", additionalItems=" + additionalItems + "]";
	}
	
	
}
