package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessAssertion;

public interface Assertion extends AlgebraParserElement{
	/**
	 * Restituisce una rappresentazione usando la sintassi di JSON Schema dell'oggetto corrente
	 * @return restituisce un oggetto JSON Simple che rappresenta lo schema, vedi le implementazioni per maggiori informazioni
	 */
	public Object toJSONSchema();
	
	/**
	 * Ritorna l'asserzione negata
	 * @return
	 */
	public Assertion not();
	
	
	public Assertion notElimination(); //not pushing?
	
	
	public String toGrammarString();

	public WitnessAssertion toWitnessAlgebra();
}
