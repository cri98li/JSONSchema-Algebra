package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

public class URI_JS {
	private String uri;
	private String normalizedURI;
	
	public URI_JS(String uri) {
		this.uri = uri;
	}
	
	public void found(int draftVersion) {
		normalizeURI(draftVersion);
	}
	
	private void normalizeURI(int draftVersion) {
		String[] splittedURI = uri.split("/"); // expected: ["#", "$defs", "a", "b", "foo"]
		
		String normalizedURI ="";
		int i = 0;
		
		normalizedURI += "#/"+ ((draftVersion < 8) ? "definitions":"$defs"); 	// uri --> #/$defs
		normalizedURI += "/" + splittedURI[splittedURI.length -1];				// uri --> #/$defs + /foo
		
		if(splittedURI[i].equals("#")) i++;
		if(splittedURI[i].equals("definitions") || splittedURI[i].equals("$defs")) i++;
		
		for(; i < splittedURI.length -1; i++)
			normalizedURI += "_"+splittedURI[i];
			
		// expected: uri --> #/$defs/foo_a_b
	}
	
	public String toString() {
		if(normalizedURI == null)
			return uri;
		return normalizedURI;
	}
	
	
	//TODO: iteratore
}
