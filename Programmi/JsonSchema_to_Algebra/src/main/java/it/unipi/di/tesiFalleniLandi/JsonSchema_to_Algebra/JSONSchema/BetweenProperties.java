package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class BetweenProperties implements JSONSchemaElement{
	private Object minProperties;
	private Object maxProperties;
	
	public BetweenProperties() { }
	
	public void setMinProperties(Object obj) {
		minProperties = obj;
	}
	
	public void setMaxProperties(Object obj) {
		maxProperties = obj;
	}

	@Override
	public String toString() {
		return "BetweenProperties [minProperties=" + minProperties + ", maxProperties=" + maxProperties + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		
		if(minProperties != null) obj.put("minProperties", minProperties);
		
		if(maxProperties != null) obj.put("maxProperties", maxProperties);
		
		return obj;
	}

	@Override
	public String toGrammarString() {
		String min = GrammarStringDefinitions.NEG_INF, max = GrammarStringDefinitions.POS_INF;
		
		if(minProperties != null) min = minProperties+"";
		if(maxProperties != null) max = maxProperties+"";
		
		return String.format(GrammarStringDefinitions.BETWEENPROPERTIES, min, max);
	}

	@Override
	public BetweenProperties assertionSeparation() {
		BetweenProperties obj = new BetweenProperties();
		
		if(minProperties != null) obj.minProperties = minProperties;
		if(maxProperties != null) obj.maxProperties = maxProperties;
		
		return obj;
	};
	
	@Override
	public List<URI_JS> getRef() {
		return new LinkedList<>();
	}

	@Override
	public JSONSchema searchDef(Iterator<String> URIIterator) {
		return null;
	}

	@Override
	public List<Entry<String,Defs>> collectDef() {
		
		return new LinkedList<>();
	}

	@Override
	public int numberOfAssertions() {
		return 1;
	}
	
	public BetweenProperties clone() {
		BetweenProperties clone = new BetweenProperties();
		
		clone.minProperties = minProperties;
		clone.maxProperties = maxProperties;
		
		return clone;
	}
}
