package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.AlgebraStrings;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.Utils_JSONSchema;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAnd;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessPattReqManager;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessVarManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AllOf_Assertion implements Assertion{
	private List<Assertion> andList;
	private boolean duplicates, //used to replace allOf[...] keyword with { ... } when possible
			containsFalseBooleanAssertion;

	private static Logger logger = LogManager.getLogger(AllOf_Assertion.class);
	
	public AllOf_Assertion() {
		this.andList = new LinkedList<>();
		duplicates = false;
		containsFalseBooleanAssertion = false;
		logger.trace("Creating an empty AllOf_Assertion");
	}

	public void addAll(List<Assertion> list) {
		for(Assertion assertion : list) {
			duplicates |= contains(assertion);
			containsFalseBooleanAssertion |= (Boolean_Assertion.class == assertion.getClass());
			logger.trace("Added {} to {}", assertion, this);
		}
		andList.addAll(list);
	}
	
	public void add(Assertion assertion) {
		if(assertion == null) return;
		duplicates |= contains(assertion);
		containsFalseBooleanAssertion |= (Boolean_Assertion.class == assertion.getClass());
		andList.add(assertion);

		logger.trace("Added {} to {}", assertion, this);
	}

	private boolean contains(Assertion assertion){
		if(andList == null) return false;
		for(Assertion a : andList)
			if(a.getClass() == assertion.getClass()) return true;

		return false;
	}
	
	public void add(AllOf_Assertion assertion) {
		addAll(assertion.andList);
	}
	
	@Override
	public String toString() {
		return "And_Assertion [" + andList + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JsonElement toJSONSchema(WitnessVarManager rootVar) {
		// allOf containing false
		if(containsFalseBooleanAssertion){
			JsonArray array = new JsonArray();
			JsonObject obj = new JsonObject();
			obj.add("allOf", array);
			array.add(false);
			return obj;
		}


		if(duplicates) {  // if there are duplicates, then translate as "allOf"
			JsonArray array = new JsonArray();
			
			for(Assertion assertion : andList)
				array.add(assertion.toJSONSchema(rootVar));

			JsonObject obj = new JsonObject();
			obj.add("allOf", array);

			return obj;

		} else {  //if there are no duplicates, then translate as JSONObject
			JsonObject obj = new JsonObject();

			for (Assertion assertion : andList)
				if (assertion.getClass() != Boolean_Assertion.class) {
					obj = Utils_JSONSchema.mergeJsonObject(obj, assertion.toJSONSchema(rootVar).getAsJsonObject());
				}

			return obj;
		}
	}

	@Override
	public Assertion not() {
		AnyOf_Assertion or = new AnyOf_Assertion();
		
		for(Assertion assertion : andList) {
			Assertion not = assertion.not();
			if(not != null)
				or.add(not);
		}
		
		return or;
	}

	@Override
	public Assertion notElimination() {
		AllOf_Assertion and = new AllOf_Assertion();
		
		for(Assertion assertion : andList) {
			Assertion not = assertion.notElimination();
			if(not != null)
				and.add(not);
		}
		
		return and;
	}

	@Override
	public String toGrammarString() {
		StringBuilder str = new StringBuilder();
		
		Iterator<Assertion> it = andList.iterator();
			
		while(it.hasNext()) {
			String returnedValue = it.next().toGrammarString();
			if(returnedValue.isEmpty())
				continue;
			str.append(AlgebraStrings.COMMA)
					.append(returnedValue);
		}
		
		if(str.length() == 0) return "";
		if(andList.size() == 1) return str.delete(0, AlgebraStrings.COMMA.length()).toString();
		if(!duplicates) {
			str = str.delete(0, AlgebraStrings.COMMA.length()); //TODO: CHECK
			return str.append("\r\n}").insert(0, "{\r\n").toString();
		}

		return AlgebraStrings.ALLOF(str.substring(AlgebraStrings.COMMA.length()));
	}

	@Override
	public WitnessAssertion toWitnessAlgebra(WitnessVarManager varManager, Defs_Assertion env, WitnessPattReqManager pattReqManager) throws REException {
		WitnessAnd and = new WitnessAnd();

		for(Assertion a : andList)
			and.add(a.toWitnessAlgebra(varManager, env, pattReqManager));

		return and;
	}

	public List<Assertion> getAndList(){
		return andList;
	}
}
