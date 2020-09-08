package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.Utils;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.ANTLR4.AlgebraParser;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.ANTLR4.ErrorListener;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.ANTLR4.GrammaticaLexer;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.ANTLR4.GrammaticaParser;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.MainClass_Algebra;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.JSONSchema;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.MainClass_JSONSchema;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.Utils_JSONSchema;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Scanner;

public class NotElimExperiments
{
	private static HashMap<Integer,String> operations = new HashMap<>();

	public NotElimExperiments() {
		operations.put(1, "JsonSchema2fullAlgebra");
		operations.put(2, "JsonSchema2witnessAlgebra");
		operations.put(3, "fullAlgebra_notElimination");
		operations.put(4, "witnessAlgebra_notElimination");
	}


	private static Long operation(String path, Integer code) throws IOException {
		Instant start = Instant.now();
		String extension = ".json";
		JSONSchema jsonSchema=null;
		switch (code){
			case 1:
				JSONSchema root;
				Gson gson = new GsonBuilder()
						.disableHtmlEscaping()
						.setPrettyPrinting()
						.serializeNulls()
						.create();

				try (Reader reader = new FileReader(path)) {
					JsonObject object = gson.fromJson(reader, JsonObject.class);
					root = new JSONSchema(object);
					//full algebra
					jsonSchema = Utils_JSONSchema.normalize(root);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				break;
			case 2:
				break;
			default:
				System.out.println("unknown experiment");
				return -1l;
		}
		Instant end = Instant.now();
		//write the result
		FileWriter fw = new FileWriter(path+operations.get(code)+extension);
		fw.write(Utils_JSONSchema.toGrammarString(jsonSchema));
		fw.close();

		return Duration.between(start, end).toSeconds();
	}
	public static void main(String[] args ) throws IOException{

		if(args.length !=2){
			System.out.println("Expects 2 arguments: file name and experiments code");
			operations.forEach((k,v) -> System.out.println("code: "+k+"  corresponds to :"+v));
			System.exit(-1);
		}
		String filename = args[0];
		int op = 0;
		try{
			op = Integer.parseInt(args[1]);
			long duration = operation(filename,op);
			String operation = operations.get(op);
			//output (filename, operation, execution_time) duration=-1 means failure
			System.out.println(filename+"\t"+operation+"\t"+duration);
		}catch(Exception ex) {
			ex.printStackTrace();
			System.exit(-1);
		}




	}
}