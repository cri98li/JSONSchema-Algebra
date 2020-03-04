package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

public class UniqueItems implements JSONSchemaElement{
	private boolean uniqueItems;
	
	public UniqueItems(Object obj){
		uniqueItems = (boolean) obj;
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
		// TODO Auto-generated method stub
		return null;
	}

}
