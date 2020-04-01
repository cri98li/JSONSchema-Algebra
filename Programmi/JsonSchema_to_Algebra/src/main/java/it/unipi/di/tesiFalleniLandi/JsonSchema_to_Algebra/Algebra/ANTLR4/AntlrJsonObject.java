package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4;

import com.google.gson.JsonObject;

public class AntlrJsonObject extends AntlrValue{

	private JsonObject object;
	
	public AntlrJsonObject() {
		object = new JsonObject();
	}
	
	public void add(String key, Object value){
		if(value == null)	value = new JsonObject();
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
