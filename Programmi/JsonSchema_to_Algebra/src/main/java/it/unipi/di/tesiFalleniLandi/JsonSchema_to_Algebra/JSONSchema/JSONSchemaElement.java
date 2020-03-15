package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;
import java.util.List;

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
	
	/**
	 * Restituisce una lista contenente tutti gli URI delle $ref trovate 
	 * @return una lista contenente tutti gli uri dei vari $ref, vuota nel caso non ce ne fosse nessuno
	 */
	public List<URI_JS> getRef();
	
	/**
	 * Cerca di trovare il JSONSchema associato ad un determinato URI e colleziona le altre def sparse nel documento
	 * @param URIIterator iteratore sulle varie componenti del URI
	 * @return ritorna un'istanza di defs collezionando, oltre che alla def indicata dall'uri, tutte le altre istanze di defs trovate
	 */
	public Defs searchDef(Iterator<String> URIIterator);
}
