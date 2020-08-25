package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.Utils_JSONSchema;

import java.io.FileWriter;
import java.io.IOException;

public class Endpoint {
	public static void main(String[] args) throws IOException {


		String path = System.getProperty("user.dir")+ "/JsonSchema_to_Algebra/testFiles/";
		String file = "example_4-5";
		String extension = ".algebra";
		String inputFileName = path+file+extension;


        Assertion schema = Utils_FullAlgebra.parseFile(inputFileName);
		System.out.println("parsed "+inputFileName);


//		JsonObject JSON = (JsonObject) schema.toJSONSchema();
//		System.out.println(JSON.toString());

		schema = schema.notElimination();
//		System.out.println(Utils.beauty(schema.toGrammarString()));

		String outputFileName = path+"not_"+file+extension;
		FileWriter fw = new FileWriter(outputFileName);
		fw.write(Utils.beauty(schema.toGrammarString()));
		fw.close();
		System.out.println("output "+ outputFileName);

//		JSON = (JsonObject) schema.toJSONSchema();
//		System.out.println(JSON.toString());
	}
}
