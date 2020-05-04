<<<<<<< HEAD:Programmi/JsonSchema_to_Algebra/src/main/java/it/unipi/di/tesiFalleniLandi/JsonSchema_to_Algebra/FullAlgebra/AllOf_Assertion.java
package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAnd;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAssertion;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AllOf_Assertion implements Assertion{
	private List<Assertion> andList;
	private boolean duplicates, containsFalseBooleanAssertion;
	
	public AllOf_Assertion() {
		this.andList = new LinkedList<>();
		duplicates = false;
		containsFalseBooleanAssertion = false;
	}

	public void addAll(List<Assertion> list) {
		for(Assertion assertion : list) {
			duplicates |= contains(assertion);
			containsFalseBooleanAssertion |= (Boolean_Assertion.class == assertion.getClass());
		}
		andList.addAll(list);
	}
	
	public void add(Assertion assertion) {
		duplicates |= contains(assertion);
		containsFalseBooleanAssertion |= (Boolean_Assertion.class == assertion.getClass());
		andList.add(assertion);
	}

	private boolean contains(Assertion assertion){
		if(andList == null) return false;
		for(Assertion a : andList)
			if(a.getClass() == assertion.getClass()) return true; //qui potremmo chiamare il metodo per semplificare i bet

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
	public Object toJSONSchema() {
		if(containsFalseBooleanAssertion){
			JSONArray array = new JSONArray();
			JSONObject obj = new JSONObject();
			obj.put("allOf", array);
			array.add(false);
			return obj;
		}

		if(duplicates) {
			JSONArray array = new JSONArray();
			
			for(Assertion assertion : andList)
				array.add(assertion.toJSONSchema());

			JSONObject obj = new JSONObject();
			obj.put("allOf", array);

			return obj;

		} else {
			JSONObject obj = new JSONObject();

			for (Assertion assertion : andList)
				if (assertion.getClass() != Boolean_Assertion.class) {
					obj.putAll((JSONObject) assertion.toJSONSchema());
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
		String str = "";
		
		Iterator<Assertion> it = andList.iterator();
			
		while(it.hasNext()) {
			String returnedValue = it.next().toGrammarString();
			if(returnedValue.isEmpty())
				continue;
			str += GrammarStringDefinitions.COMMA + returnedValue;
		}
		
		if(str.isEmpty()) return "";
		if(andList.size() == 1) return str.substring(GrammarStringDefinitions.COMMA.length());
		if(!duplicates) return "{\r\n" + str.substring(GrammarStringDefinitions.COMMA.length()) +"\r\n}";
		return String.format(GrammarStringDefinitions.ALLOF, str.substring(GrammarStringDefinitions.COMMA.length()));
	}

	@Override
	public WitnessAnd toWitnessAlgebra() {
		WitnessAnd and = new WitnessAnd();
		for(Assertion a : andList)
			and.add(a.toWitnessAlgebra());

		return and;
	}

	public List<Assertion> getAndList(){
		return andList;
	}
}
=======
package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAnd;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAssertion;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AllOf_Assertion implements Assertion{
	private List<Assertion> andList;
	private boolean duplicates, containsFalseBooleanAssertion;
	
	public AllOf_Assertion() {
		this.andList = new LinkedList<>();
		duplicates = false;
		containsFalseBooleanAssertion = false;
	}

	public void addAll(List<Assertion> list) {
		for(Assertion assertion : list) {
			duplicates |= contains(assertion);
			containsFalseBooleanAssertion |= (Boolean_Assertion.class == assertion.getClass());
		}
		andList.addAll(list);
	}
	
	public void add(Assertion assertion) {
		duplicates |= contains(assertion);
		containsFalseBooleanAssertion |= (Boolean_Assertion.class == assertion.getClass());
		andList.add(assertion);
	}

	private boolean contains(Assertion assertion){
		if(andList == null) return false;
		for(Assertion a : andList)
			if(a.getClass() == assertion.getClass()) return true; //qui potremmo chiamare il metodo per semplificare i bet

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
	public Object toJSONSchema() {
		if(containsFalseBooleanAssertion){
			JSONArray array = new JSONArray();
			JSONObject obj = new JSONObject();
			obj.put("allOf", array);
			array.add(false);
			return obj;
		}

		if(duplicates) {
			JSONArray array = new JSONArray();
			
			for(Assertion assertion : andList)
				array.add(assertion.toJSONSchema());

			JSONObject obj = new JSONObject();
			obj.put("allOf", array);

			return obj;

		} else {
			JSONObject obj = new JSONObject();

			for (Assertion assertion : andList)
				if (assertion.getClass() != Boolean_Assertion.class) {
					obj.putAll((JSONObject) assertion.toJSONSchema());
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
		String str = "";
		
		Iterator<Assertion> it = andList.iterator();
			
		while(it.hasNext()) {
			String returnedValue = it.next().toGrammarString();
			if(returnedValue.isEmpty())
				continue;
			str += GrammarStringDefinitions.COMMA + returnedValue;
		}
		
		if(str.isEmpty()) return "";
		if(andList.size() == 1) return str.substring(GrammarStringDefinitions.COMMA.length());
		if(!duplicates) return "{\r\n" + str.substring(GrammarStringDefinitions.COMMA.length()) +"\r\n}";
		return String.format(GrammarStringDefinitions.ALLOF, str.substring(GrammarStringDefinitions.COMMA.length()));
	}

	@Override
	public WitnessAnd toWitnessAlgebra() {
		WitnessAnd and = new WitnessAnd();
		for(Assertion a : andList)
			and.add(a.toWitnessAlgebra());

		return and;
	}

	public List<Assertion> getAndList(){
		return andList;
	}
}
>>>>>>> 9be72e1ac293591d2d50b1d0779180c7b28dedeb:Programmi/JsonSchema_to_Algebra/src/main/java/it/unipi/di/tesiFalleniLandi/JsonSchema_to_Algebra/FullAlgebra/And_Assertion.java
