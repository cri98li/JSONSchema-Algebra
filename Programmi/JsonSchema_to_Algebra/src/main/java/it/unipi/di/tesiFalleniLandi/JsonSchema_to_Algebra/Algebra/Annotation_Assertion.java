package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.HashMap;

import com.google.gson.JsonObject;

public class Annotation_Assertion implements Assertion{
	private HashMap<String, String> annotations;
	
	public Annotation_Assertion() {
		annotations = new HashMap<>();
	}
	
	public void add(String key, String value) {
		annotations.put(key, value);
	}
	
	@Override
	public String getJSONSchemaKeyword() {
		return "unknow";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JsonObject toJSONSchema() {
		JsonObject obj = new JsonObject();
		
		obj.putAll(annotations);
		
		return obj;
	}

}
