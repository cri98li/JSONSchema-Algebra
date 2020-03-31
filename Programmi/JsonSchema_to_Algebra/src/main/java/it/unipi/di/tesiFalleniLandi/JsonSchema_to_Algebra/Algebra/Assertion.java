package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

public interface Assertion {
	/**
	 * Restituisce la keyword associata all'oggetto
	 * @return String contenente la keyword
	 */
	public String getJSONSchemaKeyword();
	
	/**
	 * Restituisce una rappresentazione usando la sintassi di JSON Schema dell'oggetto corrente
	 * @return restituisce un oggetto JSON Simple che rappresenta lo schema, vedi le implementazioni per maggiori informazioni
	 */
	public Object toJSONSchema();
}
