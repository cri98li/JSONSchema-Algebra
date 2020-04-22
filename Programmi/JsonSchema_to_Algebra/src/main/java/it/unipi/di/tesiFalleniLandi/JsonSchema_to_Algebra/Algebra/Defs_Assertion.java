package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;

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

		//corpo del json schema
		if(rootDef != null) {
			if(rootDef.getClass() != Boolean_Assertion.class) // == è un caso particolare (può succedere solo se si parte dalla grammatica) "Caso rognoso" di un crucco
				Utils.putContent(obj, rootDef.getJSONSchemaKeyword(), rootDef.toJSONSchema());
			
			JSONObject def = new JSONObject();
			Set<Entry<String, Assertion>> entrySet = defs.entrySet();
			
			for(Entry<String,Assertion> entry : entrySet) {
				JSONObject tmp = new JSONObject();
				if(entry.getValue().getClass() == Boolean_Assertion.class)
					def.put(entry.getKey(), entry.getValue().toJSONSchema());
				else {
					Utils.putContent(tmp, entry.getValue().getJSONSchemaKeyword(), entry.getValue().toJSONSchema());
					def.put(entry.getKey(), tmp);
				}

				def.put(entry.getKey(), entry.getValue().toJSONSchema());
			}

			obj.put("$defs", def);
			return obj;
		}

		/*
		//altre definizioni
		Set<Entry<String, Assertion>> entrySet = defs.entrySet();
		
		for(Entry<String,Assertion> entry : entrySet) {
			JSONObject tmp = new JSONObject();
			if(entry.getValue().getClass() == Boolean_Assertion.class)
				obj.put(entry.getKey(), entry.getValue().toJSONSchema());
			else {
				Utils.putContent(tmp, entry.getValue().getJSONSchemaKeyword(), entry.getValue().toJSONSchema());
				obj.put(entry.getKey(), tmp);
			}
		}
		*/
		
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
