package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.Utils;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.ANTLR4.AlgebraParser;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.ANTLR4.ErrorListener;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.ANTLR4.GrammaticaLexer;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.ANTLR4.GrammaticaParser;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.MainClass_Algebra;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Utils_FullAlgebra;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.JSONSchema;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.MainClass_JSONSchema;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.Utils_JSONSchema;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.ReportResults;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessEnv;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessVar;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import patterns.REException;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class NotElimExperiments
{
	private static HashMap<Integer,String> operations = new HashMap<>();
	private static String _size_before = "size_before";
	private static String _size_after = "size_after";
	private static String _exec_time = "exec_time";
	private static String _error = "error";
	private static long _error_code = -666l;



	public NotElimExperiments() {
		operations.put(1, "JsonSchema2fullAlgebra");
		operations.put(2, "JsonSchema2witnessAlgebra");
		operations.put(3, "JsonSchema2fullAlgebra_notElimination");
		operations.put(4, "JsonSchema2witnessAlgebra_notElimination");
	}


	private static HashMap<String, Long> operation(File file, Integer code) throws IOException {
		Instant start = Instant.now();
		String extension = ".algebra";
		Assertion jsonSchema=null;
		String outputSchema = null;
		JSONSchema root;
		HashMap<String, Long> result = new LinkedHashMap<>();


		Gson gson = new GsonBuilder()
				.disableHtmlEscaping()
				.setPrettyPrinting()
				.serializeNulls()
				.create();

		try (Reader reader = new FileReader(file.getAbsolutePath())) {
			JsonObject schemaObject = null,object = gson.fromJson(reader, JsonObject.class);
			//extract id and  schema_file
			Iterator<?> it = object.keySet().iterator();
			while(it.hasNext()) {
				String key = (String) it.next();
				switch (key) {
					case "id":
						result.put(key, object.get(key).getAsLong());
						break;
					case "schema_file":
						schemaObject = object.getAsJsonObject(key);
						break;
				}
			}
			root = new JSONSchema(schemaObject);
			//convert to full algebra
			jsonSchema = Utils_JSONSchema.normalize(root).toGrammar();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
		}catch (Exception e){
			System.out.println("skipped file " + file.getName()+" due to  "+e.getCause());
			result.put(_error,_error_code);
			return result;
		}

		switch (code){
			case 1: //JsonSchema2fullAlgebra
				outputSchema = Utils.beauty(jsonSchema.toGrammarString());
				break;
			case 2: //JsonSchema2witnessAlgebra
				try {
					WitnessEnv env = Utils_FullAlgebra.getWitnessAlgebra(jsonSchema);
					outputSchema = Utils.beauty(env.getFullAlgebra().toGrammarString());
				} catch (REException e) {
					e.printStackTrace();
				}
				break;
			case 3: //fullAlgebra_notElimination
				try {
					outputSchema = Utils.beauty(jsonSchema.notElimination().toGrammarString());
					result.put(_size_before, (long) outputSchema.length());

				} catch (Exception e){
					System.out.println("skipped file " + file.getName()+" due to  "+e.getCause());
					result.put(_error,_error_code);
					return result;
				}

				break;
			case 4: //witnessAlgebra_notElimination
				try {
					WitnessEnv env = Utils_FullAlgebra.getWitnessAlgebra(jsonSchema);
					env.buildOBDD_notElimination(); //modify in-place
					outputSchema = Utils.beauty(env.getFullAlgebra().toGrammarString());
					result.put(_size_before, (long) outputSchema.length());
				} catch (REException e) {
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("requested experiment is unknown!");
		}
		result.put(_size_after, (long) outputSchema.length());
		Instant end = Instant.now();
		result.put(_exec_time, Duration.between(start, end).toMillis());

		//write the result
//		FileWriter fw = new FileWriter(path+"_"+operations.get(code)+extension);
//		fw.write(outputSchema);
//		fw.close();

		return result;
	}


	public static void main(String[] args ) throws IOException{

		NotElimExperiments obj = new NotElimExperiments();
		if(args.length !=2){
			System.out.println("Expects 2 arguments: file name and experiments code (1 to 4)");
			operations.forEach((k,v) -> System.out.println("code: "+k+"  corresponds to: "+v));
			System.exit(-1);
		}
		String path = args[0];
		int op = Integer.parseInt(args[1]);
		File dir = new File(path);
		File [] files = dir.listFiles();
		BufferedWriter dest = new BufferedWriter(new FileWriter(path+"/output.csv"));
		StringBuilder b = new StringBuilder();

		boolean headerOut = false;
		for (File file : files) {
			HashMap<String, Long> result = operation(file,op);
			if(!result.keySet().contains(_error))
			{
				if(!headerOut){
					result.forEach((k,v) -> b.append(k).append(","));
					b.replace(b.length()-1,b.length(),"");
					b.append("\r\n");
					headerOut=true;
				}
				result.forEach((k,v) -> b.append(v).append(","));
				b.replace(b.length()-1,b.length(),"");
				b.append("\r\n");
			}


		}

		dest.write(b.toString());
		dest.close();

	}
}