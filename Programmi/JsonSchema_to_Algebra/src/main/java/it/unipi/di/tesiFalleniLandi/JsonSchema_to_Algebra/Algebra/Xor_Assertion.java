package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.List;

public class Xor_Assertion implements Assertion{
	private List<Assertion> xorList;
	
	public Xor_Assertion(Xor_Assertion xorAssertion) {
		xorList = xorAssertion.xorList;
	}

	@Override
	public String toString() {
		return "Xor_Assertion [orList=" + xorList + "]";
	}
}
