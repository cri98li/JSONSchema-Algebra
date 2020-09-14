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
import java.util.concurrent.*;

public class NotElimExperiments
{
	private static HashMap<Integer,String> operations = new HashMap<>();
	private static HashMap<Long,String> errors = new HashMap<>();

	private static String _objectid = "objectid";
	private static String _size_before = "size_before";
	private static String _size_after = "size_after";
	private static String _exec_time = "exec_time";
	private static String _error = "error";
	private static String _operation = "operation";
	private static String _idrun = "idrun";

//	private static long _error_code = -666l;



	public NotElimExperiments() {
		operations.put(1, "JsonSchema2fullAlgebra");
		operations.put(2, "JsonSchema2witnessAlgebra");
		operations.put(3, "JsonSchema2fullAlgebra_notElimination");
		operations.put(4, "JsonSchema2witnessAlgebra_notElimination");

		errors.put(0l, "FileNotFoundException");
		errors.put(1l, "IOException");
		errors.put(2l, "JsonSyntaxException");
		errors.put(3l, "TimeoutException");
		errors.put(4l, "NotEliminationCode");
		errors.put(99l, "Exception");

	}


	private static HashMap<String, Long> operation(File file, Integer code, Integer idrun) throws IOException {
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

		JsonObject schemaObject = null,object=null;
		try (Reader reader = new FileReader(file.getAbsolutePath())) {
			try {
				object = gson.fromJson(reader, JsonObject.class);
			} catch (JsonSyntaxException e) {
				result.put(_error, 2l);
				return result;
			}
		}catch (FileNotFoundException e) {
			result.put(_error, 0l);
			return result;
		} catch (IOException e) {
			result.put(_error, 1l);
			return result;
		}

		//extract id and  schema_file
		Iterator<?> it = object.keySet().iterator();
		while(it.hasNext()) {
			String key = (String) it.next();
			switch (key) {
				case "id":
					result.put(_objectid, object.get(key).getAsLong());
					break;
				case "schema_file":
					schemaObject = object.getAsJsonObject(key);
					break;
			}
		}
		root = new JSONSchema(schemaObject);
			//convert to full algebra
		jsonSchema = Utils_JSONSchema.normalize(root).toGrammar();

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
					String before = Utils.beauty(jsonSchema.toGrammarString());
					result.put(_size_before, (long) before.length());
					outputSchema = Utils.beauty(jsonSchema.notElimination().toGrammarString());

				} catch (Exception e){
					System.out.println("skipped file " + file.getName()+" due to  "+e.getCause());
					result.put(_error,4l);
					return result;
				}

				break;
			case 4: //witnessAlgebra_notElimination
				try {
					WitnessEnv env = Utils_FullAlgebra.getWitnessAlgebra(jsonSchema);
					env.buildOBDD_notElimination(); //modify in-place
					outputSchema = Utils.beauty(env.getFullAlgebra().toGrammarString());
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
		result.put(_operation,(long) code);
		result.put(_idrun,(long) idrun);
		return result;
	}


	public static void main(String[] args ) throws IOException{




		int timeout = 1; //in minutes

		NotElimExperiments obj = new NotElimExperiments();
		if(args.length !=3){
			System.out.println("Expects 3 arguments: directory path,  experiments code (1 to 4), idrun");
			operations.forEach((k,v) -> System.out.println("code: "+k+"  corresponds to: "+v));
			System.exit(-1);
		}
		String path = args[0];
		int op = Integer.parseInt(args[1]);
		File dir = new File(path);
		File [] files = dir.listFiles();
		Integer idrun = Integer.parseInt(args[2]);

		BufferedWriter res = new BufferedWriter(new FileWriter(path+"/output.csv"));
		BufferedWriter err = new BufferedWriter(new FileWriter(path+"/errors.log"));

		StringBuilder b = new StringBuilder();
		StringBuilder l = new StringBuilder();

		boolean headerOut = false;
		for (File file : files) {
			final HashMap<String, Long>[] result = new HashMap[]{new LinkedHashMap<>()};

			final Runnable stuffToDo = new Thread() {
				@Override
				public void run() {
					try {
						result[0] = operation(file,op,idrun);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};

			final ExecutorService executor = Executors.newSingleThreadExecutor();
			final Future future = executor.submit(stuffToDo);
			executor.shutdown(); // This does not cancel the already-scheduled task.

			try {
				future.get(timeout, TimeUnit.MINUTES);
			}
			catch (InterruptedException ie) {
				/* Handle the interruption. Or ignore it. */
			}
			catch (ExecutionException ee) {
				/* Handle the error. Or ignore it. */
			}
			catch (TimeoutException te) {
				/* Handle the timeout. Or ignore it. */
				result[0].put(_error,3l);
			}
			if (!executor.isTerminated())
				executor.shutdownNow();





			if(result[0].keySet().contains(_error))
			{
				result[0].forEach((k, v) ->l.append(k).append("\t").append(errors.get(v)));
				l.replace(l.length()-1,l.length(),"");
				l.append("\r\n");
			}
			else
			{
				if(!headerOut){
					result[0].forEach((k, v) -> b.append(k).append(","));
					b.replace(b.length()-1,b.length(),"");
					b.append("\r\n");
					headerOut=true;
				}
				result[0].forEach((k, v) -> b.append(v).append(","));
				b.replace(b.length()-1,b.length(),"");
				b.append("\r\n");
			}


		}

		err.write(l.toString());
		err.close();
		res.write(b.toString());
		res.close();

	}
}