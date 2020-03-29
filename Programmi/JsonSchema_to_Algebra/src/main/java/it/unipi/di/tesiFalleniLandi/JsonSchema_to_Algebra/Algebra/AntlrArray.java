package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

public class AntlrArray extends AntlrValue{
	private List<Object> list;
	
	public AntlrArray() {
		list = new LinkedList<>();
	}
	
	public void add(AntlrValue value) {
		list.add(value.getValue());
	}
	
	
	@Override
	public String getJSONSchemaKeyword() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object toJSONSchema() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object getValue() {
		return list;
	}
}
