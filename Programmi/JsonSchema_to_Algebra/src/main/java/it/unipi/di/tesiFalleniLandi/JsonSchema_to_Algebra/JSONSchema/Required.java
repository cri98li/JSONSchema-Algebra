package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.json.simple.JSONArray;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class Required implements JSONSchemaElement{
	private List<String> required;

	public Required(Object obj) {
		JSONArray array = (JSONArray) obj;
		required = new LinkedList<>();;
		
		Iterator<?> it = array.iterator();
		
		while(it.hasNext())
			required.add((String) it.next());
	}
	
	public Required() {	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONArray toJSON() {
		JSONArray array = new JSONArray();
		
		for(String s : required)
			array.add(s);
		
		return array;
	}

	@Override
	public String toGrammarString() {
		String str = "";
		
		Iterator<String> it = required.iterator();
		
		if(it.hasNext())
			str += it.next();
		
		while(it.hasNext()) {
			str += GrammarStringDefinitions.AND + it.next();
		}
		
		return String.format(GrammarStringDefinitions.REQUIRED, str);
	}

	@Override
	public Required assertionSeparation() {
		Required obj = new Required();
		
		obj.required = new LinkedList<>(this.required);
		
		return obj;
	}

	@Override
	public List<URI_JS> getRef() {
		return new LinkedList<>();
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
