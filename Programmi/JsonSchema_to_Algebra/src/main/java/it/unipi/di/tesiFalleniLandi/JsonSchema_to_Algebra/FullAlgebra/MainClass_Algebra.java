package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;
import org.json.simple.JSONObject;

import java.io.IOException;

public class MainClass_Algebra {
	public static void main(String[] args) throws IOException {
        String path = "test.algebra";
		
        Assertion schema = Utils_FullAlgebra.parseFile(path);

		System.out.println(schema.toString());

		JSONObject JSON = (JSONObject) schema.toJSONSchema();
		System.out.println(JSON.toJSONString());

		System.out.println(Utils.beauty(((JSONObject)schema.notElimination().toJSONSchema()).toJSONString()));


		System.out.println(Utils.beauty(schema.notElimination().toGrammarString()));
	}
}
