package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.List;

public class Or_Assertion implements Assertion{
	private List<Assertion> orList;
	
	public Or_Assertion(AntrlList orList) {
		this.orList = orList.getValue();
	}

	@Override
	public String toString() {
		return "Or_Assertion [" + orList + "]";
	}
}
