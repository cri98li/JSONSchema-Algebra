package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.JsonArray;

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

	@Override
	public Object toJSONSchema() {
		if(reqList.size() == 1)
			return reqList.get(0);
		
		JsonArray array = new JsonArray();
		for(String s : reqList)
			array.add(s);
			
		return array;
	}
}
