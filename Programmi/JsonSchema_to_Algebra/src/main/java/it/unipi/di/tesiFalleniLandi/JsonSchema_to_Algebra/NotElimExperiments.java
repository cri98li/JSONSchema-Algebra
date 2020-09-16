package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.Utils;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Utils_FullAlgebra;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.JSONSchema;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.Utils_JSONSchema;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessEnv;
import patterns.REException;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;

public class NotElimExperiments
{
	private static HashMap<Integer,String> operations = new HashMap<>();

	private  HashMap<String,String> errorsMap; //filename->exception code
	private  HashMap<String, Long> resultMap; //key->value

	private static String _objectid = "objectid";
	private static String _size_before = "size_before";
	private static String _size_after = "size_after";
	private static String _exec_time = "exec_time";
	private static String _error = "error";
	private static String _operation = "operation";
	private static String _idrun = "idrun";


	public void addError(String fname, String exception) {
		errorsMap.put(fname,exception.replace("\n", " ").replace("\r", " "));
	}

	public void addResult(String key, Long val) {
		resultMap.put(key,val);
	}



	public NotElimExperiments() {
		operations.put(1, "JsonSchema2fullAlgebra");
		operations.put(2, "JsonSchema2witnessAlgebra");
		operations.put(3, "JsonSchema2fullAlgebra_notElimination");
		operations.put(4, "JsonSchema2witnessAlgebra_notElimination");

		errorsMap = new HashMap<>();
		resultMap = new HashMap<>();

//		errors.put(0l, "FileNotFoundException");
//		errors.put(1l, "IOException");
//		errors.put(2l, "JsonSyntaxException");
//		errors.put(3l, "TimeoutException");
//		errors.put(4l, "NotEliminationCode");
//		errors.put(99l, "Exception");

	}

	public boolean operation(File file, Integer code, Integer idrun, Integer timeout)  {
		Instant start = Instant.now();
		String extension = ".algebra";
		final Assertion[] jsonSchema = {null};
		final String[] outputSchema = {null};
		JSONSchema root;
		String filename = file.getName();

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
				this.addError(filename,e.getMessage());
				return false;
			}
		}catch (FileNotFoundException e) {
			this.addError(filename,e.getMessage());
			return false;
		} catch (IOException e) {
			this.addError(filename,e.getMessage());
			return false;
		}

		try{
			//extract line and  schema_file
			Iterator<?> it = object.keySet().iterator();
			while(it.hasNext()) {
				String key = (String) it.next();
				switch (key) {
					case "line":
						resultMap.put(_objectid, object.get(key).getAsLong());
						break;
					case "schema_file":
						schemaObject = object.getAsJsonObject(key);
						break;
				}
			}

			root = new JSONSchema(schemaObject);
//			//convert to full algebra
//			jsonSchema = Utils_JSONSchema.normalize(root).toGrammar();
		}catch (Exception e) {
			this.addError(filename,e.getMessage());
			return false;
		}


		switch (code){
			case 1: //JsonSchema2fullAlgebra
				outputSchema[0] = Utils.beauty(jsonSchema[0].toGrammarString());
				break;
			case 2: //JsonSchema2witnessAlgebra
				try {
					WitnessEnv env = Utils_FullAlgebra.getWitnessAlgebra(jsonSchema[0]);
					outputSchema[0] = Utils.beauty(env.getFullAlgebra().toGrammarString());
				} catch (REException e) {
					this.addError(filename,e.getMessage());
					return false;
				}
				break;
			case 3: //fullAlgebra_notElimination
//				try {
//					//convert to full algebra
//					jsonSchema = Utils_JSONSchema.normalize(root).toGrammar();
//					String before = Utils.beauty(jsonSchema.toGrammarString());
//					addResult(_size_before, (long) before.length());
//				} catch (Exception e){
//					this.addError(filename,e.getMessage());
//					return false;
//				}

					//timeout code
					Assertion finalJsonSchema = jsonSchema[0];
					final Runnable stuffToDo = new Thread() {
						@Override
						public void run() {
							try{
								jsonSchema[0] = Utils_JSONSchema.normalize(root).toGrammar();
								String before = Utils.beauty(jsonSchema[0].toGrammarString());
								addResult(_size_before, (long) before.length());
								outputSchema[0] = Utils.beauty(finalJsonSchema.notElimination().toGrammarString());
							}catch (Exception e){
								addError(filename,e.getMessage());
								return;
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
						this.addError(filename,ie.getMessage());
						return false;
					}
					catch (ExecutionException ee) {
						/* Handle the error. Or ignore it. */
						this.addError(filename,ee.getMessage());
						return false;
					}
					catch (TimeoutException te) {
						/* Handle the timeout. Or ignore it. */
//						this.addError(filename,te.getCause().getMessage());
						this.addError(filename,"Timeout");
						return false;
					}
					if(errorsMap.containsKey(filename))
						return false;

					if (!executor.isTerminated())
						executor.shutdownNow();
				break;
			case 4: //witnessAlgebra_notElimination
				try {
					WitnessEnv env = Utils_FullAlgebra.getWitnessAlgebra(jsonSchema[0]);
					env.buildOBDD_notElimination(); //modify in-place
					outputSchema[0] = Utils.beauty(env.getFullAlgebra().toGrammarString());
				} catch (REException e) {
					this.addError(filename,e.getMessage());
					return false;
				}
				break;
		}
		addResult(_size_after, (long) outputSchema[0].length());
		Instant end = Instant.now();
		addResult(_exec_time, Duration.between(start, end).toMillis());
		addResult(_operation,(long) code);
		addResult(_idrun,(long) idrun);
		return true;
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
		BufferedWriter err = new BufferedWriter(new FileWriter(path+"/output.log"));


		boolean headerOut = false;
		boolean b = false;

		for (File file : files) {
			System.out.println(file);
			try{
				b = obj.operation(file, op, idrun, timeout);
			}
			catch (OutOfMemoryError e) {
				obj.addError(file.getName(),e.getMessage());
			}
			StringBuilder r = new StringBuilder();
			StringBuilder e = new StringBuilder();

			if(b)
			{
				//write result
				if(!headerOut){
					obj.resultMap.forEach((k, v) -> r.append(k).append(","));
					r.replace(r.length()-1,r.length(),"");//remove last comma
					r.append("\r\n");
					headerOut=true;
				}
				obj.resultMap.forEach((k, v) -> r.append(v).append(","));
				r.replace(r.length()-1,r.length(),"");//remove last comma
				r.append("\r\n");
				res.write(r.toString());
				//clear
				obj.resultMap.clear();
				res.flush();
			}
			else{
				//write error
				obj.errorsMap.forEach((k, v) ->e.append(k).append("\t").append(v));
				e.append("\r\n");
				err.write(e.toString());
				obj.errorsMap.clear();
				err.flush();
			}
		}

		err.close();
		res.close();

	}

//	public void writeResultsErrors(String path) throws IOException {
//
//		BufferedWriter res = new BufferedWriter(new FileWriter(path+"/output.csv"));
//		BufferedWriter err = new BufferedWriter(new FileWriter(path+"/output.log"));
//
//		StringBuilder r = new StringBuilder();
//		StringBuilder e = new StringBuilder();
//
//		boolean headerOut = false;
//
//		if(!headerOut){
//			this.resultMap.forEach((k, v) -> r.append(k).append(","));
//			r.replace(r.length()-1,r.length(),"");//remove last comma
//			r.append("\r\n");
//			headerOut=true;
//		}
//		this.resultMap.forEach((k, v) -> r.append(v).append(","));
//		r.replace(r.length()-1,r.length(),"");//remove last comma
//		r.append("\r\n");
//		res.write(r.toString());
//
//		errorsMap.forEach((k, v) ->e.append(k).append("\t").append(v));
//		e.append("\r\n");
//		err.write(e.toString());
//
//		err.close();
//		res.close();
//
//	}
}