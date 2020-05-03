package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAssertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessEnv;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessVar;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

//TODO: AGGIORNARE I METODI PER ROOTDEF
public class Defs_Assertion implements Assertion{
	private HashMap<String, Assertion> defs;
	private String rootDef;
	public static Defs_Assertion env = null;
	
	public Defs_Assertion() {
		env = this;
		defs = new HashMap<String, Assertion>();
	}
	
	public void add(String key, Assertion value) throws ParseCancellationException {
		if(defs.containsKey(key)) throw new ParseCancellationException("Detected 2 defs with the same name");
		defs.put(key, value);
	}
	
	public void setRootDef(String rootDefName, Assertion rootDef) {
		add(rootDefName, rootDef);
		this.rootDef = rootDefName;
	}

	public void addAll(Defs_Assertion defs){
		this.defs.putAll(defs.defs);
	}

	@Override
	public Object toJSONSchema() {
		JSONObject obj = new JSONObject();
		if(rootDef != null)
			obj = (JSONObject) defs.get(rootDef).toJSONSchema();

		JSONObject jsonDefs = new JSONObject();
		for(Entry<String, Assertion> a : defs.entrySet())
			if(!a.getKey().equals(rootDef))
				jsonDefs.put(a.getKey(), a.getValue().toJSONSchema());

		obj.put("$defs", jsonDefs);

		return obj;
	}

	@Override
	public Assertion not() {
		//Defs_Assertion notDef = new Defs_Assertion();

		/*if(rootDef != null){
			String newRoot = GrammarStringDefinitions.NOT_DEFS + rootDef;
			notDef.setRootDef(newRoot, defs.get(newRoot));
			defs.remove(newRoot);
		}

		Set<Entry<String, Assertion>> entrySet = defs.entrySet();

		for(Entry<String, Assertion> entry: entrySet) {
			if(entry.getKey().startsWith(GrammarStringDefinitions.NOT_DEFS))
				notDef.add(entry.getKey().substring(GrammarStringDefinitions.NOT_DEFS.length()), entry.getValue().notElimination().not());
			else notDef.add(GrammarStringDefinitions.NOT_DEFS + entry.getKey(), entry.getValue().notElimination().not());
		}

		return notDef;*/

		Defs_Assertion not = this.notElimination();
		not.rootDef = GrammarStringDefinitions.NOT_DEFS + not.rootDef;

		return not;
	}

	@Override
	public Defs_Assertion notElimination() {
		Defs_Assertion returnDef = new Defs_Assertion();
		//Completo i not

		for(Entry<String, Assertion> entry : defs.entrySet()) {
			returnDef.defs.put(entry.getKey(), entry.getValue().notElimination());

			//caso negazione di not_x --> x
			if(entry.getKey().startsWith(GrammarStringDefinitions.NOT_DEFS))
				continue;

			if (!defs.containsKey(GrammarStringDefinitions.NOT_DEFS+entry.getKey()))
				returnDef.defs.put(GrammarStringDefinitions.NOT_DEFS+entry.getKey(), entry.getValue().notElimination().not());
		}

		returnDef.rootDef = rootDef;

		return returnDef;
	}

	@Override
	public String toGrammarString() {
		String def = "";

		if(defs != null) {
			Set<Entry<String, Assertion>> entrySet = defs.entrySet();

			for (Entry<String, Assertion> entry : entrySet)
					if (!entry.getKey().equals(rootDef))
						def += GrammarStringDefinitions.COMMA + String.format(GrammarStringDefinitions.DEFS, entry.getKey(), entry.getValue().toGrammarString());
					else
						if(rootDef != null)
							def += GrammarStringDefinitions.COMMA+String.format(GrammarStringDefinitions.ROOTDEF, rootDef, defs.get(rootDef).toGrammarString());
						else
							def += GrammarStringDefinitions.COMMA+String.format(GrammarStringDefinitions.ROOTDEF, GrammarStringDefinitions.ROOTDEF_DEFAULTNAME, "");
			}

		if(def.isEmpty()) return "";
		return def.substring(GrammarStringDefinitions.COMMA.length());
	}

	@Override
	public WitnessEnv toWitnessAlgebra() {
		WitnessEnv env = new WitnessEnv();

		for(Entry<String, Assertion> entry : defs.entrySet())
			if(entry.getKey().equals(rootDef))
				env.setRootVar(new WitnessVar(entry.getKey()), entry.getValue().toWitnessAlgebra());
			else
				env.add(new WitnessVar(entry.getKey()), entry.getValue().toWitnessAlgebra());

		return env;
	}

	public Assertion getDef(String ref){
		return defs.get(ref);
	}
}
