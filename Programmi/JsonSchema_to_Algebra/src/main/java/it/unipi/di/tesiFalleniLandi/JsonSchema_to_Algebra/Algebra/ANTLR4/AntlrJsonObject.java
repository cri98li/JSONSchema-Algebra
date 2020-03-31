package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4;

import org.json.simple.JSONObject;

public class AntlrJsonObject extends AntlrValue{

	private JSONObject object;
	
	public AntlrJsonObject() {
		object = new JSONObject();
	}
	
	@SuppressWarnings("unchecked")
	public void add(String key, Object value){
		if(value == null)	value = new JSONObject();
		AntlrValue tmp = (AntlrValue) value;
		this.object.put(key, tmp.getValue());
	}
	
	@Override
	public String getJSONSchemaKeyword() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object toJSONSchema() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object getValue() {
		return object;
	}

}
