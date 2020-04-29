package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.MyPattern;

import java.util.LinkedList;
import java.util.List;

public class AddPatternRequired_Assertion implements Assertion{
	private List<MyPattern> pattList;
	private Assertion additionalProperties;

	public AddPatternRequired_Assertion() {
		pattList = new LinkedList<>();
	}
	
	public AddPatternRequired_Assertion(List<MyPattern> pattList, Assertion additionalProperties) {
		this.pattList = pattList;
		this.additionalProperties = additionalProperties;
	}

	@Override
	public String toString() {
		return "AddPatternRequired_Assertion [nameList=" + pattList + ", additionalProperties=" + additionalProperties
				+ "]";
	}

	public void setPattList(List<MyPattern> pattList) {
		this.pattList = pattList;
	}

	public void setAdditionalProperties(Assertion additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	
	public void addName(MyPattern name) {
		pattList.add(name);
	}

	@Override
	public Object toJSONSchema() {
		And_Assertion andList = new And_Assertion();
		Type_Assertion t = new Type_Assertion();
		t.add(GrammarStringDefinitions.TYPE_OBJECT);
		andList.add(t);
		Properties_Assertion prop = new Properties_Assertion();
		for(MyPattern s : pattList)
			prop.addPatternProperties(s, new Boolean_Assertion(true));

		prop.setAdditionalProperties(additionalProperties);
		andList.add(prop);

		return andList.toJSONSchema();

	}

	@Override
	public Assertion not() {
		And_Assertion and = new And_Assertion();
		Properties_Assertion properties = new Properties_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add("obj");
		
		for(MyPattern name : pattList) {
			properties.addPatternProperties(name, new Boolean_Assertion(true));
		}
		if(additionalProperties.not() != null)
			properties.setAdditionalProperties(additionalProperties.not());
		and.add(type);
		and.add(properties);
		
		return and;
	}

	@Override
	public Assertion notElimination() {
		AddPatternRequired_Assertion apr = new AddPatternRequired_Assertion();
		
		apr.pattList.addAll(pattList);
		apr.additionalProperties = additionalProperties.notElimination();
		
		return apr;
	}

	@Override
	public String toGrammarString() {
		String str = "";
		
		for(MyPattern s : pattList)
			str += GrammarStringDefinitions.COMMA + "\"" + s + "\"";
		
		if(additionalProperties == null)
			return String.format(GrammarStringDefinitions.ADDPATTERNREQUIRED, str.substring(GrammarStringDefinitions.COMMA.length()), "");

		if(str.isEmpty())
			return String.format(GrammarStringDefinitions.ADDPATTERNREQUIRED, "", additionalProperties.toGrammarString());
		else
			return String.format(GrammarStringDefinitions.ADDPATTERNREQUIRED, str.substring(GrammarStringDefinitions.COMMA.length()), additionalProperties.toGrammarString());
	}
}
