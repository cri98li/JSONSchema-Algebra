package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class BetweenNumber implements JSONSchemaElement{
	//Memorizzandoli come Object non devo fare magheggi per castare in Long o Double
	private Object maximum;
	private Object minimum;
	
	private Object exclusiveMaximum;
	private Object exclusiveMinimum;
	
	private Boolean booleanExclusiveMaximum;
	private Boolean booleanExclusiveMinimum;
	
	public BetweenNumber() {
	}
	
	public void setMax(Object obj) {
		if(booleanExclusiveMaximum != null && booleanExclusiveMaximum)
			this.exclusiveMaximum = obj;
		else
			this.maximum = obj;
	}
	
	public void setMin(Object obj) {
		if(booleanExclusiveMinimum != null && booleanExclusiveMinimum)
			this.exclusiveMaximum = obj;
		else
			this.minimum = obj;
	}
	
	
	public void setExclusiveMax(Object obj) {
		try {
			booleanExclusiveMaximum = (boolean) obj;
			if(booleanExclusiveMaximum && maximum != null) {
				exclusiveMaximum = maximum;
				maximum = null;
			}
		}catch(ClassCastException e) {
			this.exclusiveMaximum = obj;
		}
	}
	
	public void setExclusiveMin(Object obj) {
		try {
			booleanExclusiveMinimum = (boolean) obj;
			if(booleanExclusiveMinimum && minimum != null) {
				exclusiveMinimum = minimum;
				minimum = null;
			}
		}catch(ClassCastException e) {
			this.exclusiveMinimum = obj;
		}
	}
	
	
	

	@Override
	public String toString() {
		return "BetweenNumber [maximum=" + maximum + "\\r\\n minimum=" + minimum + "\\r\\n exclusiveMaximum="
				+ exclusiveMaximum + "\\r\\n exclusiveMinimum=" + exclusiveMinimum + "\\r\\n booleanExclusiveMaximum="
				+ booleanExclusiveMaximum + "\\r\\n booleanExclusiveMinimum=" + booleanExclusiveMinimum + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		
		if(maximum != null) obj.put("maximum", maximum);
		if(minimum != null) obj.put("minimum", minimum);
		
		if(exclusiveMaximum != null) obj.put("exclusiveMaximum", exclusiveMaximum);
		if(exclusiveMinimum != null) obj.put("exclusiveMinimum", exclusiveMinimum);
		
		if(booleanExclusiveMaximum != null) obj.put("exclusiveMaximum", booleanExclusiveMaximum);
		if(booleanExclusiveMinimum != null) obj.put("exclusiveMinimum", booleanExclusiveMinimum);
		
		return obj;
	}

	@Override
	public String toGrammarString() {
		String str1 = ""; //bet
		String str2 = ""; //xbet
		
		
		String min = GrammarStringDefinitions.NULLVALUE, max = GrammarStringDefinitions.NULLVALUE;
		if(minimum != null) min = minimum+"";
		if(maximum != null) max = maximum+"";
		
		if(minimum != null || maximum != null)
			str1 = String.format(GrammarStringDefinitions.BETWEENNUMBER, min, max);
		
		if(exclusiveMinimum != null) min = exclusiveMinimum+"";
		if(exclusiveMaximum != null) max = exclusiveMaximum+"";
		
		if(exclusiveMinimum != null || exclusiveMaximum != null)
			str2 = String.format(GrammarStringDefinitions.BETWEENNUMBER_EXCL, min, max);
		
		
		if(str1 == "" && str2 != null)
			return str2;
		if(str2 == "" && str1 != null)
			return str1;
		
		return str1 + GrammarStringDefinitions.AND + str2;
	}

	@Override
	public BetweenNumber assertionSeparation() {
		BetweenNumber obj = new BetweenNumber();
		
		if(maximum != null) obj.maximum = maximum;
		if(minimum != null) obj.minimum = minimum;
		
		if(exclusiveMaximum != null) obj.exclusiveMaximum = exclusiveMaximum;
		if(exclusiveMinimum != null) obj.exclusiveMinimum = exclusiveMinimum;
		
		if(booleanExclusiveMaximum != null) obj.booleanExclusiveMaximum = booleanExclusiveMaximum;
		if(booleanExclusiveMinimum != null) obj.booleanExclusiveMinimum = booleanExclusiveMinimum;
		
		return obj;
	}

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
}