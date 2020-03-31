package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.util.Iterator;


public class URI_JS {
	private String uri;
	private String normalizedURI;
	private String normalizedName;
	
	public URI_JS(String uri) {
		this.uri = uri;
	}
	
	public URI_JS() {
	}

	public void found() {
		normalizeURI();
	}
	
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

	private void normalizeURI() {
		//start: uri --> #/$defs/a/b/foo
		normalizedName = "";
		normalizedURI ="";
		
		//caso #
		if(uri.equals("#")) {
			normalizedURI = "";
			normalizedName = "#";
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

            @Override
            public String next() {
                return splittedURI[currentIndex];
            }
            
            @Override
            public void remove() {
            	currentIndex++;
            }
        };
        return it;
    }
}
