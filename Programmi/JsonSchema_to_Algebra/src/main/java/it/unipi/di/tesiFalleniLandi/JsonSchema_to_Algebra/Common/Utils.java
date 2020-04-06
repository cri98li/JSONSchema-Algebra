package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONObject;

public class Utils {
	/**
	 * Inserisce il contenuto di toPut in schema
	 * @param schema dove inserire il contenuto di toPut
	 * @param keyword parola chiave associata a toPut
	 * @param toPut Oggetto da inserire in schema
	 */
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
				"AllOf_Schema"
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
}
