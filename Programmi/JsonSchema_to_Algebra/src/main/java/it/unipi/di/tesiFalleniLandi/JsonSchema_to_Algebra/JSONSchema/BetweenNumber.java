package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.AllOf_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Bet_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.XBet_Assertion;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class BetweenNumber implements JSONSchemaElement{
	private Number maximum;
	private Number minimum;
	private Number exclusiveMaximum;
	private Number exclusiveMinimum;
	private Boolean booleanExclusiveMaximum;
	private Boolean booleanExclusiveMinimum;
	
	public BetweenNumber() { }
	
	public void setMax(JsonElement obj) {
		if(!obj.isJsonPrimitive() || !obj.getAsJsonPrimitive().isNumber())
			throw new ParseCancellationException("expected number as value of maximum got " + obj);

		if(booleanExclusiveMaximum != null && booleanExclusiveMaximum)
			this.exclusiveMaximum = obj.getAsNumber();
		else
			this.maximum = obj.getAsNumber();
	}
	
	public void setMin(JsonElement obj) {
		if(!obj.isJsonPrimitive() || !obj.getAsJsonPrimitive().isNumber())
			throw new ParseCancellationException("expected number as value of minimum got "+ obj);

		if(booleanExclusiveMinimum != null && booleanExclusiveMinimum)
			this.exclusiveMaximum = obj.getAsNumber();
		else
			this.minimum = obj.getAsNumber();
	}
	
	
	public void setExclusiveMax(JsonElement obj) {
		if(!obj.isJsonPrimitive() || (!obj.getAsJsonPrimitive().isBoolean() && !obj.getAsJsonPrimitive().isNumber()))
			throw new ParseCancellationException("expected number or boolean as value of exclusiveMaximum got "+obj);

		try {
			booleanExclusiveMaximum = obj.getAsBoolean();
			if(booleanExclusiveMaximum && maximum != null) {
				exclusiveMaximum = maximum;
				maximum = null;
			}
		}catch(ClassCastException e) {
			this.exclusiveMaximum = obj.getAsNumber();
		}
	}
	
	public void setExclusiveMin(JsonElement obj) {
		if(!obj.isJsonPrimitive() || (!obj.getAsJsonPrimitive().isBoolean() && !obj.getAsJsonPrimitive().isNumber()))
			throw new ParseCancellationException("expected number or boolean as value of exclusiveMinimum got "+obj);
		try {
			booleanExclusiveMinimum = obj.getAsBoolean();
			if(booleanExclusiveMinimum && minimum != null) {
				exclusiveMinimum = minimum;
				minimum = null;
			}
		}catch(ClassCastException e) {
			this.exclusiveMinimum = obj.getAsNumber();
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
	public JsonElement toJSON() {
		JsonObject obj = new JsonObject();
		
		if(maximum != null) obj.addProperty("maximum", maximum);
		if(minimum != null) obj.addProperty("minimum", minimum);
		
		if(exclusiveMaximum != null) obj.addProperty("exclusiveMaximum", exclusiveMaximum);
		if(exclusiveMinimum != null) obj.addProperty("exclusiveMinimum", exclusiveMinimum);
		
		if(booleanExclusiveMaximum != null) obj.addProperty("exclusiveMaximum", booleanExclusiveMaximum);
		if(booleanExclusiveMinimum != null) obj.addProperty("exclusiveMinimum", booleanExclusiveMinimum);
		
		return obj;
	}

	@Override
	public Assertion toGrammar() {
		AllOf_Assertion allOf = new AllOf_Assertion();
		Bet_Assertion bet = new Bet_Assertion();
		XBet_Assertion xbet = new XBet_Assertion();
		allOf.add(bet);
		allOf.add(xbet);

		if(minimum != null) {
			bet.setMin(minimum);
		}
		if(maximum != null){
			bet.setMax(maximum);
		}

		if(minimum != null || maximum != null){

		}


		if(exclusiveMinimum != null) {
			xbet.setMin(exclusiveMinimum);
		}
		if(exclusiveMaximum != null) {
			xbet.setMax(exclusiveMaximum);
		}

		return allOf;
		/*
		String str1 = ""; //bet
		String str2 = ""; //xbet

		String min = FullAlgebraString.NEG_INF, max = FullAlgebraString.POS_INF;
		if(minimum != null) min = minimum+"";
		if(maximum != null) max = maximum+"";
		
		if(minimum != null || maximum != null)
			str1 = String.format(FullAlgebraString.BETWEENNUMBER, min, max);
		
		if(exclusiveMinimum != null) min = exclusiveMinimum+"";
		if(exclusiveMaximum != null) max = exclusiveMaximum+"";
		
		if(exclusiveMinimum != null || exclusiveMaximum != null)
			str2 = String.format(FullAlgebraString.BETWEENNUMBER_EXCL, min, max);
		
		
		if(str1.isEmpty() && str1 != null)
			return str2;
		if(str2.isEmpty() && str2 != null)
			return str1;
		
		return str1 + FullAlgebraString.COMMA + str2;
		 */
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

	@Override
	public int numberOfTranslatableAssertions() {
		return 1;
	}
	
	public BetweenNumber clone() {
		BetweenNumber clone = new BetweenNumber();
		
		clone.maximum = maximum;
		clone.minimum = minimum;
		clone.exclusiveMaximum = exclusiveMaximum;
		clone.exclusiveMinimum = exclusiveMinimum;
		clone.booleanExclusiveMaximum = booleanExclusiveMaximum;
		clone.booleanExclusiveMinimum = booleanExclusiveMinimum;
		
		return clone;
	}
}