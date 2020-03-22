package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public class Bet_Assertion implements Assertion{
	
	private Integer min, max;
	
	public Bet_Assertion() {	}
	
	public Bet_Assertion(Integer min, Integer max) {
		this.min = min;
		this.max = max;
	}
	
	public void setMin(int min) {
		this.min = min;
	}
	
	public void setMax(int max) {
		this.max = max;
	}
	
	public Bet_Assertion intersect(Bet_Assertion bet) {
		Bet_Assertion intersectedBet = new Bet_Assertion();
		
		intersectedBet.setMin((min > bet.min)? min:bet.min);
		intersectedBet.setMax((max < bet.max)? max:bet.max);
		
		return intersectedBet;
	}

	@Override
	public String toString() {
		return "Bet_Assertion [" + min + ", max=" + max + "]";
	}
	
	
}
