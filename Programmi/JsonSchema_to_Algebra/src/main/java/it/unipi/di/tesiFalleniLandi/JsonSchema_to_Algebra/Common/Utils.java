package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import org.json.simple.JSONObject;

public class Utils {
	/**
	 * Inserisce il contenuto di toPut in schema
	 * @param schema dove inserire il contenuto di toPut
	 * @param keyword parola chiave associata a toPut
	 * @param toPut Oggetto da inserire in schema
	 */
	public static final String PUTCONTENT = "putContent";
	@SuppressWarnings("unchecked")
	public static void putContent(JSONObject schema, String keyword, Object toPut) {
		List<String> putContentKeywords = Arrays.asList( new String[]{
				"properties",
				"ifThenElse",
				"items",
				"betweenItems",
				"length",
				"contains",
				"betweenNumber",
				"betweenProperties",
				"unknow",
				"assertionList",
				"rootDef",
				"AllOf_Schema",
				PUTCONTENT
		});
		
		if(!putContentKeywords.contains(keyword))
		{
			schema.put(keyword, toPut);
			return;
		}
		
		Set<?> keys = ((JSONObject) toPut).keySet();
		for(Object key : keys) {
			schema.put(key, ((JSONObject) toPut).get(key));
		}
	}
	
	public static String beauty(String input) {
		String output = "";
		int tab = 0;
		
		for(int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			Character c1 = (i+1 == input.length()) ? null : input.charAt(i+1);
			
			switch(c) {
			case '\n':
				output += (c1 != null && (c1 == '}' || c1 == ']')) ? c+tabs(tab-1) : c+tabs(tab);
				continue;
				
			case '[': case '{':
				tab++;
				break;
				
			case ']': case '}':
				tab--;
				break;
				
			default:
				break;
			}
			
			output += c;
		}
		
		return output;
	}
	
	
	private static String tabs(int n) {
		String output = "";
		
		for(int i = 0; i < n; i++) output += "\t";
		
		return output;
	}
}
