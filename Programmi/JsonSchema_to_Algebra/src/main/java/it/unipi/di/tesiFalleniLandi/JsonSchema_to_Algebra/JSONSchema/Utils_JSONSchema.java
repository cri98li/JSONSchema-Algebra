package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;

public class Utils_JSONSchema {
	public static JSONSchema parse(String path) throws FileNotFoundException, IOException, ParseException {
		
		try (Reader reader = new FileReader(path)){
        	JSONObject object = (JSONObject) new JSONParser().parse(reader);
        	return new JSONSchema(object);
        }
	}
	
	public static JSONSchema normalize(JSONSchema root) {
		return referenceNormalization(root.assertionSeparation());
	}
	
	public static JSONSchema referenceNormalization(JSONSchema root) {
		JSONSchema newRoot = new JSONSchema();
		List<Entry<String, Defs>> defsList = addPathElement("#", root.collectDef());
		
		List<URI_JS> refList = root.getRef();
		
		Defs finalDefs = new Defs();
		
		for(URI_JS ref : refList) {
			if(ref.toString().equals("#") || ref.toString().charAt(0) != '#')
				continue;
			boolean found = false;
			for(Entry<String, Defs> entry : defsList) {
				JSONSchema s = compareDefsRefs(entry, ref);
				if(s != null) {
					ref.found();
					finalDefs.addDef(ref.getNormalizedName(), s);
					found = true;
					//defsList.remove(entry);
					break;
				}
			}
			if(found) {
				System.out.println("TROVATO: "+ref.toString());
				continue;
			}else
				System.out.println("NON TROVATO: "+ref.toString());
			
			//Se sono arrivato qui, ho trovato un ref che non sono riuscito a risolvere. non è contenuto in nessun def?
			JSONSchema newDef = root.searchDef(ref.iterator());
			if(newDef != null) {
				ref.found();
				finalDefs.addDef(ref.getNormalizedName(), newDef);
			}
		}
		
		
		//aggiungo tutte le defs definite ma non usate
		for(Entry<String, Defs> entry : defsList)
			finalDefs.addDef(entry.getValue());
		
		//caso schema con sole definizioni
		if(root.numberOfAssertions() != 0)
			finalDefs.setRootDef(root.clone());
		
		//Aggiungo allo schema la defs normalizzata
		newRoot.addJSONSchemaElement("$defs", finalDefs);
		
		return newRoot;
	}
	
	
	
	
	private static JSONSchema compareDefsRefs(Entry<String, Defs> entry, URI_JS ref) {
		System.out.println("CONFRONTO: "+entry.getKey()+"\r\n\t"+ref.toString().replace("definitions", "$defs"));
		
		String[] defUriSplitted = entry.getKey().split("/");
		String[] refUriSplitted = ref.toString().replace("definitions", "$defs").split("/");
		
		if(defUriSplitted.length +1 != refUriSplitted.length)
			return null; //lunghezza diversa, è impossibile che siano uguali
		
		for(int i = 0; i < defUriSplitted.length; i++)
			if(!defUriSplitted[i].equals(refUriSplitted[i])) return null;
		
		return entry.getValue().containsDef(refUriSplitted[refUriSplitted.length-1]);
		
	}
	
	
	
	
	
	static List<Entry<String,Defs>> addPathElement(String key, List<Entry<String,Defs>> list){
		List<Entry<String,Defs>> newList = new LinkedList<>();
		for(Entry<String, Defs> entry : list)
			newList.add(new AbstractMap.SimpleEntry<>(key+"/"+entry.getKey(), entry.getValue()));
		
		return newList;
	}
	
	
	
	
	
	
	
	
	
	public static String toGrammarString(JSONSchema root) {
		return Utils.beauty(root.toGrammarString());
	}
	
}
