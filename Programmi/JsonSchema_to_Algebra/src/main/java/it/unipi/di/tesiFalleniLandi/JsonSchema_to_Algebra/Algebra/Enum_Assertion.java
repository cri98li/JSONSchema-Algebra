package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

public class Enum_Assertion implements Assertion{

	private List<String> _enum;
	
	public Enum_Assertion() {
		_enum = new LinkedList<>();
	}
	
	public void add(String str) {
		_enum.add(str);
	}
}
