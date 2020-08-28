package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonElement;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.ComplexPattern.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.AlgebraStrings;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessPattReq;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

import java.util.LinkedList;
import java.util.List;

public class AddPatternRequired_Assertion implements Assertion{
	private List<ComplexPattern> pattList;
	private Assertion additionalProperties;

	private static Logger logger = LogManager.getLogger(AddPatternRequired_Assertion.class);

	public AddPatternRequired_Assertion() {
		logger.trace("Creating an AddPatternRequired_Assertion");

		pattList = new LinkedList<>();
	}

	@Override
	public String toString() {
		return "AddPatternRequired_Assertion [nameList=" + pattList + ", additionalProperties=" + additionalProperties
				+ "]";
	}

	public void setPattList(List<ComplexPattern> pattList) {
		logger.trace("Setting {} as pattList to {}", pattList, this);
		this.pattList = pattList;
	}

	public void setAdditionalProperties(Assertion additionalProperties) {
		logger.trace("Setting {} as additionalProperties to {}", additionalProperties, this);
		this.additionalProperties = additionalProperties;
	}
	
	public void addName(ComplexPattern name) {
		logger.trace("Adding {} in as pattList in {}", name, this);
		pattList.add(name);
	}

	@Override
	public JsonElement toJSONSchema() {
		Type_Assertion type = new Type_Assertion();
		type.add(AlgebraStrings.TYPE_OBJECT);
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
		type.add(AlgebraStrings.TYPE_OBJECT);
		
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
			str.append(AlgebraStrings.COMMA)
					.append("\"")
					.append(s)
					.append("\"");
		
		if(additionalProperties == null)
			return AlgebraStrings.ADDPATTERNREQUIRED(str.substring(AlgebraStrings.COMMA.length()), "");

		if(str.length() == 0)
			return AlgebraStrings.ADDPATTERNREQUIRED("", additionalProperties.toGrammarString());
		else
			return AlgebraStrings.ADDPATTERNREQUIRED(str.substring(AlgebraStrings.COMMA.length()), additionalProperties.toGrammarString());
	}

	@Override
	public WitnessPattReq toWitnessAlgebra() throws REException {
		ComplexPattern p = ComplexPattern.createFromRegexp(".*");

		for(ComplexPattern pattern : pattList)
			p = p.intersect(pattern);

		return WitnessPattReq.build(p.complement(), additionalProperties.toWitnessAlgebra());
	}
}
