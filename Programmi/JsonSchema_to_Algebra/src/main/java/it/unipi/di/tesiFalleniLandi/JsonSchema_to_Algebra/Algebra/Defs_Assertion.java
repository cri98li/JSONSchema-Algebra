package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.Utils;

public class Defs_Assertion implements Assertion{

	private HashMap<String, Assertion> defs;
	
	public Defs_Assertion() {
		defs = new HashMap<String, Assertion>();
	}
	
	public void add(String key, Assertion value) {
		defs.put(key, value);
	}
	
	@Override
	public String getJSONSchemaKeyword() {
		return "$defs";
	}

	@Override
	public Object toJSONSchema() {
		JSONObject obj = new JSONObject();
		
		Set<Entry<String, Assertion>> entrySet = defs.entrySet();
		for(Entry<String,Assertion> entry : entrySet)
			Utils.putContent(obj, entry.getKey(), entry.getValue().toJSONSchema());
		
		
		return obj;
	}

}
