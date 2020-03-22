package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.LinkedList;
import java.util.List;

public class AntrlList extends AntlrValue {
	
	private List<Assertion> list;
	
	public AntrlList() {
		list = new LinkedList<>();
	}
	
	public void add(Assertion assertion) {
		try {
			list.addAll(((AntrlList)assertion).list);
		}catch(ClassCastException e) {
			list.add(assertion);
		}
	}
	
	@Override
	public List<Assertion> getValue() {
		return list;
	}

	@Override
	public String toString() {
		return "AntrlList [" + list + "]";
	}

	
}
