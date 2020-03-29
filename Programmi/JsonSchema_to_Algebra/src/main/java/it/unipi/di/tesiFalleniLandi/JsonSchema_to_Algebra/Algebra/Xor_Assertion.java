package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.Utils;

public class Xor_Assertion implements Assertion{
	private List<Assertion> xorList;
	
	public Xor_Assertion() {
		this.xorList = new LinkedList<>();
	}
	
	public void add(Assertion assertion) {
		xorList.add(assertion);
	}

	@Override
	public String toString() {
		return "Xor_Assertion [" + xorList + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "oneOf";
	}

	@Override
	public Object toJSONSchema() {
		JSONArray array = new JSONArray();
		
		for(Assertion assertion : xorList) {
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
