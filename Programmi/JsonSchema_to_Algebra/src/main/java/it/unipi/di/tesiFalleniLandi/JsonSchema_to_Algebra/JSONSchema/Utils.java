package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Utils {
	public static JSONSchema parse(String path) throws FileNotFoundException, IOException, ParseException {
		
		try (Reader reader = new FileReader(path)){
        	JSONObject object = (JSONObject) new JSONParser().parse(reader);
        	return new JSONSchema(object);
        }
	}
	
	public static JSONSchema normalize(JSONSchema root) {
		return root.assertionSeparation();
	}
	
	
	
	
}

class URIUtils{
	String originalURI;
	
	public URIUtils(String uri) {
		originalURI = uri;
	}
	
	public URIUtils(URIUtils uri) { //costruttore copia
		this.originalURI = uri.originalURI;
	}

	public String getOriginalURI()
	{
		return originalURI;
	}
	
}
