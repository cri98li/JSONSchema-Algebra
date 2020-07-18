package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.*;
import java.util.Map.Entry;

public class Defs implements JSONSchemaElement {
	private HashMap<String, JSONSchema> schemaDefs;
	private JSONSchema rootDef;
	
	public Defs(JsonElement obj) {
		JsonObject jsonObject;
		try {
			jsonObject = obj.getAsJsonObject();
		}catch(ClassCastException ex) {
			throw new ParseCancellationException("Error: $defs must be valid JSON Object!");
		}
		
		schemaDefs = new HashMap<>();
		
		for(Entry<String, JsonElement> entry : jsonObject.entrySet()) {
			try{
				schemaDefs.putIfAbsent((String) entry.getKey(), new JSONSchema(entry.getValue()));
			}catch(ClassCastException ex) {
				try{
					entry.getValue().getAsString();
				}catch (ClassCastException exx) {
					throw new ParseCancellationException("Error: no valid Defs Object!\r\n" + entry.toString());
				}
				throw new ParseCancellationException("Error: no valid Defs Object!\r\n"+ex.getLocalizedMessage());
			}
		}
	}
	
	public JSONSchema containsDef(String key) {
		return schemaDefs.get(key);
	}
	
	public void addDef(String key, JSONSchema element) {
		schemaDefs.put(key, element);
	}
	
	public void setRootDef(JSONSchema root) {
		rootDef = root;
	}

	public void addDef(Defs defs) {
		schemaDefs.putAll(defs.schemaDefs);
	}
	
	public Defs() {	
		schemaDefs = new HashMap<>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public JsonElement toJSON() {
		JsonObject obj = new JsonObject();
		JsonObject def = new JsonObject();
		
		Set<Entry<String, JSONSchema>> entrySet = schemaDefs.entrySet();
		
		for(Entry<String, JSONSchema> entry : entrySet)
			def.add(entry.getKey(), entry.getValue().toJSON());
		obj.add("$defs", def);

		if(rootDef != null)
				obj.add(Utils.ROOTDEF_FOR_JSONSCHEMA, rootDef.toJSON());

		return obj;
	}

	@Override
	public Defs assertionSeparation() {
		Defs obj = new Defs();
		obj.schemaDefs = new HashMap<>();
		
		Set<Entry<String, JSONSchema>> entrySet = schemaDefs.entrySet();
		
		for(Entry<String, JSONSchema> entry : entrySet)
			obj.schemaDefs.put(entry.getKey(), entry.getValue().assertionSeparation());
		
		if(rootDef != null)
			obj.setRootDef(this.rootDef.assertionSeparation());
		
		return obj;
	}

	@Override
	public String toGrammarString() {
		String defs = "";
		
		if(rootDef != null && rootDef.numberOfAssertions() > 0)
			defs = GrammarStringDefinitions.COMMA + String.format(GrammarStringDefinitions.ROOTDEF, "\"" + GrammarStringDefinitions.ROOTDEF_DEFAULTNAME + "\"", rootDef.toGrammarString());
		else
			defs = GrammarStringDefinitions.COMMA + String.format(GrammarStringDefinitions.ROOTDEF, "\"" + GrammarStringDefinitions.ROOTDEF_DEFAULTNAME + "\"" , "{true}");//TODO: pensare a qualcosa di più elegante
		
		Set<Entry<String, JSONSchema>> entrySet = schemaDefs.entrySet();

		for(Entry<String, JSONSchema> entry : entrySet)
			if(entry.getValue().numberOfAssertions() > 0) {
				String decodedKey = new JsonPrimitive(entry.getKey()).toString();
				defs += GrammarStringDefinitions.COMMA + String.format(GrammarStringDefinitions.DEFS, decodedKey.substring(1, decodedKey.length()-1), entry.getValue().toGrammarString());
			}
		
		if(defs.isEmpty()) return ""; //definizione non ancora supportata
		return defs.substring(GrammarStringDefinitions.COMMA.length());
	}
	
	@Override
	public int numberOfAssertions() {
		return schemaDefs.size() + ((rootDef == null) ? 0 : 1);
	}

	@Override
	public List<URI_JS> getRef() {
		List<URI_JS> returnList = new LinkedList<>();
		
		Set<Entry<String, JSONSchema>> entrySet = schemaDefs.entrySet();
		
		for(Entry<String, JSONSchema> entry : entrySet)
			returnList.addAll(entry.getValue().getRef());
		
		if(rootDef != null)
			returnList.addAll(rootDef.getRef());
		
		return returnList;
	}

	@Override
	public JSONSchema searchDef(Iterator<String> URIIterator) {
		return null;
	}

	@Override
	public List<Entry<String,Defs>> collectDef() {
		List<Entry<String,Defs>> returnList = new LinkedList<>();
		Set<Entry<String, JSONSchema>> entrySet = schemaDefs.entrySet();

		returnList.add(new AbstractMap.SimpleEntry<>("",this));

		for(Entry<String, JSONSchema> entry : entrySet)
				returnList.addAll(Utils_JSONSchema.addPathElement(entry.getKey(), entry.getValue().collectDef()));

		return returnList;
	}

	@Override
	public String toString() {
		return "Defs{" +
				"schemaDefs=" + schemaDefs +
				", rootDef=" + rootDef +
				'}';
	}

	public Defs clone() {
		Defs clone = new Defs();
		
		Set<Entry<String, JSONSchema>> entrySet = schemaDefs.entrySet();
		
		for(Entry<String, JSONSchema> entry : entrySet)
			clone.schemaDefs.put(entry.getKey(), entry.getValue().clone());

		if(rootDef != null)
			clone.rootDef = rootDef.clone();
		
		return clone;
	}
}
