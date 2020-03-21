package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

public class And_Assertion implements Assertion{
	private List<Assertion> andList;
	
	public And_Assertion(AntrlList andList) {
		this.andList = andList.getValue();
	}

	@Override
	public String toString() {
		return "And_Assertion [" + andList + "]";
	}
	
}
