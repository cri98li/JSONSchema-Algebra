package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class UniqueItems implements JSONSchemaElement{
	private boolean uniqueItems;
	
	public UniqueItems(Object obj){
		uniqueItems = (boolean) obj;
	}
	
	public UniqueItems() {
	}

	@Override
	public String toString() {
		return "UniqueItems [uniqueItems=" + uniqueItems + "]";
	}

	@Override
	public Boolean toJSON() {
		return uniqueItems;
	}

	@Override
	public String toGrammarString() {
		if(uniqueItems) return GrammarStringDefinitions.UNIQUEITEMS;
		
		return null; //non ci dovrei mai cadere
	}
	
	@Override
	public int numberOfAssertions() {
		return 1;
	}

	@Override
	public UniqueItems assertionSeparation() {
		return this.clone();
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
	public UniqueItems clone() {
		return new UniqueItems(uniqueItems);
	}
}