package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessPattReq;
import patterns.REException;

import java.util.LinkedList;
import java.util.List;

public class AddPatternRequired_Assertion implements Assertion{
	private List<ComplexPattern> pattList;
	private Assertion additionalProperties;

	public AddPatternRequired_Assertion() {
		pattList = new LinkedList<>();
	}
	
	public AddPatternRequired_Assertion(List<ComplexPattern> pattList, Assertion additionalProperties) {
		this.pattList = pattList;
		this.additionalProperties = additionalProperties;
	}

	@Override
	public String toString() {
		return "AddPatternRequired_Assertion [nameList=" + pattList + ", additionalProperties=" + additionalProperties
				+ "]";
	}

	public void setPattList(List<ComplexPattern> pattList) {
		this.pattList = pattList;
	}

	public void setAdditionalProperties(Assertion additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	
	public void addName(ComplexPattern name) {
		pattList.add(name);
	}

	@Override
	public JsonElement toJSONSchema() {
		Type_Assertion type = new Type_Assertion();
		type.add(FullAlgebraString.TYPE_OBJECT);
		Properties_Assertion prop = new  Properties_Assertion();

		for(ComplexPattern p : pattList)
			prop.addPatternProperties(p, new Boolean_Assertion(true));

		prop.setAdditionalProperties(additionalProperties.not());

		IfThenElse_Assertion ifThen = new IfThenElse_Assertion(type, new Not_Assertion(prop), null);

		return ifThen.toJSONSchema();
	}

	@Override
	public Assertion not() {
		AllOf_Assertion and = new AllOf_Assertion();
		Properties_Assertion properties = new Properties_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add(FullAlgebraString.TYPE_OBJECT);
		
		for(ComplexPattern name : pattList) {
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
		StringBuilder str = new StringBuilder();
		
		for(ComplexPattern s : pattList)
			str.append(FullAlgebraString.COMMA)
					.append("\"")
					.append(s)
					.append("\"");
		
		if(additionalProperties == null)
			return FullAlgebraString.ADDPATTERNREQUIRED(str.substring(FullAlgebraString.COMMA.length()), "");

		if(str.length() == 0)
			return FullAlgebraString.ADDPATTERNREQUIRED("", additionalProperties.toGrammarString());
		else
			return FullAlgebraString.ADDPATTERNREQUIRED(str.substring(FullAlgebraString.COMMA.length()), additionalProperties.toGrammarString());
	}

	@Override
	public WitnessPattReq toWitnessAlgebra() throws REException {
		ComplexPattern p = ComplexPattern.createFromRegexp(".*");

		for(ComplexPattern pattern : pattList)
			p = p.intersect(pattern);

		return WitnessPattReq.build(p.complement(), additionalProperties.toWitnessAlgebra());
	}
}
