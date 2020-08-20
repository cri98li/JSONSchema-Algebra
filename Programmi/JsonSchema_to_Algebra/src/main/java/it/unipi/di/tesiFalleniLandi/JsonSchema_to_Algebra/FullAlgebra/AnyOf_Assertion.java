package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessOr;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AnyOf_Assertion implements Assertion{
	private List<Assertion> orList;

	private static Logger logger = LogManager.getLogger(AnyOf_Assertion.class);
	
	public AnyOf_Assertion() {
		logger.trace("Creating an empty AnyOf_Assertion");
		this.orList = new LinkedList<>();
	}
	
	public void add(Assertion assertion) {
		if(assertion == null) return;
		logger.trace("Adding {} to {}", assertion, this);
		orList.add(assertion);
	}
	
	public void add(AnyOf_Assertion assertion) {
		addAll(assertion.orList);
	}
	
	public void addAll(List<Assertion> list) {
		logger.trace("Adding {} to {}", list, this);
		orList.addAll(list);
	}

	@Override
	public String toString() {
		return "Or_Assertion [" + orList + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JsonObject toJSONSchema() {
		JsonObject obj = new JsonObject();
		JsonArray array = new JsonArray();

		for(Assertion assertion : orList) {
			array.add(assertion.toJSONSchema());
		}

		obj.add("anyOf", array);

		return obj;
	}

	@Override
	public Assertion not() {
		AllOf_Assertion and = new AllOf_Assertion();
		
		for(Assertion assertion : orList) {
			Assertion not = assertion.not();
			if(not != null)
				and.add(not);
		}
		
		return and;
	}
	
	public Assertion notElimination() {
		AnyOf_Assertion or = new AnyOf_Assertion();
		
		for(Assertion assertion : orList) {
			Assertion not = assertion.notElimination();
			if(not != null)
				or.add(not);
		}
		
		return or;
	}
	
	
	@Override
	public String toGrammarString() {
		String str = "";
		
		Iterator<Assertion> it = orList.iterator();
			
		while(it.hasNext()) {
			Assertion next = it.next();
			String returnedValue = next.toGrammarString();
			if(returnedValue.isEmpty())
				continue;
			str += FullAlgebraString.COMMA + returnedValue;
		}
		
		if(str.isEmpty()) return "";
		if(orList.size() == 1) return str.substring(FullAlgebraString.COMMA.length());
		return FullAlgebraString.ANYOF(str.substring(FullAlgebraString.COMMA.length()));
	}

	public WitnessAssertion toWitnessAlgebra() throws REException {
		WitnessOr or = new WitnessOr();
		for(Assertion a : orList)
			or.add(a.toWitnessAlgebra());

		return or;
	}

	public List<Assertion> getOrList() {
		return orList;
	}
}