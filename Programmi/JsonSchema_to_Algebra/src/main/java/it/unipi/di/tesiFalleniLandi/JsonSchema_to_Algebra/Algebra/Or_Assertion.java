package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.Utils;

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
	public Object toJSONSchema() {
		JSONArray array = new JSONArray();
		
		for(Assertion assertion : orList) {
			JSONObject obj = new JSONObject();
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
