package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

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

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONSchema() {
		JSONObject obj = new JSONObject();

		//corpo del json schema
		if(rootDef != null)
			if (rootDef.getClass() != Boolean_Assertion.class) // == è un caso particolare (può succedere solo se si parte dalla grammatica) "Caso rognoso" di un crucco maledetto
				obj = (JSONObject) rootDef.toJSONSchema();

		if(defs != null) {
			JSONObject def = new JSONObject();
			Set<Entry<String, Assertion>> entrySet = defs.entrySet();

			for (Entry<String, Assertion> entry : entrySet) {
				JSONObject tmp = new JSONObject();
				def.put(entry.getKey(), entry.getValue().toJSONSchema());
			}

			obj.put("$defs", def);
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

		if(defs != null){
			def.defs.putAll(defs);

			//inserisco le def complementari
			for(Entry<String, Assertion> entry : defs.entrySet())
				if(!entry.getKey().contains(GrammarStringDefinitions.NOT_DEFS+entry.getKey())) //controllo che non esista già
					def.defs.put(GrammarStringDefinitions.NOT_DEFS + entry.getKey(), entry.getValue().not());
		}

		if(rootDef != null)		def.rootDef = rootDef.notElimination();
		
		return def;
	}

	@Override
	public String toGrammarString() {
		String def = "";

		if(rootDef != null ) def = GrammarStringDefinitions.COMMA + String.format(GrammarStringDefinitions.ROOTDEF, rootDef.toGrammarString());;

		if(def != null) {
			Set<Entry<String, Assertion>> entrySet = defs.entrySet();

			for (Entry<String, Assertion> entry : entrySet)
				def += GrammarStringDefinitions.COMMA + String.format(GrammarStringDefinitions.DEFS, entry.getKey(), entry.getValue().toGrammarString());
		}
		if(def.isEmpty()) return "";
		return def.substring(GrammarStringDefinitions.COMMA.length());
	}
}
