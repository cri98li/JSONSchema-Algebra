package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

public class If_Then_Else implements JSONSchemaElement {
	private JSONSchema ifStatement, thenStatement, elseStatement;
	
	private boolean inizialized = false;
	
	public If_Then_Else(){ }
	
	/*public If_Then_Else(JSONObject obj){
		ifStatement = new JSONSchema(obj);
	}*/
	
	public void setIf(Object obj) {
		inizialized = true;
		ifStatement = new JSONSchema(obj);
	}
	
	public void setThen(Object obj) {
		inizialized = true;
		thenStatement = new JSONSchema(obj);
	}
	
	public void setElse(Object obj) {
		inizialized = true;
		elseStatement = new JSONSchema(obj);
	}
	
	public boolean isInitialized() {
		return inizialized;
	}

	@Override
	public String toString() {
		return "If_Then_Else [ifStatement=" + ifStatement + ", thenStatement=" + thenStatement + ", elseStatement="
				+ elseStatement + "]";
	}

	@Override
	public String toJSONString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toGrammarString() {
		// TODO Auto-generated method stub
		return null;
	}
}
