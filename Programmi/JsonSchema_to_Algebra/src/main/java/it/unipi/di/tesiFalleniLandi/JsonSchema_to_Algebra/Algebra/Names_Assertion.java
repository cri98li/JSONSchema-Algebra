package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;

public class Names_Assertion implements Assertion{
	private Assertion names;
	
	public Names_Assertion(Assertion names) {
		this.names = names;
	}

	@Override
	public String toString() {
		return "Names_Assertion [" + names + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "propertyNames";
	}

	@Override
	public Object toJSONSchema() {
		JSONObject tmp = new JSONObject();
		Utils.putContent(tmp, names.getJSONSchemaKeyword(), names.toJSONSchema());
		return tmp;
	}
	
	@Override
	public Assertion not() {
		And_Assertion and = new And_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add("obj");
		and.add(type);
		and.add(new ExName_Assertion(names.not()));
		
		return and;
	}

	@Override
	public Assertion notElimination() {
		Assertion tmp = names.notElimination();
		
		return new Names_Assertion(tmp);
	}

	@Override
	public String toGrammarString() {
		return String.format(GrammarStringDefinitions.PROPERTYNAMES, names.toGrammarString());
	}

}
