package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.json.simple.JSONObject;

public class AntlrJsonObject implements AntlrValue{

	private JSONObject object;
	
	public AntlrJsonObject() {
		object = new JSONObject();
	}
	
	@SuppressWarnings("unchecked")
	public void add(String key, Object value){
		if(object.containsKey(key)) throw new ParseCancellationException("Detected an invalid JSON object (duplicated keys)");
		if(value == null)	value = new JSONObject();
		AntlrValue tmp = (AntlrValue) value;
		this.object.put(key, tmp.getValue());
	}

	@Override
	public Object getValue() {
		return object;
	}

}
