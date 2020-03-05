package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import org.json.simple.JSONObject;

public class IfThenElse implements JSONSchemaElement {
	private JSONSchema ifStatement, thenStatement, elseStatement;
	
	private boolean inizialized = false;
	
	public IfThenElse(){ }
	
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

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		
		if(ifStatement != null) obj.put("if", ifStatement.toJSON());
		
		if(thenStatement != null) obj.put("then", thenStatement.toJSON());
		
		if(elseStatement != null) obj.put("else", elseStatement.toJSON());
		
		return obj;
	}

	@Override
	public String toGrammarString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IfThenElse assertionSeparation() {
		IfThenElse obj = new IfThenElse();
		
		if(ifStatement != null) obj.ifStatement = ifStatement.assertionSeparation();
		if(thenStatement != null) obj.thenStatement = thenStatement.assertionSeparation();
		if(elseStatement != null) obj.elseStatement = elseStatement.assertionSeparation();
		
		
		return obj;
	}
}
