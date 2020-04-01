package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.JsonObject;

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
	public JsonObject toJSONSchema() {
		JsonObject schema = new JsonObject();
		
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
