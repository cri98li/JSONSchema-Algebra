package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.AntlrBoolean;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;

public class Or_Assertion implements Assertion{
	private List<Assertion> orList;
	
	public Or_Assertion() {
		this.orList = new LinkedList<>();
	}
	
	public void add(Assertion assertion) {
		orList.add(assertion);
	}

	@Override
	public String toString() {
		return "Or_Assertion [" + orList + "]";
	}
	
	@Override
	public String getJSONSchemaKeyword() {
		return "anyOf";
	}

	@Override
	public JsonArray toJSONSchema() {
		JsonArray array = new JsonArray();
		
		for(Assertion assertion : orList) {
			JsonObject obj = new JsonObject();
			if(assertion.getClass() == AntlrBoolean.class) {
				array.add(assertion.toJSONSchema());
				continue;
			}
			Utils.putContent(obj, assertion.getJSONSchemaKeyword(), assertion.toJSONSchema());
			array.add(obj);
		}
		
		
		return array;
	}
}
