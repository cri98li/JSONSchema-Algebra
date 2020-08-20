package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.FullAlgebraString;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessException;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessEnv;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessVar;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

public class Defs_Assertion implements Assertion{
	private HashMap<String, Assertion> defs; // all the definitions
	private String rootDef; // name of the main definition
	protected static Defs_Assertion env = null; //used by pattOfS

	private static Logger logger = LogManager.getLogger(Defs_Assertion.class);

	public Defs_Assertion() {
		logger.trace("Creating an empty Defs_Assertion");
		env = this;
		defs = new HashMap<>();
	}
	
	public void add(String key, Assertion value) throws ParseCancellationException {
		if(defs.containsKey(key)) {
			throw new ParseCancellationException("Detected 2 defs with the same name");
		}
		defs.put(key, value);
	}
	
	public void setRootDef(String rootDefName, Assertion rootDef) {
		logger.trace("Setting {}={} as rootdef", rootDefName);
		add(rootDefName, rootDef);
		this.rootDef = rootDefName;
	}

	public void addAll(Defs_Assertion defs){
		logger.trace("Adding {} as defs", defs);
		this.defs.putAll(defs.defs);
	}

	@Override
	public JsonElement toJSONSchema() {
		JsonObject obj = new JsonObject();
		if(rootDef != null)
			obj = defs.get(rootDef).toJSONSchema().getAsJsonObject();

		JsonObject jsonDefs = new JsonObject();
		for(Entry<String, Assertion> a : defs.entrySet())
			if(!a.getKey().equals(rootDef))
				jsonDefs.add(a.getKey(), a.getValue().toJSONSchema().getAsJsonObject());

		obj.add("$defs", jsonDefs);

		return obj;
	}

	@Override
	public Assertion not() {
		Defs_Assertion not = this.notElimination();
		not.rootDef = FullAlgebraString.NOT_DEFS + not.rootDef;

		return not;
	}

	@Override
	public Defs_Assertion notElimination() {
		Defs_Assertion returnDef = new Defs_Assertion();

		//Complete the not
		for(Entry<String, Assertion> entry : defs.entrySet()) {
			returnDef.defs.put(entry.getKey(), entry.getValue().notElimination());

			//case negation of not_x --> x
			if(entry.getKey().startsWith(FullAlgebraString.NOT_DEFS))
				continue;

			if (!defs.containsKey(FullAlgebraString.NOT_DEFS+entry.getKey()))
				returnDef.defs.put(FullAlgebraString.NOT_DEFS+entry.getKey(), entry.getValue().notElimination().not());
		}

		returnDef.rootDef = rootDef;

		return returnDef;
	}

	@Override
	public String toGrammarString() {
		StringBuilder def = new StringBuilder();

		if(defs != null) {
			Set<Entry<String, Assertion>> entrySet = defs.entrySet();

			for (Entry<String, Assertion> entry : entrySet) {
				String tmp = entry.getValue().toGrammarString();
				if(tmp.isEmpty()) continue;

				if (!Objects.equals(entry.getKey(), rootDef)) {
					def.append(FullAlgebraString.COMMA)
							.append(FullAlgebraString.DEFS(entry.getKey(), tmp));
				} else if (rootDef != null)
					def.append(FullAlgebraString.COMMA)
							.append(FullAlgebraString.ROOTDEF("\"" + rootDef + "\"", tmp));
				else
					def.append(FullAlgebraString.COMMA)
							.append(FullAlgebraString.ROOTDEF("\"" + FullAlgebraString.ROOTDEF_DEFAULTNAME + "\"", ""));
			}
		}

		if(def.length() == 0) return "";
		return def.substring(FullAlgebraString.COMMA.length());
	}

	@Override
	public WitnessEnv toWitnessAlgebra() throws REException {
		WitnessEnv env = new WitnessEnv();

		for(Entry<String, Assertion> entry : defs.entrySet())
			if(entry.getKey().equals(rootDef))
				env.setRootVar(new WitnessVar(entry.getKey()), entry.getValue().toWitnessAlgebra());
			else
				env.add(new WitnessVar(entry.getKey()), entry.getValue().toWitnessAlgebra());

		env.buildOBDD();
		logger.trace("Trying to notEliminate {}", env);
		env.notElimination();

		return env;
	}

	public Assertion getDef(String ref){
		return defs.get(ref);
	}

	public String getRootName(){
		return rootDef;
	}
}
