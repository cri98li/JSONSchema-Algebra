package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;

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

	@Override
	public String getJSONSchemaKeyword() {
		return "required";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object toJSONSchema() {
		if(reqList.size() == 1)
			return reqList.get(0);
		
		JSONArray array = new JSONArray();
		for(String s : reqList)
			array.add(s);
			
		return array;
	}

	@Override
	public Assertion not() {
		return null;
	}
}
