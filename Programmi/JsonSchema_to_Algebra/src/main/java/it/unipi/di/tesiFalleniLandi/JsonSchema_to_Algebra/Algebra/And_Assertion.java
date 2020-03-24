package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

public class And_Assertion implements Assertion{
	private List<Assertion> andList;
	
	public And_Assertion() {
		this.andList = new LinkedList<>();
	}

	public void add(Assertion assertion) {
		andList.add(assertion);
	}
	
	@Override
	public String toString() {
		return "And_Assertion [" + andList + "]";
	}
	
}
