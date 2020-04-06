package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

public class RequiredPattern_Assertion implements Assertion{
private List<String> reqPatList;
	
	public RequiredPattern_Assertion() {
		reqPatList = new LinkedList<>();
	}
	
	public RequiredPattern_Assertion(List<String> list) {
		this.reqPatList = list;
	}
	
	public void add(String str) {
		reqPatList.add(str);
	}

	@Override
	public String toString() {
		return "RequiredPattern_Assertion [" + reqPatList + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "requiredPattern";
	}

	@Override
	public Assertion not() {
		return null;
	}

	@Override
	public Object toJSONSchema() {
		// TODO Auto-generated method stub
		return null;
	}

}
