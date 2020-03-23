package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.json.simple.JSONArray;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;

public class Enum implements JSONSchemaElement, Comparable<Object>{
	protected List<String> enumArray_str;
	protected List<Long> enumArray_num;
	protected List<Boolean> enumArray_bool;
	protected List<JSONSchema> enumArray_obj;
	protected List<Enum> enumArray_array;
	protected boolean thereIsNull;
	protected boolean arrayOnly;
	
	private Enum(JSONArray array) {
		arrayOnly = true;
		enumArray_str = new LinkedList<>();
		enumArray_num = new LinkedList<>();
		enumArray_bool = new LinkedList<>();
		enumArray_obj = new LinkedList<>();
		enumArray_array = new LinkedList<>();
		thereIsNull = false;
		
		Iterator<?> it = array.iterator();
		while(it.hasNext()){
			Object currentObject = it.next();
			parseArray(currentObject);
		}
	}
	
	protected Enum() {
		arrayOnly = false;
		enumArray_str = new LinkedList<>();
		enumArray_num = new LinkedList<>();
		enumArray_bool = new LinkedList<>();
		enumArray_obj = new LinkedList<>();
		enumArray_array = new LinkedList<>();
		arrayOnly = false;
	}
	
	public Enum(Object obj) {
		arrayOnly = false;
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
		}catch(ClassCastException e) {}
	}
	
	protected void parseArray(Object currentObject) {
		if(putStringValue(currentObject)) return;
		if(putNumericValue(currentObject)) return;
		if(putBooleanValue(currentObject)) return;
		if(putNullValue(currentObject)) return;
		if(putArrayValue(currentObject)) return;
		if(putObjectValue(currentObject)) return;
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
		try {
			enumArray_num.add((Long) obj);
			return true;
		}catch(ClassCastException e) {
			return false;
		}
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
			enumArray_obj.add(new JSONSchema(obj));
			return true;
		}catch(ClassCastException e) {
			return false;
		}
	}
	
	private boolean putArrayValue(Object obj) {
		try {
			enumArray_array.add(new Enum((JSONArray) obj));
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
		if(!arrayOnly)
			return "Enum [enumArray_str=" + enumArray_str + ", enumArray_num=" + enumArray_num + ", enumArray_bool="
				+ enumArray_bool + ", enumArray_obj=" + enumArray_obj + ", enumArray_array=" + enumArray_array
				+ ", thereIsNull=" + thereIsNull + "]";
		
		return "[enumArray_str=" + enumArray_str + ", enumArray_num=" + enumArray_num + ", enumArray_bool="
		+ enumArray_bool + ", enumArray_obj=" + enumArray_obj + ", enumArray_array=" + enumArray_array
		+ ", thereIsNull=" + thereIsNull + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object toJSON() {
		JSONArray array = new JSONArray();
		
		Iterator<?> it = enumArray_str.iterator();
		while(it.hasNext()) array.add(it.next());
		
		it = enumArray_num.iterator();
		while(it.hasNext()) array.add(it.next());
		
		it = enumArray_bool.iterator();
		while(it.hasNext()) array.add(it.next());
		
		Iterator<? extends JSONSchemaElement> it_JSE = enumArray_obj.iterator();
		while(it_JSE.hasNext()) array.add(it_JSE.next().toJSON());
		
		it_JSE = enumArray_array.iterator();
		while(it_JSE.hasNext()) array.add(it_JSE.next().toJSON());
		
		if(thereIsNull) array.add(null);
		
		return array;
	}

	@Override
	public String toGrammarString() {
		String str = ""; String separator = "; ";
		
		Iterator <?> it = enumArray_str.iterator();
		while(it.hasNext()) str += (separator + it.next());
		
		it = enumArray_bool.iterator();
		while(it.hasNext()) str += (separator + it.next());
		
		it = enumArray_num.iterator();
		while(it.hasNext()) str += (separator + it.next());
		
		
		
		Iterator<? extends JSONSchemaElement> it_JSE = enumArray_obj.iterator();
		while(it_JSE.hasNext()) str += (separator + it_JSE.next().toGrammarString());
		
		it_JSE = enumArray_array.iterator();
		while(it_JSE.hasNext()) str += (separator + it_JSE.next().toGrammarString());
		
		
		
		if(thereIsNull) str += (separator + "null");
		
		if(arrayOnly)
			return "["+ str.subSequence(separator.length(), str.length()) + "]";
		
		return String.format(GrammarStringDefinitions.ENUM, str.subSequence(separator.length(), str.length()));
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Enum assertionSeparation() {
		Enum _enum = new Enum();
		
		if(enumArray_str != null) _enum.enumArray_str = new LinkedList<>(enumArray_str);
		
		if(enumArray_num != null) _enum.enumArray_num = new LinkedList<>(enumArray_num);
		
		if(enumArray_bool != null) _enum.enumArray_bool = new LinkedList<>(enumArray_bool);
		
		if(enumArray_array != null) _enum.enumArray_array = new LinkedList<>(enumArray_array);
		
		_enum.thereIsNull = this.thereIsNull;
		
		if(enumArray_obj != null) {
			for(JSONSchema s : enumArray_obj)
				_enum.enumArray_obj.add(s.assertionSeparation());
		}
		
		return _enum;
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
	public int numberOfGeneratedAssertions() {
		return 1;
	}
}
