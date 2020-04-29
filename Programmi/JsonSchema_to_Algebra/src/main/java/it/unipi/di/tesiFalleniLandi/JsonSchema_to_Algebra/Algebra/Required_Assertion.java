package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.MyPattern;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.Type;
import org.json.simple.JSONArray;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.json.simple.JSONObject;

public class Required_Assertion implements Assertion{
	private List<String> reqList;
	
	public Required_Assertion() {
		reqList = new LinkedList<>();
	}
	
	public Required_Assertion(List<String> list) {
		this.reqList = list;
	}
	
	public void add(String str) {
		reqList.add(str);
	}

	@Override
	public String toString() {
		return "Required_Assertion [" + reqList + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object toJSONSchema() {
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();

		for(String str : reqList)
			array.add(str);

		obj.put("required", array);

		return obj;
	}

	@Override
	public Assertion not() {
		And_Assertion and = new And_Assertion();
		Or_Assertion or = new Or_Assertion();
		Type_Assertion type = new Type_Assertion();
		type.add(GrammarStringDefinitions.TYPE_OBJECT);
		and.add(type);

		for(String key : reqList){
			Properties_Assertion props = new Properties_Assertion();
			props.addPatternProperties(new MyPattern(key), new Boolean_Assertion(false));
			or.add(props);
		}
		and.add(or);

		return and;
	}

	@Override
	public Assertion notElimination() {
		return new Required_Assertion();
	}
	
	@Override
	public String toGrammarString() {
		String str = "";
		
		if(reqList.isEmpty()) return "";
		
		Iterator<String> it = reqList.iterator();
		
		if(it.hasNext())
			str += "\""+it.next()+"\"";
		
		while(it.hasNext()) {
			str += GrammarStringDefinitions.COMMA +"\""+ it.next()+"\"";
		}
		
		return String.format(GrammarStringDefinitions.REQUIRED, str);
	}
}
