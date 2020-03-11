package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

interface JSONSchemaElement {	
	/**
	 * Restituisce una rappresentazione JSON (json simple) del json schema rappresentata dall'oggetto.
	 * @return un oggetto che rappresenta lo schema, vedi le implementazioni per maggiori informazioni
	 */
	public Object toJSON(); //ritorna una rappresentazione JSON dell'oggetto
	
	/**
	 * Applica lo step di separazione delle asserzioni.
	 * @return restituisce una nuova istanza dell'oggetto con applicata la regola di separazione delle asserzioni
	 */
	public JSONSchemaElement assertionSeparation();//ritorna una rappresentazione JSON dell'oggetto con asserzioni separate
	
	/**
	 * Converte la keyword del JSON Schema nella corrispondente rappresentazione nella nostra algebra.
	 * @return una stringa contenente la rappresentazione
	 */
	public String toGrammarString();
}
