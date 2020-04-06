package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4;

import java.util.LinkedList;
import java.util.List;

public class AntlrArray implements AntlrValue{
	private List<Object> list;
	
	public AntlrArray() {
		list = new LinkedList<>();
	}
	
	public void add(AntlrValue value) {
		list.add(value.getValue());
	}

	@Override
	public Object getValue() {
		return list;
	}
}
