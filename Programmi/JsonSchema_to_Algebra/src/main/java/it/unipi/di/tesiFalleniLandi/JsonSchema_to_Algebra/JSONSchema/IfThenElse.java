package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

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
		String if_str = "", then_str = "", else_str = "";
		if(ifStatement != null) 
			if_str = ifStatement.toGrammarString();
		if(thenStatement != null)
			then_str = thenStatement.toGrammarString();
		if(elseStatement != null)
			else_str = elseStatement.toGrammarString();
		
		return String.format(GrammarStringDefinitions.IF_THEN_ELSE, if_str, then_str, else_str);
	}

	@Override
	public IfThenElse assertionSeparation() {
		IfThenElse obj = new IfThenElse();
		
		if(ifStatement != null) obj.ifStatement = ifStatement.assertionSeparation();
		if(thenStatement != null) obj.thenStatement = thenStatement.assertionSeparation();
		if(elseStatement != null) obj.elseStatement = elseStatement.assertionSeparation();
		
		
		return obj;
	}

	@Override
	public List<URI_JS> getRef() {
		List<URI_JS> returnList = new LinkedList<>();
		
		if(ifStatement != null) returnList.addAll(ifStatement.getRef());
		if(thenStatement != null) returnList.addAll(thenStatement.getRef());
		if(elseStatement != null) returnList.addAll(elseStatement.getRef());
		
		return returnList;
	}

	@Override
	public JSONSchema searchDef(Iterator<String> URIIterator) {
		if(URIIterator.hasNext())
			switch(URIIterator.next()) {
			case "if":
				URIIterator.remove();
				return ifStatement.searchDef(URIIterator);
			case "then":
				URIIterator.remove();
				return thenStatement.searchDef(URIIterator);
			case "else":
				URIIterator.remove();
				return elseStatement.searchDef(URIIterator);
			}
		
		return null;
	}

	@Override
	public List<Entry<String,Defs>> collectDef() {
		List<Entry<String,Defs>> returnList = new LinkedList<>();
		
		if(ifStatement != null) returnList.addAll(Utils.addPathElement("if", ifStatement.collectDef()));
		if(thenStatement != null) returnList.addAll(Utils.addPathElement("then", thenStatement.collectDef()));
		if(elseStatement != null) returnList.addAll(Utils.addPathElement("else", elseStatement.collectDef()));
		
		return returnList;
	}
}
