package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.UnsenseAssertion;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class Required implements JSONSchemaElement{
	private List<String> required;

	public Required(Object obj) {
		JSONArray array;
		try {
			array = (JSONArray) obj;
		}catch (ClassCastException ex) {
			//per consistenza con draft-4
			if(obj.getClass() == String.class) {
				required = new LinkedList<>();
				required.add((String) obj);
				return;
			}

			throw new UnsenseAssertion(ex.getMessage());
		}
		required = new LinkedList<>();

		Iterator<?> it = array.iterator();

		while(it.hasNext())
			required.add((String) it.next());
	}

	public Required() {
		required = new LinkedList<>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		
		for(String s : required)
			array.add(s);

		obj.put("required", array);

		return obj;
	}

	@Override
	public String toGrammarString() {
		String str = "";
		
		if(required.isEmpty()) return str;
		
		Iterator<String> it = required.iterator();
		
		if(it.hasNext())
			str += "\""+it.next()+"\"";
		
		while(it.hasNext()) {
			str += GrammarStringDefinitions.COMMA +"\""+ it.next()+"\"";
		}
		
		return String.format(GrammarStringDefinitions.REQUIRED, str);
	}
	
	@Override
	public int numberOfAssertions() {
		return (required.size() == 0)? 0: 1;
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
	
	@Override
	public Required clone() {
		Required newReq = new Required();
		newReq.required.addAll(required);
		return newReq;
	}
}
