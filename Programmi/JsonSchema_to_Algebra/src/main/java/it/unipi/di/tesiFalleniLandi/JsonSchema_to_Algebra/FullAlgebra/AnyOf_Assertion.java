<<<<<<< HEAD:Programmi/JsonSchema_to_Algebra/src/main/java/it/unipi/di/tesiFalleniLandi/JsonSchema_to_Algebra/FullAlgebra/AnyOf_Assertion.java
package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAnd;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessOr;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AnyOf_Assertion implements Assertion{
	private List<Assertion> orList;
	
	public AnyOf_Assertion() {
		this.orList = new LinkedList<>();
	}
	
	public void add(Assertion assertion) {
		orList.add(assertion);
	}
	
	public void add(AnyOf_Assertion assertion) {
		addAll(assertion.orList);
	}
	
	public void addAll(List<Assertion> list) {
		orList.addAll(list);
	}

	@Override
	public String toString() {
		return "Or_Assertion [" + orList + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONSchema() {
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();

		for(Assertion assertion : orList) {
			array.add(assertion.toJSONSchema());
		}

		obj.put("anyOf", array);

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
			String returnedValue = it.next().toGrammarString();
			if(returnedValue.isEmpty())
				continue;
			str += GrammarStringDefinitions.COMMA + returnedValue;
		}
		
		if(str.isEmpty()) return "";
		if(orList.size() == 1) return str.substring(GrammarStringDefinitions.COMMA.length());
		return String.format(GrammarStringDefinitions.ANYOF, str.substring(GrammarStringDefinitions.COMMA.length()));
	}

	public WitnessOr toWitnessAlgebra() {
		WitnessOr or = new WitnessOr();
		for(Assertion a : orList)
			or.add(a.toWitnessAlgebra());

		return or;
	}
}
=======
package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAnd;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessOr;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AnyOf_Assertion implements Assertion{
	private List<Assertion> orList;
	
	public AnyOf_Assertion() {
		this.orList = new LinkedList<>();
	}
	
	public void add(Assertion assertion) {
		orList.add(assertion);
	}
	
	public void add(AnyOf_Assertion assertion) {
		addAll(assertion.orList);
	}
	
	public void addAll(List<Assertion> list) {
		orList.addAll(list);
	}

	@Override
	public String toString() {
		return "Or_Assertion [" + orList + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONSchema() {
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();

		for(Assertion assertion : orList) {
			array.add(assertion.toJSONSchema());
		}

		obj.put("anyOf", array);

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
			String returnedValue = it.next().toGrammarString();
			if(returnedValue.isEmpty())
				continue;
			str += GrammarStringDefinitions.COMMA + returnedValue;
		}
		
		if(str.isEmpty()) return "";
		if(orList.size() == 1) return str.substring(GrammarStringDefinitions.COMMA.length());
		return String.format(GrammarStringDefinitions.ANYOF, str.substring(GrammarStringDefinitions.COMMA.length()));
	}

	public WitnessOr toWitnessAlgebra() {
		WitnessOr or = new WitnessOr();
		for(Assertion a : orList)
			or.add(a.toWitnessAlgebra());

		return or;
	}
}
>>>>>>> 9be72e1ac293591d2d50b1d0779180c7b28dedeb:Programmi/JsonSchema_to_Algebra/src/main/java/it/unipi/di/tesiFalleniLandi/JsonSchema_to_Algebra/FullAlgebra/Or_Assertion.java
