package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class BetweenItems implements JSONSchemaElement{
	private Object minItems;
	private Object maxItems;
	
	public BetweenItems() { }
	
	public void setMinItems(Object obj) {
		minItems = obj;
	}
	
	public void setMaxItems(Object obj) {
		maxItems = obj;
	}
	
	@Override
	public String toString() {
		return "BetweenItems [minItems=" + minItems + ", maxItems=" + maxItems + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		
		if(minItems != null) obj.put("minItems", minItems);
		if(maxItems != null) obj.put("maxItems", maxItems);
		
		return obj;
	}

	@Override
	public String toGrammarString() {
		String str = "";
		
			if(minItems != null && maxItems != null) {
				str = String.format(GrammarStringDefinitions.BETWEENITEMS, minItems, maxItems);
			} else if (minItems != null && maxItems == null) {
				str = String.format(GrammarStringDefinitions.BETWEENITEMS, minItems, GrammarStringDefinitions.NULLVALUE);
			}else if(minItems == null && maxItems != null) {
				str = String.format(GrammarStringDefinitions.BETWEENITEMS, GrammarStringDefinitions.NULLVALUE, maxItems);
			}/*else {
				str = String.format(GrammarStringDefinitions.BETWEENITEMS, "", "");
			}*/
			
		return str;
	}

	@Override
	public BetweenItems assertionSeparation() {
		BetweenItems obj = new BetweenItems();
		
		if(minItems != null) obj.minItems = minItems;
		if(maxItems != null) obj.maxItems = maxItems;
		
		return obj;
	}

	@Override
	public List<URI_JS> getRef() {
		return new LinkedList<>();
	}

	@Override
	public JSONSchema searchDef(Iterator<String> URIIterator) {
		return null;
	}

	@Override
	public List<Entry<String,Defs>> collectDef() {
		
		return new LinkedList<>();
	}

	@Override
	public int numberOfAssertions() {
		return 1;
	}

	public BetweenItems clone() {
		BetweenItems clone = new BetweenItems();
		
		clone.minItems = minItems;
		clone.maxItems = maxItems;
		
		return clone;
	}
}
