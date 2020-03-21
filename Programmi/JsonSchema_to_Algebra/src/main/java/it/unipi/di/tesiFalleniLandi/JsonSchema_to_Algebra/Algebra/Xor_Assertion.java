package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.List;

public class Xor_Assertion implements Assertion{
	private List<Assertion> xorList;
	
	public Xor_Assertion(AntrlList xorList) {
		this.xorList = xorList.getValue();
	}

	@Override
	public String toString() {
		return "Xor_Assertion [xorList=" + xorList + "]";
	}
}
