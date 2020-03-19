package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

public class And_Assertion implements Assertion{
	private List<Assertion> andList;
	
	public And_Assertion() {
		andList = new LinkedList<>();
	}
	
	public And_Assertion(List<Assertion> andList) {
		super();
		this.andList = andList;
	}
	
	public And_Assertion(And_Assertion andAssertion) {
		andList = andAssertion.andList;
	}

	public void add(Assertion s) {
		andList.add(s);
	}

	@Override
	public String toString() {
		return "And_Assertion [" + andList + "]";
	}
	
}
