package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class Enum implements JSONSchemaElement{
	protected List<String> enumArray_str;
	protected List<Object> enumArray_num;
	protected List<Boolean> enumArray_bool;
	protected List<JSONObject> enumArray_obj;
	protected List<JSONArray> enumArray_array;
	protected boolean thereIsNull;
	
	protected Enum() {
		enumArray_str = new LinkedList<>();
		enumArray_num = new LinkedList<>();
		enumArray_bool = new LinkedList<>();
		enumArray_obj = new LinkedList<>();
		enumArray_array = new LinkedList<>();
		thereIsNull = false;
	}
	
	public Enum(Object obj) {
		enumArray_str = new LinkedList<>();
		enumArray_num = new LinkedList<>();
		enumArray_bool = new LinkedList<>();
		enumArray_obj = new LinkedList<>();
		enumArray_array = new LinkedList<>();
		thereIsNull = false;
		
		try {
			JSONArray array = (JSONArray) obj;
			Iterator<?> it = array.iterator();
			
			while(it.hasNext()){
				Object currentObject = it.next();
				parseArray(currentObject);
			}
		}catch(ClassCastException e) {
			e.printStackTrace();
		}
	}
	
	protected void parseArray(Object currentObject) {
		if(putStringValue(currentObject)) return;
		if(putNumericValue(currentObject)) return;
		if(putBooleanValue(currentObject)) return;
		if(putNullValue(currentObject)) return;
		if(putArrayValue(currentObject)) return;
		putObjectValue(currentObject);
	}
	
	private boolean putStringValue(Object obj) {
		try {
			enumArray_str.add((String) obj);
			return true;
		}catch(ClassCastException e) {
			return false;
		}
	}
	
	private boolean putNumericValue(Object obj) {
		if(obj.getClass() == Double.class || obj.getClass() == Long.class || obj.getClass() == Integer.class){
			enumArray_num.add(obj);
			return true;
		}

		return false;
	}
	
	private boolean putBooleanValue(Object obj) {
		try {
			enumArray_bool.add((Boolean) obj);
			return true;
		}catch(ClassCastException e) {
			return false;
		}
	}
	
	private boolean putObjectValue(Object obj) {
		try {
			enumArray_obj.add((JSONObject) obj);
			return true;
		}catch(ClassCastException e) {
			return false;
		}
	}
	
	private boolean putArrayValue(Object obj) {
		try {
			enumArray_array.add((JSONArray) obj);
			return true;
		}catch(ClassCastException e) {
			return false; 
		}
	}
	
	private boolean putNullValue(Object obj) {
		return (thereIsNull = (obj == null));
	}

	@Override
	public String toString() {
		return "Enum [enumArray_str=" + enumArray_str + ", enumArray_num=" + enumArray_num + ", enumArray_bool="
				+ enumArray_bool + ", enumArray_obj=" + enumArray_obj + ", enumArray_array=" + enumArray_array
				+ ", thereIsNull=" + thereIsNull + "]";

	}

	@SuppressWarnings("unchecked")
	@Override
	public Object toJSON() {
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		
		Iterator<?> it = enumArray_str.iterator();
		while(it.hasNext()) array.add(it.next());
		
		it = enumArray_num.iterator();
		while(it.hasNext()) array.add(it.next());
		
		it = enumArray_bool.iterator();
		while(it.hasNext()) array.add(it.next());

		Iterator<? extends JSONObject> it_JSO = enumArray_obj.iterator();
		while(it_JSO.hasNext()) array.add(it_JSO.next());
		
		Iterator<? extends JSONArray>it_JSE = enumArray_array.iterator();
		while(it_JSE.hasNext()) array.add(it_JSE.next());
		
		if(thereIsNull) array.add(null);

		obj.put("enum", array);

		return obj;
	}

	@Override
	public String toGrammarString() {
		String str = ""; String separator = ", ";
		
		Iterator <?> it = enumArray_str.iterator();
		while(it.hasNext()) str += (separator + "\""+it.next()+"\"");
		
		it = enumArray_bool.iterator();
		while(it.hasNext()) str += (separator + it.next());
		
		it = enumArray_num.iterator();
		while(it.hasNext()) str += (separator + it.next());
		
		Iterator<? extends JSONObject> it_JSO = enumArray_obj.iterator();
		while(it_JSO.hasNext()) str += (separator + it_JSO.next().toJSONString());
		
		Iterator<? extends JSONArray> it_JSE = enumArray_array.iterator();
		while(it_JSE.hasNext()) str += (separator + it_JSE.next().toJSONString());
		
		if(thereIsNull) str += (separator + "null");

		return String.format(GrammarStringDefinitions.ENUM, str.subSequence(separator.length(), str.length()));
	}


	@Override
	public Enum assertionSeparation() {
		return this.clone();
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
	public int numberOfAssertions() {
		return enumArray_str.size() + enumArray_array.size() + enumArray_bool.size() + enumArray_obj.size() + enumArray_num.size();
	}
	
	public Enum clone() {
		Enum clone = new Enum();
		
		Iterator <?> it = enumArray_str.iterator();
		while(it.hasNext()) clone.enumArray_str.add((String) it.next());
		
		it = enumArray_bool.iterator();
		while(it.hasNext()) clone.enumArray_bool.add((Boolean) it.next());
		
		it = enumArray_num.iterator();
		while(it.hasNext()) clone.enumArray_num.add((Object) it.next());
		
		Iterator<JSONObject> it_JS = enumArray_obj.iterator();
		while(it_JS.hasNext()) clone.enumArray_obj.add((JSONObject) it_JS.next().clone());
		
		Iterator<JSONArray> it_JSA = enumArray_array.iterator();
		while(it_JSA.hasNext()) clone.enumArray_array.add((JSONArray) it_JSA.next().clone());

		clone.thereIsNull = thereIsNull;
		
		return clone;
	}
}
