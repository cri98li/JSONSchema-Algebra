package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.HashMap;
import org.json.simple.JSONObject;

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
	public JSONObject toJSONSchema() {
		JSONObject obj = new JSONObject();
		
		obj.putAll(annotations);
		
		return obj;
	}

	@Override
	public Assertion not() {
		return new Not_Assertion(this);
	}

	@Override
	public Assertion notElimination() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toGrammarString() {
		throw new UnsupportedOperationException();
	}

}
