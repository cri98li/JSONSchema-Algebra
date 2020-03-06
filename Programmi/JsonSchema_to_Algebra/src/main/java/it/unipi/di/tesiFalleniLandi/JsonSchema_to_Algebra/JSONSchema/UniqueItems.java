package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class UniqueItems implements JSONSchemaElement{
	private boolean uniqueItems;
	
	public UniqueItems(Object obj){
		uniqueItems = (boolean) obj;
	}
	
	
	
	public UniqueItems() {
		// TODO Auto-generated constructor stub
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
	public UniqueItems assertionSeparation() {
		UniqueItems obj = new UniqueItems();
		
		obj.uniqueItems = this.uniqueItems;
		
		return obj;
	}

}
