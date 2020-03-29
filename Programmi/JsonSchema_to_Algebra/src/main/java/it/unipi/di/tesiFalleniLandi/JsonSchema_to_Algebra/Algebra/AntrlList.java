package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.Utils;

public class AntrlList extends AntlrValue {
	
	private List<Assertion> list;
	
	public AntrlList() {
		list = new LinkedList<>();
	}
	
	public void add(Assertion assertion) {
		try {
			list.addAll(((AntrlList)assertion).list);
		}catch(ClassCastException e) {
			list.add(assertion);
		}
	}
	
	@Override
	public List<Assertion> getValue() {
		return list;
	}

	@Override
	public String toString() {
		return "AntrlList [" + list + "]";
	}

	@Override
	public String getJSONSchemaKeyword() {
		//throw new UnsupportedOperationException(); //elemento root dello schema
		return "items";
	}

	@Override
	public Object toJSONSchema() {
		JSONObject schema = new JSONObject();
		
		for(Assertion assertion : list)
			if(assertion.getClass() == AntlrBoolean.class) 
				continue;
			else Utils.putContent(schema, assertion.getJSONSchemaKeyword(), assertion.toJSONSchema());
		
		return schema;
	}
	
}
