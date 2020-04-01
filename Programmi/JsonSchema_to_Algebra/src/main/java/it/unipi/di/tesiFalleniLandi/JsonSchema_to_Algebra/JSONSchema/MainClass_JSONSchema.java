package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MainClass_JSONSchema {
public static void main(String[] args) throws FileNotFoundException, IOException {
	String path = "test.json";
	JSONSchema root;
	JsonParser parser = new JsonParser();
	JsonElement jsonTree = parser.parse(path);
	
    try (Reader reader = new FileReader(path)){
    	JsonObject object = jsonTree.getAsJsonObject();;
    	root = new JSONSchema(object);
    }
    
    System.out.println(root.toJSON().toString().replace("\\", ""));
    
    /*List<Entry<String, Defs>> entryList = root.collectDef();
    
    for(Entry<String, Defs> entry : entryList) {
    	System.out.println(entry.getKey()+ " >> " + entry.getValue().toJSON());
    }*/
    
    root = Utils_JSONSchema.referenceNormalization(root);
    
    System.out.println(root.toJSON().toString().replace("\\", ""));
    
    //System.out.println(root.assertionSeparation().toJSON());
    
    System.out.println(Utils_JSONSchema.toGrammarString(root.assertionSeparation()));
	}
}