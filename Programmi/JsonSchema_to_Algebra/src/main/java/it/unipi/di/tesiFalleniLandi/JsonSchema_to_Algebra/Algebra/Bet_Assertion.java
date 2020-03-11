package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public class Bet_Assertion implements S{
	
	private Integer min, max;
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
}
