package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class AddPatternRequired_Assertion implements Assertion{
	private List<String> nameList; 
	private Assertion additionalProperties;

	public AddPatternRequired_Assertion() {
		nameList = new LinkedList<>();
	}
	
	public AddPatternRequired_Assertion(List<String> nameList, Assertion additionalProperties) {
		this.nameList = nameList;
		this.additionalProperties = additionalProperties;
	}

	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}

	public void setAdditionalProperties(Assertion additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	
	public void addName(String name) {
		nameList.add(name);
	}

	@Override
	public String getJSONSchemaKeyword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object toJSONSchema() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Assertion not() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Assertion notElimination() {
		AddPatternRequired_Assertion apr = new AddPatternRequired_Assertion();
		
		apr.nameList.addAll(nameList);
		apr.additionalProperties = additionalProperties.notElimination();
		
		return apr;
	}

	@Override
	public String toGrammarString() {
		String str = "";
		
		for(String s : nameList)
			str += GrammarStringDefinitions.COMMA + "\"" + s + "\"";
		
		if(additionalProperties == null)
			return String.format(GrammarStringDefinitions.ADDPATTERNREQUIRED, str.substring(GrammarStringDefinitions.COMMA.length()), "");
		
		return String.format(GrammarStringDefinitions.ADDPATTERNREQUIRED, str.substring(GrammarStringDefinitions.COMMA.length()), additionalProperties.toGrammarString());
	}
}
