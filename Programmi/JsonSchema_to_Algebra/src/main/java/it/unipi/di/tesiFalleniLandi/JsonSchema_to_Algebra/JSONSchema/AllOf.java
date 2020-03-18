package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.json.simple.JSONArray;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class AllOf implements JSONSchemaElement{
	private List<JSONSchema> allOf;
	
	public AllOf() {
		allOf = new LinkedList<>();
	}
	
	public AllOf(Object obj) {
		JSONArray array = (JSONArray) obj;
		allOf = new LinkedList<>();
		
		Iterator<?> it = array.iterator();
		
		while(it.hasNext()) {
			allOf.add(new JSONSchema(it.next()));
		}
	}
	
	public void addElement(JSONSchema schema) {
		if(allOf == null) allOf = new LinkedList<>();
		allOf.add(schema);
	}
	
	@Override
	public String toString() {
		return "AllOf [allOf=" + allOf + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONArray toJSON() {
		JSONArray array = new JSONArray();
		
		for(JSONSchema js : allOf)
			array.add(js.toJSON());
		
		return array;
	}

	@Override
	public String toGrammarString() {
		String str = "";
		
		Iterator<JSONSchema> it = allOf.iterator();
		if(it.hasNext()) 
			str += it.next().toGrammarString();
			
		while(it.hasNext())
			str += GrammarStringDefinitions.AND + it.next().toGrammarString();
			
		return String.format(GrammarStringDefinitions.ALLOF, str);
	}

	@Override
	public AllOf assertionSeparation() {
		AllOf obj = new AllOf();
		
		obj.allOf = new LinkedList<>();
		for(JSONSchema s : allOf)
			obj.allOf.add(s.assertionSeparation());
			
		
		return obj;
	}

	@Override
	public List<URI_JS> getRef() {
		List<URI_JS> returnList = new LinkedList<>();
		
		Iterator<JSONSchema> it = allOf.iterator();
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
