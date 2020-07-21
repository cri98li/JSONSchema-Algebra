package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import patterns.Pattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.*;
import patterns.REException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Required_Assertion implements Assertion{
	private List<String> reqList;
	
	public Required_Assertion() {
		reqList = new LinkedList<>();
	}
	
	public Required_Assertion(List<String> list) {
		this.reqList = list;
	}
	
	public void add(String str) {
		reqList.add(str);
	}

	@Override
	public String toString() {
		return "Required_Assertion [" + reqList + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JsonElement toJSONSchema() {
		JsonArray array = new JsonArray();
		JsonObject obj = new JsonObject();

		for(String str : reqList)
			array.add(str);

		obj.add("required", array);

		return obj;
	}

	@Override
	public Assertion not() {
		AllOf_Assertion and = new AllOf_Assertion();
		AnyOf_Assertion or = new AnyOf_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add(GrammarStringDefinitions.TYPE_OBJECT);
		and.add(type);

		for(String key : reqList){
			Properties_Assertion props = new Properties_Assertion();
			props.addPatternProperties(ComplexPattern.createFromName(key), new Boolean_Assertion(false));
			or.add(props);
		}
		and.add(or);

		return and;
	}

	@Override
	public Assertion notElimination() {
		return new Required_Assertion(reqList);
	}
	
	@Override
	public String toGrammarString() {
		String str = "";
		
		if(reqList.isEmpty()) return str;
		
		Iterator<String> it = reqList.iterator();
		
		if(it.hasNext())
			str += "\""+it.next()+"\"";
		
		while(it.hasNext()) {
			str += GrammarStringDefinitions.COMMA +"\""+ it.next()+"\"";
		}
		
		return String.format(GrammarStringDefinitions.REQUIRED, str);
	}

	@Override
	public WitnessAssertion toWitnessAlgebra() throws REException {
		WitnessOr or = new WitnessOr();
		WitnessAnd and = new WitnessAnd();
		Type_Assertion tmp = new Type_Assertion();
		tmp.add(GrammarStringDefinitions.TYPE_OBJECT);
		WitnessAssertion type = tmp.not().toWitnessAlgebra();
		or.add(type);

		for(String str : reqList) {
			ComplexPattern p = ComplexPattern.createFromName(str);
			WitnessPattReq pattReq = WitnessPattReq.build(p, new WitnessBoolean(true));
			and.add(pattReq);
		}

		or.add(and);

		return or;
	}
}
