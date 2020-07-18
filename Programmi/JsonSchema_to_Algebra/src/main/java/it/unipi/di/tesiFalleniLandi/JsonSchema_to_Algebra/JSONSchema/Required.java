package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.UnsenseAssertion;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class Required implements JSONSchemaElement{
	private List<String> required;

	public Required(JsonElement obj) {
		if(obj.isJsonArray()){
			JsonArray array = obj.getAsJsonArray();
			required = new LinkedList<>();

			Iterator<JsonElement> it = array.iterator();

			while(it.hasNext())
				required.add(it.next().getAsString());

		} else {
			try {
				//per consistenza con draft-4
				required = new LinkedList<>();
				required.add(obj.getAsString());
			}catch (ClassCastException ex) {
				throw new UnsenseAssertion(ex.getMessage());
			}
		}
	}

	public Required() {
		required = new LinkedList<>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public JsonElement toJSON() {
		JsonObject obj = new JsonObject();
		JsonArray array = new JsonArray();
		
		for(String s : required)
			array.add(s);

		obj.add("required", array);

		return obj;
	}

	@Override
	public String toGrammarString() {
		String str = "";
		String tmp = "";
		String decodedKey = "";
		
		if(required.isEmpty()) return str;
		
		Iterator<String> it = required.iterator();
		
		if(it.hasNext()) {
			tmp = it.next();
			decodedKey = new JsonPrimitive(tmp).toString();
			str += "\"" + decodedKey.substring(1, decodedKey.length()-1) + "\"";
		}
		
		while(it.hasNext()) {
			tmp = it.next();
			decodedKey = new JsonPrimitive(tmp).toString();
			str += GrammarStringDefinitions.COMMA +"\""+ decodedKey.substring(1,decodedKey.length()-1)+"\"";
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
