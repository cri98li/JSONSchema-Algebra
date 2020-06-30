package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.GrammarStringDefinitions;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Classe di utilità per rappresentare URI
 */
public class URI_JS {
	private String uri;				//URI originale 		es: properties/aaa/properties/bbb
	private String normalizedURI;	//URI nomalizzato 		es: /$defs/
	private String normalizedName;	//Nome dell'elemento 	es: aaa_properties_bbb
	
	public URI_JS(String uri) {
		this.uri = uri;
	}
	
	public URI_JS() { }

	public void found() {
		normalizeURI();
	}

	/**
	 *
	 * @return ritorna l'uri originale se non normalizzato, l'uri normalizzato altrimenti
	 */
	public String toString() {
		if(normalizedURI == null)
			return uri;
		return normalizedName;
	}

	public String getNormalizedName() {
		normalizeURI();
		return normalizedName;
	}

	public void setNormalizedName(String normalizedName) {
		this.normalizedName = normalizedName;
	}

	//Tenta di normalizzare un URI
	private void normalizeURI() {
		if(uri.toLowerCase().contains(".json") || !uri.startsWith("#"))
			throw new ParseCancellationException("Unsupported URI");

		//start: uri --> #/$defs/a/b/foo
		normalizedName = "";
		normalizedURI = "";
		
		//caso #
		if(uri.equals("#")) {
			normalizedURI = "";
			normalizedName = GrammarStringDefinitions.ROOTDEF_DEFAULTNAME;
			return;
		}
		
		String[] splittedURI = uri.split("/"); // expected: ["#", "$defs", "a", "b", "foo"]
		
		int i = 0;
		
		normalizedURI += "#/$defs/"; 	// uri --> #/$defs
		
		if(splittedURI[i].equals("#")) i++;
		if(splittedURI[i].equals("definitions") || splittedURI[i].equals("$defs")) i++;

		normalizedName = splittedURI[i++];
		for(; i < splittedURI.length; i++)
			normalizedName += "_"+splittedURI[i];
			
		
		// expected: a_b_foo
	}
	
	public URI_JS clone() {
		URI_JS clone = new URI_JS();
		
		clone.uri = uri;
		clone.normalizedURI = normalizedURI;
		clone.normalizedName = normalizedName;
		
		return clone;
	}
	
	
	
	
	
	
	/*
	Divide l'URI secondo i "/", ritorna poi l'iteratore *
	 */
	public Iterator<String> iterator() {
        Iterator<String> it = new Iterator<>() {

            private int currentIndex;
            private String[] splittedURI;
            
            {
            	splittedURI = uri.split("/");
            	if(splittedURI[0].contains("#")) currentIndex = 1;
            	else currentIndex = 0;
            }
            
            @Override
            public boolean hasNext() {
                return currentIndex != splittedURI.length;
            }

            // *** ATTENZIONE: per far andare avanti l'iteratore, richiamare il metodo remove
            @Override
            public String next() {
            	try {
					return splittedURI[currentIndex];
				}catch (Exception e){
            		throw new NoSuchElementException(e.getMessage());
				}

            }
            
            @Override
            public void remove() {
            	currentIndex++;
            }
        };
        return it;
    }
}
