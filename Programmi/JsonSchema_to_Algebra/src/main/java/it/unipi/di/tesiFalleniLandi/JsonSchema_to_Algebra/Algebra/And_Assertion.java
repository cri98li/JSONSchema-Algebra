package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.AntlrBoolean;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;

public class And_Assertion implements Assertion{
	private List<Assertion> andList;
	
	public And_Assertion() {
		this.andList = new LinkedList<>();
	}

	public void add(Assertion assertion) {
		andList.add(assertion);
	}
	
	@Override
	public String toString() {
		return "And_Assertion [" + andList + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "allOf";
	}

	@Override
	public JsonArray toJSONSchema() {
		JsonArray array = new JsonArray();
		
		for(Assertion assertion : andList) {
			JsonObject obj = new JsonObject();
			if(assertion.getClass() == AntlrBoolean.class) { //caso boolean as a schema
				array.add(assertion.toJSONSchema());
				continue;
			}
			Utils.putContent(obj, assertion.getJSONSchemaKeyword(), assertion.toJSONSchema());
			array.add(obj);
		}
		
		
		return array;
	}
	
	
}
