package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public class UniqueItems_Assertion implements Assertion{
	private boolean uniqueItems;
	
	public UniqueItems_Assertion() {
		uniqueItems = true;
	}

	@Override
	public String toString() {
		return "UniqueItems_Assertion";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "uniqueItems";
	}

	@Override
	public Boolean toJSONSchema() {
		return uniqueItems;
	}

	@Override
	public Assertion not() {
		UniqueItems_Assertion ui = new UniqueItems_Assertion();
		ui.uniqueItems = !uniqueItems;
		return ui;
	}
}
