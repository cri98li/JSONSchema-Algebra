package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.Utils;

import java.io.IOException;

public class MainClass_Algebra {
	public static void main(String[] args) throws IOException {
        String path = "test.algebra";
		
        Assertion schema = Utils_FullAlgebra.parseFile(path);

		JsonObject JSON = (JsonObject) schema.toJSONSchema(null);
		System.out.println(JSON.toString());

		schema = schema.notElimination();
		System.out.println(Utils.beauty(schema.toGrammarString()));

		JSON = (JsonObject) schema.toJSONSchema(null);
		System.out.println(JSON.toString());
	}
}
