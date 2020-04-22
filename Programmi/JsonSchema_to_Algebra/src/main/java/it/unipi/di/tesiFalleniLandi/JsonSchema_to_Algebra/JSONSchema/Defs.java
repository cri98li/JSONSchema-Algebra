package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class Defs implements JSONSchemaElement{
	private HashMap<String, JSONSchema> schemaDefs;
	private JSONSchema rootDef;
	
	public Defs(Object obj) {
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) obj;
		}catch(ClassCastException ex) {
			System.out.println("Error: $defs must be valid JSON Object!");
		}
		
		schemaDefs = new HashMap<>();
		@SuppressWarnings("unchecked")
		Set<Map.Entry<?,?>> entrySet = jsonObject.entrySet();
		
		for(Entry<?, ?> entry : entrySet) {
			try{
				schemaDefs.putIfAbsent((String) entry.getKey(), new JSONSchema(entry.getValue()));
			}catch(ClassCastException ex) {
				System.out.println("Error: no valid Defs Object!\r\n"+ex.getLocalizedMessage());
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
	
	/*
	 * Unisce due Defs
	 */
	public void addDef(Defs defs) {
		schemaDefs.putAll(defs.schemaDefs);
	}
	
	public Defs() {	
		schemaDefs = new HashMap<>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		
		Set<Entry<String, JSONSchema>> entrySet = schemaDefs.entrySet();
		
		for(Entry<String, JSONSchema> entry : entrySet)
			obj.put(entry.getKey(), entry.getValue().toJSON());
		
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
		
		if(rootDef != null)
			defs = GrammarStringDefinitions.COMMA + String.format(GrammarStringDefinitions.ROOTDEF, rootDef.toGrammarString());
		
		Set<Entry<String, JSONSchema>> entrySet = schemaDefs.entrySet();

		for(Entry<String, JSONSchema> entry : entrySet)
			defs += GrammarStringDefinitions.COMMA + String.format(GrammarStringDefinitions.DEFS, entry.getKey(), entry.getValue().toGrammarString());
		
		if(defs.isEmpty()) return ""; //definizione non ancora supportata
		return defs.substring(GrammarStringDefinitions.COMMA.length());
	}
	
	@Override
	public int numberOfAssertions() {
		/*
		Set<Entry<String, JSONSchema>> entrySet = schemaDefs.entrySet();
		
		for(Entry<String, JSONSchema> entry : entrySet)
			count += entry.getValue().numberOfAssertions();
		*/
		
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
		returnList.add(new AbstractMap.SimpleEntry<>("",this));
		//ci va aggiunto rootDef????
		return returnList;
	}

	@Override
	public String toString() {
		return "Defs [schemaDefs=" + schemaDefs + "]";
	}

	public Defs clone() {
		Defs clone = new Defs();
		
		Set<Entry<String, JSONSchema>> entrySet = schemaDefs.entrySet();
		
		for(Entry<String, JSONSchema> entry : entrySet)
			clone.schemaDefs.put(entry.getKey(), entry.getValue().clone());
		
		clone.rootDef = rootDef;
		
		return clone;
	}
}
