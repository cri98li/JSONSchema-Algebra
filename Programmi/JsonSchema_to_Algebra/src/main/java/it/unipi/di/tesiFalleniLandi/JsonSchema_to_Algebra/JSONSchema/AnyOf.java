package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.json.simple.JSONArray;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class AnyOf implements JSONSchemaElement{
	private List<JSONSchema> anyOf;
	
	public AnyOf(Object obj) {
		JSONArray array = (JSONArray) obj;
		anyOf = new LinkedList<>();
		
		Iterator<?> it = array.iterator();
		
		while(it.hasNext()) {
			anyOf.add(new JSONSchema(it.next()));
		}
	}
	
	
	public AnyOf() {
		// TODO Auto-generated constructor stub
	}
	
	public void addElement(JSONSchema schema) {
		if(anyOf == null) anyOf = new LinkedList<>();
		anyOf.add(schema);
	}


	@Override
	public String toString() {
		return "AnyOf [anyOf=" + anyOf + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONArray toJSON() {
		JSONArray array = new JSONArray();
		
		for(JSONSchema js : anyOf)
			array.add(js.toJSON());
		
		return array;
	}

	public String toGrammarString() {
		String str = "";
		
		Iterator<JSONSchema> it = anyOf.iterator();
			
		while(it.hasNext()) {
			String returnedValue = it.next().toGrammarString();
			if(returnedValue.isEmpty())
				continue;
			str += GrammarStringDefinitions.COMMA + returnedValue;
		}
		
		if(str.isEmpty()) return "";
		if(anyOf.size() == 1) return str.substring(GrammarStringDefinitions.COMMA.length());
		return String.format(GrammarStringDefinitions.ANYOF, str.substring(GrammarStringDefinitions.COMMA.length()));
	}
	
	@Override
	public int numberOfGeneratedAssertions() {
		return anyOf.size();
	}

	@Override
	public AnyOf assertionSeparation() {
		AnyOf obj = new AnyOf();
		
		obj.anyOf = new LinkedList<>();
		for(JSONSchema s : anyOf)
			obj.anyOf.add(s.assertionSeparation());
			
		
		return obj;
	}

	@Override
	public List<URI_JS> getRef() {
		List<URI_JS> returnList = new LinkedList<>();
		
		Iterator<JSONSchema> it = anyOf.iterator();
		while(it.hasNext())
			returnList.addAll(it.next().getRef());
		
		return returnList;
	}

	@Override
	public JSONSchema searchDef(Iterator<String> URIIterator) {
		return null;
	}


	@Override
	public List<Entry<String,Defs>> collectDef() {
		return new LinkedList<>();
	}
	
	
}
