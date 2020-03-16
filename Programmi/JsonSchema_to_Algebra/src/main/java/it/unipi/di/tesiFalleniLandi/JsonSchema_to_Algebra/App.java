package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.JSONSchema;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.Utils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException, IOException, ParseException
    {
    	String path = "test.json";
    	JSONSchema root;
    	
        try (Reader reader = new FileReader(path)){
        	JSONObject object = (JSONObject) new JSONParser().parse(reader);
        	root = new JSONSchema(object);
        }
        
        System.out.println(root.toJSON().toString().replace("\\", ""));
        
        /*List<Entry<String, Defs>> entryList = root.collectDef();
        
        for(Entry<String, Defs> entry : entryList) {
        	System.out.println(entry.getKey()+ " >> " + entry.getValue().toJSON());
        }*/
        
        Utils.normalizeRefDef(root);
        
        System.out.println(root.toJSON().toString().replace("\\", ""));
        
        //System.out.println(root.assertionSeparation().toGrammarString());
        
    }
}
