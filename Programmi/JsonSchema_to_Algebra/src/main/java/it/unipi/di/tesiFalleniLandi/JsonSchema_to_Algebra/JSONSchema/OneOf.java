package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.OneOf_Assertion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class OneOf implements JSONSchemaElement{
	private List<JSONSchema> oneOf;

	private static Logger logger = LogManager.getLogger(OneOf.class);
	
	public OneOf(JsonElement obj) {
		logger.trace("Creating pattern by parsing {}", obj);

		JsonArray array = obj.getAsJsonArray();
		oneOf = new LinkedList<>();
		
		Iterator<JsonElement> it = array.iterator();

		while(it.hasNext()) {
			oneOf.add(new JSONSchema(it.next()));
		}
	}
	
	public OneOf() {
		oneOf = new LinkedList<>();
		logger.trace("Creating an empty OneOf");
	}
	
	public void addElement(JSONSchema schema) {
		if(oneOf == null) oneOf = new LinkedList<>();
		logger.trace("Adding {} to {}", schema, this);
		oneOf.add(schema);
	}
	
	@Override
	public String toString() {
		return "OneOf [oneOf=" + oneOf + "]";
	}


	@SuppressWarnings("unchecked")
	@Override
	public JsonObject toJSON() {
		JsonObject obj = new JsonObject();
		JsonArray array = new JsonArray();
		
		for(JSONSchema js : oneOf)
			array.add(js.toJSON());

		obj.add("oneOf", array);

		return obj;
	}

	@Override
	public Assertion toGrammar() {
		OneOf_Assertion oneOf = new OneOf_Assertion();

		for(JSONSchema element : this.oneOf)
			oneOf.add(element.toGrammar());

		return oneOf;
	}

	
	@Override
	public int numberOfTranslatableAssertions() {
		int returnValue = 0;
		if(oneOf != null)
			for(JSONSchemaElement jse : oneOf)
				returnValue += jse.numberOfTranslatableAssertions();

		return returnValue;
	}

	@Override
	public OneOf assertionSeparation() {
		OneOf obj = new OneOf();
		
		obj.oneOf = new LinkedList<>();
		for(JSONSchema s : oneOf)
			obj.oneOf.add(s.assertionSeparation());
			
		
		return obj;
	}
	

	@Override
	public List<URI_JS> getRef() {
		List<URI_JS> returnList = new LinkedList<>();
		
		for(JSONSchema schema : oneOf)
			returnList.addAll(schema.getRef());
		
		return returnList;
	}

	@Override
	public JSONSchema searchDef(Iterator<String> URIIterator) {
		if(URIIterator.hasNext() && URIIterator.next().equals("oneOf")) {
			URIIterator.remove();
			try {
				int i = Integer.parseInt(URIIterator.next());
				logger.debug("searchDef: searching for index {} in oneOf[{}]. URIIterator: {}", i, oneOf.size(), URIIterator);
				if (i < oneOf.size()) {
					URIIterator.remove();
					return oneOf.get(i).searchDef(URIIterator);
				}
			} catch (ClassCastException | NumberFormatException e) {
				logger.catching(e); //error in the ref URI
			}
		}

		return null;
	}



	@Override
	public List<Entry<String,Defs>> collectDef() {
		return new LinkedList<>();
	}
	
	
	@Override
	public OneOf clone(){
		OneOf clone = new OneOf();
		
		for(JSONSchema el : oneOf)
			clone.addElement(el.clone());
		
		return clone;
	}
}
