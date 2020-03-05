package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;

public class Required implements JSONSchemaElement{
	private List<String> required;

	public Required(Object obj) {
		JSONArray array = (JSONArray) obj;
		required = new LinkedList<>();;
		
		Iterator<?> it = array.iterator();
		
		while(it.hasNext())
			required.add((String) it.next());
	}
	
	public Required() {
		// TODO Auto-generated constructor stub
	}

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Required assertionSeparation() {
		Required obj = new Required();
		
		obj.required = new LinkedList<>(this.required);
		
		return obj;
	}
	
	
}
