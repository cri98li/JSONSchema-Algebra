package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.ComplexPattern.ComplexPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Required_Assertion implements Assertion{
	private List<String> reqList;

	private static Logger logger = LogManager.getLogger(Required_Assertion.class);
	
	public Required_Assertion() {
		logger.trace("Creating an empty Required_Assertion");
		reqList = new LinkedList<>();
	}
	
	public Required_Assertion(List<String> list) {
		this.reqList = list;
		logger.trace("Created a new Required_Assertion: {}", this);
	}
	
	public void add(String str) {
		logger.trace("Adding {} to {}", str, this);
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
		type.add(FullAlgebraString.TYPE_OBJECT);
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
		StringBuilder str = new StringBuilder();
		
		if(reqList.isEmpty()) return "";
		
		Iterator<String> it = reqList.iterator();
		
		if(it.hasNext())
			str.append("\"")
					.append(it.next())
					.append("\"");
		
		while(it.hasNext()) {
			str.append(FullAlgebraString.COMMA)
					.append("\"")
					.append(it.next())
					.append("\"");
		}
		
		return FullAlgebraString.REQUIRED(str.toString());
	}

	@Override
	public WitnessAssertion toWitnessAlgebra() throws REException {
		WitnessOr or = new WitnessOr();
		WitnessAnd and = new WitnessAnd();
		Type_Assertion tmp = new Type_Assertion();
		tmp.add(FullAlgebraString.TYPE_OBJECT);
		WitnessAssertion type = tmp.not().toWitnessAlgebra();


		for(String str : reqList) {
			ComplexPattern p = ComplexPattern.createFromName(str);
			WitnessPattReq pattReq = WitnessPattReq.build(p, new WitnessBoolean(true));
				and.add(pattReq);
		}

		or.add(type);
		or.add(and);


		return or;
	}
}
