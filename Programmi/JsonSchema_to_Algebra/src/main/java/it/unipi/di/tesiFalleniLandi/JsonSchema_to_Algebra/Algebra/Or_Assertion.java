package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.List;

public class Or_Assertion implements Assertion{
	private List<Assertion> orList;
	
	public Or_Assertion(Or_Assertion orAssertion) {
		orList = orAssertion.orList;
	}

	@Override
	public String toString() {
		return "Or_Assertion [orList=" + orList + "]";
	}
}
