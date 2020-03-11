package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

public class And_Assertion implements S{
	private List<S> andList;
	
	public And_Assertion() {
		andList = new LinkedList<>();
	}
	
	public void add(S s) {
		andList.add(s);
	}
}
