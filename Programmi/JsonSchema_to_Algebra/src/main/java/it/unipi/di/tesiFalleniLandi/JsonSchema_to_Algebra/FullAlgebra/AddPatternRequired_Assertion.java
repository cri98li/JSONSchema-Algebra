package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import patterns.Pattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.PosixPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessPattReq;

import java.util.LinkedList;
import java.util.List;

public class AddPatternRequired_Assertion implements Assertion{
	private List<PosixPattern> pattList;
	private Assertion additionalProperties;

	public AddPatternRequired_Assertion() {
		pattList = new LinkedList<>();
	}
	
	public AddPatternRequired_Assertion(List<PosixPattern> pattList, Assertion additionalProperties) {
		this.pattList = pattList;
		this.additionalProperties = additionalProperties;
	}

	@Override
	public String toString() {
		return "AddPatternRequired_Assertion [nameList=" + pattList + ", additionalProperties=" + additionalProperties
				+ "]";
	}

	public void setPattList(List<PosixPattern> pattList) {
		this.pattList = pattList;
	}

	public void setAdditionalProperties(Assertion additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	
	public void addName(PosixPattern name) {
		pattList.add(name);
	}

	@Override
	public Object toJSONSchema() {
		throw new UnsupportedOperationException("Da trovare la libreria");

	}

	@Override
	public Assertion not() {
		AllOf_Assertion and = new AllOf_Assertion();
		Properties_Assertion properties = new Properties_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add(GrammarStringDefinitions.TYPE_OBJECT);
		
		for(PosixPattern name : pattList) {
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
		
		for(PosixPattern s : pattList)
			str += GrammarStringDefinitions.COMMA + "\"" + s + "\"";
		
		if(additionalProperties == null)
			return String.format(GrammarStringDefinitions.ADDPATTERNREQUIRED, str.substring(GrammarStringDefinitions.COMMA.length()), "");

		if(str.isEmpty())
			return String.format(GrammarStringDefinitions.ADDPATTERNREQUIRED, "", additionalProperties.toGrammarString());
		else
			return String.format(GrammarStringDefinitions.ADDPATTERNREQUIRED, str.substring(GrammarStringDefinitions.COMMA.length()), additionalProperties.toGrammarString());
	}

	@Override
	public WitnessPattReq toWitnessAlgebra() {
		PosixPattern p = Pattern.createFromRegexp("bug*"); //TODO: bug --> dovrebbe essere vuoto
		for(PosixPattern pattern : pattList)
			p = p.intersect(pattern);

		return new WitnessPattReq(p, additionalProperties.toWitnessAlgebra());
		//return new WitnessPattReq(p.complement(), additionalProperties.toWitnessAlgebra()); TODO: addPattReq complement
	}
}
