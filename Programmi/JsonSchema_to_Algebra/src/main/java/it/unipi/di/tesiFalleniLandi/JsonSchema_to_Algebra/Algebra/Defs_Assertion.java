package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.JSONSchema;

//TODO: AGGIORNARE I METODI PER ROOTDEF
public class Defs_Assertion implements Assertion{
	private HashMap<String, Assertion> defs;
	private Assertion rootDef;
	
	public Defs_Assertion() {
		defs = new HashMap<String, Assertion>();
	}
	
	public void add(String key, Assertion value) {
		defs.put(key, value);
	}
	
	public void setRootDef(Assertion rootDef) {
		this.rootDef = rootDef;
	}
	
	@Override
	public String getJSONSchemaKeyword() {
		if(rootDef != null) return "rootDef";
		return "$defs";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONSchema() {
		JSONObject obj = new JSONObject();
		
		if(rootDef != null) {
			Utils.putContent(obj, rootDef.getJSONSchemaKeyword(), rootDef.toJSONSchema());
			
			JSONObject def = new JSONObject();
			Set<Entry<String, Assertion>> entrySet = defs.entrySet();
			
			for(Entry<String,Assertion> entry : entrySet) {
				JSONObject tmp = new JSONObject();
				tmp.put(entry.getValue().getJSONSchemaKeyword(), entry.getValue().toJSONSchema());
				def.put(entry.getKey(), tmp);
			}
			
			obj.put("$defs", def);
			
			return obj;
		}
		
		Set<Entry<String, Assertion>> entrySet = defs.entrySet();
		
		for(Entry<String,Assertion> entry : entrySet) {
			JSONObject tmp = new JSONObject();
			tmp.put(entry.getValue().getJSONSchemaKeyword(), entry.getValue().toJSONSchema());
			obj.put(entry.getKey(), tmp);
		}
		
		
		return obj;
	}

	

	@Override
	public String toString() {
		return "Defs_Assertion [defs=" + defs + "\\r\\n rootDef=" + rootDef + "]";
	}

	@Override
	public Assertion not() {
		Defs_Assertion notDef = new Defs_Assertion();
		
		//TODO: negare il rootdef?
		
		Set<Entry<String, Assertion>> entrySet = defs.entrySet();
		
		for(Entry<String, Assertion> entry: entrySet) {
			notDef.add(entry.getKey(), entry.getValue());
			notDef.add("not_"+entry.getKey(), entry.getValue().not());
		}
		
		return notDef;
	}

	@Override
	public Assertion notElimination() {
		Defs_Assertion def = new Defs_Assertion();
		
		def.defs.putAll(defs); //va fatto solo al rootDef?
		def.rootDef = rootDef.notElimination();
		
		return def;
	}

	@Override
	public String toGrammarString() {
		String def = GrammarStringDefinitions.COMMA + String.format(GrammarStringDefinitions.ROOTDEF, rootDef.toGrammarString());;
		
		Set<Entry<String, Assertion>> entrySet = defs.entrySet();

		for(Entry<String, Assertion> entry : entrySet)
			def+= GrammarStringDefinitions.COMMA + String.format(GrammarStringDefinitions.DEFS, entry.getKey(), entry.getValue().toGrammarString());
		
		return def.substring(GrammarStringDefinitions.COMMA.length());
	}
}
