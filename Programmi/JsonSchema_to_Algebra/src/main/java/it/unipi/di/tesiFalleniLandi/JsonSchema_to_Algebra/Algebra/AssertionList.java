package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONObject;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.AntlrBoolean;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;

public class AssertionList implements Assertion {
	
	private List<Assertion> list;
	
	public AssertionList() {
		list = new LinkedList<>();
	}
	
	public void add(Assertion assertion) {
		try {
			list.addAll(((AssertionList)assertion).list);
		}catch(ClassCastException e) {
			list.add(assertion);
		}
	}

	@Override
	public String toString() {
		return "AssertionList [" + list + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		return "assertionList";
	}

	@Override
	public JSONObject toJSONSchema() {
		JSONObject schema = new JSONObject();
		
		for(Assertion assertion : list)
			if(assertion.getClass() == AntlrBoolean.class) {
				continue;
			}
			else {
				Utils.putContent(schema, assertion.getJSONSchemaKeyword(), assertion.toJSONSchema());
			}
		
		return schema;
	}
	
}
