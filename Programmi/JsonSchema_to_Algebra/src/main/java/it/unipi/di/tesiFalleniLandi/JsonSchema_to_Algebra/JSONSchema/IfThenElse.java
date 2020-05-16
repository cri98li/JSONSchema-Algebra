package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class IfThenElse implements JSONSchemaElement {
	private JSONSchema ifStatement, thenStatement, elseStatement;
	
	public IfThenElse(){ }
	
	public void setIf(Object obj) {
		ifStatement = new JSONSchema(obj);
	}
	
	public void setThen(Object obj) {
		thenStatement = new JSONSchema(obj);
	}
	
	public void setElse(Object obj) {
		elseStatement = new JSONSchema(obj);
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
		if(ifStatement != null) { 
			if_str = ifStatement.toGrammarString();
			then_str = thenStatement.toGrammarString();
		}
		if(elseStatement != null)
			else_str = elseStatement.toGrammarString();
		else
			return String.format(GrammarStringDefinitions.IF_THEN, if_str, then_str);
		
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
		
		if(ifStatement != null) returnList.addAll(Utils_JSONSchema.addPathElement("if", ifStatement.collectDef()));
		if(thenStatement != null) returnList.addAll(Utils_JSONSchema.addPathElement("then", thenStatement.collectDef()));
		if(elseStatement != null) returnList.addAll(Utils_JSONSchema.addPathElement("else", elseStatement.collectDef()));
		
		return returnList;
	}

	@Override
	public int numberOfAssertions() {
		return 1;
	}
	
	public IfThenElse clone() {
		IfThenElse clone = new IfThenElse();
		
		if(ifStatement != null) clone.ifStatement = ifStatement.clone();
		if(thenStatement != null) clone.thenStatement = thenStatement.clone();
		if(elseStatement != null) clone.elseStatement = elseStatement.clone();
		
		return clone;
	}
}
